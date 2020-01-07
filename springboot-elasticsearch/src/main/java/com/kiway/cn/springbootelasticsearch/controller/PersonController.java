package com.kiway.cn.springbootelasticsearch.controller;

import com.kiway.cn.springbootelasticsearch.bean.Person;
import com.kiway.cn.springbootelasticsearch.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *搜索人管理Controller
 *
 * @author LiuZJ
 * @date 2019/12/31 14:35
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping("/init")
    public void importAll() {
        Map<String,Object> map = new HashMap<>(3);
        //建立索引
        boolean flag = personService.createIndex();
        List<Person> people = personService.importAll();
        //添加文档
        personService.saveAll(people);
        map.put("create index is:", flag);
        System.out.println(map);
    }

    @GetMapping("/all")
    public Iterator<Person> all() {
        return personService.findAll();
    }


    @GetMapping("/findByFirstName/{firstName}")
    public Map<String,Object> findByFirstName(@PathVariable("firstName") String firstName){
        Map<String,Object> map = new HashMap<>(3);
        Page<Person> list = personService.findByFirstName(firstName);
        map.put("通过firstName分页查询", list);
        return map;
    }

    @DeleteMapping("/delete")
    public Map<String,Object> delete() {
        Map<String,Object> map = new HashMap<>(3);
        boolean es = personService.deleteIndex("myfirstes");
        map.put("delete index is:",es);
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByid(@PathVariable("id")Long id){
        List<Long> list = Arrays.asList(id);
        personService.deletebyId(list);
    }

    @GetMapping("/search")
    public Page<Person> search(@RequestParam(value = "firstName",required = false) String firstName,
                               @RequestParam(value = "age",required = false) Integer age) {
        return personService.search(firstName, age);
    }





}
