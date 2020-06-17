package com.example.student.dao

import com.example.student.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StuDao :JpaRepository<Student,String> {
}