package com.soom.monolithic_api.domain.account.controller

import com.soom.monolithic_api.domain.account.dto.AccountDto
import com.soom.monolithic_api.domain.account.response.*
import com.soom.monolithic_api.domain.account.service.AccountProfileImageService
import com.soom.monolithic_api.domain.account.service.AccountProfileService
import com.soom.monolithic_api.global.service.LoginAccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/account/profile")
class ProfileController (
    private val profileService: AccountProfileService,
    private val profileImageService: AccountProfileImageService,
    private val loginAccountService: LoginAccountService
        ) {
    //계정 ID로 프로필 조회
    @GetMapping("/{id}")
    fun getProfileByAccountId(@PathVariable id: Long): ResponseEntity<out GetProfileResponse> {
        val dto: AccountDto = profileService.getProfile(id)
        return getResponseEntityByAccountDto(dto)
    }
    //로그인된 계정의 프로필 조회
    @GetMapping
    fun getProfileByLoginAccount(): ResponseEntity<out GetProfileResponse> {
        val id: Long = loginAccountService.getLoginAccount().meta.id
        val dto: AccountDto = profileService.getProfile(id)
        return getResponseEntityByAccountDto(dto)
    }

    private fun getResponseEntityByAccountDto(dto: AccountDto): ResponseEntity<out GetProfileResponse>
    = when(val response = GetProfileResponse.of(dto)) {
        is GetTeacherProfileResponse -> ResponseEntity.ok(response)
        is GetStudentProfileResponse -> ResponseEntity.ok(response)
    }

    //로그인된 계정의 프로필 사진 등록
    @PostMapping("/image")
    fun addProfileImageAtLoginAccount(): ResponseEntity<AddProfileImageResponse> {
        //TODO
        return ResponseEntity.ok().build()
    }
    //로그인된 게정의 프로필 사진 수정
    @PutMapping("/image")
    fun editProfileImageAtLoginAccount(): ResponseEntity<EditProfileImageResponse> {
        //TODO
        return ResponseEntity.ok().build()
    }
    //로그인된 계정의 프로필 사진 삭제
    @DeleteMapping("/image")
    fun removeProfileImageAtLoginAccount(): ResponseEntity<Void> {
        //TODO
        return ResponseEntity.noContent().build()
    }
}