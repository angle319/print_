package com.ccolor.mybatis.mapper;

import com.ccolor.mybatis.bean.V_product_print;
import com.ccolor.mybatis.bean.V_product_printExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface V_product_printMapper {
    int countByExample(V_product_printExample example);

    int deleteByExample(V_product_printExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(V_product_print record);

    int insertSelective(V_product_print record);

    List<V_product_print> selectByExample(V_product_printExample example);

    V_product_print selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") V_product_print record, @Param("example") V_product_printExample example);

    int updateByExample(@Param("record") V_product_print record, @Param("example") V_product_printExample example);

    int updateByPrimaryKeySelective(V_product_print record);

    int updateByPrimaryKey(V_product_print record);
}