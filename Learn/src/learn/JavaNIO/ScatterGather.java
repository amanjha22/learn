package learn.JavaNIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Aman on 28-05-2017.
 */
public class ScatterGather {
    public static void main(String[] args)throws IOException {
        RandomAccessFile file= new RandomAccessFile("C:/Users/Aman/Desktop/Shows to Watch.txt","rw");
        FileChannel channel= file.getChannel();

        ByteBuffer buffer= ByteBuffer.allocate(24);
        ByteBuffer buffer1=  ByteBuffer.allocate(24);

        //Scattering reads over multiple Buffers
        ByteBuffer[] buffers= {buffer,buffer1};
        long bytesRead= channel.read(buffers);


        while(bytesRead>=0){

            buffer.flip();
            buffer1.flip();

            while(buffer.hasRemaining())
                System.out.print((char)buffer.get());
            while (buffer1.hasRemaining())
                System.out.print((char)buffer1.get());

            buffer.clear();
            buffer1.clear();
            System.out.println("\nNew Buffer Read");
            bytesRead= channel.read(buffers);
        }

        //Buffers can also be gathered to write to channels
        //Example:
        //channel.write(buffers);
        file.close();
    }
}
