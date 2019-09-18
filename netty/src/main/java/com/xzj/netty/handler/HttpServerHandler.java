package com.xzj.netty.handler;

import com.xzj.netty.factory.NamedPoolThreadFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class HttpServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
    public static ExecutorService req_pool;

    public static void init() {
        req_pool = new ThreadPoolExecutor(32, 32, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new NamedPoolThreadFactory("sdk-biz"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (HttpServerHandler.req_pool == null) {
            init();
        }
        HttpServerHandler.req_pool.execute(new BizHandler(ctx, msg));

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause.getMessage(), cause);
        ctx.close();
    }
}
