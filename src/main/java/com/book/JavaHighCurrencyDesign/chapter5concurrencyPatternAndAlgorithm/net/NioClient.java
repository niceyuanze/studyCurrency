package com.book.JavaHighCurrencyDesign.chapter5concurrencyPatternAndAlgorithm.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * Created by niceyuanze on 17-5-19.
 */
public class NioClient {
    private Selector selector;
    public void init(String ip, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = SelectorProvider.provider().openSelector();
        channel.connect(new InetSocketAddress(ip, port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    public void working() throws IOException {
        while(true){
            if(!selector.isOpen()){
                break;
            }
            selector.select();
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();
            }
        }
    }

    public void connect(SelectionKey key){
        SocketChannel channel = (SocketChannel)key.channel();
        if(channel.isConnectionPending()){

        }
    }
}
