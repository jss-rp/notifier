package com.jss.notifier

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

case object MainActor {
  sealed trait Command

  case class Message(content: String) extends Command

  def apply(): Behavior[Boolean] = Behaviors.setup { context =>
    val orderShipmentNotifier = context.spawn(OrderShipmentNotifier(), "OrderShipmentNotifier")
    //    Behaviors.withTimers { timers =>
    //      Behaviors.receiveMessage { message =>
    //        timers.startTimerAtFixedRate(message, FiniteDuration(5, TimeUnit.SECONDS))
    //        context.log.info("{}", message)
    //        Behaviors.same
    //      }
    //    }
    orderShipmentNotifier ! Message("initialize ")
    Behaviors.same
  }
}
