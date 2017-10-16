package org.simple.util.common;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.betwixt.BindingConfiguration;
import org.apache.commons.betwixt.expression.Context;
import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.betwixt.strategy.DefaultObjectStringConverter;
import org.xml.sax.SAXException;

public class XmlUtils {

	/**
	 * xml转javabean
	 * 
	 * @param xml
	 *            xml文本
	 * @param rootKey
	 *            根节点
	 * @param clazzType
	 *            javabean类型
	 * @return javabean实例
	 * @throws IntrospectionException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static <T> T xmlToJavaBean(String xml, String rootKey, Class<T> clazzType) throws IntrospectionException, IOException, SAXException {
		try (StringReader sr = new StringReader(xml)) {
			BeanReader beanReader = new BeanReader();
			beanReader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
			beanReader.getBindingConfiguration().setMapIDs(false);
			beanReader.registerBeanClass(rootKey, clazzType);

			@SuppressWarnings("unchecked")
			T javaBean = (T) beanReader.parse(sr);
			return javaBean;
		}
	}

	/**
	 * javabean转xml
	 * 
	 * @param instance
	 *            javabean实例
	 * @return xml文本
	 * @throws IOException
	 * @throws SAXException
	 * @throws IntrospectionException
	 */
	public static <T> String javaBeanToXml(T instance) throws IOException, SAXException, IntrospectionException {
		String reslutXml = "";
		try (StringWriter outputWriter = new StringWriter()) {
			outputWriter.write("<?xml version='1.0' encoding='UTF-8'?>");

			// 创建一个BeanWriter实例，并将BeanWriter的输出重定向到指定的输出流
			BeanWriter beanWriter = new BeanWriter(outputWriter);

			// 配置BeanWriter对象
			beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
			beanWriter.getBindingConfiguration().setMapIDs(false);
//			beanWriter.getBindingConfiguration().setClassNameAttribute(classNameAttribute);
			beanWriter.setWriteEmptyElements(true);
			beanWriter.enablePrettyPrint();

			BindingConfiguration bc = beanWriter.getBindingConfiguration();
			bc.setObjectStringConverter(new DateConverter());

			beanWriter.write(instance);
			reslutXml = outputWriter.toString();
		}
		return reslutXml;
	}

}

/**
 * 日期转换，主要是解决日期为null或者空字符串解析报错问题
 * 
 * @author LiuJunGuang
 * @date 2013年12月31日下午6:56:49
 */
class DateConverter extends DefaultObjectStringConverter {
	private static final long serialVersionUID = -197858851188189916L;

	@Override
	@SuppressWarnings("rawtypes")
	public String objectToString(Object object, Class type, String flavour, Context context) {
		return super.objectToString(object, type, flavour, context);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object stringToObject(String string, Class type, String flavour, Context context) {
		if (string == null || "".equals(string))
			return null;
		return super.stringToObject(string, type, flavour, context);
	}

}
