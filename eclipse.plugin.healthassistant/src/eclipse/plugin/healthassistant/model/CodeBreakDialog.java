/**   
 * @{#} MyMessageDialog.java Create on 2011-04-01 上午08:33:24  nileader
 *   
 * Copyright (c) 2011 by nileader   
 */
package eclipse.plugin.healthassistant.model;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.FULL_CLASS_NAME_OF_CODEBREAK_PREFERENCEPAGE;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_STATE;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;

import eclipse.plugin.healthassistant.Activator;

/**
 * 类说明: 自定义的一个对话框, 提示对话框
 * 
 * @author <a href="mailto:nileader@gmail.com">nileader</a>
 * @version Create 2011-4-01 Modify 2011-4-02(nileader)
 */

public class CodeBreakDialog extends Dialog {

	
	final int SET_PREFERENCE = 3;
	final int NEVER_BREAK	 = 2;
	final int OK_KNOW	     = 1;
	
	private String title   = "健康秘书";
	private String content = "该休息啦,弯弯腰,扭扭脖子.身体是革命的本钱！";
	
	public CodeBreakDialog(Shell parentShell) {
		super(parentShell);
	}
	
	public CodeBreakDialog(Shell parentShell, String title, String content) {
		super(parentShell);
		this.title 	 = title;
		this.content = content;
	}

    /**
     * 在这个方法里构建Dialog中的界面内容
     */
    protected Control createDialogArea(Composite parent) {
    	
    	// 设置一个容器
		Composite myComposite = new Composite(parent, SWT.NONE);
		
    	getShell().setText(this.title); 			//设置Dialog的标题
    	
    	Label label = new Label(myComposite, SWT.BORDER);
        label.setText(this.content );
        label.setBounds (0, 0, 500, 20);
        
        return parent;        
        
    }

    /**
     * 重载这个方法可以改变窗口的默认式样
     * SWT.RESIZE：窗口可以拖动边框改变大小
     * SWT.MAX：　窗口可以最大化
     */
    protected int getShellStyle() {
        return super.getShellStyle();
    }
	
	
	/**
	 * 重载默认的按钮建立方法，让其在本地的执行失效
	 */
	@Override
	protected Button createButton(Composite parent, int buttonId, String buttonText,
			boolean defaultButton) {
		return null;
	}
	@Override
	protected void initializeBounds() {
		/*
		 * 参数1:取得放按钮的面板， 参数2:定交按钮的id值，id值的作用见上面的buttonPressed方法 参数3:按钮上的文字
		 * 参数4:是否为Dialog的默认按钮
		 */
		super.createButton((Composite) this.getButtonBar(), OK_KNOW,        "知道啦",   		false);
		super.createButton((Composite) this.getButtonBar(), NEVER_BREAK,    "下次不再提醒",	false);
		super.createButton((Composite) this.getButtonBar(), SET_PREFERENCE, "设置提示频率",	false);
		super.initializeBounds();
	}
	
	
	
	/**
	 * 对话框上任意按钮按下下触发的事件
	 */
	@Override
	protected void buttonPressed(int buttonId) {
		
		//进入Preferences页面进行设置
        if (buttonId == SET_PREFERENCE){
            PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, FULL_CLASS_NAME_OF_CODEBREAK_PREFERENCEPAGE, new String[]{FULL_CLASS_NAME_OF_CODEBREAK_PREFERENCEPAGE}, null);
            dialog.open();
        }
        if(buttonId == NEVER_BREAK ){
        	IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        	store.setValue(KEY_OF_BREAK_DIALOG_STATE, "false" );
        }

        super.buttonPressed(Dialog.OK);
        
    }
}
