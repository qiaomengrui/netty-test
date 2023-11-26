import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Testttt {

    @Test
    public static void k(String[] args) {
        //channel读取文件
        try (FileChannel channel = new FileInputStream("src/data.txt").getChannel()) {
            // buffer缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            //循环读取
            while (true) {
                int len = channel.read(buffer);
                System.out.println("read byte" + len);
                if (len == -1) {
                    break;
                }
                //打印buffer
                buffer.flip(); //切换为读模式
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();//一次读一个字节
                    System.out.println( (char) b);
                }
                buffer.clear(); //切换为写
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    static void fun(){
        System.out.println(ByteBuffer.allocate(15).getClass());
        System.out.println(ByteBuffer.allocateDirect(15).getClass());
    }
}
