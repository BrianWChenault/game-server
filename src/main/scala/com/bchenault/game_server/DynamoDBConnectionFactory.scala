package com.bchenault.game_server

import awscala.Region
import awscala.dynamodbv2.DynamoDB

object DynamoDBConnectionFactory {
  val environment: String = sys.env.getOrElse("STAGE_NAME", "local")
  val region: String = sys.env.getOrElse("AWS_REGION", "us-east-2")
  def getConnection(): DynamoDB = {
    if (environment == "local") {
      DynamoDB.local()
    } else {
      DynamoDB().at(awscala.Region(region))
    }
  }
}
