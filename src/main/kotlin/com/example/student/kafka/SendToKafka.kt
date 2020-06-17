package com.example.student.kafka

import com.example.student.StudentApplication
import com.example.student.entity.Student
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping

class SendToKafka {
    @Autowired
    private lateinit var template: KafkaTemplate<String, Any>

    @GetMapping("/sendStu")
    @ResponseBody
    fun sendStudent(id: String, name: String): String {
        val student = Student()
        student.id = id
        student.name = name
        template.send(topic, student)
        return student.toString()
    }

    @KafkaListener(id = "", topics = [topic], groupId = "group.sendStu")
    fun listener(record: ConsumerRecord<String, Any>?) {
        LOGGER.info("message input value:{}", record)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(StudentApplication::class.java)
        private const val topic = "stu"
    }
}
