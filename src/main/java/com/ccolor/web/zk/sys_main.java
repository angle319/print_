package com.ccolor.web.zk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonModel;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.service.PageControlService;
import com.ccolor.web.util.ModelDefine;
import com.ccolor.web.zk.model.ZKmodel;
/**
 * 系統啟始頁面
 * @author angle319
 *
 */
public class sys_main extends Window {
	PageControlService pcs = (PageControlService) SpringUtil.getBean("PageControlService");

	public void init() {
		setDefaultData();
		//驗證帳號,公用
		//seesinCheck();
	}

	private void setDefaultData() {
		List<PageControl> list = pcs.getAll();
		// treeMenu(list,0);
		Menubar menu = (Menubar) getFellow("menubar");
		Iterator it=menu.getChildren().iterator();
		while(it.hasNext()) {
			Component com=(Component) it.next();
			if (!(com.getId()).equals("sys_maintance")) {
				it.remove();
				com.detach();
			}
		}
		treeMenu(menu, list, 0);
	}

	private Component treeMenu(Component com, List<PageControl> data, int parent) {
		Iterator<PageControl> iterator = data.listIterator();
		List<Component> list = new ArrayList<Component>();
		while (iterator.hasNext()) {
			PageControl pc = iterator.next();
			if (pc.getParentId() == parent) {
				String menuid = "menu_" + String.valueOf(pc.getSpid());
				Menu temp = new Menu();
				Menupopup menupop = new Menupopup();
				Component com_tmp = null;
				temp.appendChild(menupop);
				Component com_select = treeMenu(temp, data, pc.getSpid());
				if (com_select instanceof Menu
						&& ((Menupopup) com_select.getChildren().get(0)).getChildren().size() > 0) {
					temp.setLabel(pc.getName());
					temp.setAttribute("pageCtrl", pc);
					//temp.addEventListener("onClick", evnt());
					com_tmp = com_select;
				} else {
					Menuitem menuitem = new Menuitem();
					menuitem.setLabel(pc.getName());
					menuitem.setAttribute("pageCtrl", pc);
					menuitem.addEventListener("onClick", evnt());
					com_tmp = menuitem;
				}
				if (com instanceof Menubar) {
					com.appendChild(com_tmp);
				} else {
					com.getChildren().get(0).appendChild(com_tmp);
				}
			}
		}
		return com;
	}

	private EventListener<Event> evnt() {
		EventListener<Event> eventListener = new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				Div content= (Div) getFellow("content");
				PageControl pc=(PageControl) event.getTarget().getAttribute("pageCtrl");
				int type=pc.getTypeId();
				String src="";
				Component com=null;
				Map map=new HashMap();
				map.put("pc", pc);
				Window window;
				for(Component c:content.getChildren()){
					c.detach();
				}
				switch (type) {
				case ModelDefine.postModel:
					window = (Window) Executions.createComponents("post/theory.zul", null, map);
					com=window;
					break;
				case ModelDefine.productModel:
					window = (Window) Executions.createComponents("product/product.zul", null, map);
					com=window;
					break;
				default:
					break;
				}
				try{
					content.appendChild(com);
				}catch(NullPointerException e){
					Messagebox.show("沒有適合的模組");
				}
			}
		};
		return eventListener;
	}
	public void sys_page(String str) {
		Div content= (Div) getFellow("content");
		final Window window = (Window) Executions.createComponents(str, null, null);
		for(Component c:content.getChildren()){
			c.detach();
		}
		content.appendChild(window);
	}
}
