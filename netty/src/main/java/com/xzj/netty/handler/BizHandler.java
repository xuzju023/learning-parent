package com.xzj.netty.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_0;


public class BizHandler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BizHandler.class);





    private ChannelHandlerContext ctx;

    private Object msg;





    private boolean responseIsGZip = false;

    public BizHandler(final ChannelHandlerContext ctx, final Object msg) {
        this.ctx = ctx;
        this.msg = msg;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            if (!(msg instanceof FullHttpRequest)) {
                LOGGER.warn("massage is not http request!!!");
            }
            FullHttpRequest req = (FullHttpRequest) msg;
            HttpHeaders head = req.headers();
            response(getResponseResult());
        } catch (Exception e) {
            responseIsGZip = false;
            LOGGER.error("server handler error!", e);
            response(getResponseResult());
        } finally {
            //释放netty的内存池
            ReferenceCountUtil.release(msg);
        }
    }

    private byte[] getResponseResult() {
        String data="响应成功";
       
        return data.getBytes();
    }

  
 

    private void response(byte[] result) {
       
        ByteBuf content = Unpooled.wrappedBuffer(result);
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_0, OK, content);
        res.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=utf-8");
        res.headers().set(HttpHeaderNames.CONTENT_LENGTH, result.length);
      
        ctx.writeAndFlush(res).addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                ctx.close();
            }
        });
    }
}
