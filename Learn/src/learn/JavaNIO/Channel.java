package learn.JavaNIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Aman on 27-05-2017.
 */
public class Channel {
    public static void main(String[] args) throws IOException {

        //Random Access File Enables file reading and writing at same time, it also allows randomly accessing data from specific position in files
        //It does not Uses InputStream or OutputStream, instead it uses DataInput and DataOutput
        RandomAccessFile file= new RandomAccessFile("C:/Users/Aman/Desktop/Shows to Watch.txt","rw");
        FileChannel channel= file.getChannel();

        ByteBuffer buffer= ByteBuffer.allocate(48);
        int bytesRead=0;

        while (bytesRead!=-1){
            bytesRead=channel.read(buffer);

            // System.out.println("Read" + bytesRead+" bytes");
            buffer.flip();

            while (buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }

            buffer.clear();
        }
        file.close();
    }
}
