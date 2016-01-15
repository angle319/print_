package com.ccolor.web.zk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.zkforge.ckez.CKeditor;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.bean.V_post;
import com.ccolor.mybatis.bean.V_postExample;
import com.ccolor.mybatis.service.V_postService;
import com.ccolor.web.zk.model.AbstractForm;

public class postForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	String def = "";
	final protected int add = 1;
	final protected int update = 2;
	int action;
	V_postService txtSer = (V_postService) SpringUtil.getBean("V_postService");
	Map map = Executions.getCurrent().getArg();
	CKeditor cke = null;
	int pid;
	int spid;
	String img_dir;
	public void init() {
		
		PageControl pc = (PageControl) map.get("pc");
		cke = (CKeditor) (this.getFellow("ed"));
		img_dir="/resources/image/"+pc.getSpid()+"/";
		createFileDir(img_dir);
		cke.setFilebrowserBrowseUrl(img_dir);
		cke.setFilebrowserUploadUrl(img_dir);
		cke.setFilebrowserFlashBrowseUrl(img_dir);
		/**
		 * db schema pkey must be change
		 */
		((Label)this.getFellow("location")).setValue(pc.getName());;
		V_postExample vpc=new V_postExample();
		vpc.createCriteria().andSpIdEqualTo(pc.getSpid());
		List<V_post> lvt = txtSer.getTextBeanByBean(vpc);
		
		if (lvt.size()==0) {
			action = add;
			cke.setValue(def);
			spid=pc.getSpid();
		} else {
			V_post vt=lvt.get(0);
			pid =vt.getPid();
			action = update;
			spid=pc.getSpid();
			cke.setValue(vt.getText());
		}
		Events.postEvent("onChange", cke, null);
	}

	public void save() {
		switch (action) {
		case add:
			add();
			break;
		case update:
			update();
			break;
		default:
			break;
		}
	}

	public void upload(final byte[] data, String path) {
		PageControl pc = (PageControl) map.get("pc");
		ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
		final File file = new File(sc.getRealPath(img_dir + path));
		final EventListener el = new EventListener() {
			@Override
			public void onEvent(Event ev) throws Exception {
				if (Messagebox.ON_OK.equals(ev.getName())) {
					saveFile(data, file);
				}
			}
		};
		if (!file.exists()) {
			saveFile(data, file);
		} else {
			Messagebox.show("已經有相同檔案,確定新增?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, el);
		}

	}
	public void createFileDir(String str){
		String filePath = str;
		ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
		File file = new File(sc.getRealPath(filePath));
		if(!file.exists())
		{
		    file.mkdirs();
		}
	}
	private void saveFile(final byte[] data, File file) {
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("無法找到該檔案");
			e.printStackTrace();
		}
		try {
			os.write(data);
		} catch (IOException e) {
			System.out.println("檔案寫入失敗!!");
			e.printStackTrace();
		}
		try {
			os.close();
			Messagebox.show("上傳成功!新增至圖庫!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void selectModel(){
		map.put("window", this);
		Window window = (Window) Executions.createComponents("post/model.zul", null, map);
		window.doModal();
		
	}

	@Override
	public void add() {
		V_post vt = new V_post();
		vt.setText(cke.getValue());
		vt.setSpId(spid);
		vt.setParentTag("");
		txtSer.addTextData(vt);
		Messagebox.show("新增完畢");
	}

	@Override
	public void update() {
		V_post vt = new V_post();
		vt.setText(cke.getValue());
		vt.setParentTag("");
		vt.setPid(pid);
		vt.setSpId(spid);
		txtSer.updateTextData(vt);
		Messagebox.show("存檔完畢");
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

	}
}
