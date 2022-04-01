package com.soom.monolithic_api.domain.account.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/account/profile")
class ProfileController {
    //계정 ID로 프로필 조회
    @GetMapping("/{id}")
    fun getProfileByAccountId(): ResponseEntity<GetProfileResponse> {
        //TODO
        return ResponseEntity.ok().build()
    }
    //로그인된 계정의 프로필 조회
    @GetMapping
    fun getProfileByLoginAccount(): ResponseEntity<GetProfileResponse> {
        //TODO
        return ResponseEntity.ok().build()
    }

    //로그인된 계정의 프로필 사진 등록
    @PostMapping("/image")
    fun addProfileImageAtLoginAccount(): ResponseEntity<AddProfileResponse> {
        //TODO
        return ResponseEntity.ok().build()
    }
    //로그인된 게정의 프로필 사진 수정
    @PutMapping("/image")
    fun editProfileImageAtLoginAccount(): ResponseEntity<EditProfileResponse> {
        //TODO
        return ResponseEntity.ok().build()
    }
    //로그인된 계정의 프로필 사진 삭제
    @DeleteMapping("/image")
    fun removeProfileImageAtLoginAccount(): ResponseEntity<RemoveProfileResponse> {
        //TODO
        return ResponseEntity.ok().build()
    }
}