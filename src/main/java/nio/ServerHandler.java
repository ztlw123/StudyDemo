package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author zjh
 * @Date 2020/10/26,16:03
 */
public class ServerHandler implements Runnable {

    private static int count = 0;

    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean started;

    public ServerHandler(int port) {

        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();

            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            started = true;
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        started = false;
    }

    public void run() {
        while (started) {
            try {
                selector.select(1000);  //非阻塞，每隔1s被唤醒一次。 阻塞为selector.select()
                Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();

                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //处理新接入消息，即有新连接产生，就为其新注册一个SocketChannel，该Channel关注事件为读事件
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            //读消息
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                int num = sc.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);

                    String clientMessage = new String(bytes);
                    System.out.println("Server: receives clientMessage->" + clientMessage);
                    if (clientMessage.startsWith("I am the client")) {
                        String serverResponseWords =
                                "I am the server, and you are the " + (++count) + "th client.";
                    }

                    //发回复
                    byte[] res = clientMessage.getBytes();
                    ByteBuffer writeBuf = ByteBuffer.allocate(res.length);
                    writeBuf.put(res);
                    writeBuf.flip();
                    sc.write(writeBuf);
                }
            }
        }
    }
}
