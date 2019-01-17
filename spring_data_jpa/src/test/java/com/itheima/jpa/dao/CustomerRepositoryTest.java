package com.itheima.jpa.dao;

import com.itheima.jpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author XiangYang-2025228450@qq.com
 * @Date 2019/1/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAllCustomer() {
        List<Customer> customers = customerRepository.findAllCustomer();
        System.out.println(customers.size());
    }

    @Test
    public void findCustomer() {
        Customer customer = customerRepository.findCustomer("紫禁城");
        System.out.println(customer.getCustName());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateCustomer() {
        customerRepository.updateCustomer("长城",11L);
    }

    @Test
    public void findByCustNameAndCustId() {
        Customer c = customerRepository.findByCustNameAndCustId("长城", 11L);
        System.out.println(c);
    }
}
