package com.hm.router;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class SubRouter {
    private Vertx vertx;
    public SubRouter(Vertx vertx) {
        this.vertx=vertx;
    }

    public Router good(){
        Router restAPI = Router.router(vertx);
        restAPI.get("/goods/:goodId").handler(rc -> {

            // TODO Handle the lookup of the product....
            rc.response().write("goodJSON");

        });
        restAPI.put("/goods/:goodId").handler(rc -> {

            // TODO Add a new product...
            rc.response().end();

        });
        restAPI.delete("/goods/:goodId").handler(rc -> {

            // TODO delete the product...
            rc.response().end();

        });
        return restAPI;
    }
}
