package com.soom.monolithic_api.infra.service

import com.soom.monolithic_api.infra.type.S3DataType
import org.springframework.web.multipart.MultipartFile

interface AwsS3Service {
    fun upload(image: MultipartFile, profileImage: S3DataType): String

}
