package cn.piesat.analogterminal.netty.client;

import cn.piesat.analogterminal.netty.handler.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zk
 * @date 2019/2/11 19:49
 */
@Component
public class NettyClient {

    private Logger logger = LoggerFactory.getLogger(NettyClient.class);
    @Value("${netty.host}")
    private String host;
    @Value("${netty.port}")
    private int port;
    @Value("${netty.reconnect}")
    private int reconnect;
    private Channel channel;

    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new NettyClientHandler());
                    }
                });
        while (true) {
            try {
                ChannelFuture f = b.connect(host, port).sync();
                logger.info("【客户端】已连接服务端！");
                channel = f.channel();
                f.channel().closeFuture().sync();
                logger.info("【客户端】服务端断开连接，"+ reconnect/1000 +"秒后重连！");
                channel = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                logger.info("【客户端】服务端连接失败，"+ reconnect/1000 +"秒后重连！");
                e.printStackTrace();
            }

            try {
                Thread.sleep(reconnect);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsg(byte[] msg) {
        if(channel != null) {
            ByteBuf heapByteBuf = Unpooled.buffer();
            heapByteBuf.writeBytes(msg);
            channel.writeAndFlush(heapByteBuf);
        } else {
            logger.info("channel is null，请先连接上服务器！");
        }
    }
}
