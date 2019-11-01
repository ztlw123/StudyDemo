package flink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zjh
 * @Date 2019/11/01,15:07
 */
public class WordServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(9000);
            System.out.println("服务端启动成功");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Socket serverSocket = null;
        try {
            serverSocket = server.accept();
            System.out.println("客户端接入：" + serverSocket.getInetAddress());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter serverWriter = new PrintWriter(serverSocket.getOutputStream());
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
        String serverLine = serverInput.readLine();

        while (!serverLine.equals("bye")) {
            serverWriter.println(serverLine);
            serverWriter.flush();
            System.out.println("传输内容：" + serverLine);

            serverLine = serverInput.readLine();
        }

        serverWriter.close();
        serverSocket.close();;
        server.close();
    }
}
