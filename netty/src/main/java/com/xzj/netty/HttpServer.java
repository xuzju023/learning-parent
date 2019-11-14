package com.xzj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.spi.SelectorProvider;

/**
 * @date 2019/11/5 18:15
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    protected static EventLoopGroup bossGroup;
    protected static EventLoopGroup workerGroup;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = getBossGroup();
        EventLoopGroup workerGroup = getWorkerGroup();
        logger.info("starting...");
        if (bossGroup == null) {
            NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("sdk-boss"), SelectorProvider.provider());
            nioEventLoopGroup.setIoRatio(100);
            bossGroup = nioEventLoopGroup;
        }

        if (workerGroup == null) {
            NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("sdk-work"), SelectorProvider.provider());
            workerGroup = nioEventLoopGroup;
        }
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channelFactory(new ReflectiveChannelFactory<ServerChannel>(NioServerSocketChannel.class));
            b.childHandler(new ChannelInitializer<Channel>() {
                @Override
                public void initChannel(Channel ch) throws Exception {
                    initPipeline(ch.pipeline());
                }
            });
            initOptions(b);
            ChannelFuture f = b.bind("127.0.0.1", 9999).sync();
            logger.info("server start success bind port:{}", 9999);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("server start exception", e);
        } finally {
            stop();
        }
    }

    protected static void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("handler", new HttpServerHandler());
        pipeline.addBefore("handler", "encaps", new HttpObjectAggregator(20971520));
        pipeline.addBefore("encaps", "codec", new HttpServerCodec());
    }

    public static EventLoopGroup getBossGroup() {
        return workerGroup;
    }

    public static EventLoopGroup getWorkerGroup() {
        return bossGroup;
    }

    protected static void initOptions(ServerBootstrap b) {
        b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        b.option(ChannelOption.SO_BACKLOG, 1);
        b.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        b.childOption(ChannelOption.SO_KEEPALIVE, false);
        b.childOption(ChannelOption.TCP_NODELAY, true);
    }

    /**
     * 释放线程池和句柄
     */
    public static void stop() {
        logger.info("shutdown {}...", HttpServer.class.getSimpleName());
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().syncUninterruptibly();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully().syncUninterruptibly();
        }
        logger.info("{} shutdown success.", HttpServer.class.getSimpleName());
        System.exit(0);
    }
}
