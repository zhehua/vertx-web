package com.hm.util;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

public class DBUtil {
    private Vertx vertx;
    public DBUtil(Vertx vertx) {
        this.vertx=vertx;
    }

    public  MySQLPool getConnection(){
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
                .setPort(3306)
                .setHost("127.0.0.1")
                .setDatabase("stu")
                .setUser("root")
                .setPassword("***");

        // Pool options
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

        // Create the pooled clien
        MySQLPool client = MySQLPool.pool(vertx,connectOptions, poolOptions);
        return client;
    }
}
