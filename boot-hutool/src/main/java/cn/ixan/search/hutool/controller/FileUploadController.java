package cn.ixan.search.hutool.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/20 下午9:18
 * @description 文件上传控制器
 */
@Api(tags = "文件上传")
@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	/**
	 * 保存上传的文件
	 */
	private String saveFileToLocal(MultipartFile file) {
		try {
			String name = "/tmp/" + file.getName();
			FileOutputStream writer = new FileOutputStream(new File(name));
			writer.write(file.getBytes());
			writer.flush();
			writer.close();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@ApiOperation("文件上传")
	@PostMapping(path = "/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		return saveFileToLocal(file);
	}
}
