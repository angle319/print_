package com.ccolor.web.component;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import com.ccolor.mybatis.bean.PageControl;

public class MenuCOM {
	ArrayList list = null;
	String rootTag = "ul";
	String context = null;
	int id;

	public MenuCOM(ArrayList list, int id,String context) {
		this.list = list;
		this.id = id;
		this.context=context;
	}

	public String getMenuHtml() {
		return getMenuHtml("menu_size");
	}

	private String getMenuHtml(String rootId) {
		Document doc = DocumentHelper.createDocument();
		doc.addProcessingInstruction("XML", "");
		Element root = doc.addElement(rootTag);
		if (rootId != null)
			root.addAttribute("id", rootId);
		root.addAttribute("class", "nav nav-stacked nav-pills");
		root.addAttribute("style", "padding-top: 5%;padding-bottom: 5%;");
		for (Object obj : list) {
			PageControl temp = (PageControl) obj;
			if (temp.getSpid() == id && temp.getParentId() > 0) {
				setNode(root, temp.getParentId(), id);
			}
		}
		if (root.elements().size() == 0)
			return "";
		return root.asXML();
	}

	public Element setNode(Element base, int parent, int id) {
		Iterator it = list.iterator();
		DOMElement li = null;
		DOMElement ul = null;
		while (it.hasNext()) {
			PageControl pc = (PageControl) it.next();
			if (pc.getParentId() == parent) {
				li = new DOMElement("li");
				ul = new DOMElement("ul");
				Element a = new DOMElement("a");
				setMenuAtrr(a, pc);
				a.addText(pc.getName());
				DOMElement temp = (DOMElement) setNode(ul, pc.getSpid(), id);
				if (temp.getChildNodes().getLength() > 0) {
					li.addAttribute("class", "dropdown");
					a.addAttribute("class", "dropdown-toggle");
					a.addAttribute("data-toggle", "dropdown");
					a.addAttribute("role", "button");
					a.addAttribute("aria-expanded", "false");
					temp.addAttribute("class", "dropdown-menu");
					temp.addAttribute("role", "menu");
					base.add(li);
					li.add(a);
					li.add(temp);
					Element span = a.addElement("span");
					span.addAttribute("class", "caret");
				} else {
					base.add(li);
					li.add(a);
					if (pc.getSpid() == id)
						li.addAttribute("class", "active");
				}
			}
		}
		return base;
	}

	public void setMenuAtrr(Element e, PageControl pc) {
		if (pc.getTypeId() == 1&&"".equals(pc.getPath())) {
			e.addAttribute("href", context+"/content/" + pc.getSpid());
		}else if(pc.getTypeId()==2&&"".equals(pc.getPath())){
			e.addAttribute("href",  context+"/product/" + pc.getSpid());
		}else{
			e.addAttribute("href", context+"/content/" + pc.getSpid());
		}
	}
}
