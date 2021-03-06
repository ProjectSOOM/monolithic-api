package com.soom.monolithic_api.global.security.service

import com.soom.monolithic_api.domain.account.common.data.dto.*
import com.soom.monolithic_api.domain.account.common.data.type.AccountType
import com.soom.monolithic_api.domain.account.common.data.type.DepartmentType
import com.soom.monolithic_api.domain.account.common.data.type.GenderType
import com.soom.monolithic_api.domain.account.common.data.type.SchoolType
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service //TODO 추후 실제 Service 로 전환
class DummyLoginAccountService: LoginAccountService {
    override fun getLoginAccount(): AccountDto = StudentDto(
        AccountBasicDto("지인호", GenderType.남성, LocalDate.MIN, "", SchoolType.광주_소프트웨어_마이스터_고등학교),
        AccountAuthDto("s20072@gsm.hs.kr", "1234"),
        AccountMetaDto(0, AccountType.학생, LocalDateTime.MIN),
        StudentAdditionalDto(3119, LocalDate.MIN, DepartmentType.소프트웨어_개발)
    )
}