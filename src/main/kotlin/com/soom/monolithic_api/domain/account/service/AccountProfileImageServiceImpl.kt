package com.soom.monolithic_api.domain.account.service

import com.soom.monolithic_api.domain.account.dto.AccountDto
import com.soom.monolithic_api.domain.account.dto.ProfileImageDto
import com.soom.monolithic_api.domain.account.entity.StudentEntity
import com.soom.monolithic_api.domain.account.entity.TeacherEntity
import com.soom.monolithic_api.domain.account.repository.StudentRepository
import com.soom.monolithic_api.domain.account.repository.TeacherRepository
import com.soom.monolithic_api.domain.account.template.RepositoryAccountTemplate
import com.soom.monolithic_api.infra.service.AwsS3Service
import com.soom.monolithic_api.infra.type.S3DataType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AccountProfileImageServiceImpl(
    private val accountTemplate: RepositoryAccountTemplate,
    private val awsS3Service: AwsS3Service,
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
): AccountProfileImageService {
    override fun save(id: Long, image: MultipartFile): ProfileImageDto {
        val imageId = awsS3Service.upload(image, S3DataType.PROFILE_IMAGE.pathFormatter.invoke(arrayOf(id)))
        val userId: Long = accountTemplate.doAndGetWithAccountById(id, changeTeacherProfile(imageId), changeStudentProfile(imageId)).meta.id
        return ProfileImageDto(userId, imageId)
    }

    private fun changeStudentProfile(imageId: String): (StudentEntity) -> AccountDto {
        return { entity ->
            val new = StudentEntity(entity.id, entity.name, entity.gender, entity.birth,
                entity.email, entity.encodedPassword, entity.role, entity.school, imageId,
                entity.classNumber, entity.admissionAt, entity.department)
            studentRepository.save(new)
            new.toDto() }
    }

    private fun changeTeacherProfile(imageId: String): (TeacherEntity) -> AccountDto {
        return { entity ->
            val new = TeacherEntity(entity.id, entity.name, entity.gender, entity.birth,
                entity.email, entity.encodedPassword, entity.role, entity.school, imageId,
                entity.major, entity.teacherType)
            teacherRepository.save(new)
            new.toDto() }
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}