package com.ricky.adocao.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.DefaultContentTypeResolver
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.util.MimeTypeUtils
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/user")
        registry.setApplicationDestinationPrefixes("/app")
        registry.setUserDestinationPrefix("/user")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws").withSockJS();
        registry.addEndpoint("/ws");
    }

    override fun configureMessageConverters(messageConverters: MutableList<MessageConverter>): Boolean {
        val resolver = DefaultContentTypeResolver()
        val converter = MappingJackson2MessageConverter()

        converter.objectMapper = ObjectMapper()
        converter.contentTypeResolver = resolver
        resolver.defaultMimeType = MimeTypeUtils.APPLICATION_JSON
        messageConverters.add(converter)
        return false
    }
}