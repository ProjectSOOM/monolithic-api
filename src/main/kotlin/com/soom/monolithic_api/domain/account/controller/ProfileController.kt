package com.soom.monolithic_api.domain.account.controller

import com.soom.monolithic_api.domain.account.type.DepartmentType
import com.soom.monolithic_api.domain.account.type.Gender
import com.soom.monolithic_api.domain.account.type.TeacherType
import com.soom.monolithic_api.domain.account.type.UserType
import neiseApi.payload.sche.SchoolType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/account/profile")
class ProfileController {
    //계정 ID로 프로필 조회
    @GetMapping("/{id}")
    fun getProfileByAccountId(@PathVariable id: String): ResponseEntity<out GetProfileResponse> {
        //TODO
        return ResponseEntity.ok().build()
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

//프로필 식별 정보를 담은 응답
sealed class ProfileResponse (
    val id: String,
    val type: UserType
        )

//프로필 조회 정보를 담은 응답
sealed class GetProfileResponse (
    id: String, type: UserType,
    val profileImage: String?,
    val school: SchoolType,
    val birth: LocalDate,
    val createdAt: LocalDateTime,
    val email: String,
    val name: String,
    val gender: Gender
) : ProfileResponse(id, type)
class GetTeacherProfileResponse (
    id: String, profileImage: String?, school: SchoolType, birth: LocalDate,createdAt: LocalDateTime, email: String, name: String, gender: Gender,
    val major: String,
    val teacherType: TeacherType,
) : GetProfileResponse(id, UserType.교사, profileImage, school, birth, createdAt, email, name, gender)
class GetStudentProfileResponse (
    id: String, profileImage: String?, school: SchoolType, birth: LocalDate, createdAt: LocalDateTime, email: String, name: String, gender: Gender,
    val clazzNumber: Int, //학번
    val admissionAt: LocalDate,
    val department: DepartmentType,
) : GetProfileResponse(id, UserType.학생, profileImage, school, birth, createdAt, email, name, gender)

//프로필 이미지 정보를 담은 응답
sealed class ProfileImageResponse (
    val userId: Long,
    val imageId: String
    )
class EditProfileImageResponse (userId: Long, imageId: String): ProfileImageResponse(userId, imageId)
class AddProfileImageResponse(userId: Long, imageId: String) : ProfileImageResponse(userId, imageId)