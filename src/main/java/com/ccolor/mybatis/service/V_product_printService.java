package com.ccolor.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccolor.mybatis.bean.V_product_price;
import com.ccolor.mybatis.bean.V_product_priceExample;
import com.ccolor.mybatis.bean.V_product_print;
import com.ccolor.mybatis.bean.V_product_printExample;
import com.ccolor.mybatis.mapper.V_product_priceMapper;
import com.ccolor.mybatis.mapper.V_product_printMapper;

@Service
public class V_product_printService {
	@Autowired
	V_product_printMapper v_product_printMapper;
	@Autowired
	V_product_priceMapper V_product_priceMapper;

	public List<V_product_print> selectAll() {
		return v_product_printMapper.selectByExample(null);
	}

	public V_product_print selectByPKey(int id) {
		return v_product_printMapper.selectByPrimaryKey(id);
	}
	public List<V_product_print> selectBySPID(int id) {
		V_product_printExample vp=new V_product_printExample();
		vp.createCriteria().andSpIdEqualTo(id);
		return v_product_printMapper.selectByExample(vp);
	}
	public List<V_product_print> selectByBeen(V_product_print vp) {
		V_product_printExample vppe = new V_product_printExample();
		vppe.createCriteria().andNameEqualTo(vp.getName()).andContentPicEqualTo(vp.getContentPic());
		return v_product_printMapper.selectByExample(vppe);
	}

	@Transactional
	public void addProduct(V_product_print vp, V_product_price[] vprice_array) {
		
		List list = selectByBeen(vp);
		if (list.size() == 0) {
			v_product_printMapper.insertSelective(vp);
			V_product_print vp_temp = selectByBeen(vp).get(0);
			addPrice(vp_temp.getPid(),vprice_array);
		} else {
			throw new DuplicateKeyException("產品物件重複!!");
		}
	}
	@Transactional
	public void addPrice(int pkey, V_product_price[] vprice_array) {
		for (V_product_price vprice : vprice_array) {
			vprice.setPid(pkey);
			V_product_priceMapper.insertSelective(vprice);
		}
	}
	@Transactional
	public void updateProduct(V_product_print vp, V_product_price[] vprice_array) {
		V_product_priceExample vpp=new V_product_priceExample();
		vpp.createCriteria().andPidEqualTo(vp.getPid());
		vp.setMainPic(null);
		vp.setContentPic(null);
		v_product_printMapper.updateByPrimaryKeySelective(vp);
		V_product_priceMapper.deleteByExample(vpp);
		addPrice(vp.getPid(), vprice_array);
	}
	@Transactional
	public void deleteProduct(int gid) {
		V_product_priceExample vpp=new V_product_priceExample();
		vpp.createCriteria().andPidEqualTo(gid);	
		V_product_priceMapper.deleteByExample(vpp);
		v_product_printMapper.deleteByPrimaryKey(gid);
		
	}
}
