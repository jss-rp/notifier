package com.jss.notifier.domain

import akka.actor.ActorSystem
import akka.stream.{IOResult, Materializer}
import akka.stream.scaladsl.{FileIO, Source, StreamConverters}
import akka.util.ByteString
import spray.json.JsValue

import java.io.{File, InputStream}
import java.nio.charset.Charset
import java.nio.file.Path
import scala.concurrent.{ExecutionContext, Future}
import scala.sys.SystemProperties.systemPropertiesToCompanion


object JSONFileReader {
  def read(filename: String): Source[String, Future[IOResult]] = {
    val fileStream: InputStream = ClassLoader.getSystemResourceAsStream(filename)
    val source: Source[ByteString, Future[IOResult]] = StreamConverters.fromInputStream(() => fileStream)

    source.reduce((acc, raw) => acc ++ raw)
      .map { raw => raw.decodeString(Charset.defaultCharset()) }
  }
}
