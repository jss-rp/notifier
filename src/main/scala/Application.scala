package com.jss.notifier

import com.jss.notifier.tracker.TrackingMore

import akka.ConfigurationException
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.*
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}


object Application {
  def main(args: Array[String]): Unit = ActorSystem(MainActor(), "Notifier")
}