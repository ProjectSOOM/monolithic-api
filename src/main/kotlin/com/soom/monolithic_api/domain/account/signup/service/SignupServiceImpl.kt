package com.soom.monolithic_api.domain.account.signup.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.common.data.entity.AccountEntity
import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity
import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity
import com.soom.monolithic_api.domain.account.common.data.type.RoleType
import com.soom.monolithic_api.domain.account.common.data.type.SchoolType
import com.soom.monolithic_api.domain.account.common.repository.AccountRepository
import com.soom.monolithic_api.domain.account.signup.dto.SignupDto
import com.soom.monolithic_api.domain.account.signup.dto.StudentSignupDto
import com.soom.monolithic_api.domain.account.signup.dto.TeacherSignupDto
import org.springframework.security.crypto.password.PasswordEncoder

class SignupServiceImpl(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder
    ): SignupService {
    //학생 회원가입 수행
    override fun signupStudent(dto: StudentSignupDto): AccountDto =
        signup {signupDto: StudentSignupDto, pair->
            StudentEntity(
                0L, signupDto.profile.name, signupDto.profile.gender, signupDto.profile.birth,
                signupDto.auth.email, pair.first,
                RoleType.USER, pair.second, "",
                signupDto.student.classNumber, signupDto.student.admissionAt, signupDto.student.department)
        }.invoke(dto, passwordEncoder, accountRepository)
    //교사 회원가입 수행
    override fun signupTeacher(dto: TeacherSignupDto): AccountDto =
        signup {signupDto: TeacherSignupDto, pair ->
            TeacherEntity(
                0L, signupDto.profile.name, signupDto.profile.gender, signupDto.profile.birth,
                signupDto.auth.email, pair.first,
                RoleType.OPERATOR, pair.second, "",
                signupDto.teacher.major, signupDto.teacher.teacherType)
        }.invoke(dto, passwordEncoder, accountRepository)
    //회원가입 수행
    private fun <T: SignupDto, T2: AccountEntity> signup(makeEntity: (T, Pair<String, SchoolType>) -> T2)
    : (T, PasswordEncoder, AccountRepository) -> AccountDto = { dto, passwordEncoder, accountRepository ->
        (passwordEncoder.encode(dto.auth.password) to getSchoolByEmail(dto.auth.email))
            .run {
                makeEntity.invoke(dto, this)
            }.apply { accountRepository.save(this).id }
            .toDto()
    }
    //이메일에서 학교정보 추출
    private fun getSchoolByEmail(email: String): SchoolType {
        TODO("이메일을 통해서 학교 구하는 로직 작성")
    }
}