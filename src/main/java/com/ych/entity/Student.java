package com.ych.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
* Created by GeneratorFx on 2017-05-03.
*/
@Entity
@Table(name = "student", schema = "studb")
public class Student implements Serializable {

    @Id
    @Column(name = "stuNo", nullable = false)
private String stuNo;
    @Basic
    @Column(name = "stuName", nullable = false)
private String stuName;
public String getStuno() {
return stuNo;
}

public void setStuno(String stuNo) {
this.stuNo = stuNo;
}

public String getStuname() {
return stuName;
}

public void setStuname(String stuName) {
this.stuName = stuName;
}

}
