package com.fimon.scc.mapper;

import com.fimon.scc.entity.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper {

    @Insert("insert into t_group values " +
            "(#{id}, #{name}, #{desc})")
    void createGroup(Group group);

    @Update("<script>" +
            "update t_group" +
            "<set>" +
            "<if test='name != null'>name=#{name}, </if>" +
            "<if test='desc != null'>`desc`=#{desc}, </if>" +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    void updateGroup(Group group);

    @Select("select * from t_group")
    Group[] getAllGroups();

    @Select("select * from t_group " +
            "where id=#{id}")
    Group getDetails(long id);

    @Delete("delete from t_group where id=#{id}")
    void deleteGroup(long id);
}
