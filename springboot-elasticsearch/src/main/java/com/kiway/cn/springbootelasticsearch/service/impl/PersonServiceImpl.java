package com.kiway.cn.springbootelasticsearch.service.impl;

import com.kiway.cn.springbootelasticsearch.bean.Person;
import com.kiway.cn.springbootelasticsearch.service.PersonEsRepository;
import com.kiway.cn.springbootelasticsearch.service.PersonService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * TODO
 *
 * @author LiuZJ
 * @date 2019/12/31 14:19
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonEsRepository repository;
    @Autowired(required = false)
    private ElasticsearchTemplate elasticsearchTemplate;

    private Pageable pageable = PageRequest.of(0,10);

    @Override
    public boolean createIndex() {
        return elasticsearchTemplate.createIndex(Person.class);
    }

    @Override
    public boolean deleteIndex(String index) {
        return elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public void save(Person person) {
        repository.save(person);
    }

    @Override
    public void saveAll(List<Person> list) {
         repository.saveAll(list).iterator();
    }

    @Override
    public Iterator<Person> findAll() {
        return repository.findAll().iterator();
    }

    @Override
    public Page<Person> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName, pageable);
    }

    @Override
    public Page<Person> findByLastName(String lastName) {
        return repository.findByLastName(lastName, pageable);
    }

    @Override
    public void deletebyId(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<Person> personList = new ArrayList<>();
            for (Long id : ids) {
                Person person = new Person();
                person.setId(id);
                personList.add(person);
            }
            repository.deleteAll(personList);
        }
    }

    @Override
    public Page<Person> search(String firstName, Integer age) {
        //自定义查询
        //创建查询条件生成器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withPageable(pageable);
        //组合条件查询(满足条件即生效)
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(firstName!=null) {
            //过滤名字相同的才拿到
            boolQueryBuilder.must(QueryBuilders.termQuery("firstName", firstName));
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        if(age!=null) {
            //年龄大于等于23并且小于等于25的
            boolQueryBuilder.must(QueryBuilders.rangeQuery("age").gte(23).lte(25));
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
            //根据名字排序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC));
        }

        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        return repository.search(searchQuery);
    }
























    @Override
    public int update(Long id) {
        return 0;
    }



    @Override
    public List<Person> importAll() {
        List<Person> list = new ArrayList<>();
        list.add(getPerson(1L,"liu","zengjin",20,"to be a better man",Arrays.asList("算法","算命")));
        list.add(getPerson(2L,"xiao","yuwen",21,"like a watch",Arrays.asList("游戏","LOL")));
        list.add(getPerson(3L,"yin","fang",22,"like a watch",Arrays.asList("游戏","LOL")));
        list.add(getPerson(4L,"xiao","yi",23,"fly on the blue sky",Arrays.asList("eat","sleep")));
        list.add(getPerson(5L,"jay","chou",24,"November's Chopin",Arrays.asList("eat","sleep")));
        list.add(getPerson(6L,"liu","zy",25,"November's Chopin",Arrays.asList("eat","sleep")));
        return list;
    }

    private Person getPerson(Long id,String firstName,String lastName,Integer age,String about,List<String> interests) {
        Person person = new Person();
        person.setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAge(age)
                .setAbout(about)
                .setInterests(interests);
        return person;
    }
}
