package com.api.lebooo.repair.api.controller;

import com.api.lebooo.enums.CodeEnum;
import me.fishlord.common.result.ErrorCode;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 文件上传
 * @author fishlord
 *
 */
@Controller
@RequestMapping(value = "/api")
public class FileApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileApiController.class);
	
	@Value("${upload.filepath}")
	private String uediterFilepath;
	
	/**
	 * 上传
	 * @return
	 * type： 0 购买凭证图片，1 故障现象图片
	 */
    @RequestMapping("/up/upload")
    @ResponseBody
	public ResultEntity upload(@RequestParam(value = "upfile", required = true) MultipartFile file, String type){

    	ResultEntity resultEntity = new ResultEntity();
		if (StringUtils.isBlank(type)){
			resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
			resultEntity.setData(null);
			return resultEntity;
		}
		File rootPath = new File(uediterFilepath);//设置的保存路径
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String res = sdf.format(new Date());
		//原始名称
		String originalFilename = file.getOriginalFilename();
		//文件名
		String fileName = res + RandomStringUtils.randomAlphanumeric(8) + originalFilename.substring(originalFilename.lastIndexOf("."));
		//创建年件夹
		Calendar date = Calendar.getInstance();

		if ("0".equals(type)){
			File dateDirs = new File(date.get(Calendar.YEAR)
					+ File.separator + (date.get(Calendar.MONTH) + 1) + File.separator + "01");
			//新⽂件
			File newFile = new File(rootPath + File.separator + dateDirs + File.separator + fileName);
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			try {
				String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + "01" + "/" + fileName;
				file.transferTo(new File(rootPath + "/" + fileUrl));
				resultEntity.setData("file/image/"+fileUrl);
				resultEntity.setCode(ErrorCode.SUCCESS);
			}catch (IOException e) {
				logger.error("文件保存失败",e);
				resultEntity.setCode(ErrorCode.ERROR);
				resultEntity.setMsg("文件保存失败");
			}
		}else if ("1".equals(type)){
			File dateDirs = new File(date.get(Calendar.YEAR)
					+ File.separator + (date.get(Calendar.MONTH) + 1) + File.separator + "02");
			//新⽂件
			File newFile = new File(rootPath + File.separator + dateDirs + File.separator + fileName);
			if (!newFile.getParentFile().exists()) {
				newFile.getParentFile().mkdirs();
			}
			try {
				String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + "02" + "/" + fileName;
				file.transferTo(new File(rootPath + "/" + fileUrl));
				resultEntity.setData("file/image/"+fileUrl);
				resultEntity.setCode(CodeEnum.SUCCESS.getCode());
			}catch (IOException e) {
				logger.error("文件保存失败",e);
				resultEntity.setCode(CodeEnum.ERROR.getCode());
				resultEntity.setMsg("文件保存失败");
			}
		}


		return resultEntity;
	}

	/**
	 * 批量上传
	 * @return
	 * type： 0 购买凭证图片，1 故障现象图片
	 */
	@RequestMapping("/up/mulUpload")
	@ResponseBody
    public ResultEntity multipartFile(@RequestParam(value = "upfile", required = true) MultipartFile[] files, String type){

		ResultEntity resultEntity = new ResultEntity();
		if (StringUtils.isBlank(type)){
			resultEntity.setCode(CodeEnum.SUCCESS_NOT_DATE.getCode());
			resultEntity.setData(null);
			return resultEntity;
		}
    	List<String> list = new ArrayList<>();
    	for (MultipartFile file : files) {
			 File rootPath = new File(uediterFilepath);//设置的保存路径
			 SimpleDateFormat sdf = new SimpleDateFormat("dd");
			 String res = sdf.format(new Date());
			 //原始名称
			 String originalFilename = file.getOriginalFilename();
			 //文件名
			 String fileName = res + RandomStringUtils.randomAlphanumeric(8) + originalFilename.substring(originalFilename.lastIndexOf("."));

			 if ("0".equals(type)){
				 //创建年件夹
				 Calendar date = Calendar.getInstance();
				 File dateDirs = new File(date.get(Calendar.YEAR)
						 + File.separator + (date.get(Calendar.MONTH) + 1) + File.separator + "01");
				 //新⽂件
				 File newFile = new File(rootPath + File.separator + dateDirs + File.separator + fileName);
				 if (!newFile.getParentFile().exists()) {
					 newFile.getParentFile().mkdirs();
				 }
				 try {
					 String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + "01" + "/" + fileName;
					 file.transferTo(new File(rootPath + "/" + fileUrl));
					 list.add("file/image/"+fileUrl);
				 }catch (IOException e) {
					 logger.error("文件保存失败",e);
					 resultEntity.setCode(ErrorCode.ERROR);
					 resultEntity.setMsg("文件保存失败");
					 return resultEntity;
				 }
			 }else if ("1".equals(type)) {
				 //创建年件夹
				 Calendar date = Calendar.getInstance();
				 File dateDirs = new File(date.get(Calendar.YEAR)
						 + File.separator + (date.get(Calendar.MONTH) + 1) + File.separator + "02");
				 //新⽂件
				 File newFile = new File(rootPath + File.separator + dateDirs + File.separator + fileName);
				 if (!newFile.getParentFile().exists()) {
					 newFile.getParentFile().mkdirs();
				 }
				 try {
					 String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + "02" + "/" + fileName;
					 file.transferTo(new File(rootPath + "/" + fileUrl));
					 list.add("file/image/"+fileUrl);
				 }catch (IOException e) {
					 logger.error("文件保存失败",e);
					 resultEntity.setCode(CodeEnum.ERROR.getCode());
					 resultEntity.setMsg("文件保存失败");
					 return resultEntity;
				 }
			 }
		}
		resultEntity.setCode(CodeEnum.SUCCESS.getCode());
    	resultEntity.setData(list);
    	return resultEntity;

    }

    /*@RequestMapping("/file/eupload")
    @ResponseBody
	public ResultEntity eupload(@RequestParam(value = "file", required = true) CommonsMultipartFile file) throws Exception{
    	ResultEntity resultEntity = new ResultEntity();
    	
		 try {
			 Workbook workbook = new XSSFWorkbook(file.getInputStream()); 
			 Sheet sheet = workbook.getSheetAt(0);  
			 int rowNum = sheet.getLastRowNum();
			 
			 String res="";
			 for (int i = 1; i <= rowNum; i++) {
				 Row row = sheet.getRow(i);
				 String code = row.getCell(0).getStringCellValue();
				 String time = row.getCell(1).getStringCellValue();
				 res+=(code+","+time+"|");
			 }
		String[] arrStrings = res.split("\\|");
			for (String string : arrStrings) {
				if (StringUtils.isNoneBlank(string)) {
					String code = string.split(",")[0];
					String term = string.split(",")[1];
					System.out.println(code);
					System.out.println(term);
				}
			}
			 resultEntity.setData(res);
			 resultEntity.setCode(ErrorCode.SUCCESS);
		 }catch (IOException e) {
			 logger.error("文件保存失败",e);
			 resultEntity.setCode(ErrorCode.ERROR);
			 resultEntity.setMsg("文件保存失败");
		 }
		return resultEntity;
	}*/
    
    
}
