package com.goodhope.goldselling.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactoryUtil {
	private static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:applicationContext.xml", "classpath:applicationContext-dao.xml", "classpath:applicationContext-hibernate.xml", "classpath:applicationContext-service.xml" });

	public static Object getBean(String bean) {
		return context.getBean(bean);
	}
}
