package com.jss.notifier

import akka.Done
import akka.actor.typed.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{FileIO, Sink, Source}
import akka.util.LineNumbers.SourceFile
import com.jss.notifier.domain.JSONFileReader

import java.net.URI
import java.nio.charset.Charset
import java.nio.file.Path
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object Application {
  def main(args: Array[String]): Unit = {
    implicit val notifier: ActorSystem[Boolean] = ActorSystem(MainActor(), "Notifier")
    implicit val context: ExecutionContext = notifier.executionContext

    JSONFileReader.read("teste.json")
      .runWith(Sink.head)
      .onComplete {
        case Success(value) => println(value)
        case Failure(exception) => println(exception)
      }
  }
}
