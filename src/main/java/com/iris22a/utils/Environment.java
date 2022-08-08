package com.iris22a.utils;

public class Environment {
	public static String filepath="/src/test/resources/Environment.properties";
	public static String URL;
	public static String user1;
	
	
	static  {
		PropUtils prop =new PropUtils();
		String baseDir = System.getProperty("user.dir");// CWD
         URL=prop.getValue(baseDir+filepath,"app_url");
         user1=prop.getValue(baseDir+filepath,"app_user1");// we not use this in current progrram
	
	}
	
	

//	public Environment() {
//		PropUtils prop =new PropUtils();
//		String baseDir = System.getProperty("user.dir");// CWD
//        URL=prop.getValue(baseDir+filepath,"app_url");
//	
//	}
	
//	#### this method written instead of static block
//	public String getAppUrl() {
//		PropUtils prop =new PropUtils();
//		String baseDir = System.getProperty("user.dir");// CWD
//     	return prop.getValue(baseDir+filepath, "app_url");
	
//}

}
