package com.ych.service.impl;

import com.ych.dao.StudentDao;
import com.ych.entity.Student;
import com.ych.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
* Created by GeneratorFx on 2017-05-03.
*/
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    public List getStudentList() {
        String sql = "select * from student ";
        Object[] queryParams={};
        return studentDao.execSQLQueryToMap(sql,queryParams);
    }

//@Resource
//StudentDao studentDao;
//
//@Override
//public Student findById(Object id) {
//return studentDao.findById(id);
//}
//
//@Override
//public List<Student> findAll() {
//return studentDao.findAll();
//}
//
//@Override
//public void save(Student entity) {
//studentDao.save(entity);
//}
//
//@Override
//public Student update(Student entity) {
//return studentDao.update(entity);
//}
//
//@Override
//public void delete(Student entity) {
//studentDao.delete(entity);
//}
}
