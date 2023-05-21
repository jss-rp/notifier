package com.jss.notifier

import akka.ConfigurationException
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}


object Application {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "Notifier")
    implicit val context: ExecutionContextExecutor = system.executionContext


    val apiKey = HttpHeader.parse(name = "Tracking-Api-Key", value = "uhs7844a-1r8v-m487-rt6o-6c11pesh4f9g") match {
      case HttpHeader.ParsingResult.Ok(header: HttpHeader, Nil) => header
      case result => throw new ConfigurationException(result.errors.head.formatPretty)
    }

    val contentType = HttpHeader.parse(name = "Content-Type", value = "application/json") match {
      case HttpHeader.ParsingResult.Ok(header: HttpHeader, Nil) => header
      case result => throw new ConfigurationException(result.errors.head.formatPretty)
    }

    val httpRequest = HttpRequest(
      uri = "https://api.trackingmore.com/v4/trackings/get?tracking_numbers=NL583785855BR",
      headers = Seq(apiKey)
    )

    val eventualResponse: Future[HttpResponse] = Http(system).singleRequest(httpRequest)

    eventualResponse.onComplete {
      case Success(res) => {
        val raw = Unmarshal(res.entity).to[String]
        raw.onComplete {
          case Success(printable) => print(printable)
          case Failure(exception) => throw new RuntimeException(exception)
        }
//        println(res.entity.transformDataBytes(Flow))
        res.entity.discardBytes().future()
      }
      case Failure(_) => sys.error("something wrong")
    } (context)


//    eventualResponse.
//    var value: Try[HttpResponse] = eventualResponse.value.get
//
//    if(value.isSuccess) {
//      println(value.get.entity)
//    }

//    system ! true
  }
}