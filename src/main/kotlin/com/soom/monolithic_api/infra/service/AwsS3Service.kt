package com.soom.monolithic_api.infra.service

import org.springframework.web.multipart.MultipartFile

interface AwsS3Service {
    fun upload(image: MultipartFile, filePath: String): String
}
