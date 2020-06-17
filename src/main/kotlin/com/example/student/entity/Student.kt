package com.example.student.entity

import scala.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "student")

data class Student(
         @Id
         @Column (name ="id",nullable = false)
         var id : String?=null,
         @Column (name="name",nullable = false)
         var name : String?=null): Serializable

{
        companion object {
        val SerialVersionUID = 1L;
        }
}

