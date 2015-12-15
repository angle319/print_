package com.ccolor.mybatis.mapper;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.bean.PageControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageControlMapper {
    int countByExample(PageControlExample example);

    int deleteByExample(PageControlExample example);

    int deleteByPrimaryKey(Integer spid);

    int insert(PageControl record);

    int insertSelective(PageControl record);

    List<PageControl> selectByExample(PageControlExample example);

    PageControl selectByPrimaryKey(Integer spid);

    int updateByExampleSelective(@Param("record") PageControl record, @Param("example") PageControlExample example);

    int updateByExample(@Param("record") PageControl record, @Param("example") PageControlExample example);

    int updateByPrimaryKeySelective(PageControl record);

    int updateByPrimaryKey(PageControl record);
}