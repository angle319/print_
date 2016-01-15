package com.ccolor.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.mesg.MCommon;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.bean.PageControlExample;
import com.ccolor.mybatis.mapper.PageControlMapper;

@Service
public class PageControlService {
	@Autowired
	PageControlMapper pcMapper;
	public List<PageControl> getAll() {
		PageControlExample pce=new PageControlExample();
		pce.setOrderByClause("sort,spid asc");
		return pcMapper.selectByExample(pce);
	}
	public PageControl getByPK(int spid) {
		return pcMapper.selectByPrimaryKey(spid);
	}
	public List<PageControl> getExceptRoot() {
		PageControlExample pce=new PageControlExample();
		pce.createCriteria().andSpidGreaterThan(0).andVisibleEqualTo(true);
		pce.setOrderByClause("sort,spid asc");
		return pcMapper.selectByExample(pce);
	}
	public void addPageControll(PageControl record){
		pcMapper.insertSelective(record);
	}
	public void updatePageControll(PageControl record){
		pcMapper.updateByPrimaryKeySelective(record);
	}
}
