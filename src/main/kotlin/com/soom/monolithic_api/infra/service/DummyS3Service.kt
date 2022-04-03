package com.soom.monolithic_api.infra.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
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