package com.ccolor.web.zk;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.ccolor.mybatis.bean.PageControl;
import com.ccolor.mybatis.bean.V_product_price;
import com.ccolor.mybatis.bean.V_product_print;
import com.ccolor.mybatis.service.PageControlService;
import com.ccolor.mybatis.service.V_product_priceService;
import com.ccolor.mybatis.service.V_product_printService;
import com.ccolor.web.zk.model.AbstractForm;
import com.ccolor.web.zk.model.ZKmodel;

public class productForm extends AbstractForm {
	Map map = Executions.getCurrent().getArg();
	V_product_printService v_print = (V_product_printService) SpringUtil.getBean("V_product_printService");
	V_product_priceService v_price = (V_product_priceService) SpringUtil.getBean("V_product_priceService");
	V_product_print print_bean = new V_product_print();
	ArrayList<V_product_price> price_bean_arr = new ArrayList<V_product_price>();

	public void init() {
		int type = (Integer) map.get("type");
		setModel();// 查詢套版
		if (type == 2) {
			setData();
		}
	}

	public void submit() {
		int type = (Integer) map.get("type");
		if (type < 3) {
			PageControl pc = (PageControl) map.get("pc");
			print_bean.setSpId(pc.getSpid());
			setModelBean();
		}
		switch (type) {
		case 1:
			add();
			break;
		case 2:
			update();
			break;
		case 3:
			delete();
			break;
		default:
			break;
		}
		this.detach();
		Events.postEvent("onCreate", (Window) map.get("parent_win"), null);
	}

	public void setModelBean() {
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sdf.format(date);
		String img_dir = ZKmodel.productPath + now;
		String img_dir_b = img_dir + "/browser";
		print_bean.setMainPic(img_dir);
		print_bean.setContentPic(img_dir_b);
		print_bean.setName(ZKmodel.getValue(this, "name"));
		print_bean.setDescript(ZKmodel.getValue(this, "descript"));
		print_bean.setKeyword(ZKmodel.getValue(this, "keywords"));
		print_bean.setSpecification(ZKmodel.getValue(this, "spec"));
		print_bean.setSize(ZKmodel.getValue(this, "size"));
		print_bean.setBind(ZKmodel.getValue(this, "bind"));
		print_bean.setContentNum(ZKmodel.getValue(this, "contnent_num"));
		print_bean.setHeaving(ZKmodel.getValue(this, "heaving"));
		Vbox note_ = (Vbox) getFellow("note");
		StringBuilder temp_ = new StringBuilder();
		for (Component textbox : note_.getChildren()) {
			if (textbox instanceof Textbox && temp_.length() != 0) {
				temp_.append(ZKmodel.paserPattern + ((Textbox) textbox).getValue());
			} else if (textbox instanceof Textbox && temp_.length() == 0) {
				temp_.append(((Textbox) textbox).getValue());
			} else {
				Messagebox.show("doesn't support note pattern");
			}
		}
		;
		print_bean.setNote(temp_.toString());
		Rows rows = (Rows) getFellow("price_list");
		price_bean_arr.clear();
		for (int j = 2; j < rows.getChildren().size() - 1; j++) {
			Component row = rows.getChildren().get(j);
			if (row instanceof Row) {
				V_product_price price = new V_product_price();
				if (row.getChildren().get(0) instanceof Label) {
					price.setQuantity(1);

				} else if (row.getChildren().get(0) instanceof Textbox) {
					price.setQuantity(Integer.parseInt(((Textbox) row.getChildren().get(0)).getValue()));
				}
				price.setPrice(Integer.parseInt(((Textbox) row.getChildren().get(1)).getValue()));
				price_bean_arr.add(price);
			} else {
				Messagebox.show("doesn't support price pattern");
			}
		}
	}

	public boolean createMainImg(String img_dir) {
		return createMainImg(img_dir, true);
	}

	public boolean createMainImg(String img_dir, boolean isNew) {
		if (isNew)
			createFileDir(img_dir);
		Image img = (Image) getFellow("main_pic");
		org.zkoss.image.Image  img_c= img.getContent();
		if(img_c==null&&!"".equals(img.getSrc())) return true;
		return saveFile(img_c.getByteData(), img_dir + "/" + "main.jpg");
	}

	public boolean createBrowserImg(String img_dir_b) {
		return createBrowserImg(img_dir_b, true);
	}

	public boolean createBrowserImg(String img_dir_b, boolean isNew) {
		boolean b = true;
		if (isNew)
			createFileDir(img_dir_b);
		Div brow = (Div) getFellow("pic_conllection");
		int i = 1;
		for (Component img : brow.getChildren()) {
			if (img instanceof Image) {
				Image tempImg = (Image) img;
				org.zkoss.image.Image img_c = tempImg.getContent();
				if (img_c == null) {
					String[] tempSrc = tempImg.getSrc().split("/");
					String tempFileName = tempSrc[tempSrc.length - 1];
					renameFile(img_dir_b, tempFileName, i + ".jpg");
				} else {
					boolean temp = saveFile(img_c.getByteData(), img_dir_b + "/" + i + ".jpg");
					if (!temp) {
						b = temp;
					}
				}
				i++;
			}
		}
		return b;
	}

	private boolean renameFile(String img, String nameOld, String nameNew) {
		ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
		File file = new File(sc.getRealPath(img) + "/" + nameOld);
		File file2 = new File(sc.getRealPath(img) + "/" + nameNew);
		return file.renameTo(file2);
	}

	private boolean saveFile(final byte[] data, String img) {
		ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
		File file = new File(sc.getRealPath(img));
		FileOutputStream os = null;
		if (!file.exists()) {try {
			File dir = new File(file.getParent());
			dir.mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
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
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createFileDir(String str) {
		String filePath = str;
		ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
		File file = new File(sc.getRealPath(filePath));
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public void setModel() {

	}

	@Override
	public void setData() {
		int gid = Integer.parseInt((String) map.get("gid"));
		V_product_print pb = v_print.selectByPKey(gid);
		List<V_product_price> list = v_price.selectByPid(gid);
		ZKmodel.setValue(this, "name", pb.getName());
		ZKmodel.setValue(this, "descript", pb.getDescript());
		ZKmodel.setValue(this, "keywords", pb.getKeyword());
		ZKmodel.setValue(this, "spec", pb.getSpecification());
		ZKmodel.setValue(this, "size", pb.getSize());
		ZKmodel.setValue(this, "bind", pb.getBind());
		ZKmodel.setValue(this, "contnent_num", pb.getContentNum());
		ZKmodel.setValue(this, "heaving", pb.getHeaving());
		// main pic
		Image m_pic = ((Image) getFellow("main_pic"));
		m_pic.setSrc(pb.getMainPic() + "/main.jpg");
		m_pic.setAttribute("path", pb.getMainPic());
		m_pic.setWidth("200px");

		// content pic
		Div div = (Div) getFellow("pic_conllection");
		div.setAttribute("path", pb.getContentPic());
		String[] fileName = loadContentPic(pb.getContentPic());
		if (fileName != null) {
			for (String name : fileName) {
				Image img = new Image();
				img.setSrc(pb.getContentPic() + "/" + name);
				img.setWidth("30%");
				img.setStyle("margin:1%");
				div.appendChild(img);
			}
		}
		// note
		Vbox box = (Vbox) getFellow("note");
		String[] temp = pb.getNote().split(ZKmodel.getSpiltPatter());
		if (temp != null) {
			for (int i = 0; i < temp.length; i++) {
				if (i == 0) {
					((Textbox) box.getChildren().get(0)).setValue(temp[i]);
				} else {
					Textbox tb = new Textbox();
					tb.setValue(temp[i]);
					box.appendChild(tb);
				}

			}
		}
		// price setter
		Rows rows = (Rows) getFellow("price_list");
		for (V_product_price been : list) {
			if (been.getQuantity() == 1) {
				((Textbox) (rows.getChildren().get(2)).getChildren().get(1)).setValue(String.valueOf(been.getPrice()));
			} else {
				Row row = new Row();
				Textbox tb1 = new Textbox();
				Textbox tb2 = new Textbox();
				tb1.setValue(String.valueOf(been.getQuantity()));
				tb2.setValue(String.valueOf(been.getPrice()));
				row.appendChild(tb1);
				row.appendChild(tb2);
				rows.getChildren().add(3, row);
			}
		}

	}

	public String[] loadContentPic(String dir) {
		ServletContext sc = (ServletContext) Sessions.getCurrent().getWebApp().getNativeContext();
		File f = new File(sc.getRealPath(dir));
		if (f.isDirectory()) {
			return f.list();
		}
		return null;
	}

	@Override
	public void add() {
		try {
			v_print.addProduct(print_bean, price_bean_arr.toArray(new V_product_price[price_bean_arr.size()]));
			if (createMainImg(print_bean.getMainPic()) && createBrowserImg(print_bean.getContentPic())) {
				Messagebox.show("完成新增!");
			} else {
				throw new Exception("上傳失敗");
			}
		} catch (Exception e) {
			Messagebox.show("失敗!" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		int gid = Integer.parseInt((String) map.get("gid"));
		print_bean.setPid(gid);
		String main_p=(String) getFellow("main_pic").getAttribute("path");
		String content_p=(String) getFellow("pic_conllection").getAttribute("path");
		v_print.updateProduct(print_bean, price_bean_arr.toArray(new V_product_price[price_bean_arr.size()]));
		try {
			if (createMainImg(main_p, false) && createBrowserImg(content_p, false)) {
				Messagebox.show("完成上傳!");
			} else {
				throw new Exception("上傳失敗");
			}
		} catch (Exception e) {
			Messagebox.show("失敗!" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		final int gid = Integer.parseInt((String) map.get("gid"));
		Messagebox.show("確定刪除嗎??", "再次確認", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
							v_print.deleteProduct(gid);
						}
					}
				});
	}

}
