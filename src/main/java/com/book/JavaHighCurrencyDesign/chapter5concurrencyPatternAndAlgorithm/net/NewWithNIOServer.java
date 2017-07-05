package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niceyuanze on 17-5-18.
 */
public class NewWithNIOServer {
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();
    public static Map<Socket, Long> time_stat =
            new HashMap<>(1024);

    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress isa =
                new InetSocketAddress(InetAddress.getLocalHost(),8000);

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

//        InetSocketAddress
        for(;;){
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            long e = 0;
            while(i.hasNext()){
                SelectionKey sk = (SelectionKey) i.next();
                i.remove();
                if(sk.isAcceptable()){
//                    doAccept(sk);
                }else if(sk.isValid() && sk.isReadable()){
//                    if(!time_stat.containsKey((SocketChannel)sk.channel()))
                }

            }
        }
    }

}
