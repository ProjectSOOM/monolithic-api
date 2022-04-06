package com.soom.monolithic_api.infra.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service //TODO 추후 실제 Service 로 전환
class DummyS3Service: AwsS3Service {
    override fun upload(image: MultipartFile, filePath: String): String {
        val id = java.util.UUID.randomUUID().toString()
        log("uploading image to s3 bucket")
        log("path: $filePath/$id.png")
        log("image: $image")
        log("uploaded!")
        return id
    }
    fun log(message: String) {
        println("[DummyS3Service] $message")
    }
}