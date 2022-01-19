package com.fimon.scc.entity;

import lombok.Data;

@Data
public class Item {

    private Long sku;

    private String name;

    private String desc;

    private Integer category;

    private String[] tags;

    private Integer count;

    private Integer status;

    private Long groupId;

    // more ...

}
