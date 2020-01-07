package com.kiway.cn.springbootelasticsearch.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

/**
 * es测试bean
 *  indexName: 相当于数据库的名称
 *  type: 相当于表名
 * @author LiuZJ
 * @date 2019/12/12 9:46
 */
@Data
@Accessors(chain = true)
@Document(indexName = "myfirstes",type = "person",shards = 1, replicas = 0)
public class Person implements Serializable {

    private static final long serialVersionUID = 6329064538114726203L;

    /**id*/
    @Id
    private Long id;

    /**性别*/
    @Field(type = FieldType.Keyword)
    private String firstName;

    /**名字*/
    private String lastName;

    /**年龄*/
    private Integer age;

    /**兴趣*/
    private String about;

    /**爱好*/
    private List<String> interests;


}
