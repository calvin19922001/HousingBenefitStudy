package hk.gov.housingauthority.nhs.framework.util.file.properties;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {
    private final static Log logger = LogFactory.getLog(PropertiesUtil.class);
    
    public final static String PROP_FILE_ENV = "environment.properties";
    
    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        InputStream is = null;
        try {
            ClassLoader oClassLoader = Thread.currentThread().getContextClassLoader();

            if (oClassLoader == null) {
                oClassLoader = PropertiesUtil.class.getClassLoader();
            }

            is = oClassLoader.getResourceAsStream(fileName);
           	properties.load(is);
           	
        } catch (Exception e) {
            logger.error("Can not load property file - " + fileName, e);
            e.printStackTrace();
        } finally {
        	try {
        		if (is != null) is.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        return properties;
    }
}
