package com.ccolor.mybatis.mapper;

import com.ccolor.mybatis.bean.V_post;
import com.ccolor.mybatis.bean.V_postExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface V_postMapper {
    int countByExample(V_postExample example);

    int deleteByExample(V_postExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(V_post record);

    int insertSelective(V_post record);

    List<V_post> selectByExample(V_postExample example);

    V_post selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") V_post record, @Param("example") V_postExample example);

    int updateByExample(@Param("record") V_post record, @Param("example") V_postExample example);

    int updateByPrimaryKeySelective(V_post record);

    int updateByPrimaryKey(V_post record);
}