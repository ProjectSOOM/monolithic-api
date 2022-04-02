package com.soom.monolithic_api.infra.type

enum class S3DataType(
    val dirPath: (Array<Any?>) -> String
) {
    PROFILE_IMAGE(format("user/profile/%s"));
}

fun format(form: String): (Array<out Any?>) -> String = {arg -> String.format(form, *arg)}

fun get(form: String, vararg args: Any?): String = format(form).invoke(args)