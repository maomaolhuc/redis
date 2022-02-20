package com.lhuc.service.impl;

import com.lhuc.domain.Student;
import com.lhuc.mapper.StudentMapper;
import com.lhuc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhucstart
 * @create 2021-04-23 14:45
 */
@Service
public class StudentServiceImpl implements StudentService {



    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> findStudentList() {
        return studentMapper.selectList();
    }
}
