package com.jss.notifier
package com.jss.notifier.target

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration

class OrderShipmentNotifier(id: String, tracker: Tracker) {
  def checkOrder(): Unit = tracker.track(id)
}

object OrderShipmentNotifier {

  def apply(): Behavior[Command] = Behaviors.setup { context =>
    implicit val system: ActorSystem[Nothing] = context.system
    Behaviors.withTimers { timers =>
      Behaviors.receiveMessage { message =>
        timers.startTimerAtFixedRate(message, FiniteDuration(30, TimeUnit.SECONDS))
        Behaviors.same
      }
    }
  }
}
