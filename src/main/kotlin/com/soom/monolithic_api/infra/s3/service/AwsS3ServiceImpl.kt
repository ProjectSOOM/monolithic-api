package com.soom.monolithic_api.infra.s3.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class AwsS3ServiceImpl(
    private val amazonS3: AmazonS3
) : AwsS3Service {
    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    override fun upload(image: MultipartFile, filePath: String): String =
        (key(filePath, image.originalFilename) to ObjectMetadata().apply {
            contentType = image.contentType
            contentLength = image.size
        }).apply{ PutObjectRequest(bucket, first, image.inputStream, second)
            .withCannedAcl(CannedAccessControlList.PublicRead)
            .run(amazonS3::putObject)
        }.first

    private fun key(filePath: String, originalFilename: String?) = "$filePath/${UUID.randomUUID().toString()}"

}