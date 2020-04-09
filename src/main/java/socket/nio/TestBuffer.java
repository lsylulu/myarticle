package socket.nio;

import java.nio.ByteBuffer;

/**
 * 代码来源：尚学堂
 */
public class TestBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer= ByteBuffer.allocate(16);
        byte[] temp =new byte[]{1,2,3};
        //写入之前java.nio.HeapByteBuffer[pos=0 lim=16 cap=16]
        //position：游标指向的位置
        //limit：访问位置限制，在limit内为有效数据，可以被访问
        System.out.println("写入之前"+buffer);
        //写入字节数组到缓存
        buffer.put(temp);
        //写入之后java.nio.HeapByteBuffer[pos=3 lim=16 cap=16]
        System.out.println("写入之后"+buffer);
        //重置游标
        buffer.flip();
        //重置游标之后java.nio.HeapByteBuffer[pos=0 lim=3 cap=16]
        //limit=position，position=0
        System.out.println("重置游标之后"+buffer);
        //获取当前游标指向位置的数据
        System.out.println(buffer.get());
        for (int i=0;i<buffer.remaining();i++){
            int data=buffer.get(i);//获取指定位置的数据
            System.out.println(i+"-"+data);
        }

        buffer.flip();
        buffer.flip();
        //position=0,limit=cap
        //清空buffer
        buffer.clear();
        buffer.put((byte)10);


    }
}
