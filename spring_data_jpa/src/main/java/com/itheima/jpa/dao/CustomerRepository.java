package com.itheima.jpa.dao;

import com.itheima.jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author XiangYang-2025228450@qq.com
 * @Date 2019/1/16
 */
public interface CustomerRepository extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {

    //@Query 使用jpql的方式查询
    // 不支持 select * | select count(custId) from Customer
    @Query(value = "from Customer")
    public List<Customer> findAllCustomer();

    //@Query 使用jpql的方式查询。?1代表参数的占位符，其中1对应方法中的参数索引
    @Query(value="from Customer where custName = ?1")
    public Customer findCustomer(String custName);

    @Query(value="update Customer set custName = ?1 where custId = ?2")
    @Modifying
    public void updateCustomer(String custName,Long custId);

    public Customer findByCustNameAndCustId(String s, long l);

}
