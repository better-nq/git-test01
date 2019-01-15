package com.itcast.pojo;

import org.junit.Test;

/**
 * @Author XiangYang-2025228450@qq.com
 * @Date 2019/1/15
 */

public class StudentTest {

    @Test
    public void testStudent() {
        Student student = new Student();
        student.setName("迪丽热巴");
        student.setSex("女");
        System.out.println(student);
    }

}
