/**   
 * @{#} Message.java Create on 2011-3-31 下午03:19:23  nileader
 *   
 * Copyright (c) 2011 by nileader  
 */   
package eclipse.plugin.healthassistant.common;   

import java.util.MissingResourceException;
import java.util.ResourceBundle;
  
/** 
 * 类说明: 字符串常量,在Messages.properties文件中配置
 * @author <a href="mailto:nileader@gmail.com.com">nileader</a>  
 * @version Create 2011-3-31 Modify 2011-3-31(nileader)   
 */

public class Messages {
	
	private static final String BUNDLE_NAME = "eclipse.plugin.healthassistant.common.Messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Messages() {
    }

    /**
     * 从Messages.properties文件中获取指定key的内容
     * @param key
     * @return
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
  