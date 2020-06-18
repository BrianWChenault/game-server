import java.util.UUID

import awscala.dynamodbv2.DynamoDB
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent
import com.bchenault.game_server.{DynamoDBConnectionFactory, OnConnectHandler, UserConnection}
import org.scalatest.{FlatSpec, Matchers}

class GameServerApiSpec
  extends FlatSpec
  with Matchers {

  private implicit val db: DynamoDB = DynamoDBConnectionFactory.getConnection()
  private val gameConnectionsTable = db.table("game_connections")

  "GameServerApi" should "register user connections" in {
    println("\nTEST\n")
    val allTables = db.createTable("test", ("foo", ScalarAttributeType.S))
    println(s"all tables $allTables")
    println(s"table $gameConnectionsTable")
//    val testUser = s"user_${UUID.randomUUID()}"
//    val testGame = s"game_${UUID.randomUUID()}"
//    val testConnection = s"connection_${UUID.randomUUID()}"
//    val connectHandler = new OnConnectHandler()
//    val connectionRequest = new APIGatewayV2WebSocketEvent()
//    connectionRequest.setBody(s"""{"action":"$$connect", "gameId":"$testGame", "userName":"$testUser"}""")
//    val requestContext = new APIGatewayV2WebSocketEvent.RequestContext()
//    requestContext.setConnectionId(testConnection)
//    connectionRequest.setRequestContext(requestContext)
//    val connectResponse = connectHandler.handleRequest(connectionRequest, new LambdaContextStub())
//
//    connectResponse.getBody shouldBe "connect request received"
//    val testItem = gameConnectionsTable.get(testConnection, testGame).get
//    val connection = UserConnection.fromItem(testItem).get
//    connection.connectionId shouldBe testConnection
//    connection.gameId shouldBe testGame
//    connection.userName shouldBe testUser
  }


}
