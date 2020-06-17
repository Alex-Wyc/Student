package com.example.student.controller

import com.example.student.entity.Student
import com.example.student.service.StuService
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import java.util.*

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@SpringBootTest
internal class StuControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var service: StuService

    @Before
    fun setUp() {
    }

    @Test
    fun add() {

    }

    @Test
    fun findAll() {

        this.mockMvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect((content().string("qwe")))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun findById() {
    }

    @Test
    fun deleteById() {
    }
}