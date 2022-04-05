package com.soom.monolithic_api.domain.account.profile.controller

import com.soom.monolithic_api.domain.account.profile.data.dto.ProfileImageDto
import com.soom.monolithic_api.domain.account.profile.data.response.AddProfileImageResponse
import com.soom.monolithic_api.domain.account.profile.data.response.EditProfileImageResponse
import com.soom.monolithic_api.domain.account.profile.data.response.GetProfileResponse
import com.soom.monolithic_api.domain.account.profile.service.AccountProfileImageService
import com.soom.monolithic_api.domain.account.common.service.AccountService
import com.soom.monolithic_api.global.service.LoginAccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v2/account/profile")
class ProfileController (
    private val profileService: AccountService,
    private val profileImageService: AccountProfileImageService,
    private val loginAccountService: LoginAccountService) {
    //계정 ID로 프로필 조회
    @GetMapping("/{id}")
    fun getProfileByAccountId(@PathVariable id: Long): ResponseEntity<out GetProfileResponse> =
        profileService.getAccount(id)
        .let { GetProfileResponse.of(it) }
        .let { ResponseEntity.ok(it) }
    //로그인된 계정의 프로필 조회
    @GetMapping
    fun getProfileByLoginAccount(): ResponseEntity<out GetProfileResponse> =
        getLoginAccountId()
        .let{profileService.getAccount(it) }
        .let { GetProfileResponse.of(it) }
        .let { ResponseEntity.ok(it) }

    //로그인된 계정의 프로필 사진 등록
    @PostMapping("/image")
    fun addProfileImageAtLoginAccount(@ModelAttribute image: MultipartFile): ResponseEntity<AddProfileImageResponse> =
        saveProfileAndGetDto(getLoginAccountId(), image)
            .let { AddProfileImageResponse.of(it) }
            .let { ResponseEntity.ok(it) }
    //로그인된 게정의 프로필 사진 수정
    @PutMapping("/image")
    fun editProfileImageAtLoginAccount(@ModelAttribute image: MultipartFile): ResponseEntity<EditProfileImageResponse> =
        saveProfileAndGetDto(getLoginAccountId(), image)
            .let{ EditProfileImageResponse.of(it) }
            .let { ResponseEntity.ok(it) }
    //로그인된 계정의 프로필 사진 삭제
    @DeleteMapping("/image")
    fun removeProfileImageAtLoginAccount(): ResponseEntity<Void> =
        getLoginAccountId()
            .let{ profileImageService.delete(it) }
            .let { ResponseEntity.noContent().build() }

    private fun saveProfileAndGetDto(id: Long, image: MultipartFile): ProfileImageDto = profileImageService.save(id, image)
    private fun getLoginAccountId(): Long = loginAccountService.getLoginAccount().meta.id
}