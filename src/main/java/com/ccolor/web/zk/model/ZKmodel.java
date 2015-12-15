package com.ccolor.web.zk.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import com.ccolor.web.util.ImageUtil;

public class ZKmodel {
	static public String paserPattern="|,|";
	static public String productPath="/resources/image/product/";
	static public String getValue(Component com,String id){
		String value = null;
		if(com==null||"".equals(id)){
			throw new NullPointerException("物件不允許空值");
		}else if(com.getFellow(id) instanceof Textbox){
			value =((Textbox)com.getFellow(id)).getValue();
		}else if(com.getFellow(id) instanceof Listcell){
			value =((Listcell)com.getFellow(id)).getValue();
		}else if(com.getFellow(id) instanceof Listitem){
			value =((Listitem)com.getFellow(id)).getValue();
		}else if(com.getFellow(id) instanceof Label){
			value =((Label)com.getFellow(id)).getValue();
		}else if(com.getFellow(id) instanceof Textbox){
			value =((Textbox)com.getFellow(id)).getValue();
		}
		return value;
	}
	static public void setValue(Component com,String id,String obj){
		setObjValue(com,id,obj);
	}
	static public void setValue(Component com,String id,int obj){
		setObjValue(com,id,obj);
	}
	static public void setValue(Component com,String id,double obj){
		setObjValue(com,id,obj);
	}
	@SuppressWarnings("unused")
	static private void setObjValue(Component com,String id,Object obj){
		String value=null;
		value=String.valueOf(obj);
		if(com==null||"".equals(id)){
			throw new NullPointerException("物件不允許空值");
		}else if(com.getFellow(id) instanceof Textbox){
			((Textbox)com.getFellow(id)).setValue(value);
		}else if(com.getFellow(id) instanceof Listcell){
			((Listcell)com.getFellow(id)).setValue(value);
		}else if(com.getFellow(id) instanceof Listitem){
			((Listitem)com.getFellow(id)).setValue(value);
		}else if(com.getFellow(id) instanceof Label){
			((Label)com.getFellow(id)).setValue(value);
		}else if(com.getFellow(id) instanceof Textbox){
			((Textbox)com.getFellow(id)).setValue(value);
		}
	}
	
	static public String paserHtmlSign(String str){
		str.replace("\"", "&#34;");
		return str;
	
	}
	static public String getSpiltPatter(){
		return ZKmodel.paserPattern.replace("|", "\\|");
	}
	static public void setDynamicImage(Image img){
		setDynamicImage(img,-1);
	}
	static public void setDynamicImage(Image img,int width){
		ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
		AImage aimg;
		try {
			aimg = new AImage(sc.getRealPath(img.getSrc()));
			if(width!=-1&&img.getContent()==null){
				BufferedImage bi=ImageUtil.getScaledImage(aimg.getByteData(), width);
				img.setContent(bi);
			}else if(img.getContent()==null){
				img.setContent(aimg);
			}
		} catch (IOException e) {
			Logger.getLogger("顯示圖片資料找不到").error(e.getMessage());
			//e.printStackTrace();
		}
	}
	
}
