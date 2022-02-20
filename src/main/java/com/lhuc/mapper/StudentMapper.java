package com.lhuc.mapper;

import com.lhuc.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lhucstart
 * @create 2021-04-23 14:31
 */
@Repository
public interface StudentMapper {

    List<Student> selectList();

}
