package com.fimon.scc.mapper;

import com.fimon.scc.entity.params.AssignParam;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassifyMapper {

    @Update("update t_item " +
            "set group_id=#{groupId} " +
            "where sku=#{sku}")
    void assign(AssignParam params);

    @Update("update t_item " +
            "set group_id=-1 " +
            "where sku=#{sku}")
    void remove(long sku);

    @Update("update t_item " +
            "set group_id=-1 " +
            "where group_id=#{id}")
    void removeAll(long id);
}
