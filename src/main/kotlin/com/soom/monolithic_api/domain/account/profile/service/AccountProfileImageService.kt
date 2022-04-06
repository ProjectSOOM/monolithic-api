package com.soom.monolithic_api.domain.account.profile.service

import com.soom.monolithic_api.domain.account.profile.data.dto.ProfileImageDto
import org.springframework.web.multipart.MultipartFile

interface AccountProfileImageService {
    fun save(id: Long, image: MultipartFile): ProfileImageDto
    fun delete(id: Long)
}
