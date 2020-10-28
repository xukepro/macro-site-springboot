package com.xuke.macrosite.chat.server.ws;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.xuke.macrosite.common.protobuf.ChatMsgReqProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import static io.netty.buffer.Unpooled.wrappedBuffer;

import java.util.List;

/**
 * Created by xuke on 2020/9/27
 */
public class WSServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        /* 空闲状态处理 */
        pipeline.addLast(new IdleStateHandler(15, 15, 15));


        /* 与chat-client通信测试时，需要注释以下代码 */
        /*-----------------------------------------------------------------------------------------------------------*/
        pipeline.addLast(new HttpServerCodec());

        pipeline.addLast(new HttpObjectAggregator(65536));

        pipeline.addLast(new ChunkedWriteHandler());

        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true));
        pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
            @Override
            protected void decode(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
                ByteBuf buf = webSocketFrame.content();
                list.add(buf);
                buf.retain();
            }
        });
        pipeline.addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
            @Override
            protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> list) throws Exception {
                ByteBuf result = null;
                if (msg instanceof MessageLite) {
                    result = wrappedBuffer(((MessageLite) msg).toByteArray());
                }
                if (msg instanceof MessageLite.Builder) {
                    result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
                }
                WebSocketFrame frame = new BinaryWebSocketFrame(result);
                list.add(frame);
            }
        });
        /*-----------------------------------------------------------------------------------------------------------*/

        // 协议包解码时指定Protobuf字节数实例化为CommonProtocol类型
//        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ProtobufDecoder(ChatMsgReqProto.ChatMsgReq.getDefaultInstance()));

        // websocket定义了传递数据的6中frame类型
        pipeline.addLast(new WSServerHandler());
    }
}
