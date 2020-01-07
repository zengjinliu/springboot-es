package com.kiway.cn.springbootelasticsearch.service;

import com.kiway.cn.springbootelasticsearch.bean.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author LiuZJ
 * @date 2019/12/12 9:58
 */

public interface PersonEsRepository extends ElasticsearchRepository<Person, Long> {

    Page<Person> findByFirstName(String firstName, Pageable pageable);

    Page<Person> findByLastName(String lastName, Pageable pageable);


}
