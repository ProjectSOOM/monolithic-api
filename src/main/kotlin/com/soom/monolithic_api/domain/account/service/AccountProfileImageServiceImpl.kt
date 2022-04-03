package com.soom.monolithic_api.domain.account.service

import com.soom.monolithic_api.domain.account.dto.AccountDto
import com.soom.monolithic_api.domain.account.dto.ProfileImageDto
import com.soom.monolithic_api.domain.account.entity.AccountEntity
import com.soom.monolithic_api.domain.account.entity.StudentEntity
import com.soom.monolithic_api.domain.account.entity.TeacherEntity
import com.soom.monolithic_api.domain.account.repository.AccountRepository
import com.soom.monolithic_api.domain.account.template.RepositoryAccountTemplate
import com.soom.monolithic_api.infra.service.AwsS3Service
import com.soom.monolithic_api.infra.type.S3DataType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AccountProfileImageServiceImpl(
    private val accountTemplate: RepositoryAccountTemplate,
    private val awsS3Service: AwsS3Service,
    private val accountRepository: AccountRepository
): AccountProfileImageService {
    override fun save(id: Long, image: MultipartFile): ProfileImageDto {
        val imageId = awsS3Service.upload(image, S3DataType.PROFILE_IMAGE.pathFormatter.invoke(arrayOf(id)))
        val userId: Long = accountTemplate.doAndGetWithAccountById(id, changeAccountProfile(imageId), changeAccountProfile(imageId)).meta.id
        return ProfileImageDto(userId, imageId)
    }

    override fun delete(id: Long) = accountTemplate.doWithAccountById(id, deleteAccountProfile())

    private fun deleteAccountProfile(): (AccountEntity) -> Unit = {entity ->
        run { changeAccountProfile("").invoke(entity) }
    }

    private fun changeAccountProfile(imageId: String): (AccountEntity) -> AccountDto = { entity ->
        when(entity) {
            is StudentEntity -> entity.copy(profileImage = imageId)
            is TeacherEntity -> entity.copy(profileImage = imageId)
        }.run{
            accountRepository.save(this)
            this.toDto()
        }
    }
}