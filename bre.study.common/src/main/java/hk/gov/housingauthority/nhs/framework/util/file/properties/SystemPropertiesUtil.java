package hk.gov.housingauthority.nhs.framework.util.file.properties;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemPropertiesUtil {
    private static SystemPropertiesUtil oInstance = new SystemPropertiesUtil();
    private static Properties oProperties;
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final static String PROP_FILE_SYS = "system.properties";
    protected static String env = PropertiesUtil.loadProperties(PropertiesUtil.PROP_FILE_ENV).getProperty("env");

    private SystemPropertiesUtil() {
    	// Modified by Mandy Cheung start
    	oProperties = PropertiesUtil.loadProperties(PROP_FILE_SYS);
    	// Modified by Mandy Cheung end
    }

    public static String getProperty(String key) {
        return getProperty(key, true);
    }
    /**
     * Get the value of the a property
     *
     * @return the string value of the property
     */
    public static String getProperty(String key, boolean useEnvIfNull) {

        // Add by Bobby for debugging
        //System.out.println("XXXXXXXXXXXXXXXXXXXXXXX "+oProperties.size());
        //System.out.println(oProperties.getProperty("file.online.help.path"));

        if (oProperties == null) {
        	oProperties = PropertiesUtil.loadProperties(PROP_FILE_SYS);
        }

        // Add by Bobby for debugging
        // System.out.println("XXXXXXXXXXXXXXXXXXXXXXX "+oProperties.size());
        // System.out.println(oProperties.getProperty("file.online.help.path"));
        
        //if property value is null, try find it again by using env prefix if useEnvIfNull
        String property = oProperties.getProperty(key);
        if (property == null && useEnvIfNull) {
        	return getProperty(env + "." + key, false);
        } else {
        	return property;
        }
    }

    /**
     * Retrieves the property value as an integer for the specified
     * property name
     *
     * @param sPropertyName property name
     * @param iDefaultValue return this value if property not found
     * @return property value as an integer of property name
     */
    public static int getInt(String sPropertyName, int iDefaultValue) {
        try {
            String sProperty = getProperty(sPropertyName);
            return Integer.parseInt(sProperty);
        } catch (Exception e) {
            return iDefaultValue;
        }
    }

    /**
     * Retrieves the property value as a String for the specified
     * property name
     *
     * @param sPropertyName property name
     * @param sDefaultValue return this value if property not found
     * @return property value as a string of property name
     */
    public static String getString(String sPropertyName,
                                   String sDefaultValue) {
        try {
            String value = getProperty(sPropertyName);
            if (value == null) {
                return sDefaultValue;
            } else {
                return value;
            }
        } catch (Exception e) {
            return sDefaultValue;
        }
    }

    /**
     * Get the map of properties
     *
     * @return the sub group of entries
     */
    public static HashMap getProperties(String keyGroup) {
        HashMap hashmap = new HashMap();
        if (oProperties == null) {
        	oProperties = PropertiesUtil.loadProperties(PROP_FILE_SYS);
        }
        Enumeration enumeration = oProperties.keys();
        while (enumeration.hasMoreElements()) {
            String tempKey = (String) enumeration.nextElement();
            if (tempKey.startsWith(keyGroup)) {
                hashmap.put(tempKey, oProperties.get(tempKey));
            }
        }
        return hashmap;
    }

    /**
     * Return the boolean value of the property. The value can be
     * true or t (any case) for true state and any other value will be false
     * including null values or no entry
     *
     * @param key the key name
     * @return the boolean value
     */
    public static boolean getBoolean(String key, boolean bDefaultValue) {
        try {
            String s = getProperty(key);
            if (s != null) {
                return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t");
            } else {
                return bDefaultValue;
            }
        } catch (Exception e) {
            return bDefaultValue;
        }
    }

}
