package com.bchenault.game_server;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketResponse;

public class GameMessageHandler implements RequestHandler<APIGatewayV2WebSocketEvent, APIGatewayV2WebSocketResponse> {
    @Override
    public APIGatewayV2WebSocketResponse handleRequest(APIGatewayV2WebSocketEvent input, Context context) {
        Response response = ApiHandler.gameMessageHandler(input, context);
        APIGatewayV2WebSocketResponse gatewayResponse = new APIGatewayV2WebSocketResponse();
        gatewayResponse.setBody(response.body());
        gatewayResponse.setHeaders(response.javaHeaders());
        gatewayResponse.setStatusCode(response.statusCode());
        return gatewayResponse;
    }
}
