package com.ccolor.web.zk;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Map;

import javax.servlet.ServletContext;

import org.zkforge.ckez.CKeditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class modelForm extends Window{
	postForm win = (postForm) Executions.getCurrent().getArg().get("window");
	public void init(){
		
	}
	public void setPicText(String str){
		try {
			String path = "/WEB-INF/views/edit/post/model/" + str + ".html";
			ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
			File file = new File(sc.getRealPath(path));
			InputStreamReader fr = new InputStreamReader(new FileInputStream(file),"UTF-8");
			BufferedReader br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        System.out.println(sb.toString());
			CKeditor cke = (CKeditor) win.getFellow("ed");
			cke.setValue(sb.toString());
			Events.postEvent("onChange", cke, null);
			this.detach();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
