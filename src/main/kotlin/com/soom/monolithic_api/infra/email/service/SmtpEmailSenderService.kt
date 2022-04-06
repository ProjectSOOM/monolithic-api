package com.soom.monolithic_api.infra.email.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

class SmtpEmailSenderService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: TemplateEngine
): EmailSenderService {
    override fun sendEmail(email: String, title: String, templatePath: String, data: Map<String, String>) {
        javaMailSender.createMimeMessage()
            .apply {
                MimeMessageHelper(this, true)
                    .apply {
                        setSubject(title)
                        setTo(email)
                        setText(text(templatePath, data))
                    }
            }.let { javaMailSender.send(it) }
    }

    private fun text(templatePath: String, models: Map<String, String>) =
        Context()
            .apply { models.forEach(::setVariable) }
            .let { templateEngine.process(templatePath, it) }
}