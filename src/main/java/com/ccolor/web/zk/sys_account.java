package com.ccolor.web.zk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ccolor.mybatis.bean.User;
import com.ccolor.mybatis.service.UserService;
/**
 * account page and edit page 
 * [特別注意] this page add edit_page's event , so does't have accountform
 * @author angle
 * @uncode utf-8
 */
public class sys_account extends Window {
	UserService us = (UserService) SpringUtil.getBean("UserService");
	public void init() {
		updateList();
	}

	public void add() {
		final Window window = (Window) Executions.createComponents("sys/account_form.zul", null, null);
		window.doModal();
		Button bn = (Button) window.getFellow("submit");
		final EventListener el = new EventListener() {
			@Override
			public void onEvent(Event e) throws Exception {
				if (Messagebox.ON_OK.equals(e.getName())) {
					User user = new User();
					user.setId(((Textbox) window.getFellow("name")).getValue());
					user.setPass(((Textbox) window.getFellow("password")).getValue());
					user.setDescript(((Textbox) window.getFellow("descript")).getValue());
					us.addUser(user);
					updateList();
					window.detach();
				}
			}
		};
		bn.addEventListener("onClick", new EventListener() {
			public void onEvent(Event event) throws Exception {
				clearMsg(window);
				if (checkdata(window)) {
					Messagebox.show("確定新增?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, el);
				}
			}
		});
	}

	public void edit() {
		Listbox lb = (Listbox) getFellow("account_list");
		if (lb.getSelectedItem() != null) {
			final Window window = (Window) Executions.createComponents("sys/account_form.zul", null, null);
			window.doModal();
			String id = ((Listcell) lb.getSelectedItem().getChildren().get(0)).getLabel();
			User user = us.getUser(id);
			((Textbox) window.getFellow("name")).setValue(user.getId());
			((Textbox) window.getFellow("descript")).setValue(user.getDescript());
			Button bn = (Button) window.getFellow("submit");
			final EventListener el = new EventListener() {
				@Override
				public void onEvent(Event e) throws Exception {
					if (Messagebox.ON_OK.equals(e.getName())) {
						User user = new User();
						user.setId(((Textbox) window.getFellow("name")).getValue());
						user.setPass(((Textbox) window.getFellow("password")).getValue());
						user.setDescript(((Textbox) window.getFellow("descript")).getValue());
						us.updateUser(user);
						updateList();
						window.detach();
					}
				}
			};
			bn.addEventListener("onClick", new EventListener() {
				public void onEvent(Event event) throws Exception {
					clearMsg(window);
					if (checkdata(window)) {
						Messagebox.show("確定修改?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
								el);
					}
				}
			});
		} else {
			Messagebox.show("請選擇對象");
		}
	}

	public void clearMsg(Window win) {
		((Label) win.getFellow("msg1")).setValue("");
		((Label) win.getFellow("msg2")).setValue("");
		((Label) win.getFellow("msg3")).setValue("");
	}

	public void updateList() {
		List<User> list = us.getAllUser();
		Listbox lb = (Listbox) getFellow("account_list");
		lb.getItems().clear();
		for (User us : list) {
			Listitem li = new Listitem();
			Listcell lc_id = new Listcell(us.getId());
			Listcell lc_des = new Listcell(us.getDescript());
			Listcell lc_pass = new Listcell();
			lc_pass.setVisible(false);
			li.appendChild(lc_id);
			li.appendChild(lc_des);
			li.appendChild(lc_pass);
			lb.appendChild(li);

		}
	}

	public boolean checkdata(Window win) {
		String str1 = ((Textbox) win.getFellow("password")).getValue();
		String str2 = ((Textbox) win.getFellow("re_password")).getValue();
		if ("".equals(((Textbox) win.getFellow("name")).getValue().trim())) {
			((Label) win.getFellow("msg1")).setValue("不可為空白");
		} else if ("".equals(str1.trim())) {
			((Label) win.getFellow("msg2")).setValue("不可為空白");
		} else if (!(str1).equals(str2) || "".equals(str2.trim())) {
			((Label) win.getFellow("msg3")).setValue("不可為空白,且與密碼必須相同");
		} else {
			return true;
		}
		return false;
	}
}
