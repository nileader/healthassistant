/**   
 * @{#} PreferenceConstants.java Create on 2011-4-01 下午05:18:34  nileader
 *   
 * Copyright (c) 2011 by nileader   
 */
package eclipse.plugin.healthassistant.preferences;
/**
 * 类说明: 首选项页面中的一些KEY
 * 
 * @author <a href="mailto:nileader@gmail.com">nileader</a>
 * @version Create 2011-04-01 Modify 2011-04-01(nileader)
 */
public class PreferenceConstants {

	/**是否开启CodeBreak*/
	public static final String KEY_OF_BREAK_DIALOG_STATE     = "keyOfBreakDialogState";
	
	/**弹出窗口中的标题*/
	public static final String KEY_OF_BREAK_DIALOG_TITLE     = "keyOfBreakDialogTitle";
	
	/**弹出窗口中的提醒内容*/
	public static final String KEY_OF_BREAK_DIALOG_CONTENT    = "keyOfBreakDialogContent";
	
	/**弹出窗口中的频率,单位是分钟*/
	public static final String KEY_OF_BREAK_DIALOG_TIMEDELAY = "keyOfBreakDialogTimeDelay";

	/**首选项界面类的完整类名*/
	public final static String FULL_CLASS_NAME_OF_CODEBREAK_PREFERENCEPAGE = "eclipse.plugin.healthassistant.preferences.CodeBreakPreferencePage";
}
