package com.stackzhang.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/11/21 9:10 下午
 * @description 附件相关控制器
 */
@RestController
@RequestMapping("/api")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return "上传失败，请选择文件";
		}

		String fileName = file.getOriginalFilename();
		String filePath = "/Users/mac/Desktop/tmp";
		logger.info("临时路径为:[{}]", filePath);
		File dest = new File(filePath + "/" + fileName);
		try {
			file.transferTo(dest);
			logger.info("上传成功");
			return "上传成功";
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		return "上传失败！";
	}
}
