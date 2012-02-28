/**   
 * @{#} CodeBreakUtil.java Create on 2011-3-31 下午09:25:30  nileader
 *   
 * Copyright (c) 2011 by nileader   
 */   
package eclipse.plugin.healthassistant.util;   

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eclipse.plugin.healthassistant.Activator;
/** 
 * 类说明: 通用的一些工具类  
 * @author <a href="mailto:nileader@gmail.com">nileader</a>  
 * @version Create 2011-3-31 Modify 2011-4-02(nileader)   
 */

public class CodeBreakUtil {
	
	/**
	 * 显示一些错误信息窗口给用户.
	 * @param title 	窗口标题
	 * @param message	错误概要
	 * @param reason	错误原因
	 * @param e
	 */
	public static void showErrorDialog(final String title, final String message, final String reason, Throwable e){
		final IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, reason, e);
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
		Display.getDefault().syncExec(new Runnable() {
            public void run() {
				ErrorDialog.openError(window.getShell(), title, message, status);
            }
         });
	}
}
  