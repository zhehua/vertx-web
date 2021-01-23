package com.hm;

import com.hm.handler.IndexHandler;
import com.hm.router.SubRouter;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);//主路由
        //一级路由
        // 监听/index地址
        //order 路由匹配顺序，router.request.next(); // 调下一个匹配规则
        router.route("/index").handler(new IndexHandler(vertx));
        //post请求
        router.post("/postData").handler(request -> {
            JsonObject res = request.getBodyAsJson(); // 获取到body体的数据
            System.out.println(res);
            System.out.println(request.pathParam("d"));
            System.out.println(request.request().getParam("d"));
            request.queryParams().forEach(q-> System.out.println(q));
            String acceptableContentType = request.getAcceptableContentType();
           // request.response().putHeader("content-type", "application/json");
            request.response().end("post end!");
        });
        //get请求 http://localhost:8080/getMsg?param=hello
        router.get("/getMsg").handler(request -> {
            String param = request.request().getParam("param");
            System.out.println("接收到用户传递的参数为：" + param);
            request.response().putHeader("Content-type", "text/html;charset=utf-8")//解决中文乱码
                    .end("接收到用户传递的参数为：" + param);
        });
// 获取参数 http://localhost:8089/method/user/123
        router.route(HttpMethod.GET, "/method/:user/:pass").handler(request -> {
            String user = request.request().getParam("user");
            String pass = request.request().getParam("pass");
            request.response()
                    .putHeader("Content-type", "text/html;charset=utf-8")
                    .end("接收到的用户名为：" + user + " 接收到的密码为：" + pass);
        });
        //二级路由
        SubRouter subRouter = new SubRouter(vertx);
        //加入主路由
        router.mountSubRouter("/goodsAPI", subRouter.good());
        server.requestHandler(router)
                .listen(8089);
    }
}
