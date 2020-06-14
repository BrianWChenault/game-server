package com.bchenault.game_server;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class OnConnectHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        ApiHandler.Response response = ApiHandler.handle(input, context);
        return new APIGatewayProxyResponseEvent()
                .withBody(response.body())
                .withStatusCode(response.statusCode())
                .withHeaders(response.javaHeaders());
    }
}
