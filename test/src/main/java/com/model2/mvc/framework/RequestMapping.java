package com.model2.mvc.framework;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class RequestMapping {
	
	private static RequestMapping requestMapping;
	private Map<String, Action> map;
	private Properties properties;
	
	private RequestMapping(String resources) {
		map = new HashMap<String, Action>();
		InputStream in = null;
		try{
			//in = getClass().getClassLoader().getResourceAsStream(resources);  // == in = new FileInputStream(resources);
			/*
			System.out.println("======");
			
			System.out.println(System.getProperty("user.dir"));
			System.out.println(Paths.get("").toAbsolutePath());
			System.out.println(Paths.get("").toUri());
			System.out.println(Paths.get("").toFile());
			
			//�� ģ������ eclipse ��θ� ������ 
			
			System.out.println(getClass().getPackage());
			System.out.println(getClass().getProtectionDomain());
			System.out.println(getClass().getProtectionDomain().getCodeSource());
			System.out.println(getClass().getProtectionDomain().getCodeSource().getClass());
			System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
			System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			*/
			in = new FileInputStream(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+resources);
			properties = new Properties(); // key - value �� �߻�ȭ ĸ��ȭ�� bean
			properties.load(in); //load �� ���� input Stream �� �־��ָ� �˾Ƽ� parsing ==> �׷� properties ���� ������ �� ���� ���� 
		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties ���� �ε� ���� :"  + ex);
		}finally{
			if(in != null){
				try{ in.close(); } catch(Exception ex){}
			}
		}
	}
	
	public synchronized static RequestMapping getInstance(String resources){
		if(requestMapping == null){
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}
	
	public Action getAction(String path){
		Action action = map.get(path); // map �������⸸ ���ݾ� --> ������ ���� ����ͼ� �־��ְ� ������ ������ ���� ����?
		if(action == null){
			String className = properties.getProperty(path); // properties ���� string �� ������� (~~ action �̴�
			System.out.println("prop : " + properties);
			System.out.println("path : " + path);			
			System.out.println("className : " + className);
			className = className.trim(); // �� �� ���� ���� 
			try{
				System.out.println("className : " + className);
				Class c = Class.forName(className); // FQCN String �� �ָ� �������ִ� ���� �Ǹ��� ģ��! ==> Class
				System.out.println("===");
				Object obj = c.newInstance();  // �����ؼ� ��ü�� ������ 
				System.out.println("===");
				if(obj instanceof Action){	// instanceof ==> equals, ��ȿ�� check, Action �� �������� check
					map.put(path, (Action)obj);  // casting �������?
					action = (Action)obj;
				}else{
					throw new ClassCastException("Class����ȯ�� ���� �߻�  ");
				}
			}catch(Exception ex){
				System.out.println(ex);
				throw new RuntimeException("Action������ ���ϴ� ���� ���� �߻� : " + ex);
			}
		}
		return action; // map �� ������ ���� logic �ѹ� �� ��ĥ �ʿ� ����? �� �ϳ� �ν��Ͻ��� ������ ������ �� �� ��
	}
}