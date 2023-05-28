package com.jss.notifier
package com.jss.notifier.tracker

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.unmarshalling.Unmarshal
import spray.json.*

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}
import scala.util.{Failure, Success}

class Cainiao(implicit val system: ActorSystem[Nothing]) extends Tracker {
  override def track(shipmentId: String): Unit = {
    implicit val context: ExecutionContextExecutor = system.executionContext
    val uri: String = s"https://global.cainiao.com/global/detail.json?mailNos=$shipmentId&lang=pt-BR&language=pt-BR"
    val request = HttpRequest(uri = uri)

    Http().singleRequest(request)
      .onComplete {
        case Success(response) =>
          Unmarshal(response.entity).to[String].onComplete {
            case Success(str) =>
              val json = str.parseJson
              val data: JsObject = json.asJsObject
                .fields("module")
                .asInstanceOf[JsArray].elements(0)
                .asJsObject

              val statusJson = JsObject(
                "current_status" -> data.fields("statusDesc"),
                "status_stage" -> data.fields("latestTrace")
                  .asJsObject.fields("standerdDesc"),
                "latest_timestp" -> data.fields("latestTrace")
                  .asJsObject.fields("timeStr"))

              system.log.info("{}: {}", shipmentId, statusJson)
            case Failure(error) => throw new RuntimeException(error)
          }

        case Failure(_) => sys.error(_)
      }
  }
}
