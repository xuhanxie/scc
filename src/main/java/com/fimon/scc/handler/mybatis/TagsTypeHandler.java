package com.fimon.scc.handler.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(String[].class)
public class TagsTypeHandler implements TypeHandler<String[]> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        String str = strings == null ? "" : String.join(",", strings);
        preparedStatement.setString(i, str);
    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        String[] arr = str == null ? new String[0] : str.split(",");
        return arr;
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        String[] arr = str == null ? new String[0] : str.split(",");
        return arr;
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        String[] arr = str == null ? new String[0] : str.split(",");
        return arr;
    }
}
