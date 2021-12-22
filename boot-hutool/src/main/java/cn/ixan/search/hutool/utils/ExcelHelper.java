package cn.ixan.search.hutool.utils;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/22 下午6:53
 * @description Excel 工具类
 */
public class ExcelHelper {
	private ExcelHelper() {
		throw new UnsupportedOperationException();
	}

	public static <T> ExcelWriter writeListFromStream(Class<T> clazz, List<T> list) {
		ExcelWriter writer = ExcelUtil.getWriter(true);
		Field[] fields = clazz.getDeclaredFields();
		//设置标题
		for (Field field : fields) {
			ExcelRef annotation = field.getAnnotation(ExcelRef.class);
			String header = annotation.header();
			//注意这里:与读取时相反
			writer.addHeaderAlias(field.getName(), header);
		}
		writer.write(list, true);
		return writer;
	}

	public static <T> List<T> getListFromStream(InputStream inputStream, Class<T> clazz) {
		ExcelReader reader = ExcelUtil.getReader(inputStream);
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ExcelRef annotation = field.getAnnotation(ExcelRef.class);
			String header = annotation.header();
			reader.addHeaderAlias(header, field.getName());
		}
		return reader.readAll(clazz);
	}
}
