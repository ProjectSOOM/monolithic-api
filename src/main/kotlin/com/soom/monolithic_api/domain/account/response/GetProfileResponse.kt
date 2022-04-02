package com.soom.monolithic_api.domain.account.response

import com.soom.monolithic_api.domain.account.dto.AccountDto
import com.soom.monolithic_api.domain.account.dto.StudentProfileDto
import com.soom.monolithic_api.domain.account.dto.TeacherProfileDto
import com.soom.monolithic_api.domain.account.type.*
import java.time.LocalDate
import java.time.LocalDateTime

//프로필 조회 정보를 담은 응답
sealed class GetProfileResponse (
    id: String, type: AccountType,
    val profileImage: String?,
    val school: SchoolType,
    val birth: LocalDate,
    val createdAt: LocalDateTime,
    val email: String,
    val name: String,
    val gender: Gender
) : ProfileResponse(id, type) {
    companion object {
        fun of(dto: AccountDto): GetProfileResponse =
            when(dto) {
                is TeacherProfileDto -> GetTeacherProfileResponse.of(dto)
                is StudentProfileDto -> GetStudentProfileResponse.of(dto)
            }
    }
}
//교사 프로필 조회 정보를 담은 응답
class GetTeacherProfileResponse (
    id: String, profileImage: String?, school: SchoolType, birth: LocalDate, createdAt: LocalDateTime, email: String, name: String, gender: Gender,
    val major: String,
    val teacherType: TeacherType
) : GetProfileResponse(id, AccountType.교사, profileImage, school, birth, createdAt, email, name, gender) {
    companion object {
        fun of(dto: TeacherProfileDto): GetTeacherProfileResponse = GetTeacherProfileResponse(
            dto.meta.id, dto.basic.profileImage, dto.basic.school, dto.basic.birth,
            dto.meta.createdAt, dto.auth.email, dto.basic.name, dto.basic.gender,
            dto.teacher.major, dto.teacher.teacherType
        )
    }
}
//학생 프로필 조회 정보를 담은 응답
class GetStudentProfileResponse (
    id: String, profileImage: String?, school: SchoolType, birth: LocalDate, createdAt: LocalDateTime, email: String, name: String, gender: Gender,
    val classNumber: Int, //학번
    val admissionAt: LocalDate,
    val department: DepartmentType
) : GetProfileResponse(id, AccountType.학생, profileImage, school, birth, createdAt, email, name, gender) {
    companion object {
        fun of(dto: StudentProfileDto): GetStudentProfileResponse = GetStudentProfileResponse(
            dto.meta.id, dto.basic.profileImage, dto.basic.school, dto.basic.birth,
            dto.meta.createdAt, dto.auth.email, dto.basic.name, dto.basic.gender,
            dto.student.classNumber, dto.student.admissionAt, dto.student.department
        )
    }
}
