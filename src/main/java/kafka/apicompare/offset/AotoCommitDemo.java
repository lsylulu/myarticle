package kafka.apicompare.offset;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class AotoCommitDemo {
    public static void main(String[] args) {
        args = new String[]{"192.168.29.100:9092", "topic1", "group1", "consumer3"};
        if (args == null || args.length != 4) {
            System.err.print(
                    "Usage:\n\tjava -jar kafka_consumer.jar ${bootstrap_list} ${topic_name} ${group_name} ${client_name}");
            System.exit(1);
        }
        String bootstrap = args[0];
        String topic = args[1];
        String groupid = args[2];
        String clientid = args[3];

        Properties props = new Properties();
        props.put("bootstrap.servers",bootstrap);
        props.put("group.id",groupid);
        props.put("client.id",clientid);
        props.put("enable.auto.commit","true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializier", StringSerializer.class.getName());
        props.put("value.deserializier", StringSerializer.class.getName());
        props.put("auto.AotoCommitDemo.reset", "earliest");
        KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(props);
        consumer.assign(Arrays.asList(new TopicPartition(topic,0),new TopicPartition(topic,1)));
        while(true){
            ConsumerRecords< String,String> records=consumer.poll(100);
            records.forEach(record-> System.out.printf("client: %s,topic: %s,partition: %d,AotoCommitDemo: %d,key: %s",record.partition(),record.offset(),record.key(),record.value()));
        }
    }

}
