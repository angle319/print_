package com.ccolor.web;

import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.bean.V_post;
import com.ccolor.mybatis.bean.V_product_price;
import com.ccolor.mybatis.service.PageControlService;
import com.ccolor.mybatis.service.UserService;
import com.ccolor.mybatis.service.V_postService;
import com.ccolor.mybatis.service.V_product_priceService;
import com.ccolor.web.component.MenuCOM;
import com.ccolor.web.component.NavbarCOM;
import com.ccolor.web.util.ModelDefine;
import com.ccolor.web.util.PathUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	@Resource
	@Qualifier("PageControlService")
	PageControlService pcs;
	@Resource
	@Qualifier("V_postService")
	V_postService vts;
	
	ArrayList pc_list = null;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "/home", "/index" }, method = RequestMethod.GET)
	public String home(WebRequest webRequest, Locale locale, Model model) {
		int spid = 1;
		PageControl pc = null;
		try {
			spid = Integer.parseInt(webRequest.getParameter("pid"));
		} catch (Exception e) {
		}
		pc = pcs.getByPK(spid);

		try {
			V_post vtx = new V_post();
			vtx = vts.getTextBeanBySPID(pc.getSpid());
			model.addAttribute("html_content", vtx.getText());
		} catch (IndexOutOfBoundsException e) {
			model.addAttribute("html_content", "NO DATA");
		}
		return "web/index.jsp";
	}
	@RequestMapping(value = {"/content/{id}" }, method = RequestMethod.GET)
	public String content(@PathVariable("id") String id,WebRequest webRequest, Locale locale, Model model) {
		PageControl pc = null;
		pc = pcs.getByPK(Integer.parseInt(id));
		try {
			V_post vtx = new V_post();
			vtx = vts.getTextBeanBySPID(pc.getSpid());
			model.addAttribute("html_content", vtx.getText());
		} catch (IndexOutOfBoundsException e) {
			model.addAttribute("html_content", "NO DATA");
		}
		if("".equals(pc.getPath())||pc.getPath()==null){
			return "web/content.jsp";
		}else{
			return "web/customize/"+pc.getPath()+".jsp";
		}
	}
	
	private String jsEXE(String js_code) {
		String start = "<script type=\"text/javascript\">";
		String end = "</script>";
		return start + js_code + end;
	}
	

}
