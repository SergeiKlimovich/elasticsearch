package com.klimovich.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Person {
    private String id;
    private String name;
    private String age;
}
