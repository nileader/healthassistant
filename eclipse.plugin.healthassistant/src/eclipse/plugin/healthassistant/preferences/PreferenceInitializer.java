/**   
 * @{#} PreferenceInitializer.java Create on 2011-4-01 下午02:08:34  nileader
 *   
 * Copyright (c) 2011 by nileader
 */
package eclipse.plugin.healthassistant.preferences;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_CONTENT;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_STATE;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_TIMEDELAY;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_TITLE;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import eclipse.plugin.healthassistant.Activator;
/**
 * 类说明: 首选项窗口内容初始化
 * 
 * @author <a href="mailto:nileader@taobao.com">nileader</a>
 * @version Create 2011-4-01 Modify 2011-4-01(nileader)
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * 初始化界面,只有在第一次有效果.(在Eclipse的首选项中还没有这些key)
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setDefault(KEY_OF_BREAK_DIALOG_STATE,     "true");
		store.setDefault(KEY_OF_BREAK_DIALOG_TITLE,     "健康秘书");
		store.setDefault(KEY_OF_BREAK_DIALOG_CONTENT,   "该休息啦,弯弯腰,扭扭脖子.身体是革命的本钱!");
		store.setDefault(KEY_OF_BREAK_DIALOG_TIMEDELAY, "60");
	
	}

}
