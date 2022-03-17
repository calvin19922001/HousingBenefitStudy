package hk.gov.housingauthority.nhs.framework.util.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * =============================================================<br>
 * Copyright 2006 Suzsoft                                       <br>
 * -------------------------------------------------------------<br>
 * Description:                                                 <br>
 * NA                                                           <br>
 * -------------------------------------------------------------<br>
 * Revision:                                                    <br>
 * Date              Author            Remarks                  <br>
 * Aug 07, 2006    James.Zhang       Created class              <br>
 * =============================================================<br>
 */
public final class ThreadLocalDataBean {

    private static ThreadLocal threadData = new ThreadLocal();

    public static Object getProperty(String name) {
        Map map = (Map)threadData.get();
        if (map == null) {
            return null;
        }
        return map.get(name);
    }

    public static void setProperty(String name, Object value) {
        Map map = (Map) threadData.get();
        if (map == null) {
            map = new HashMap();
        }
        map.put(name, value);
        threadData.set(map);
    }

    public static void clean() {
        threadData.set(null);
    }

}
