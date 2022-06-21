package com.zzg.mapper;

import com.zzg.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface StudentMapper {

    @Insert("insert into t_student(sex,age,name) values (#{sex},#{age},#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addStudent(Student student);
}
