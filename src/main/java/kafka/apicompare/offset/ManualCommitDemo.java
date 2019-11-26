package kafka.apicompare.offset;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class ManualCommitDemo {
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
        props.put("enable.auto.commit","false");
        props.put("key.deserializier", StringSerializer.class.getName());
        props.put("value.deserializier", StringSerializer.class.getName());
        props.put("max.poll.interval.ms", "300000");
        props.put("max.poll.records", "500");
        props.put("auto.offset.reset", "earliest");
        KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topic));
        AtomicLong atomicLong=new AtomicLong();
        while(true){
            ConsumerRecords<String,String> records=consumer.poll(100);
            records.forEach(record->{
                System.out.printf("client: %s,topic: %s,partition: %d,AotoCommitDemo: %d,key: %s",record.partition(),record.offset(),record.key(),record.value());
//                //1.同步commit
//                if(atomicLong.get()%10==0){
//                    //组的commit上一次消费的offset
//                    consumer.commitSync();
//                }
                //2.异步commit
                if(atomicLong.get()%10==0){
                    consumer.commitAsync((Map<TopicPartition, OffsetAndMetadata> offsets,Exception exception)->{
                        offsets.forEach((TopicPartition partition,OffsetAndMetadata offset)->{
                            System.out.printf("commit %s-%d-%d %n",partition.topic(),partition.partition(),offset.offset());
                            offset.offset();
                        });
                        if(null!=exception){
                            exception.printStackTrace();
                        }
                    });
                }
            });
        }
    }
}
