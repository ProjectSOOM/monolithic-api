package com.soom.monolithic_api.domain.account.response

//프로필 이미지 정보를 담은 응답
sealed class ProfileImageResponse (
    val userId: Long,
    val imageId: String
)
class EditProfileImageResponse (userId: Long, imageId: String): ProfileImageResponse(userId, imageId)
class AddProfileImageResponse(userId: Long, imageId: String) : ProfileImageResponse(userId, imageId)
