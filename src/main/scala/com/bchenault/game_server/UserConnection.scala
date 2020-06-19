package com.bchenault.game_server

import awscala.dynamodbv2.Item
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent
import net.liftweb.json.{DefaultFormats, parse}

import scala.util.Try

case class UserConnection(connectionId: String, gameId: String, userName: String)

object UserConnection {
  implicit val formats = DefaultFormats
  def fromItem(item: Item): Option[UserConnection] = {
    for {
      connectionId <- item.attributes.find(_.name == "connectionId").flatMap(_.value.s)
      gameId <- item.attributes.find(_.name == "gameId").flatMap(_.value.s)
      userName <- item.attributes.find(_.name == "userName").flatMap(_.value.s)
    } yield {
      UserConnection(connectionId, gameId, userName)
    }
  }

  def fromRequest(request: APIGatewayV2WebSocketEvent): Option[UserConnection] = {
    val json = parse(request.getBody)
    for {
      gameId <- Try((json \ "gameId").extract[String]).toOption
      userName <- Try((json \ "userName").extract[String]).toOption
    } yield {
      UserConnection(
        connectionId = request.getRequestContext.getConnectionId,
        gameId = gameId,
        userName = userName
      )
    }
  }
}
