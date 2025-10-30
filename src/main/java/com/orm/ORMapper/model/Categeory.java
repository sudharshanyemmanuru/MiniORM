package com.orm.ORMapper.model;

import com.orm.ORMapper.annotations.Column;
import com.orm.ORMapper.annotations.Entity;
import com.orm.ORMapper.annotations.Id;

@Entity
public class Categeory {
    @Id
    private int categeoryId;
    @Column(columnName = "categeory_name",size = 10)
    private String categeoryName;
}
