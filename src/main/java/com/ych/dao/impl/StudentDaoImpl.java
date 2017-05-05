package com.ych.dao.impl;

import com.ych.dao.StudentDao;
import com.ych.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
* Created by GeneratorFx on 2017-05-03.
*/
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao  {


}
