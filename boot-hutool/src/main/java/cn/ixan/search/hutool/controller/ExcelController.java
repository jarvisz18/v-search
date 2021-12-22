package cn.ixan.search.hutool.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.ixan.search.hutool.domain.StudentDTO;
import cn.ixan.search.hutool.utils.ExcelHelper;
import cn.ixan.search.hutool.utils.ExcelValiHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/22 下午6:56
 * @description Excel导入工具测试
 */
@RequestMapping(value = "/api")
@Controller
public class ExcelController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

	@PostMapping("/v3/readExcel")
	public void readExcelV3(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
		ServletOutputStream out = null;
		ExcelWriter writer = null;
		try {
			List<StudentDTO> successList = new ArrayList<>();
			List<StudentDTO> failList = new ArrayList<>();

			InputStream inputStream = file.getInputStream();
			List<StudentDTO> list = ExcelHelper.getListFromStream(inputStream, StudentDTO.class);

			//校验数据
			if (CollectionUtils.isNotEmpty(list)) {
				for (StudentDTO studentDTO : list) {
					String s = ExcelValiHelper.validateEntity(studentDTO);
					if (StringUtils.isNotEmpty(s)) {
						studentDTO.setErrorMsg(s);
						failList.add(studentDTO);
					} else {
						successList.add(studentDTO);
					}
				}
			}
			LOGGER.info("导入成功的数据共计:[{}]条", successList.size());
			successList.forEach(System.out::println);
			LOGGER.info("导入失败的数据共计:[{}]条", failList.size());
			failList.forEach(System.out::println);

			//错误数据写回Excel
			writer = ExcelHelper.writeListFromStream(StudentDTO.class, failList);

			//response为HttpServletResponse对象
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
			//error.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
			response.setHeader("Content-Disposition", "attachment;filename=error.xlsx");
			out = response.getOutputStream();

			writer.flush(out, true);
		} catch (IOException | NoSuchFieldException e) {
			e.printStackTrace();
		} finally {
			// 关闭writer，释放内存
			if (writer != null) {
				writer.close();
			}
			//此处记得关闭输出Servlet流
			IoUtil.close(out);

		}
	}

	@PostMapping("/v2/readExcel")
	public void readExcelV2(@RequestParam("file") MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			List<StudentDTO> list = ExcelHelper.getListFromStream(inputStream, StudentDTO.class);
			list.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/v1/readExcel")
	public void readExcelV1(@RequestParam("file") MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			ExcelReader reader = ExcelUtil.getReader(inputStream);
			reader.addHeaderAlias("学号", "studentNumber");
			reader.addHeaderAlias("用户姓名", "userName");
			reader.addHeaderAlias("年龄", "age");
			reader.addHeaderAlias("性别", "sex");
			reader.addHeaderAlias("出生日期", "birthday");
			reader.addHeaderAlias("所在院系", "department");


			List<StudentDTO> list = reader.readAll(StudentDTO.class);
			list.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
