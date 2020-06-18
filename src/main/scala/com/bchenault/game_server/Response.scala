package com.bchenault.game_server
import scala.jdk.CollectionConverters._

case class Response(body: String, headers: Map[String, String], statusCode: Int = 200) {
  def javaHeaders: java.util.Map[String, String] = headers.asJava
}
