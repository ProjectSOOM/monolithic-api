package com.soom.monolithic_api.domain.account.profile.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.profile.data.dto.ProfileImageDto
import com.soom.monolithic_api.domain.account.common.data.entity.AccountEntity
import com.soom.monolithic_api.domain.account.common.repository.AccountRepository
import com.soom.monolithic_api.domain.account.common.template.AccountTemplate
import com.soom.monolithic_api.infra.s3.service.AwsS3Service
import com.soom.monolithic_api.infra.s3.type.S3DataType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AccountProfileImageServiceImpl(
    private val accountTemplate: AccountTemplate,
    private val awsS3Service: AwsS3Service,
    private val accountRepository: AccountRepository
): AccountProfileImageService {
    override fun save(id: Long, image: MultipartFile): ProfileImageDto =
        awsS3Service.upload(image, S3DataType.PROFILE_IMAGE.pathFormatter.invoke(arrayOf(id)))
        .let { it to accountTemplate.doAndGetWithAccountById(id, changeAccountProfile(it), changeAccountProfile(it)).meta.id }
        .run{ ProfileImageDto(second, first) }

    override fun delete(id: Long) = accountTemplate.doWithAccountById(id, deleteAccountProfile())

    private fun deleteAccountProfile(): (AccountEntity) -> Unit = { entity -> run { changeAccountProfile("").invoke(entity) } }
    private fun changeAccountProfile(imageId: String): (AccountEntity) -> AccountDto = { entity ->
        entity.apply{profileImage = imageId}
            .apply{ accountRepository.save(this) }
            .run (AccountEntity::toDto)
    }
}