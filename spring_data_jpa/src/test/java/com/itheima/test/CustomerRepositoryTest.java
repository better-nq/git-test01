package com.itheima.test;

import com.itheima.jpa.dao.CustomerRepository;
import com.itheima.jpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void testSave(){
        List<Customer> customers = new ArrayList<Customer>();
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setCustName("世界之窗" + i);
            customer.setCustIndustry("放松娱乐");
            customers.add(customer);
        }
        customerRepository.save(customers);
    }

    /**
     * 根据id删除：调用delete(id)方法
     */
    @Test
    public void testDelete(){
        customerRepository.delete(1L);
    }

    /**
     * 根据id查询：调用findOne(id)方法
     */
    @Test
    public void testFindById(){
        Customer customer = customerRepository.findOne(2L);
        System.out.println(customer);
    }

    /**
     * 修改客户：调用save(obj)方法
     *          对于save方法的解释：如果执行此方法是对象中存在id属性，即为更新操作会先根据id查询，再更新
     *          如果执行此方法中对象中不存在id属性，即为保存操作
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate(){
        //根据id查询id为2的客户
        Customer customer = customerRepository.getOne(2L);
        //修改客户名称
        customer.setCustName("紫禁城");
        //更新
        customerRepository.save(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testFind(){
        //Customer customer = customerRepository.getOne(13L);
        Customer customer = new Customer();
        customer.setCustId(12L);
        customer.setCustAddress("合肥OOO");
        customer.setCustPhone("10086");
        customerRepository.save(customer);
        System.out.println(customer);
    }

}
