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
			
			//이 친구들은 eclipse 경로를 가져옴 
			
			System.out.println(getClass().getPackage());
			System.out.println(getClass().getProtectionDomain());
			System.out.println(getClass().getProtectionDomain().getCodeSource());
			System.out.println(getClass().getProtectionDomain().getCodeSource().getClass());
			System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
			System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			*/
			in = new FileInputStream(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+resources);
			properties = new Properties(); // key - value 를 추상화 캡슐화한 bean
			properties.load(in); //load 를 통해 input Stream 만 넣어주면 알아서 parsing ==> 그럼 properties 에서 정보를 다 갖고 있음 
		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :"  + ex);
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
		Action action = map.get(path); // map 만들어놓기만 했잖아 --> 없으면 정보 갖고와서 넣어주고 있으면 가져다 쓰면 되지?
		if(action == null){
			String className = properties.getProperty(path); // properties 에는 string 만 들어있음 (~~ action 이다
			System.out.println("prop : " + properties);
			System.out.println("path : " + path);			
			System.out.println("className : " + className);
			className = className.trim(); // 앞 뒤 공백 제거 
			try{
				System.out.println("className : " + className);
				Class c = Class.forName(className); // FQCN String 만 주면 생성해주는 아주 훌륭한 친구! ==> Class
				System.out.println("===");
				Object obj = c.newInstance();  // 생성해서 객체로 가져옴 
				System.out.println("===");
				if(obj instanceof Action){	// instanceof ==> equals, 유효성 check, Action 의 하위인지 check
					map.put(path, (Action)obj);  // casting 해줘야지?
					action = (Action)obj;
				}else{
					throw new ClassCastException("Class형변환시 오류 발생  ");
				}
			}catch(Exception ex){
				System.out.println(ex);
				throw new RuntimeException("Action정보를 구하는 도중 오류 발생 : " + ex);
			}
		}
		return action; // map 에 있으면 위의 logic 한번 더 거칠 필요 없지? 딱 하나 인스턴스만 있으면 가져다 쓸 수 있
	}
}