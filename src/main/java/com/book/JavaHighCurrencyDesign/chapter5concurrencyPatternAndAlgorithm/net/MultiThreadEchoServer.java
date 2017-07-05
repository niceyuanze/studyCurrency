package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niceyuanze on 17-5-18.
 */
public class MultiThreadEchoServer {

    private static ExecutorService tp =
            Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable{
        Socket clientSocker;

        public HandleMsg(Socket clientSocker) {
            this.clientSocker = clientSocker;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;

            try {
                is = new BufferedReader(new InputStreamReader(clientSocker.getInputStream()));
                os = new PrintWriter(clientSocker.getOutputStream(), true);
                String inputLine = null;
                long b = System.currentTimeMillis();
                while((inputLine = is.readLine()) != null){
                    os.println(inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend:"+(e-b)+"ms");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(is != null)is.close();
                    if(os != null)os.close();
                    clientSocker.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        ServerSocket echoServer = null;
        Socket clientSocker = null;

        try{
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            System.out.println(e);
        }
        while(true){
            try {
                clientSocker = echoServer.accept();
                System.out.println(clientSocker.getRemoteSocketAddress()+" connect!");
                tp.execute(new HandleMsg(clientSocker));
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}
