package com.bchenault.game_server

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent
import awscala.dynamodbv2._

object ApiHandler {
  private implicit val db: DynamoDB = DynamoDBConnectionFactory.getConnection()
  private val gameConnectionsName: String = sys.env.getOrElse("TABLE_NAME", "game_connections")
  private val gameConnectionsTable: Option[Table] = db.table(gameConnectionsName)

  def onConnectHandler(request: APIGatewayV2WebSocketEvent, context: Context): Response = {
    val clientConnection = request.getRequestContext.getConnectionId
    val userConnection = UserConnection.fromJson(request.getBody).copy(connectionId = clientConnection)

    val responseCode = gameConnectionsTable.map { table =>
      table.put(userConnection.gameId, clientConnection, "userName"->userConnection.userName)
      200
    }.getOrElse(500)
    Response("connect request received", Map.empty[String, String], responseCode)
  }

  def onDisconnectHandler(request: APIGatewayV2WebSocketEvent, context: Context): Response = {
    val clientConnection = request.getRequestContext.getConnectionId
    val responseCode = gameConnectionsTable.map { table =>
      val filterCondition = Seq("connectionId" -> cond.eq(clientConnection:_ *))
      val connectionsToRemove = table.scan(filterCondition).flatMap(UserConnection.fromItem)
      connectionsToRemove.foreach( deadConnection => table.delete(deadConnection.gameId, deadConnection.connectionId))
      200
    }.getOrElse(500)
    Response("disconnect request received", Map.empty[String, String], responseCode)
  }

  def gameMessageHandler(request: APIGatewayV2WebSocketEvent, context: Context): Response = {
    Response("game message received", Map.empty[String, String])
  }
}
