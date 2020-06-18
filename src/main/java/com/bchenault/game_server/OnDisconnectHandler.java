package com.bchenault.game_server;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketResponse;

public class OnDisconnectHandler implements RequestHandler<APIGatewayV2WebSocketEvent, APIGatewayV2WebSocketResponse> {
    @Override
    public APIGatewayV2WebSocketResponse handleRequest(APIGatewayV2WebSocketEvent input, Context context) {
        Response response = ApiHandler.onDisconnectHandler(input, context);
        APIGatewayV2WebSocketResponse gatewayResponse = new APIGatewayV2WebSocketResponse();
        gatewayResponse.setHeaders(response.javaHeaders());
        gatewayResponse.setBody(response.body());
        gatewayResponse.setStatusCode(response.statusCode());
        return gatewayResponse;
    }
}
