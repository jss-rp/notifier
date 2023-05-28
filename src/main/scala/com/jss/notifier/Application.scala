package com.jss.notifier

import akka.actor.typed.ActorSystem

object Application {
  def main(args: Array[String]): Unit = ActorSystem(MainActor(), "Notifier")
}
