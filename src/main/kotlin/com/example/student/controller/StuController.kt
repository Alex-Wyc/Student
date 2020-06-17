package com.example.student.controller

import com.example.student.entity.Student
import com.example.student.service.StuService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping

class StuController {
    @Autowired
    private lateinit var  stuService: StuService


    //添加
    @ApiOperation(value = "添加学生", notes = "输入信息添加学生")
    @PostMapping("/add")
    @ResponseBody
    fun add(name: String, id: String): Student? {
        val s1 = Student()
        s1.id = id
        s1.name = name
        return stuService!!.add(id,name)
    }

    //查询
    @ApiOperation(value = "查询学生信息", notes = "显示所有学生信息")
    @GetMapping("/findAll")
    @ResponseBody
    fun findAll(): MutableList<Student>? {
        return stuService?.findAll()
    }

    //根据id查询
    @ApiOperation(value = "根据id查询",notes = "输入要查询的id")
    @GetMapping("/findById")
    @ResponseBody
    fun findById(id: String): Student? {
        return stuService!!.findStudentById(id)
    }

    //根据id删除

    @ApiOperation(value = "根据id删除",notes = "输入要删除的id")
    @RequestMapping(value = ["/deleteById"],method = arrayOf(RequestMethod.DELETE))
    @ResponseBody

    fun deleteById(id: String, @ApiIgnore sr: HttpServletResponse): String {
        sr.contentType = "text/html;charset=utf-8"
        return stuService!!.deleteStudentById(id)
    }

}
