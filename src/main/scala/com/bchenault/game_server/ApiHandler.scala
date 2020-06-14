package com.bchenault.game_server

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import scala.jdk.CollectionConverters._

object ApiHandler {
  def handle(request: APIGatewayProxyRequestEvent, context: Context): Response = {
    Response("test", Map.empty[String, String])
  }

  case class Response(body: String, headers: Map[String, String], statusCode: Int = 200) {
    def javaHeaders: java.util.Map[String, String] = headers.asJava
  }
}
