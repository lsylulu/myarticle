package kafka.apicompare.callback;

import kafka.simpledemo.partitioner.HashPartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerCallbackDemo {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.put("bootstrap.servers","kafka0:9042");
        props.put("acks","all");
        props.put("retries",3);
        props.put("batch.size",16384);
        props.put("linger.ms",1);
        props.put("buffer.memory",33554432);
        props.put("key.serializier", StringSerializer.class.getName());
        props.put("value.serializier", StringSerializer.class.getName());
        props.put("partition.class", HashPartitioner.class.getName());

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i=0;i<10;i++){
            ProducerRecord record=new ProducerRecord<String,String>("topic1",Integer.toString(i));
            producer.send(record,(metadata, exception)-> {
                if(metadata!=null){
                    System.out.println(metadata);
                }
                if(exception!=null){
                    exception.printStackTrace();
                }
            });
        }
        producer.close();
    }
}
