package com.bchenault.game_server

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent
import awscala.dynamodbv2._

object ApiHandler {
  private implicit val db: DynamoDB = DynamoDBConnectionFactory.getConnection()
  private val gameConnectionsName: String = sys.env.getOrElse("TABLE_NAME", "game_connections")
  private val gameConnectionsTable: Option[Table] = db.table(gameConnectionsName)

  def onConnectHandler(request: APIGatewayV2WebSocketEvent, context: Context): Response = {
    println(s"Received Connect Request:\n $request")

    Response("""{ "response" : "successfully connected" }""", Map.empty[String, String], 200)
  }

  def onDisconnectHandler(request: APIGatewayV2WebSocketEvent, context: Context): Response = {
    println(s"Received Disconnect Request:\n $request")
    val clientConnection = request.getRequestContext.getConnectionId
    val responseCode = gameConnectionsTable.map { table =>
      val filterCondition = Seq("connectionId" -> cond.eq(Seq(clientConnection):_ *))
      val connectionsToRemove = table.scan(filterCondition).flatMap(UserConnection.fromItem)
      connectionsToRemove.foreach( deadConnection => table.delete(deadConnection.gameId, deadConnection.connectionId))
      200
    }.getOrElse(500)
    Response("""{ "response" : "successfully disconnected" }""", Map.empty[String, String], responseCode)
  }

  def joinGameHandler(request: APIGatewayV2WebSocketEvent, context: Context): Response = {
    println(s"Received join game Request:\n $request")
    val userConnection = UserConnection.fromRequest(request)
    val responseCode = (for {
      connectionInfo <- userConnection
      table <- gameConnectionsTable
    } yield {
      table.put(connectionInfo.gameId, connectionInfo.connectionId, "userName" -> connectionInfo.userName)
      200
    }).getOrElse(500)

    Response("""{ "response" : "successfully disconnected" }""", Map.empty[String, String], responseCode)
  }

  def gameMessageHandler(request: APIGatewayV2WebSocketEvent, context: Context): Response = {
    Response("game message received", Map.empty[String, String])
  }
}
