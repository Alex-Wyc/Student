package com.example.student.controller

import com.example.student.dao.StuDao
import com.example.student.entity.Student
import com.example.student.service.StuService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@Transactional

class StuServiceSpingbootTest {

    @Autowired
    lateinit var stuDao: StuDao

    @Autowired
    lateinit var service: StuService

    @Before
    fun setUp() {

    }


    @Test
    //添加的学生已经存在
    fun addExist() {
        Assert.assertNull(service.add("1", "Alex"))
    }

    @Test
    //添加的学生不存在
    fun  addNotExist(){
        Assert.assertEquals(Student("2", "notExist"), service.add("2", "notExist"))
    }

    @Test
    //查询所有学生
    fun findAll() {
        Assert.assertEquals("1", service.findAll()?.get(0)?.id)
        Assert.assertEquals("Alex", service.findAll()?.get(0)?.name)
    }

    @Test
    fun findStudentById() {
        Assert.assertEquals("1", service.findStudentById("1")?.id)
    }

    @Test
    fun deleteStudentById() {
        Assert.assertEquals("deleteId=3", service.deleteStudentById("3"))
        LOGGER.info("deleteId:={}",stuDao.findById("3").get().id)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(StuServiceSpingbootTest::class.java)
        }

}