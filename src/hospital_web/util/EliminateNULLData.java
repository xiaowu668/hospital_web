package hospital_web.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EliminateNULLData {
	public static Object eliminate(Object obj) {
		Class clz = obj.getClass();
		Class class_Type = null;
		try {
			class_Type = Class.forName(obj.getClass().getName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Object out = null;
		// ∂‘œÛ
		try {
			out = (Serializable) class_Type.getConstructor(new Class[] {}).newInstance(new Class[] {});
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Method[] mh = clz.getMethods();
		for (Method method : mh) {
			if (method.getName().indexOf("get") != -1 && !method.getName().equals("getClass")) {
				if (method.getReturnType() != java.util.Set.class) {
					try {
						String name = method.getName().substring(3);
						String q = name.substring(0, 1);
						name = q.toLowerCase() + name.substring(1);
						Object value = method.invoke(obj);
						if (value != null && !value.equals("")) {
							for (Method method2 : mh) {
								if (method2.getName().indexOf("set") != -1) {
									if (method.getReturnType() != java.util.Set.class) {
										String name2 = method2.getName().substring(3);
										String q2 = name2.substring(0, 1);
										name2 = q2.toLowerCase()+ name2.substring(1);
										if (name.equals(name2)) {
											method2.invoke(out, value);
										}
									}
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return out;
	}
}
