package com.soom.monolithic_api.domain.account.profile.data.response

import com.soom.monolithic_api.domain.account.common.data.dto.AccountDto
import com.soom.monolithic_api.domain.account.common.data.dto.StudentDto
import com.soom.monolithic_api.domain.account.common.data.dto.TeacherDto
import com.soom.monolithic_api.domain.account.common.data.type.*
import java.time.LocalDate
import java.time.LocalDateTime

//프로필 조회 정보를 담은 응답
sealed class GetProfileResponse (
    id: Long, type: AccountType,
    val profileImage: String?,
    val school: SchoolType,
    val birth: LocalDate,
    val createdAt: LocalDateTime,
    val email: String,
    val name: String,
    val gender: GenderType
) : ProfileResponse(id, type) {
    companion object {
        fun of(dto: AccountDto): GetProfileResponse =
            when(dto) {
                is TeacherDto -> GetTeacherProfileResponse.of(dto)
                is StudentDto -> GetStudentProfileResponse.of(dto)
            }
    }
}
//교사 프로필 조회 정보를 담은 응답
class GetTeacherProfileResponse (
    id: Long, profileImage: String?, school: SchoolType, birth: LocalDate, createdAt: LocalDateTime, email: String, name: String, gender: GenderType,
    val major: String,
    val teacherType: TeacherType
) : GetProfileResponse(id, AccountType.교사, profileImage, school, birth, createdAt, email, name, gender) {
    companion object {
        fun of(dto: TeacherDto): GetTeacherProfileResponse = GetTeacherProfileResponse(
            dto.meta.id, dto.basic.profileImage, dto.basic.school, dto.basic.birth,
            dto.meta.createdAt, dto.auth.email, dto.basic.name, dto.basic.gender,
            dto.teacher.major, dto.teacher.teacherType
        )
    }
}
//학생 프로필 조회 정보를 담은 응답
class GetStudentProfileResponse (
    id: Long, profileImage: String?, school: SchoolType, birth: LocalDate, createdAt: LocalDateTime, email: String, name: String, gender: GenderType,
    val classNumber: Int, //학번
    val admissionAt: LocalDate,
    val department: DepartmentType
) : GetProfileResponse(id, AccountType.학생, profileImage, school, birth, createdAt, email, name, gender) {
    companion object {
        fun of(dto: StudentDto): GetStudentProfileResponse = GetStudentProfileResponse(
            dto.meta.id, dto.basic.profileImage, dto.basic.school, dto.basic.birth,
            dto.meta.createdAt, dto.auth.email, dto.basic.name, dto.basic.gender,
            dto.student.classNumber, dto.student.admissionAt, dto.student.department
        )
    }
}
