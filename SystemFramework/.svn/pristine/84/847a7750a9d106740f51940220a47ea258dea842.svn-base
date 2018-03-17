package com.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.factory.ServiceFactory;
import com.model.Customer;
import com.service.CustomerService;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CustomerController extends GenericController {
	private CustomerService customerService = ServiceFactory.getCustomerServiceInstance();
	private File info;// 保存上传文件
	private InputStream data;

	public File getInfo() {
		return info;
	}

	public void setInfo(File info) {
		this.info = info;
	}

	public InputStream getData() {
		return data;
	}

	public void setData(InputStream data) {
		this.data = data;
	}

	/**
	 * 数据导入
	 * 
	 * @return
	 * @throws Exception
	 */
	public String importData() throws Exception {
		String result = "success";
		List<Customer> lists = new ArrayList<Customer>();
		try {
			Workbook book = Workbook.getWorkbook(this.info);
			Sheet sheet = book.getSheet(0); // 获得第一个工作表对象
			int rownum = sheet.getRows(); // 得到行数
			for (int i = 0; i < rownum; i++) {
				Customer obj = new Customer();
				obj.setId(sheet.getCell(0, i).getContents());
				obj.setName(sheet.getCell(1, i).getContents());
				obj.setTel(sheet.getCell(2, i).getContents());
				lists.add(obj);
			}
			book.close();
			this.customerService.insert(lists);
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
		this.data = new ByteArrayInputStream(result.getBytes("UTF-8"));
		return SUCCESS;
	}

	/**
	 * 数据导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportData() throws Exception {
		File file = new File("c:\\data.xls");
		try {
			/************************ 创建工作表 **********************/
			WritableWorkbook book = Workbook.createWorkbook(new File("c:\\data.xls"));
			WritableSheet sheet = book.createSheet("客户信息表", 0);
			/*****************************************************************/

			/************************** 设置表头 ****************************/
			sheet.addCell(new Label(0, 0, "编号"));
			sheet.addCell(new Label(1, 0, "姓名"));
			sheet.addCell(new Label(2, 0, "联系电话"));
			/*****************************************************************/

			/************************* 添加单元格数据 ***************************/
			List<Customer> lists = this.customerService.findAll();
			if (lists != null && lists.size() > 0) {
				for (int i = 0; i < lists.size(); i++) {
					Customer obj = lists.get(i);
					sheet.addCell(new Label(0, i + 1, obj.getId()));
					sheet.addCell(new Label(1, i + 1, obj.getName()));
					sheet.addCell(new Label(2, i + 1, obj.getTel()));
				}
			}
			/*****************************************************************/
			/* 写入数据并关闭文件 */
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.data = new FileInputStream(file);
		return "download";
	}
}
