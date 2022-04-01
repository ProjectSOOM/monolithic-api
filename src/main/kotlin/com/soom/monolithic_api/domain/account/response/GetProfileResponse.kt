package com.soom.monolithic_api.domain.account.response

import com.soom.monolithic_api.domain.account.dto.AccountProfileDto
import com.soom.monolithic_api.domain.account.type.DepartmentType
import com.soom.monolithic_api.domain.account.type.Gender
import com.soom.monolithic_api.domain.account.type.TeacherType
import com.soom.monolithic_api.domain.account.type.AccountType
import neiseApi.payload.sche.SchoolType
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
        fun <T: GetProfileResponse> of(dto: AccountProfileDto): T {
            //TODO
            throw NotImplementedError("method is not implemented")
        }
    }
}
class GetTeacherProfileResponse (
    id: String, profileImage: String?, school: SchoolType, birth: LocalDate, createdAt: LocalDateTime, email: String, name: String, gender: Gender,
    val major: String,
    val teacherType: TeacherType
) : GetProfileResponse(id, AccountType.교사, profileImage, school, birth, createdAt, email, name, gender)
class GetStudentProfileResponse (
    id: String, profileImage: String?, school: SchoolType, birth: LocalDate, createdAt: LocalDateTime, email: String, name: String, gender: Gender,
    val classNumber: Int, //학번
    val admissionAt: LocalDate,
    val department: DepartmentType
) : GetProfileResponse(id, AccountType.학생, profileImage, school, birth, createdAt, email, name, gender)
