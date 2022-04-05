package com.soom.monolithic_api.domain.account.profile.data.response

import com.soom.monolithic_api.domain.account.profile.data.dto.ProfileImageDto

//프로필 이미지 정보를 담은 응답
sealed class ProfileImageResponse (
    val userId: Long,
    val imageId: String
)
class EditProfileImageResponse (userId: Long, imageId: String): ProfileImageResponse(userId, imageId) {
    companion object {
        fun of(dto: ProfileImageDto) = EditProfileImageResponse(dto.userId, dto.imageId)
    }
}
class AddProfileImageResponse(userId: Long, imageId: String) : ProfileImageResponse(userId, imageId) {
    companion object {
        fun of(dto: ProfileImageDto) = AddProfileImageResponse(dto.userId, dto.imageId)
    }
}
