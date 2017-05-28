package learn.JavaNIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by Aman on 28-05-2017.
 */
public class ChannelTransfer {
    public static void main(String[] args)throws IOException {
        RandomAccessFile file= new RandomAccessFile("C:/Users/Aman/Desktop/Shows to Watch.txt","rw");
        FileChannel channel= file.getChannel();

        long size=channel.size();
        RandomAccessFile file2= new RandomAccessFile("C:/Users/Aman/Desktop/Shows to Watch2.txt","rw");
        FileChannel channel2= file2.getChannel();

        channel2.transferFrom(channel,0,size);
        file.close();
        file2.close();
    }
}
