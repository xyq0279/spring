package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.service.PropertieService;
import com.jt.common.vo.PicUploadResult;

@Controller
public class FileUploadController {
	
	private static final Logger logger = Logger.getLogger(FileUploadController.class);
	@Autowired
	private PropertieService propertisService;
	
	/**
	 * 文件上传的步骤
	 * 1、采用文件正确的接收方式
	 * 2、判断是否为一个图片，0表示无异常，1代表异常（jpg|gif|png）
	 * 3、判断是不是“正经”的图片，判断是否有width和height
	 * 4、编辑磁盘目录		D:/dayCode/project/project/jt-upload/images/yyyy/MM/dd/HH/mm/文件名称
	 * 5、编辑相对路径	url:image.jt.com/images/yyyy/MM/dd/HH/mm/文件名称
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pic/upload")
	public PicUploadResult fileUpload(MultipartFile uploadFile){
		PicUploadResult result = new PicUploadResult();
		//1、获取文件名称
		String fileName = uploadFile.getOriginalFilename();
		//获取后缀名称
		String endName = fileName.substring(fileName.lastIndexOf("."));
		//3、判断图片格式
		if(!endName.matches("^\\.(jpg|png|gif)$")){
			result.setError(1);
			logger.error("~~~~~~~~~格式不正确");
			return result;
		}
		//4、判断是不是“正经”的图片，判断是否有width和height
		try {
			BufferedImage image = ImageIO.read(uploadFile.getInputStream());
			//获取高度和宽度，获取有问题时会报异常
			int width = image.getWidth();
			int height = image.getHeight();
			result.setWidth(width+"");
			result.setHeight(height+"");
			String locationPath = propertisService.REPOSITORY_PATH+"images/";
			String dataPath = new SimpleDateFormat("yyyy/MM/dd/HH/mm/").format(new Date());
			String urlPath = propertisService.IMAGE_BASE_URL+"images/";
			locationPath += dataPath+"/"+fileName;
			urlPath += dataPath+"/"+fileName;
			File newFile = new File(locationPath);
			if(!newFile.exists()){
				newFile.mkdirs();//创建文件夹
			}
			//将文件写入
			uploadFile.transferTo(newFile);
			result.setUrl(urlPath);
			logger.info("~~~~~~文件成功写入"+locationPath);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("~~~~~~~~~~~~该文件是非法文件");
			result.setError(1);
			return result;
		}
		return result;

		
		/*
		 * 1、将文件写入磁盘
		 * 2、
		 * 3、
		 
		try {
			file.transferTo(new File(""+file.getOriginalFilename()));
			return result;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
	}
}
