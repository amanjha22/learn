package learn.JavaNIO;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by Aman on 28-05-2017.
 */
public class Selectors {
    public static void main(String[] args) throws IOException{
        Selector selector=Selector.open();
        int x= SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT;

        SocketChannel channel =SocketChannel.open();
        //The channels need to be set to non blocking to be used with Selectors
        channel.configureBlocking(false);

        SelectionKey key=channel.register(selector,x);

        while(true){
            int readyChannels= selector.select();
            if(readyChannels==0)
                continue;

            Set<SelectionKey> selectionKeys= selector.selectedKeys();
            for(SelectionKey selectionKey: selectionKeys){
                if(selectionKey.isAcceptable())
                    System.out.println("Acceptable");
                if(selectionKey.isConnectable())
                    System.out.println("Connectable");
                if (selectionKey.isReadable())
                    System.out.println("Readable");
                if (selectionKey.isWritable())
                    System.out.println("Writable");
            }


        }

    }

}
