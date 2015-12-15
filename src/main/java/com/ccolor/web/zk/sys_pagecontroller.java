package com.ccolor.web.zk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.service.PageControlService;
import com.ccolor.mybatis.service.UserService;

public class sys_pagecontroller extends Window {

	private static final long serialVersionUID = 1L;

	PageControlService pcs = (PageControlService) SpringUtil.getBean("PageControlService");

	public void init() {
		Tree tr = (Tree) getFellow("tree");
		Treechildren trc = tr.getTreechildren();
		if (trc != null) {
			tr.removeChild(trc);
			trc.detach();
		}
		List list = pcs.getAll();
		tr.appendChild(treeData(list, -1));
	}

	public Treechildren treeData(List<PageControl> data, int parent) {
		Treechildren trcd = new Treechildren();
		Iterator<PageControl> iterator = data.iterator();
		while (iterator.hasNext()) {
			PageControl pc = iterator.next();

			if (pc.getParentId() == parent) {
				String popid = "pop_" + String.valueOf(pc.getSpid());
				Treeitem tri = new Treeitem();
				Treerow trr = new Treerow();
				Treecell tc = new Treecell();
				Treecell tc_des = new Treecell();
				tri.setContext(popid);
				tc.appendChild(rightMenu(popid, pc));
				tc.setLabel(String.valueOf(pc.getName()));
				tc_des.setLabel(String.valueOf(pc.getDescript()));
				trcd.appendChild(tri);
				trr.appendChild(tc);
				trr.appendChild(tc_des);
				tri.appendChild(trr);
				Treechildren temp = treeData(data, pc.getSpid());
				if (temp.getChildren().size() > 0)
					tri.appendChild(temp);
			}
		}
		return trcd;
	}

	private EventListener addevent(final int type) {
		EventListener<Event> el = new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				PageControl pc = (PageControl) event.getTarget().getParent().getAttribute("pc_bean");
				Map map = new HashMap();
				map.put("bean", pc);
				map.put("type", type);
				map.put("window", getFellow("pgctrl"));
				final Window window = (Window) Executions.createComponents("sys/page_controller_form.zul", null, map);
				window.doModal();
			}
		};
		return el;
	}

	private Menupopup rightMenu(String pop_id, PageControl pc) {
		Menupopup pop = new Menupopup();
		pop.setId(pop_id);
		pop.setAttribute("pc_bean", pc);
		Menuitem mi_add = new Menuitem();
		mi_add.setLabel("新增子項");
		mi_add.addEventListener("onClick", addevent(1));
		Menuitem mi_update = new Menuitem();
		mi_update.setLabel("修改");
		mi_update.addEventListener("onClick", addevent(2));
		Menuitem mi_delete = new Menuitem();
		mi_delete.setLabel("刪除");
		mi_delete.addEventListener("onClick", addevent(3));
		mi_delete.setVisible(false);
		pop.appendChild(mi_add);
		if (pc.getSpid() > 0) {
			pop.appendChild(mi_update);
			pop.appendChild(mi_delete);
		}
		return pop;
	}
}
