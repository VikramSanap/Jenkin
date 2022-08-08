package com.iris22a.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropUtils {
	private static final Logger LOG = Logger.getLogger(PropUtils.class);

	public String getValue(String filepath, String key) {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			LOG.error("file not found");
		}
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			LOG.error("unable to load properties file");
		}
		return prop.getProperty(key);

	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String[] getLocator(String key) {
		String baseDir = System.getProperty("user.dir");// CWD
		return getValue(baseDir + "/src/main/resources/OR.properties", key).split("##");

	}

}
