package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by niceyuanze on 17-5-18.
 */
public class Client {
    public static void main(String[] args) throws IOException {



        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;



        client = new Socket();
        try {
            client.connect(new InetSocketAddress("localhost",8000));
            writer = new PrintWriter(client.getOutputStream(),true);
            writer.println("Hello!");
            writer.flush();
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server:"+reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null)
                writer.close();

            if(reader != null)
                reader.close();

            if(client != null)
                client.close();

        }
    }
}
