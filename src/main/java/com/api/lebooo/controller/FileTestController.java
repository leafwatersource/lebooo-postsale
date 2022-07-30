package com.api.lebooo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * 文件上传
 * @author fishlord
 *
 */
@Controller
public class FileTestController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileTestController.class);
	
	@Value("${upload.filepath}")
	private String uediterFilepath;
	
	/**
	 * 上传
	 * @return
	 * type： 0 购买凭证图片，1 故障现象图片
	 */
   /* @RequestMapping("/up/upload")
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
	}*/

	/**
	 * 批量上传
	 * @return
	 * type： 0 购买凭证图片，1 故障现象图片
	 */
	/*@RequestMapping("/up/mulUpload")
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

    }*/

    
    
}
