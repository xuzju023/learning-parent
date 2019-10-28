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
import java.util.concurrent.atomic.AtomicInteger;


public class HttpServerHandler extends ChannelInboundHandlerAdapter {
    private AtomicInteger connectNum;
    public static int num;
    private static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
    public static ExecutorService req_pool;

    public static void init() {
        req_pool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new NamedPoolThreadFactory("sdk-biz"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (HttpServerHandler.req_pool == null) {
            init();
        }
        HttpServerHandler.req_pool.execute(new BizHandler(ctx, msg));
    }

    public HttpServerHandler(AtomicInteger connectNum) {
        this.connectNum = connectNum;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    
    
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println(++num);
//        if (connectNum.incrementAndGet() % 100 == 0) {
//            System.out.println("current connected" + connectNum.get());
//        }
    }
    
   
    
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause.getMessage(), cause);
        ctx.close();
    }
}
