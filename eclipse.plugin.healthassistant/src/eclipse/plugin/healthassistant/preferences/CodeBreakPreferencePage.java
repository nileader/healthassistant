/**   
 * @{#} CodeBreakPreferencePage.java Create on 2011-04-01 上午09:03:24  nileader
 *   
 * Copyright (c) 2011 by nileader   
 */
package eclipse.plugin.healthassistant.preferences;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_CONTENT;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_STATE;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_TIMEDELAY;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_TITLE;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eclipse.plugin.healthassistant.Activator;
import eclipse.plugin.healthassistant.common.CommonConstant;
import eclipse.plugin.healthassistant.common.Messages;
import eclipse.plugin.healthassistant.util.CodeBreakUtil;

/**
 * 类说明: 首选项页面  
 * @author <a href="mailto:nileader@gmail.com">nileader</a>  
 * @version Create 2011-04-01 Modify 2011-04-02(nileader)   
 */
public class CodeBreakPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	
	private StringFieldEditor timeDelayTextEditor;
	
	
	public CodeBreakPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("设置健康秘书的首选项");
	}
	
	/**
	 * 设置首选项界面
	 */
	public void createFieldEditors() {

		//设置是否开启CodeBreak
		addField(
				new RadioGroupFieldEditor(KEY_OF_BREAK_DIALOG_STATE, 
						                  "是否开启提醒", 
						                  2, 
						                  new String[][]{{"开启","true"},{"关闭","false"}}, 
						                  getFieldEditorParent() )
				);
		//设置弹出窗口中的标题
		addField(
				new StringFieldEditor(KEY_OF_BREAK_DIALOG_TITLE, 
										Messages.getString("PreferencePage.Break.Dialog.Label.Title"), 
										getFieldEditorParent() ) 
				);
		//设置弹出窗口中的提示内容
		addField(
				new StringFieldEditor(KEY_OF_BREAK_DIALOG_CONTENT, 
										Messages.getString("PreferencePage.Break.Dialog.Label.Content"),  
										getFieldEditorParent() ) 
				);
		
		//设置弹出窗口中的提示内容
		
		timeDelayTextEditor = new StringFieldEditor(KEY_OF_BREAK_DIALOG_TIMEDELAY, 
				Messages.getString("PreferencePage.Break.Dialog.Label.TimeDelay"),  
				getFieldEditorParent() );
		
				addField(timeDelayTextEditor );
	}

	public void init(IWorkbench workbench) {
	}
	
	
	@Override
    public boolean performOk()
    {
		String timeDelayTextEditorValue = timeDelayTextEditor.getStringValue();
		boolean isOk = checkParam(timeDelayTextEditorValue);
		if(!isOk ) return false;
        return super.performOk();
    }

	
	@Override
    public void performApply()
    {
		String timeDelayTextEditorValue = timeDelayTextEditor.getStringValue();
		boolean isOk = checkParam(timeDelayTextEditorValue);
		if(!isOk ) return;
        
		super.performApply();
    }
	
	/**
	 * 检查用户输入
	 * @param timeDelayTextEditorValue
	 * @return
	 */
	public boolean checkParam(String timeDelayTextEditorValue){
 		if(null == timeDelayTextEditorValue || "".equalsIgnoreCase(timeDelayTextEditorValue ) ){
 			CodeBreakUtil.showErrorDialog("健康秘书-首选项配置有误", "首选项中 【提示频率】 配置有误,请输入正确的提示频率", "你没有设置提示频率", null);
			return false;
		}
		try {
			double d            = Double.parseDouble(timeDelayTextEditorValue);
			double milliSeconds = d * CommonConstant.MILLISECONDS_OF_PER_MINUTE * 1000 / 1000;
			long c = (long) milliSeconds;
			if(c < 10 * CommonConstant.MILLISECONDS_OF_PER_MINUTE){
				CodeBreakUtil.showErrorDialog("健康秘书-首选项配置有误", "首选项中 【提示频率】 配置有误,为不影响你的编程心情和Eclipse的性能,建议你将提示频率设置为大于10分钟.", "你配置的频率过小", null);
				return false;
			}
		} catch (Exception e) {
			CodeBreakUtil.showErrorDialog("健康秘书-首选项配置有误", "首选项中 【提示频率】 配置有误,请输入一个数字类型.", "你配置的频率可能是个中文或英文", e);
			return false;
		}
		return true;
	}
	
	
}