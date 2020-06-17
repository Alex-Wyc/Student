package com.example.student.service

import com.example.student.dao.StuDao
import com.example.student.entity.Student
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service



@Service
class StuService {

    @Autowired
    private lateinit var stuDao: StuDao

    @Autowired
    private lateinit var  template: KafkaTemplate<String, Any>

    fun add(id: String, name:String): Student? {
        if(stuDao.findById(id)?.isPresent) {
            System.out.println("exist!")
                return null;
        }else{
            val s = Student()
            s.id = id
            s.name = name
            System.out.println("save!")
            this.template.send(topic, s )
            return stuDao.save(s)
            }
    }

    fun findAll(): MutableList<Student>? {
        return stuDao.findAll()
    }

    fun findStudentById(id:String):Student?{
        if(stuDao.findById(id).isPresent) {
            return stuDao.findById(id).get();
        }else{
            return null
        }
    }

    fun  deleteStudentById(id: String):String{
        if(stuDao.findById(id).isPresent){
            stuDao.deleteById(id)
            return "deleteId=$id"
        }
        else
            return "not exist"

    }
    @KafkaListener(id = "", topics = [topic], groupId = "group.sendStu")
    fun listener(record: ConsumerRecord<String, Any>?) {
    }
    companion object {
        private const val topic = "stu"

    }
}