package com.xzj.netty.handler;

import com.xzj.netty.factory.NamedPoolThreadFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.atomic.AtomicInteger;


public class HttpServerHandler extends ChannelInboundHandlerAdapter {
    private AtomicInteger connectNum;
    public static int num;
    private static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
    public static ExecutorService req_pool;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        new BizHandler(ctx, msg).run();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    
    
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);

    }
    
   
    
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error(cause.getMessage(), cause);
        ctx.close();
    }
}
