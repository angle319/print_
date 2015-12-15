package com.ccolor.web.zk;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.service.PageControlService;
import com.ccolor.web.zk.model.AbstractForm;

public class sys_pagecontrollerForm extends AbstractForm{
	final private int add = 1;
	final private int update = 2;
	final private int delete = 3;
	int type = 0;
	PageControl pc = null;
	Window window=null;
	Map map = Executions.getCurrent().getArg();
	PageControlService pcs = (PageControlService) SpringUtil.getBean("PageControlService");
	public void init() {
		type = (Integer) map.get("type");
		pc = (PageControl) map.get("bean");
		window = (Window) map.get("window");
		setDefault();
	}

	public void setDefault() {
		Button btn = (Button) getFellow("submit");
		EventListener<Event> event_l = null;
		switch (type) {
		case add:
			Checkbox chkb=(Checkbox) getFellow("vsb");
			chkb.setChecked(true);
			Listbox listbox=(Listbox) getFellow("kind");
			listbox.setSelectedIndex(0);
			event_l = new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					add();
				}
			};
			break;
		case update:
			setData();
			event_l = new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					update();
				}
			};
			break;

		case delete:
			event_l = new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					delete();
				}
			};
			break;

		default:
			break;
		}
		btn.addEventListener("onClick", event_l);
	}
	public void renderParentList() {
		Window main=(Window) Executions.getCurrent().getDesktop().getPage("main_page").getFellow("main_");
		Events.postEvent("onCreate", window, null);
		Events.postEvent("onCreate", main, null);
	}
	@Override
	public void setData() {
		Textbox name=(Textbox)getFellow("name");
		Textbox descript=(Textbox)getFellow("descript");
		Textbox path=(Textbox)getFellow("path");
		Listbox listb=(Listbox) getFellow("kind");
		Checkbox chkb=(Checkbox) getFellow("vsb");
		name.setValue(pc.getName());
		path.setValue(pc.getPath());
		descript.setValue(pc.getDescript());
		listb.setSelectedIndex(pc.getTypeId()-1);
		chkb.setChecked(pc.getVisible());
	}

	@Override
	public void add() {
		PageControl _pc=new PageControl();
		_pc.setParentId(pc.getSpid());
		_pc.setName(((Textbox)getFellow("name")).getValue());
		_pc.setDescript(((Textbox)getFellow("descript")).getValue());
		_pc.setPath(((Textbox)getFellow("path")).getValue());
		Listbox listb=(Listbox) getFellow("kind");
		_pc.setTypeId(Integer.parseInt( (String) listb.getSelectedItem().getValue()));
		Checkbox chkb=(Checkbox) getFellow("vsb");
		_pc.setVisible(chkb.isChecked());
		pcs.addPageControll(_pc);
		renderParentList();
		this.detach();
	}

	@Override
	public void update() {
		PageControl _pc=new PageControl();
		_pc.setSpid(pc.getSpid());
		_pc.setParentId(pc.getParentId());
		_pc.setName(((Textbox)getFellow("name")).getValue());
		_pc.setDescript(((Textbox)getFellow("descript")).getValue());
		_pc.setPath(((Textbox)getFellow("path")).getValue());
		Listbox listb=(Listbox) getFellow("kind");
		_pc.setTypeId(Integer.parseInt( (String) listb.getSelectedItem().getValue()));
		Checkbox chkb=(Checkbox) getFellow("vsb");
		_pc.setVisible(chkb.isChecked());
		pcs.updatePageControll(_pc);
		renderParentList();
		this.detach();
	}

	@Override
	public void delete() {
		

	}
	

}
