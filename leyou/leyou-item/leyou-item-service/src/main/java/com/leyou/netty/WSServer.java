package com.leyou.netty;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class WSServer {

    private static class SingletionWSServer {
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance() {
        return SingletionWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    @Autowired //注入 spring 環境變量，取得當前yaml的 port 號
    Environment environment;

    public WSServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    public void start() {
        String portstr = environment.getProperty("local.server.port");
        if(StringUtils.isNotBlank(portstr)){
            Integer port =  Integer.parseInt(portstr);
            this.future = server.bind(port);
        }else{
            this.future = server.bind(8088);
        }

        System.err.println("netty websocket server 启动完毕...");
    }
}
