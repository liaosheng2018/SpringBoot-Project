package com.zzg.service.impl;

import com.zzg.mapper.StudentMapper;
import com.zzg.mapper.UserMapper;
import com.zzg.model.Student;
import com.zzg.service.xaService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class xaServiceImpl implements xaService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void xaTransaction() {
        Student student = new Student();
        student.setName("zzg" + new Random().nextInt());
        student.setSex(new Random().nextInt(2) + 1);
        student.setAge(new Random().nextInt(6) + 6);
        studentMapper.addStudent(student);


        Student student2 = new Student();
        student2.setName("zzg" + new Random().nextInt());
        student2.setSex(new Random().nextInt(2) + 1);
        student2.setAge(new Random().nextInt(6) + 6);
        studentMapper.addStudent(student2);

        int a = 1 / 0; //测试回滚

    }
}
