package com.ccolor.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.zkoss.zk.ui.Sessions;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.bean.V_post;
import com.ccolor.mybatis.bean.V_product_price;
import com.ccolor.mybatis.bean.V_product_print;
import com.ccolor.mybatis.service.PageControlService;
import com.ccolor.mybatis.service.V_postService;
import com.ccolor.mybatis.service.V_product_priceService;
import com.ccolor.mybatis.service.V_product_printService;
import com.ccolor.web.zk.model.ZKmodel;

@Controller
public class ProductController {
	@Resource
	@Qualifier("V_product_printService")
	V_product_printService vpps;
	@Resource
	@Qualifier("V_product_priceService")
	V_product_priceService vps_price;
	@Resource
	@Qualifier("PageControlService")
	PageControlService pcs;
	
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(WebRequest webRequest, Locale locale, Model model) {
		int spid = 1;
		PageControl pc = null;
		try {
			spid = Integer.parseInt(webRequest.getParameter("pid"));
		} catch (Exception e) {
		}
		pc = pcs.getByPK(spid);

		try {
			List<V_product_print> list = new ArrayList<V_product_print>();
			list = vpps.selectBySPID(pc.getSpid());
			model.addAttribute("html_content", list);
		} catch (IndexOutOfBoundsException e) {
			model.addAttribute("html_content", "NO DATA");
		}
		return "web/product.jsp";
	}
	@RequestMapping(value = { "/product_detail" }, method = RequestMethod.GET)
	public String product_detail (WebRequest webRequest, Locale locale, Model model,HttpServletRequest re) {
		int spid=Integer.parseInt(webRequest.getParameter("pid"));
		int dtid=Integer.parseInt(webRequest.getParameter("dtid"));
		PageControl pc = pcs.getByPK(spid);
		V_product_print vp_bean =vpps.selectByPKey(dtid);
		List<V_product_price> vp_price=vps_price.selectByPid(dtid);
		String[] fileName = loadContentPic(re.getRealPath(vp_bean.getContentPic()));
		String[] note=vp_bean.getNote().split(ZKmodel.getSpiltPatter());
		try {
			model.addAttribute("pic_list", fileName);
			model.addAttribute("product", vp_bean);
			model.addAttribute("note", note);
			model.addAttribute("price", vp_price);
			model.addAttribute("page", pc);
		} catch (IndexOutOfBoundsException e) {
			model.addAttribute("product", "NO DATA");
			model.addAttribute("price", "NO DATA");
		}
		return "web/product_detail.jsp";
	}
	@RequestMapping(value = { "/receiveData" }, method = RequestMethod.POST)
	public @ResponseBody String receviceData(WebRequest webRequest, Locale locale, Model model,HttpServletRequest re) {
		int pid=Integer.parseInt(webRequest.getParameter("pid"));
		String name=webRequest.getParameter("name");
		String mail=webRequest.getParameter("mail");
		String phone=webRequest.getParameter("phone");
		String quatity=webRequest.getParameter("quatity");
		String note=webRequest.getParameter("note");
		
		
		return String.valueOf(true);
	}
	public String[] loadContentPic(String dir) {
		File f = new File(dir);
		if (f.isDirectory()) {
			return f.list();
		}
		return null;
	}
}
