package com.ccolor.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccolor.mybatis.bean.V_product_price;
import com.ccolor.mybatis.bean.V_product_priceExample;
import com.ccolor.mybatis.mapper.V_product_priceMapper;
@Service
public class V_product_priceService {
	@Autowired
	V_product_priceMapper V_product_priceMapper;
	public List<V_product_price> selectByPid(int pid) {
		V_product_priceExample vpe=new V_product_priceExample();
		vpe.createCriteria().andPidEqualTo(pid);
		return V_product_priceMapper.selectByExample(vpe);
	}
	
}
