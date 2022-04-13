package com.soom.monolithic_api.infra.email.service

interface EmailSenderService {
    fun sendEmail(email: String, title: String, templatePath: String, data: Map<String, String>)
}