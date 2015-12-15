package com.ccolor.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccolor.mybatis.bean.V_post;
import com.ccolor.mybatis.bean.V_postExample;
import com.ccolor.mybatis.mapper.V_postMapper;



@Service
public class V_postService {
	@Autowired
	private V_postMapper V_postMapper;
	
	public V_post getTextBeanByPK(Integer pid) {
		return V_postMapper.selectByPrimaryKey(pid);
	}
	public V_post getTextBeanBySPID(Integer pid) {
		V_postExample vpe=new V_postExample();
		vpe.createCriteria().andSpIdEqualTo(pid);
		List list=(List)V_postMapper.selectByExample(vpe);
		return (V_post) list.get(0);
	}
	public List<V_post> getTextBeanByBean(V_postExample text) {
		return V_postMapper.selectByExample(text);
	}
	public void addTextData(V_post text) {
		 V_postMapper.insertSelective(text);
	}
	public void updateTextData(V_post text) {
		 V_postMapper.updateByPrimaryKey(text);
	}
}
