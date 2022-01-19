package com.fimon.scc.mapper;

import com.fimon.scc.entity.Item;
import com.fimon.scc.entity.params.ListParam;
import com.fimon.scc.handler.mybatis.TagsTypeHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper {

    @Insert("insert into t_item " +
            "(sku, name, `desc`, category, tags, count, status) values " +
            "(#{sku}, #{name}, #{desc}, #{category}, " +
            "#{tags, typeHandler=com.fimon.scc.handler.mybatis.TagsTypeHandler}, " +
            "#{count}, #{status})")
    void createItem(Item item);

    @Update("<script>" +
            "update t_item" +
            "<set>" +
            "<if test='name != null'>name=#{name}, </if>" +
            "<if test='desc != null'>`desc`=#{desc}, </if>" +
            "<if test='category != null'>category=#{category}, </if>" +
            "<if test='tags != null'>tags=#{tags, typeHandler=com.fimon.scc.handler.mybatis.TagsTypeHandler}, </if>" +
            "<if test='count != null'>count=#{count}, </if>" +
            "<if test='status != null'>status=#{status}, </if>" +
            "</set>" +
            "where sku=#{sku}" +
            "</script>")
    void updateItem(Item item);

    @Select("select * from t_item where sku=#{sku}")
    @Results(id = "itemDetailsMapRule",
    value = {
            @Result(property = "tags", column = "tags", typeHandler = TagsTypeHandler.class)
    })
    Item itemDetails(long sku);

    @Delete("delete from t_item where sku=#{sku}")
    void deleteItem(long sku);

    @Select("select * from t_item " +
            "where sku>=#{sku} " +
            "order by sku " +
            "limit #{pageSize} offset #{offset}")
    @ResultMap("itemDetailsMapRule")
    Item[] fetchItems(ListParam params);

    @Select("select count(*) from t_item")
    int count();

}
