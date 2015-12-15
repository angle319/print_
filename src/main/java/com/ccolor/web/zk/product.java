package com.ccolor.web.zk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.ccolor.mybatis.bean.V_product_print;
import com.ccolor.mybatis.service.V_product_printService;
import com.ccolor.web.zk.model.AbstractForm;
import com.ccolor.web.zk.model.ZKmodel;

public class product extends AbstractForm {
	V_product_printService vpp = (V_product_printService) SpringUtil.getBean("V_product_printService");
	Map map = Executions.getCurrent().getArg();

	public void init() {
		map.put("parent_win", this);
		Listbox listbox = (Listbox) getFellow("list");
		listbox.getItems().clear();
		listbox.clearSelection();
		setData();
	}

	@Override
	public void setData() {
		List<V_product_print> list = vpp.selectAll();
		Listbox listbox = (Listbox) getFellow("list");
		for (V_product_print vppbeen : list) {
			Listitem listitem = new Listitem();
			Listcell lc1 = new Listcell();
			Listcell lc2 = new Listcell();
			Listcell lc3 = new Listcell();
			Listcell lc4 = new Listcell();
			Listcell lc5 = new Listcell();
			Image img = new Image();
			img.setSrc(vppbeen.getMainPic() + "/main.jpg");
			img.setWidth("60px");
			ZKmodel.setDynamicImage(img, 100);
			lc1.setLabel(String.valueOf(vppbeen.getPid()));
			lc2.appendChild(img);
			lc3.setLabel(vppbeen.getName());
			lc4.setLabel(vppbeen.getDescript());
			lc5.setLabel(vppbeen.getKeyword());
			lc1.setVisible(false);
			listitem.appendChild(lc1);
			listitem.appendChild(lc2);
			listitem.appendChild(lc3);
			listitem.appendChild(lc4);
			listitem.appendChild(lc5);
			listbox.appendChild(listitem);
		}

	}

	@Override
	public void add() {
		map.put("type", 1);
		Window window = (Window) Executions.createComponents("product/product_detail.zul", null, map);
		window.doModal();
	}

	@Override
	public void update() {
		Listbox listbox = (Listbox) getFellow("list");
		if (listbox.getSelectedIndex() == -1) {
			Messagebox.show("尚未選擇對象");
		} else {
			String gid = ((Listcell) listbox.getSelectedItem().getChildren().get(0)).getLabel();
			map.put("type", 2);
			map.put("gid", gid);
			Window window = (Window) Executions.createComponents("product/product_detail.zul", null, map);
			window.doModal();
		}
	}

	@Override
	public void delete() {
		Listbox listbox = (Listbox) getFellow("list");

		if (listbox.getSelectedItem() == null) {
			Messagebox.show("沒有選擇物件!");
		} else {
			String gid = ((Listcell) listbox.getSelectedItem().getChildren().get(0)).getLabel();
			map.put("type", 3);
			map.put("gid", gid);
			productForm window = (productForm) Executions.createComponents("product/product_detail.zul", null, map);
			window.submit();
		}
	}
}
