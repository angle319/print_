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
import com.ccolor.mybatis.service.PageControlService;
import com.ccolor.mybatis.service.V_postService;
import com.ccolor.mybatis.service.V_product_priceService;
import com.ccolor.web.component.MenuCOM;
import com.ccolor.web.component.NavbarCOM;
import com.ccolor.web.util.PathUtil;

@Controller
public class ComponentController {
	@Resource
	@Qualifier("PageControlService")
	PageControlService pcs;
	ArrayList pc_list = null;
	
	@RequestMapping(value = { "/navbar" }, method = RequestMethod.GET)
	public String naverbar(Locale locale, Model model) {
		setPageData();
		NavbarCOM control = new NavbarCOM(this.pc_list, 0);
		model.addAttribute("navbar", control.getNavbarHtml());
		return "web/component/navbar.jsp";
	}

	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String menu(WebRequest webRequest, Locale locale, Model model) {
		int spid = -1;
		setPageData();
		try {
			spid = Integer.parseInt(webRequest.getParameter("pid"));
		} catch (Exception e) {
		}
		MenuCOM control = new MenuCOM(pc_list, spid);
		model.addAttribute("menu", control.getMenuHtml());
		return "web/component/menu.jsp";
	}

	@RequestMapping(value = { "/bottom" }, method = RequestMethod.GET)
	public String bottom(Locale locale, Model model) {
		return "web/component/bottom.jsp";
	}

	@RequestMapping(value = PathUtil.Login, method = RequestMethod.GET)
	public String redirect_login(Locale locale, Model model) {
		return zk_prefix(PathUtil.Login);
	}

	@RequestMapping(value = PathUtil.Main_menu, method = RequestMethod.GET)
	public String redirect_main(Locale locale, Model model) {

		return zk_prefix(PathUtil.Main_menu);
	}
	@RequestMapping(value = {"/edit/component/{id}"}, method = RequestMethod.GET)
	public String getHtmlComponent(@PathVariable("id") String id, Model model) {
		return "edit/post/model/"+id+".html";
	}
	public String zk_prefix(String str) {
		return str + ".zul";
	}
	private void setPageData() {
		if (this.pc_list == null)
			pc_list = (ArrayList) pcs.getExceptRoot();
	}
}
