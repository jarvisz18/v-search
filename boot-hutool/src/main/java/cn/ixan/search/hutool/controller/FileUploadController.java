package cn.ixan.search.hutool.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

	/**
	 * The temporary upload location [xxx] is not valid<li/>
	 * 问题描述:springboot项目突然发现上传文件失败，提示以上信息
	 * <p>
	 * 解释:springboot启动时会创建一个的临时目录作为文件上传的临时目录，但是该目录会在n天之后被系统自动清理掉，这个清理是由linux操作系统完成的
	 * 问题解决方案: 1.创建需要的目录 2.自定义文件夹server.tomcat.basedir: /Users/mac/Desktop/tomcat
	 */
	@ApiOperation("文件上传")
	@PostMapping(path = "/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		return saveFileToLocal(file);
	}
}
