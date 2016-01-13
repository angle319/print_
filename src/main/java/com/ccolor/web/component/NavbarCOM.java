package com.ccolor.web.component;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import com.ccolor.mybatis.bean.PageControl;

public class NavbarCOM {
	ArrayList list = null;
	String rootTag = "div";
	String context = null;
	int parentId;

	public NavbarCOM(ArrayList<Object> data,int parentId,String context) {
		this.list = data;
		this.parentId=parentId;
		this.context=context;
	}
	public String getNavbarHtml() {
		return getNavbarHtml("bs-navbar-collapse");
	}
	public String getNavbarHtml(String rootId) {
		Document doc = DocumentHelper.createDocument();
		doc.addProcessingInstruction("XML", "");
		Element root=doc.addElement(rootTag);
		Element ul = root.addElement("ul");
		if(rootId!=null)root.addAttribute("id", rootId);
		root.addAttribute("class", "collapse navbar-collapse");
		ul.addAttribute("class", "nav navbar-nav");
		setNode(ul, parentId);
		return root.asXML();

	}

	public String getRootTag() {
		return rootTag;
	}

	public Element setNode(Element base, int parent) {
		Iterator it = list.iterator();
		DOMElement li=null;
		DOMElement ul=null;
		while (it.hasNext()) {
			PageControl pc = (PageControl) it.next();
			if (pc.getParentId() == parent) {
				li=new DOMElement("li");
				ul=new DOMElement("ul");
				Element a =new DOMElement("a");
				setMenuAtrr(a, pc);
				a.addText(pc.getName());
				DOMElement temp=(DOMElement)setNode(ul, pc.getSpid());
				if (temp.getChildNodes().getLength()>0) {
					li.addAttribute("class", "dropdown");
					a.addAttribute("class","dropdown-toggle");
					a.addAttribute("data-toggle","dropdown");
					a.addAttribute("role","button");
					a.addAttribute("aria-expanded","false");
					temp.addAttribute("class", "dropdown-menu");
					temp.addAttribute("role", "menu");
					base.add(li);
					li.add(a);
					li.add(temp);
					Element span=a.addElement("span");
					span.addAttribute("class", "caret");
				}else{
					base.add(li);
					li.add(a);
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
			e.addAttribute("href", context+"/"+pc.getPath() + "/" + pc.getSpid());
		}
	}
	/* nuit test
	 * public void main(String[] args) {
		temp_list = new ArrayList();

		temp_list.add(pc(0, 1, "1", "1"));
		temp_list.add(pc(0, 2, "2", "2"));
		temp_list.add(pc(1, 3, "3", "3"));
		temp_list.add(pc(1, 4, "4", "4"));
		temp_list.add(pc(3, 6, "3", "3"));
		temp_list.add(pc(3, 7, "4", "4"));
		temp_list.add(pc(2, 5, "5", "5"));
		System.out.println(getNavbarHtml());
	}

	private PageControl pc(int p, int self, String name, String path) {
		PageControl pc = new PageControl();
		pc.setParentId(p);
		pc.setSpid(self);
		pc.setName(name);
		pc.setPath(path);
		return pc;
	}*/
}
