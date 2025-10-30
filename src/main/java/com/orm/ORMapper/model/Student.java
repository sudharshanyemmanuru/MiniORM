package com.orm.ORMapper.model;

import com.orm.ORMapper.annotations.Column;
import com.orm.ORMapper.annotations.Entity;
import com.orm.ORMapper.annotations.Id;

@Entity
public class Student {
    @Id
    private int studentId;
    @Column(size = 20,columnName = "student_name")
    private String studentName;
}
