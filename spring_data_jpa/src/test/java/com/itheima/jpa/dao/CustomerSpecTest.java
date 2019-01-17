package com.itheima.jpa.dao;

import com.itheima.jpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @Author XiangYang-2025228450@qq.com
 * @Date 2019/1/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerSpecTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSpec1(){
        //构建查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");//根对象
                Predicate predicate = cb.equal(custName, "长城");
                return cb.and(predicate);
            }
        };

        Customer customer = customerRepository.findOne(specification);
        System.out.println("==========" + customer);
    }

    //模糊查询
    @Test
    public void testSpec2(){
        //构建查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");//根对象
                Predicate predicate = cb.like(custName.as(String.class), "世界%");
                return cb.and(predicate);
            }
        };

        List<Customer> customers = customerRepository.findAll(specification);
        for (Customer customer : customers) {
            System.out.println("==========" + customer);
        }

    }

    //分页查询
    @Test
    public void testSpec3(){
        //构建查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");//根对象
                Predicate predicate = cb.like(custName.as(String.class), "世界%");
                return cb.and(predicate);
            }
        };
        // 构建分页对象 page是从0开始
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> page = customerRepository.findAll(specification, pageable);
        System.out.println("总记录数："+page.getTotalElements());
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("结果集："+page.getContent());
    }

}
