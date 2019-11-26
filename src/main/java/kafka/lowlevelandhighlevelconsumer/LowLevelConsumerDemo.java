package kafka.lowlevelandhighlevelconsumer;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class LowLevelConsumerDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        final String topic="topic";
        String clientID="LowLevelConsumerDemo";
        SimpleConsumer simpleConsumer=new SimpleConsumer("kafka0",9092,100000,64*100000,clientID);
        FetchRequest req=new FetchRequestBuilder().clientId(clientID)
                .addFetch(topic,0,0L,100000).addFetch(topic,1,0L,5000).addFetch(topic,2,0L,100000).build();
        FetchResponse fetchResponse=simpleConsumer.fetch(req);
        ByteBufferMessageSet messageSet=fetchResponse.messageSet(topic,1);
        for(MessageAndOffset messageAndOffset:messageSet){
            ByteBuffer payload=messageAndOffset.message().payload();
            long offset=messageAndOffset.offset();
            byte[] bytes=new byte[payload.limit()];
            payload.get(bytes);
            System.out.println("AotoCommitDemo:"+offset+", payload:"+new String(bytes,"UTF-8"));
        }
    }
}
