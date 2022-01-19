package com.fimon.scc.entity.params;

import lombok.Data;

@Data
public class ListParam {

    private Long sku = 0l;

    private Integer page = 1;

    private Integer pageSize = 20;

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

}
