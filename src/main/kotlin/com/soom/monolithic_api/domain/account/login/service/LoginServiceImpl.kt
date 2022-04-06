package com.soom.monolithic_api.domain.account.login.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.common.exception.UnknownAccountEmailException
import com.soom.monolithic_api.domain.account.common.exception.UnknownAccountIdException
import com.soom.monolithic_api.domain.account.common.template.AccountTemplate
import com.soom.monolithic_api.domain.account.login.data.dto.LoginTokenDto
import com.soom.monolithic_api.domain.account.login.exception.WrongEmailException
import com.soom.monolithic_api.domain.account.login.exception.WrongPasswordException
import com.soom.monolithic_api.domain.account.login.exception.WrongRefreshTokenException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(
    private val loginTokenService: LoginTokenService,
    private val accountTemplate: AccountTemplate,
    private val passwordEncoder: PasswordEncoder
): LoginService {
    //이메일과 비밀번호로 로그인 토큰 발급
    override fun login(email: String, password: String): LoginTokenDto = getAccount(email, password).meta.id
        .let{ LoginTokenDto(loginTokenService.accessToken(it), loginTokenService.refreshToken(it)) }
    //재발급 토큰으로 로그인 토큰 발급
    override fun login(refreshToken: String): LoginTokenDto = getAccount(refreshToken).run(this::loginToken)
    //로그인 토큰 발급
    private fun loginToken(dto: AccountDto): LoginTokenDto =
        loginTokenService.run {
            val id = dto.meta.id //계정ID를 가져온다.
            LoginTokenDto(accessToken(id), refreshToken(id)) //로그인 토큰을 발급한다.
        }
    //이메일과 비밀번호로 계정정보 조회
    private fun getAccount(email: String, password: String): AccountDto =
        runCatching {
            accountTemplate.doAndGetWithAccountByEmail(email) //UnknownAccountEmailException 나 클로저 내 반환값이 반환된다.
            { entity ->
                if (passwordEncoder.matches(password, entity.encodedPassword)) entity.toDto() //비밀번호가 일치하면, AccountDto 를 반환한다.
                else throw WrongPasswordException(password) //비밀번호가 일치하지 않으면, WrongPasswordException 를 발생시킨다.
            } //클로저 내에서 AccountDto 를 반환하거나, WrongPasswordException를 반환한다.
        }.onFailure { //만약 로직 실행중 오류가 발생하였을 경우,
            (it as? UnknownAccountEmailException) //만약 오류가 UnknownAccountEmailException 이라면,
                ?.run { throw WrongEmailException(it.email) } //WrongEmailException 로 래핑하여 반환한다.
                ?: throw it //아니라면, 오류를 그대로 반환한다.
        }.getOrThrow() //오류가 발생하지 않았을 경우, 반환된 AccountDto 를 반환한다.
    //재발급 토큰으로 계정정보 조회
    private fun getAccount(refreshToken: String): AccountDto =
        runCatching {
            val id = loginTokenService.decode(refreshToken) //재발급 토큰을 디코딩하여 계정ID를 가져온다.
            accountTemplate.doAndGetWithAccountById(id) { //UnknownAccountIdException 나 클로저 내 반환값이 반환된다.
                entity -> entity.toDto() //AccountDto 를 반환한다.
            } //클로저 내에서 AccountDto 를 반환한다.
        }.onFailure { //만약 로직 실행중 오류가 발생하였을 경우,
            (it as? UnknownAccountIdException) //만약 오류가 UnknownAccountIdException 이라면,
                ?.run { throw WrongRefreshTokenException(refreshToken) } //WrongRefreshTokenException 로 래핑하여 반환한다.
                ?: throw it //아니라면, 오류를 그대로 반환한다.
        }.getOrThrow() //오류가 발생하지 않았을 경우, 반환된 AccountDto 를 반환한다.
}