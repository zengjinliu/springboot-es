package com.kiway.cn.springbootelasticsearch.service;

import com.kiway.cn.springbootelasticsearch.bean.Person;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * @author LiuZJ
 * @date 2019/12/31 14:19
 */
public interface PersonService {


    /**
     * 建立索引
     */
    boolean createIndex();

    /**
     * 删除索引
     * @param index
     */
    boolean deleteIndex(String index);

    /**
     * 将单个文档存入索引中
     * @param person
     */
    void save(Person person);

    /**
     * 将多个数据存入文档中
     * @param list
     * @return  0
     */
    void saveAll(List<Person> list);

    /**
     * 查询所有文档
     * @return
     */
    Iterator<Person> findAll();

    /**
     * 通过firstName查找文档
     * @param firstName
     * @return
     */
    Page<Person> findByFirstName(String firstName);

    /**
     * 通过lastName查找文档
     * @param lastName
     * @return
     */
    Page<Person> findByLastName(String lastName);

    /**
     * 通过id删除文档
     * @param ids
     * @return
     */
    void deletebyId(List<Long> ids);

    /**
     * 根据id更新文档
     * @param id
     * @return
     */
    int update(Long id);


    /**
     * 从数据库中导入所有商品到ES
     */
    List<Person> importAll();


    /**
     * 综合搜索过滤排序
     * @param firstName 姓
     * @param age 排序类别,1根据id排序，2,根据年龄排序
     * @return
     */
    Page<Person> search(String firstName,Integer age);
}
