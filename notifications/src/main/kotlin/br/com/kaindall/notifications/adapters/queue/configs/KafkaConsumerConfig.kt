package br.com.kaindall.notifications.adapters.queue.configs

import br.com.kaindall.notifications.adapters.queue.dtos.NotificationEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.util.HashMap


@Configuration
class KafkaConsumerConfig {
    @Value("\${kafka.url}")
    private val kafkaUrl: String? = null

    @Value("\${kafka.consumer.group-id}")
    private val groupId: String? = null

    @Bean
    fun kafkaNotificationListenerFactory(): ConcurrentKafkaListenerContainerFactory<String, NotificationEvent> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, NotificationEvent> =
            ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return factory
    }

    private fun consumerFactory(): ConsumerFactory<String, NotificationEvent> {
        val jsonDeserializer: JsonDeserializer<NotificationEvent> =
            JsonDeserializer(NotificationEvent::class.java, false)
        jsonDeserializer.addTrustedPackages("*")

        val configs: MutableMap<String, Any?> = HashMap()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaUrl
        configs[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        configs[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        return DefaultKafkaConsumerFactory(
            configs,
            StringDeserializer(),
            jsonDeserializer
        )
    }
}