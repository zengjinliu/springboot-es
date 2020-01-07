package com.kiway.cn.springbootelasticsearch;

import com.kiway.cn.springbootelasticsearch.bean.Person;
import com.kiway.cn.springbootelasticsearch.service.PersonEsRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootElasticsearchApplicationTests {

    @Autowired
    private PersonEsRepository repository;


    @Test
    public void test1() {

        Person article = new Person();
        article.setId(1L);

        repository.save(article);
    }
    @Test
    public void test2() {
        String a= null;
        if(StringUtils.isNotBlank(a)) {
            System.out.println("***********************");
        }
    }
    @Test
    public void test3() {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a.equals(b));
    }

}
