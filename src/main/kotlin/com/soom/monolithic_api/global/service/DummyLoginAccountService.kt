package com.soom.monolithic_api.global.service

import com.soom.monolithic_api.domain.account.dto.*
import com.soom.monolithic_api.domain.account.type.AccountType
import com.soom.monolithic_api.domain.account.type.DepartmentType
import com.soom.monolithic_api.domain.account.type.GenderType
import com.soom.monolithic_api.domain.account.type.SchoolType
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class DummyLoginAccountService: LoginAccountService {
    override fun getLoginAccount(): AccountDto = StudentDto(
        AccountBasicDto("지인호", GenderType.남성, LocalDate.MIN, "", SchoolType.광주_소프트웨어_마이스터_고등학교),
        AccountAuthDto("s20072@gsm.hs.kr", "1234"),
        AccountMetaDto(1, AccountType.학생, LocalDateTime.MIN),
        StudentAdditionalDto(3119, LocalDate.MIN, DepartmentType.소프트웨어_개발)
    )
}