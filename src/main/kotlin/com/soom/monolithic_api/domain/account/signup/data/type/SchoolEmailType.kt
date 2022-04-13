package com.soom.monolithic_api.domain.account.signup.data.type

import com.soom.monolithic_api.domain.account.common.data.type.SchoolType

enum class SchoolEmailType(
    val schoolType: SchoolType,
    val validator: (String) -> Boolean
) {
    광주_소프트웨어_마이스터고등학교(
        SchoolType.광주_소프트웨어_마이스터_고등학교,
        composeValidator(
            regexValidator("[s|t][0-9]{5}@gsm.hs.kr"),
            regexValidator("student[0-9]{6}@gsm.hs.kr"))
    ),
    대덕_소프트웨어_마이스터고등학교(
        SchoolType.대덕_소프트웨어_마이스터_고등학교,
        regexValidator(".*@dsm.hs.kr")
    ),
    대구_소프트웨어_마이스터고등학교(
        SchoolType.대구_소프트웨어_마이스터_고등학교,
        regexValidator(".*@dgsm.hs.kr")
    ),
    부산_소프트웨어_마이스터고등학교(
        SchoolType.부산_소프트웨어_마이스터_고등학교,
        regexValidator(".*@bsm.hs.kr")
    )
}

fun regexValidator(regex: String): (String) -> Boolean = { it.matches(regex.toRegex()) }
fun composeValidator(vararg validators: (String) -> Boolean): (String) -> Boolean = {
    validators.fold(false) { acc, validator -> acc || validator(it) }
}