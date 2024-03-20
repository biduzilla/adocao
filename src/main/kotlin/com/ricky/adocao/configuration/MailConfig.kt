package com.ricky.adocao.configuration

import com.ricky.adocao.service.ConfiguracaoService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl


@Configuration
class MailConfig(private val configuracaoService: ConfiguracaoService) {

    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        val props = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp";
        props["mail.smtp.auth"] = "true";
        props["mail.smtp.starttls.enable"] = "true";
        props["mail.debug"] = "true";

        return mailSender
    }
}