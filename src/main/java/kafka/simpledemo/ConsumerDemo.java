package kafka.simpledemo;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConsumerDemo {

  /**
   * @param args
   */
  public static void main(String[] args) {
     args = new String[]{"192.168.29.100:2181", "topic1", "group1", "consumer1"};
    if (args == null || args.length != 4) {
      System.err.print(
          "Usage:\n\tjava -jar kafka_consumer.jar ${zookeeper_list} ${topic_name} ${group_name} ${consumer_id}");
      System.exit(1);
    }
    String zk = args[0];
    String topic = args[1];
    String groupid = args[2];
    String consumerid = args[3];
    Properties props = new Properties();
    props.put("zookeeper.connect", zk);
    props.put("group.id", groupid);
    props.put("autooffset.reset", "largest");
    props.put("autocommit.enable", "true");
    props.put("client.id", "test");
    props.put("auto.commit.interval.ms", "1000");

    ConsumerConfig consumerConfig = new ConsumerConfig(props);
    ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

    Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
    topicCountMap.put(topic, 1);
    Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap =
        consumerConnector.createMessageStreams(topicCountMap);

    KafkaStream<byte[], byte[]> stream1 = consumerMap.get(topic).get(0);
    ConsumerIterator<byte[], byte[]> it1 = stream1.iterator();
    while (it1.hasNext()) {
      MessageAndMetadata<byte[], byte[]> messageAndMetadata = it1.next();
      String message =
          String.format("Consumer ID:%s, Topic:%s, GroupID:%s, PartitionID:%s, Offset:%s, Message Key:%s, Message Payload: %s",
              consumerid,
              messageAndMetadata.topic(), groupid, messageAndMetadata.partition(),
              messageAndMetadata.offset(), new String(messageAndMetadata.key()),new String(messageAndMetadata.message()));
      System.out.println(message);
    }
  }

}
