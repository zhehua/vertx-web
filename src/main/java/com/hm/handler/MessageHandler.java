package com.hm.handler;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class MessageHandler implements Handler<RoutingContext> {
    private Vertx vertx;
    public MessageHandler(Vertx vertx) {
        this.vertx=vertx;
    }

    @Override
    public void handle(RoutingContext event) {
        event.response().end("ok");

                }
}
