package com.ccolor.web.zk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import com.ccolor.mybatis.bean.User;
import com.ccolor.mybatis.service.UserService;
import com.ccolor.web.util.PathUtil;

import net.sourceforge.stripes.action.Message;

public class login extends Window {
	
	UserService us = (UserService) SpringUtil.getBean("UserService");

	public void submit() {
		Textbox name = (Textbox) getFellow("name");
		Textbox pa = (Textbox) getFellow("pass");
		String id = name.getValue();
		String pass = pa.getValue();
		Session session = Sessions.getCurrent();
		User user = us.getUser(id);
		name.setValue("");
		pa.setValue("");
		if (user != null && user.getPass().equals(pass)) {
			session.setAttribute("id", user.getId());
			session.setAttribute("pass", user.getPass());
			Executions.sendRedirect("../"+PathUtil.Main_menu);
		} else if (user != null && !user.getPass().equals(pass)) {
			Messagebox.show("密碼錯誤");
		} else {
			Messagebox.show("帳號密碼錯誤,三次鎖定!");
		}
		
	}
	
}
