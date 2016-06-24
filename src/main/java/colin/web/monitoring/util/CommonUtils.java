package colin.web.monitoring.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonUtils {

	private static CommonUtils INSTANCE = null;

	private CommonUtils() {
	}

	/**
	 * 
	 * 方法描述：返回工具类实例 注意事项：
	 * 
	 * @return
	 * @Exception 异常对象
	 */
	public static CommonUtils getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CommonUtils();
		}
		return INSTANCE;
	}

	/**
	 * 
	 * 方法描述： 返回用户的id 注意事项：
	 * 
	 * @return
	 * @Exception 异常对象
	 */
	public String gernerateUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * 方法描述：对象转换工具类 注意事项：
	 * 
	 * @param sourceList
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @Exception 异常对象
	 */
	public <S, T> List<T> transferObj(List<S> sourceList, Class<T> target)
			throws NoSuchFieldException, SecurityException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		List<T> targetList = new ArrayList<T>();
		Method[] setMethods = target.getDeclaredMethods();
		for (S source : sourceList) {
			T t = target.newInstance();
			for (Method setMethod : setMethods) {
				setMethod.setAccessible(true);
				if (setMethod.getName().startsWith("set")) {
					String methodName = setMethod.getName().substring(2);
					Field filed = target.getField(methodName.toLowerCase());
					Method getMethod = source.getClass().getMethod(
							"get" + methodName, filed.getType()) == null ? source
							.getClass().getMethod("is" + methodName,
									filed.getType()) : source.getClass()
							.getMethod("get" + methodName, filed.getType());
					setMethod.invoke(t, getMethod.invoke(source));
				}
			}
			targetList.add(t);
		}
		return targetList;
	}

	public static void main(String[] args) {
		// UserEntity user
	}
}
