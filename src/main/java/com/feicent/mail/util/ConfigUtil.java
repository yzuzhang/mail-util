package com.feicent.mail.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 */
public class ConfigUtil {
	
    /**
     * 读取Properties配置文件
     * @param  configPath
     * @return Properties
     * @throws IOException
     */
    public static Properties getPropertiesFile(String configPath) {
        InputStream in = null;
        Properties prop = new Properties();
        
        try {
        	in = ConfigUtil.class.getClassLoader().getResourceAsStream(configPath);
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(in);
		}
        return prop;
    }

}
