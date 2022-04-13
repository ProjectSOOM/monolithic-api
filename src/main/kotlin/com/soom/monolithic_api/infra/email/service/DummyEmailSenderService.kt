package com.soom.monolithic_api.infra.email.service

import org.springframework.stereotype.Service

@Service
class DummyEmailSenderService: EmailSenderService {
    override fun sendEmail(email: String, title: String, templatePath: String, data: Map<String, String>) = Unit
}