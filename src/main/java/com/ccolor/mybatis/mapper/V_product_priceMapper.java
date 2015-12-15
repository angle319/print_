package com.ccolor.mybatis.mapper;

import com.ccolor.mybatis.bean.V_product_price;
import com.ccolor.mybatis.bean.V_product_priceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface V_product_priceMapper {
    int countByExample(V_product_priceExample example);

    int deleteByExample(V_product_priceExample example);

    int insert(V_product_price record);

    int insertSelective(V_product_price record);

    List<V_product_price> selectByExample(V_product_priceExample example);

    int updateByExampleSelective(@Param("record") V_product_price record, @Param("example") V_product_priceExample example);

    int updateByExample(@Param("record") V_product_price record, @Param("example") V_product_priceExample example);
}