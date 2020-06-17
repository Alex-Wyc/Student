package com.example.student.kafka


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping

class KafStu {
    @Autowired
    private lateinit var template: KafkaTemplate<String,Any>

    @GetMapping("/send/{stu}")
    fun sendToKafka(@PathVariable stu: String): String {
        template!!.send(topic, stu )
        return "send:$stu"
    }

    @KafkaListener(id = "", topics = [topic], groupId = "group.stu")
    fun listener(stu: String?) {
        KafStu.LOGGER.info("message input value:{}", stu)
    }

    @GetMapping("/hello")
    fun hello():String{
        return "Hello!"
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(KafStu::class.java)
        private const val topic = "student"
    }
}