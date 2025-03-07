package com.wd.yh.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;

public class NotificationUtil {

	// 使用 NotificationGroupManager 创建 NotificationGroup
	private static final NotificationGroup NOTIFICATION_GROUP = new NotificationGroup("MyPluginNotificationGroup", NotificationDisplayType.BALLOON, true);

	/**
	 * 显示错误信息
	 *
	 * @param message 通知内容
	 */
	public static void showErrorNotification(Project project, String title, String message) {
		Notification notification = NOTIFICATION_GROUP.createNotification(
				title,  // 通知标题
				message,  // 通知内容
				NotificationType.WARNING,  // 通知类型
				null
		);
		notification.setImportant(true);
		notification.notify(project);  // 显示通知
		//Notifications.Bus.notify(notification);
	}

	public static void showErrorNotification(String title, String message) {
		Notification notification = NOTIFICATION_GROUP.createNotification(
				title,  // 通知标题
				message,  // 通知内容
				NotificationType.WARNING,  // 通知类型
				null
		);
		notification.setImportant(true);
		Notifications.Bus.notify(notification);
	}

}