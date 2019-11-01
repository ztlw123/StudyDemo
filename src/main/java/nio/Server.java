package nio;

/**
 * @Author zjh
 * @Date 2019/03/26,16:34
 */
public class Server {
    private static int DEFAULT_PORT = 19030;
    private static ServerHandler serverHandler;

    public static void start() {
        if(serverHandler != null) {
            serverHandler.stop();
        }

        serverHandler = new ServerHandler(DEFAULT_PORT);
        new Thread(serverHandler, "Server").start();
    }

    public static void main(String[] args) {
        start();
    }
}
