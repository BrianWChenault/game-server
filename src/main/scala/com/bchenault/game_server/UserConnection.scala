package com.bchenault.game_server

import awscala.dynamodbv2.Item
import net.liftweb.json.{DefaultFormats, parse}

case class UserConnection(connectionId: String, gameId: String, userName: String)

object UserConnection {
  implicit val formats = DefaultFormats
  def fromItem(item: Item): Option[UserConnection] = {
    for {
      connectionId <- item.attributes.find(_.name == "connectionId").flatMap(_.value.n)
      gameId <- item.attributes.find(_.name == "gameId").flatMap(_.value.n)
      userName <- item.attributes.find(_.name == "userName").flatMap(_.value.n)
    } yield {
      UserConnection(connectionId, gameId, userName)
    }
  }

  def fromJson(json: String): UserConnection = {
    parse(json).extract[UserConnection]
  }
}
