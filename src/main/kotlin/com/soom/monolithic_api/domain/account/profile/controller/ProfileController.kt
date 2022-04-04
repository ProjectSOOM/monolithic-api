package com.soom.monolithic_api.domain.account.profile.controller

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.profile.dto.ProfileImageDto
import com.soom.monolithic_api.domain.account.profile.response.AddProfileImageResponse
import com.soom.monolithic_api.domain.account.profile.response.EditProfileImageResponse
import com.soom.monolithic_api.domain.account.profile.response.GetProfileResponse
import com.soom.monolithic_api.domain.account.profile.service.AccountProfileImageService
import com.soom.monolithic_api.domain.account.common.service.AccountService
import com.soom.monolithic_api.global.service.LoginAccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/account/profile")
class ProfileController (
    private val profileService: AccountService,
    private val profileImageService: AccountProfileImageService,
    private val loginAccountService: LoginAccountService
        ) {
    //계정 ID로 프로필 조회
    @GetMapping("/{id}")
    fun getProfileByAccountId(@PathVariable id: Long): ResponseEntity<out GetProfileResponse> {
        val dto: AccountDto = profileService.getAccount(id)
        return ResponseEntity.ok(GetProfileResponse.of(dto))
    }
    //로그인된 계정의 프로필 조회
    @GetMapping
    fun getProfileByLoginAccount(): ResponseEntity<out GetProfileResponse> {
        val id: Long = getLoginAccountId()
        val dto: AccountDto = profileService.getAccount(id)
        return ResponseEntity.ok(GetProfileResponse.of(dto))
    }

    //로그인된 계정의 프로필 사진 등록
    @PostMapping("/image")
    fun addProfileImageAtLoginAccount(@ModelAttribute image: MultipartFile): ResponseEntity<AddProfileImageResponse> {
        val dto: ProfileImageDto = saveProfileAndGetDto(image)
        return ResponseEntity.ok(AddProfileImageResponse.of(dto))
    }
    //로그인된 게정의 프로필 사진 수정
    @PutMapping("/image")
    fun editProfileImageAtLoginAccount(@ModelAttribute image: MultipartFile): ResponseEntity<EditProfileImageResponse> {
        val dto: ProfileImageDto = saveProfileAndGetDto(image)
        return ResponseEntity.ok(EditProfileImageResponse.of(dto))
    }
    //로그인된 계정의 프로필 사진 삭제
    @DeleteMapping("/image")
    fun removeProfileImageAtLoginAccount(): ResponseEntity<Void> {
        val id: Long = getLoginAccountId()
        profileImageService.delete(id)
        return ResponseEntity.noContent().build()
    }

    private fun saveProfileAndGetDto(image: MultipartFile): ProfileImageDto {
        val id: Long = getLoginAccountId()
        return profileImageService.save(id, image)
    }

    private fun getLoginAccountId(): Long = loginAccountService.getLoginAccount().meta.id
}