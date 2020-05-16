package com.example.mysqlsharding.mapper;

import com.example.mysqlsharding.model.DBModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DBModelMappr {

    @Select("SELECT * FROM DBModel")
    List<DBModel> list();

    @Insert("INSERT INTO DBMODEL VALUES(NULL,#{beanName},#{driver},#{url},#{username},#{password})")
    Integer insert(DBModel dbModel);
}
