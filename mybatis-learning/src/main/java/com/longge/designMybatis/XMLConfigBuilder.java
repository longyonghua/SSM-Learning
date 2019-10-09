package com.longge.designMybatis;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLConfigBuilder{
	//解析主配置文件，把里面的内容填充到DefaultSqlSession所需要的地方
	//使用的技术：dom4j+xpath
	public static Configuration loadConfiguration(InputStream config){
		try{
			//定义封装连接信息的配置对象
			Configuration cfg = new Configuration();
			//1.获取SAXreader对象
			SAXReader reader = new SAXReader();
			//2.根据字节输入流获取Document对象
			Document document = reader.read(config);
			//3.获取根节点
			Element root = document.getRootElement();
			//4.使用xpath中选择指定节点的方式，获取所有property节点
			List<Element> propertyElements = root.selectNodes("//property");
			//5.遍历节点
			for(Element propertyElement : propertyElements){
				String name = propertyElement.attributeValue("name");
				if("driver".equals(name)){
					String driver = propertyElement.attributeValue("value");
					cfg.setDriver(driver);
				}
				if("url".equals(name)){
					String url = propertyElement.attributeValue("value");
					cfg.setUrl(url);
				}
				if("username".equals(name)){
					String username = propertyElement.attributeValue("value");
					cfg.setUsername(username);
				}
				if("password".equals(name)){
					String password = propertyElement.attributeValue("value");
					cfg.setPassword(password);
				}
			}
			//取出mappers中的所有mapper标签，判断它们使用了resource还是class属性
			List<Element> mapperElements = root.selectNodes("//mappers/mapper");
			for(Element mapperElement : mapperElements){
				Attribute attribute = mapperElement.attribute("resource");
				if(attribute!=null){
					System.out.println("使用的是xml");
					String mapperPath = attribute.getValue();
					Map<String,Mapper> mappers = loadMapperConfiguration(mapperPath);
					cfg.setMappers(mappers);
				}else{
					System.out.println("使用的是注解");
					String daoClassPath = mapperElement.attributeValue("class");
					Map<String,Mapper> mappers = loadMapperAnnotation(daoClassPath);
					cfg.setMappers(mappers);
				}
			}
			return cfg;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			try{
				config.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	//根据传入的参数，解析xml，并封装到Map中
	private static Map<String,Mapper> loadMapperConfiguration(String mapperPath) throws IOException {
		InputStream in = null;
		try{
			Map<String,Mapper> mappers = new HashMap<>();
			in = Resources.getResourceAsStream(mapperPath);
			SAXReader reader = new SAXReader();
			Document document = reader.read(in);
			Element root = document.getRootElement();
			String namespace = root.attributeValue("namespace");
			List<Element> selectElements = root.selectNodes("//select");
			for(Element selectElement : selectElements){
				String id = selectElement.attributeValue("id");
				String resultType = selectElement.attributeValue("resultType");
				String queryString = selectElement.getText();
				String key = namespace+"."+id;
				Mapper mapper = new Mapper();
				mapper.setQueryString(queryString);
				mapper.setResultType(resultType);
				mappers.put(key,mapper);
			}
			return mappers;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			in.close();
		}
	}

	//根据传入的参数，得到dao中所有被select注解标注的方法
	//根据方法名称和类名，以及方法上注解value属性的值，组成Mapper的必要信息
	private static Map<String,Mapper> loadMapperAnnotation(String daoClassPath) throws Exception{
		Map<String,Mapper> mappers = new HashMap<String,Mapper>();
		Class daoClass = Class.forName(daoClassPath);
		Method[] methods = daoClass.getMethods();
		for(Method method : methods){
			boolean isAnnotated = method.isAnnotationPresent(Select.class);
			if(isAnnotated){
				Mapper mapper = new Mapper();
				Select selectAnno = method.getAnnotation(Select.class);
				String queryString = selectAnno.value();
				mapper.setQueryString(queryString);
				//获取当前方法的返回值，还要求必须带有泛型信息
				Type type = method.getGenericReturnType(); //List<User>
				//判断type是不是参数化的类型
				if(type instanceof ParameterizedType){
					ParameterizedType ptype = (ParameterizedType)type;
					//得到参数化类型中的实际类型参数
					Type[] types = ptype.getActualTypeArguments();
					Class domainClass = (Class)types[0];
					String resultType = domainClass.getName();
					mapper.setResultType(resultType);
				}
				String methodName = method.getName();
				String className = method.getDeclaringClass().getName();
				String key = className+"."+methodName;
				mappers.put(key,mapper);
			}
		}
		return mappers;
	}
}