package cn.ixan.search.hutool.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/22 下午8:42
 * @description 校验工具类
 */
public class ExcelValiHelper {
	private ExcelValiHelper() {
		throw new UnsupportedOperationException();
	}

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> String validateEntity(T obj) throws NoSuchFieldException {
		StringBuilder result = new StringBuilder();
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		if (set != null && !set.isEmpty()) {
			for (ConstraintViolation<T> cv : set) {
				Field declaredField = obj.getClass().getDeclaredField(cv.getPropertyPath().toString());
				ExcelRef annotation = declaredField.getAnnotation(ExcelRef.class);
				//拼接错误信息，包含当前出错数据的标题名字+错误信息
				result.append(annotation.header()).append(cv.getMessage()).append(";");
			}
		}
		return result.toString();
	}
}
