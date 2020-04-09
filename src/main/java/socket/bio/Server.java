package socket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * bio服务端源码
 * 线程池优化后
 */
public class Server {
    public static void main(String[] args) {
        int port=genPort(args);
        ServerSocket server=null;
        ExecutorService service= Executors.newFixedThreadPool(50);
        try {
            server=new ServerSocket(port);
            System.out.println("server started!");
            while (true){
                Socket socket=server.accept();
                service.execute(new Handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server=null;
        }
    }

    private static int genPort(String[] args) {
        if(args.length>0){
            return Integer.parseInt(args[0]);
        }else{
            return 9999;
        }
    }

    static class Handler implements Runnable{

        Socket socket=null;
        public  Handler(Socket socket){
            this.socket=socket;
        }
        public void run() {
            BufferedReader reader=null;
            PrintWriter writer=null;
            try {
                reader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                writer=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
                String readMassage=null;
                while (true){
                    System.out.println("server reading...");
                    if((readMassage=reader.readLine())==null){
                        break;
                    }
                    System.out.println(readMassage);
                    writer.println("server recive:"+readMassage);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(socket!=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                socket=null;
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                reader=null;
                if(writer!=null){
                    writer.close();
                }
                writer=null;
            }
        }
    }
}
