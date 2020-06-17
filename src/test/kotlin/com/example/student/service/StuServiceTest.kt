package com.example.student.service

import com.example.student.StudentApplication
import com.example.student.dao.StuDao
import com.example.student.entity.Student
import lombok.extern.slf4j.Slf4j
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
internal class StuServiceTest {

    @Mock
    lateinit var stuDao: StuDao

    @Mock
    private var template: KafkaTemplate<String, Any>? = null

    @InjectMocks
    lateinit var service: StuService

    @Before
    fun setUp() {

        Mockito.`when`(stuDao.findById("1")).thenReturn(Optional.of(Student("1","exist")))
        Mockito.`when`(stuDao.save(Student("2","notExist"))).thenReturn(Student("2","notExist"))
        Mockito.`when`(stuDao.findAll()).thenReturn(mutableListOf(Student("1","Jim")))
        Mockito.`when`(stuDao.findById("3")).thenReturn(Optional.of(Student("3","delete")))


    }


    @Test
    //添加的学生已经存在
    fun addExist() {
        assertNull(service.add("1","exist"))
    }

    @Test
    //添加的学生不存在
    fun  addNotExist(){
        assertEquals(Student("2","notExist"),service.add("2","notExist"))
    }

    @Test
    //查询所有学生
    fun findAll() {
        assertEquals("1", service.findAll()?.get(0)?.id)
        assertEquals("Jim",service.findAll()?.get(0)?.name)
    }

    @Test
    fun findStudentById() {
        assertEquals("1", service.findStudentById("1")?.id)
    }

    @Test
    fun deleteStudentById() {
        assertEquals("deleteId=3",service.deleteStudentById("3"))
        LOGGER.info("deleteId:={}",stuDao.findById("3").get().id)
    }
    companion object {
        private val LOGGER = LoggerFactory.getLogger(StuServiceTest::class.java)
        private const val topic = "stu"
    }
}



