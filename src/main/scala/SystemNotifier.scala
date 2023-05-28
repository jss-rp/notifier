package com.jss.notifier

import java.awt.TrayIcon.MessageType
import java.awt.{SystemTray, Toolkit, TrayIcon}

object SystemNotifier {
  def apply(title: String, notification: String, level: MessageType): Unit = {
    if (SystemTray.isSupported) {
      val systemTray = SystemTray.getSystemTray
      val image = Toolkit.getDefaultToolkit.createImage("icon.png")
      val trayIcon = new TrayIcon(image, "Notifier")

      trayIcon.setImageAutoSize(true)
      trayIcon.setToolTip("Update notification")
      systemTray.add(trayIcon)
      trayIcon.displayMessage(title, notification, level)
    }
  }
}
