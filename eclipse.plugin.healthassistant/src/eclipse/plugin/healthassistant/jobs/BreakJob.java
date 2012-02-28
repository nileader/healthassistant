/**   
 * @{#} BreakJob.java Create on 2011-3-31 下午02:08:34  nileader
 *   
 * Copyright (c) 2011 by nileader   
 */
package eclipse.plugin.healthassistant.jobs;
import static eclipse.plugin.healthassistant.common.CommonConstant.MILLISECONDS_OF_PER_MINUTE;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_CONTENT;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_STATE;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_TIMEDELAY;
import static eclipse.plugin.healthassistant.preferences.PreferenceConstants.KEY_OF_BREAK_DIALOG_TITLE;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eclipse.plugin.healthassistant.Activator;
import eclipse.plugin.healthassistant.common.CommonConstant;
import eclipse.plugin.healthassistant.model.CodeBreakDialog;
import eclipse.plugin.healthassistant.util.CodeBreakUtil;
/**
 * 类说明: 定时任务,能够跳出窗口来提醒
 * 
 * @author <a href="mailto:nileader@gmail.com">nileader</a>
 * @version Create 2011-3-31 Modify 2011-3-31(nileader)
 */
public class BreakJob extends Job {

	//首次加载的时候不会提示
	public static boolean needBreak = false;
	
	public BreakJob(String jobName) {
		super(jobName);
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		IPreferenceStore iPreferenceStore = Activator.getDefault().getPreferenceStore();
		try {
			
			String stateOfBreakDialog     = iPreferenceStore.getString(KEY_OF_BREAK_DIALOG_STATE );
			String titleOfBreakDialog     = iPreferenceStore.getString(KEY_OF_BREAK_DIALOG_TITLE );
			String contentOfBreakDialog   = iPreferenceStore.getString(KEY_OF_BREAK_DIALOG_CONTENT );
			String timeDelayOfBreakDialog = iPreferenceStore.getString(KEY_OF_BREAK_DIALOG_TIMEDELAY );
			long    timeDelay              = this.getTimeDelay(iPreferenceStore);
			
			
			if(!needBreak || stateOfBreakDialog.equalsIgnoreCase("false") ) {
				BreakJob.needBreak = true;
				this.schedule(timeDelay );
				return Status.OK_STATUS;
			}
			
			//弹出提醒窗口
			showMessageBox(titleOfBreakDialog, contentOfBreakDialog + ",下次提醒在" + timeDelayOfBreakDialog + "分钟之后.");
			//设置下次提醒时间
			this.schedule(timeDelay );
		} catch (Exception e) {
			CodeBreakUtil.showErrorDialog("健康秘书-系统错误", "系统执行Job时出现错误,请及时联系旺旺:银时  谢谢!", e.toString(), e);
		}

		return Status.OK_STATUS;
	}
	
	private void showMessageBox(final String title, final String content){
	
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
		Display.getDefault().syncExec(new Runnable() {
            public void run() {
            	CodeBreakDialog codeBreakDialog = new CodeBreakDialog(window.getShell(),title,content );
            	codeBreakDialog.open();
            }
         });
	}
	
	/**
	 * 根据用户在首选项页面中填写的时间来生成一个频率
	 * @param iPreferenceStore
	 * @return int 一个延时时间
	 */
	private long getTimeDelay(IPreferenceStore iPreferenceStore){
		
		String timeDelayOfBreakDialog = iPreferenceStore.getString(KEY_OF_BREAK_DIALOG_TIMEDELAY );
		//默认是一个小时
		long    timeDelay			  = 60 * MILLISECONDS_OF_PER_MINUTE;
		if(null != timeDelayOfBreakDialog && !timeDelayOfBreakDialog.isEmpty() ){
			try {
				long timeDelayMins = 0;
					
					try {
						double d            = Double.parseDouble(timeDelayOfBreakDialog);
						double mins = d * CommonConstant.MILLISECONDS_OF_PER_MINUTE;
						timeDelayMins = (long) mins;
						if(timeDelayMins < 10 * CommonConstant.MILLISECONDS_OF_PER_MINUTE){
							timeDelayMins = 60 * CommonConstant.MILLISECONDS_OF_PER_MINUTE;
							CodeBreakUtil.showErrorDialog("健康秘书-首选项配置有误", "首选项中 【提示频率】 配置有误,为不影响你Eclipse性能和编程心情,建议你将提示频率设置为大于10分钟.已经设置为默认60分钟", "你配置的频率过小", null);
						}
					} catch (Exception e) {
						CodeBreakUtil.showErrorDialog("健康秘书-首选项配置有误", "首选项中 【提示频率】 配置有误,请输入一个数字类型.", "你配置的频率可能是个中文或英文", e);
					}
					
					timeDelay     = timeDelayMins;
					
			} catch (NumberFormatException e) {
				iPreferenceStore.putValue(KEY_OF_BREAK_DIALOG_TIMEDELAY, "60");
				CodeBreakUtil.showErrorDialog("健康秘书-首选项配置有误", "首选项中 【提示频率】 配置有误,系统已经自动将其设置为默认值: 60分钟", "提示频率应该配置一个10-300之间的整数, 但是你配置了" + timeDelayOfBreakDialog, e);
			}
		}
		return timeDelay;
	}
}
