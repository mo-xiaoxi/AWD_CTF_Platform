package com.bupt.utils;

import com.bupt.domain.Profile;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 */
public class ProfileExcelExportUtils {
	private static final String[] title = {
			"编号","姓名","性别","出生年月","省份","民族","政治面貌","身份证号",
			"类型","学校","年级","通信地址","邮编","联系电话","身高","体重","邮箱",
			"父亲姓名","父亲职务","父亲联系电话","母亲姓名","母亲职务","母亲联系电话","兴趣爱好",
			"个人描述信息"};//标题名称
	private static HSSFWorkbook workbook = null;
	private static HSSFCellStyle borderStyle = null;
	private static HSSFCellStyle pureTextCellStyle = null;

	/**
	 * @param filePath 生成excel的文件路径
	 * @param profileList 数据源信息
	 * @return excel文件
	 */
	public static File generateExcel(String filePath,List<Profile> profileList) {
		File file = null;
		FileOutputStream fileOutputStream = null;
		try {
			//创建工作簿
			workbook = new HSSFWorkbook();
			//创建Sheet
			HSSFSheet sheet = workbook.createSheet();
			setColumeWidth(sheet);
			//创建第一行
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;

			//设置字体，样式格式
			HSSFCellStyle style = setStyleAndFont();

			//第一行设置列名
			for(int i=0;i<title.length;i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
				cell.setCellValue(title[i]);
			}

			//下面追加数据
			int num = 1;
			for(Profile profile:profileList) {
				addOneProfile(num++,sheet,profile);
			}

			file = new File(filePath);
			file.createNewFile();
			fileOutputStream = new FileOutputStream(file);
			//写入数据
			workbook.write(fileOutputStream);
			//关闭
			workbook.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	private static HSSFCellStyle setStyleAndFont() {
		//基础字体格式
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");//宋体
		font.setFontHeightInPoints((short) 10);// 设置字体大小

		//设置格式为基础样式格式
		borderStyle = workbook.createCellStyle();
		borderStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		borderStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		borderStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		borderStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		borderStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		borderStyle.setFont(font);

		//设置格式为文本格式 的样式
		pureTextCellStyle = workbook.createCellStyle();
		HSSFDataFormat format = workbook.createDataFormat();
		pureTextCellStyle.setDataFormat(format.getFormat("@"));
		pureTextCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		pureTextCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		pureTextCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		pureTextCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		pureTextCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		pureTextCellStyle.setFont(font);

		//设置标题行样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		style.setVerticalAlignment(HSSFCellStyle.BORDER_THIN);//水平居中
		HSSFFont fontTitle = workbook.createFont();
		fontTitle.setFontName("宋体");//宋体
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		fontTitle.setFontHeightInPoints((short) 12);// 设置字体大小
		style.setFont(fontTitle);
		return style;
	}

	/**
	 *
	 */
	private static void setColumeWidth(HSSFSheet sheet) {
		sheet.setColumnWidth(0, 6 * 256);//编号
		sheet.setColumnWidth(1, 10 * 256);//姓名
		sheet.setColumnWidth(2, 6 * 256);//性别
		sheet.setColumnWidth(3, 12 * 256);//出生年月
		sheet.setColumnWidth(4, 12 * 256);//省份
		sheet.setColumnWidth(5, 8 * 256);//民族
		sheet.setColumnWidth(6, 12 * 256);//政治面貌
		sheet.setColumnWidth(7, 22 * 256);//身份证号
		sheet.setColumnWidth(8, 10 * 256);//类型
		sheet.setColumnWidth(9, 20 * 256);//学校
		sheet.setColumnWidth(10, 10 * 256);//年级
		sheet.setColumnWidth(11, 25 * 256);//通信地址
		sheet.setColumnWidth(12, 10 * 256);//邮编
		sheet.setColumnWidth(13, 15 * 256);//联系电话
		sheet.setColumnWidth(14, 6 * 256);//身高
		sheet.setColumnWidth(15, 6 * 256);//体重
		sheet.setColumnWidth(16, 20 * 256);//邮箱
		sheet.setColumnWidth(17, 10 * 256);//父亲姓名
		sheet.setColumnWidth(18, 15 * 256);//父亲职务
		sheet.setColumnWidth(19, 15 * 256);//父亲联系电话
		sheet.setColumnWidth(20, 10 * 256);//母亲姓名
		sheet.setColumnWidth(21, 15 * 256);//母亲职务
		sheet.setColumnWidth(22, 15 * 256);//母亲联系电话
		sheet.setColumnWidth(23, 40 * 256);//兴趣爱好
		sheet.setColumnWidth(24, 80 * 256);//个人描述信息
	}

	/**
	 */
	private static void addOneProfile(int num,HSSFSheet sheet, Profile profile){


		int count = 0;
		HSSFRow row = sheet.createRow(num);
		HSSFCell cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(String.valueOf(num));//编号
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getName());//姓名
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getGender());//性别
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getBirthday());//出生年月
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getProvince());//省份
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getNation());//民族
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getPolitics_status());//政治面貌
		cell = row.createCell(count++);
		cell.setCellStyle(pureTextCellStyle);//身份证号设置为文本格式
		cell.setCellValue(profile.getIDnumber());//身份证号码
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getType());//类型(文科，理科)
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getSchool());//学校
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getGrade());//年级
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getAddress());//通信地址
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getPostcode());//邮编
		cell = row.createCell(count++);
		cell.setCellStyle(pureTextCellStyle);//电话设置为文本格式
		cell.setCellValue(profile.getPhone());//联系电话
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getHeight());//身高
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getWeight());//体重
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getMail());//邮箱
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getFather_name());//父亲姓名
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getFather_job());//父亲职务
		cell = row.createCell(count++);
		cell.setCellStyle(pureTextCellStyle);//电话设置为文本格式
		cell.setCellValue(profile.getFather_phone());//父亲联系电话
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getMother_name());//母亲姓名
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getMother_job());//母亲职务
		cell = row.createCell(count++);
		cell.setCellStyle(pureTextCellStyle);//电话设置为文本格式
		cell.setCellValue(profile.getMother_phone());//母亲联系电话
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getHobby());//兴趣爱好
		cell = row.createCell(count++);
		cell.setCellStyle(borderStyle);
		cell.setCellValue(profile.getDescription());//个人描述信息
	}
}