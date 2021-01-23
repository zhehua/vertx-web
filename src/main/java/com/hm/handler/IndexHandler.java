package com.hm.handler;

import com.hm.util.DBUtil;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexHandler implements Handler<RoutingContext> {
    private Vertx vertx;
    public IndexHandler(Vertx vertx) {
        this.vertx=vertx;
    }

    @Override
    public void handle(RoutingContext event) {
        DBUtil dbUtil = new DBUtil(vertx);
        List<Map<String,Object>> rList=new ArrayList<>();
        MySQLPool client = dbUtil.getConnection();
        client
                .query("SELECT * FROM girl_friends")
                .execute(ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> result = ar.result();
                        System.out.println("Got " + result.size() + " rows ");
                        String[] list={"id","NAME","AGE"};
                        for (Row row : result) {
                            Map<String,Object> map=new HashMap<>();
                                for (String str : list) {
                                    map.put(str,row.getValue(str));
                                }
                                rList.add(map);
                        }
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                    // Now close the pool
                    client.close();
                    event.response().end(rList.toString());
                });
   }
}
