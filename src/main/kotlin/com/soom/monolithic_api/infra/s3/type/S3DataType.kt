package com.soom.monolithic_api.infra.s3.type

enum class S3DataType(
    val pathFormatter: (Array<Any?>) -> String
) {
    PROFILE_IMAGE({"user/profile"});
}

fun format(form: String): (Array<out Any?>) -> String = {arg -> String.format(form, *arg)}
fun get(form: String, vararg args: Any?): String = format(form).invoke(args)