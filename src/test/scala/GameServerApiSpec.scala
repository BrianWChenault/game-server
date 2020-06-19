import java.util.UUID

import awscala.dynamodbv2.DynamoDB
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent
import com.bchenault.game_server.{DynamoDBConnectionFactory, OnConnectHandler, OnDisconnectHandler, UserConnection}
import org.scalatest.concurrent.Eventually
import org.scalatest.{FlatSpec, Matchers}

class GameServerApiSpec
  extends FlatSpec
  with Matchers
  with Eventually {

  private implicit val db: DynamoDB = DynamoDBConnectionFactory.getConnection()
  private val gameConnectionsTable = db.table("game_connections").get

  "GameServerApi" should "maintain user connections" in {
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
////    connectResponse.getBody shouldBe "connect request received"
//    eventually {
//      val testItem = gameConnectionsTable.get(testGame, testConnection).get
//      val connection = UserConnection.fromItem(testItem).get
//      connection.connectionId shouldBe testConnection
//      connection.gameId shouldBe testGame
//      connection.userName shouldBe testUser
//    }
//
//    val disconnectHandler = new OnDisconnectHandler()
//    val disconnectRequest = new APIGatewayV2WebSocketEvent()
//    disconnectRequest.setBody(s"""{"action":"$$disconnect", "gameId":"$testGame", "userName":"$testUser"}""")
//    val disconnectRequestContext = new APIGatewayV2WebSocketEvent.RequestContext()
//    disconnectRequestContext.setConnectionId(testConnection)
//    disconnectRequest.setRequestContext(disconnectRequestContext)
//    val disconnectResponse = disconnectHandler.handleRequest(disconnectRequest, new LambdaContextStub())
//
////    disconnectResponse.getBody shouldBe "disconnect request received"
//    eventually {
//      val testItem = gameConnectionsTable.get(testGame, testConnection)
//      testItem shouldBe None
//    }
  }


}
