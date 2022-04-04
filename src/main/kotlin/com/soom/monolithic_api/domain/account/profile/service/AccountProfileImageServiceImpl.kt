package com.soom.monolithic_api.domain.account.profile.service

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.profile.dto.ProfileImageDto
import com.soom.monolithic_api.domain.account.common.data.entity.AccountEntity
import com.soom.monolithic_api.domain.account.common.data.entity.StudentEntity
import com.soom.monolithic_api.domain.account.common.data.entity.TeacherEntity
import com.soom.monolithic_api.domain.account.common.repository.AccountRepository
import com.soom.monolithic_api.domain.account.common.template.RepositoryAccountTemplate
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

    private fun deleteAccountProfile(): (AccountEntity) -> Unit = { entity ->
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