package com.orm.ORMapper.model;

import com.orm.ORMapper.annotations.Column;
import com.orm.ORMapper.annotations.Entity;
import com.orm.ORMapper.annotations.Id;

@Entity
public class Book {
    @Id
    private int id;
    @Column(size = 40,columnName = "book_name")
    private String name;
    @Column(columnName = "book_price")
    private double price;

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setPrice(double price){
        this.price=price;
    }
    public double getPrice(){
        return this.price;
    }
}
