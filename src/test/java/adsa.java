import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class adsa {

    @Test
    public void fsd(){
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
    public void f1(){
//        System.out.println(ByteBuffer.allocate(15).getClass());
//        System.out.println(ByteBuffer.allocateDirect(15).getClass());
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(new byte[]{'2','3','4','5'});
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
        byteBuffer.rewind();
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());

        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());

    }

    @Test
    public void fun2(){
        //1.string to buffer
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());

        //2.charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");

        //3.wrap 包装
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());

        String buffer = StandardCharsets.UTF_8.decode(buffer2).toString();

    }

    @Test
    public void fun232(){
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(buffer);
        buffer.put("w are you\n".getBytes());
        split(buffer);
    }
    private void split(ByteBuffer buffer) {
        buffer.flip();  //读模式
        for (int i = 0; i < buffer.limit(); i++) {
            //完整信息存buffer1
            if (buffer.get(i) == '\n') {
                int len = i + 1 - buffer.position();
                ByteBuffer buffer1 = ByteBuffer.allocate(len);
                //写入新buffer
                for (int j = 0; j < len; j++) {
                    buffer1.put(buffer.get());
                }
            }
        }
        buffer.compact(); //将剩余的压缩到0位置
    }

    @Test
    public void fun23241(){
        try (FileChannel from = new FileInputStream("src/data.txt").getChannel();
             FileChannel to = new FileInputStream("src/data.txt").getChannel();
        ) {
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void f231() throws IOException {
        Path path = Paths.get("src/data.txt");
        System.out.println(path);
        System.out.println(Files.exists(path));

        Path path1 = Paths.get("sd/f");
        Files.createDirectory(path1);  //创建目录
        Files.createDirectories(path1); //创建多级目录
        Files.copy(path,path1); //拷贝
    }
}
