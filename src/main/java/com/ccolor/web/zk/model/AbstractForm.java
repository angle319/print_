package com.ccolor.web.zk.model;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zul.Window;

public abstract class AbstractForm extends Window{
	public AbstractForm() {
		
	}
	public abstract void setData();
	public abstract void add();
	public abstract void update();
	public abstract void delete();
}
