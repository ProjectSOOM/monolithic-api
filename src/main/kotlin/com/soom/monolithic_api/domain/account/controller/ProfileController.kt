package com.soom.monolithic_api.domain.account.controller

import com.soom.monolithic_api.domain.account.response.AddProfileImageResponse
import com.soom.monolithic_api.domain.account.response.EditProfileImageResponse
import com.soom.monolithic_api.domain.account.response.GetProfileResponse
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
    fun getProfileByAccountId(@PathVariable id: String): ResponseEntity<out GetProfileResponse> {
        //val dto: AccountProfileDto = profileService.getProfile(id)
        return ResponseEntity.ok(/*GetProfileResponse.of(dto)*/).build()
    }
    //로그인된 계정의 프로필 조회
    @GetMapping
    fun getProfileByLoginAccount(): ResponseEntity<out GetProfileResponse> {
        //TODO
        return ResponseEntity.ok().build()
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