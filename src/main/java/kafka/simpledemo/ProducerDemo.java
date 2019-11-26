package kafka.simpledemo;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import kafka.simpledemo.partitioner.HashPartitioner;

import java.util.Properties;

public class ProducerDemo {

  static private final String TOPIC = "topic1";
  static private final String ZOOKEEPER = "192.168.29.100:2181";
  static private final String BROKER_LIST = "192.168.29.100:9092";
//  static private final int PARTITIONS = TopicAdmin.partitionNum(ZOOKEEPER, TOPIC);
  static private final int PARTITIONS = 3;


  public static void main(String[] args) throws Exception {
    Producer<String, String> producer = initProducer();
    sendOne(producer, TOPIC);
  }

  private static Producer<String, String> initProducer() {
    Properties props = new Properties();
    props.put("metadata.broker.list", BROKER_LIST);
    // props.put("serializer.class", "kafka.serializer.StringEncoder");
    props.put("serializer.class", StringEncoder.class.getName());
    props.put("partitioner.class", HashPartitioner.class.getName());
    // props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
//    props.put("compression.codec", "0");
    props.put("producer.type", "async");
    props.put("batch.num.messages", "3");
    props.put("queue.buffer.max.ms", "10000000");
    props.put("queue.buffering.max.messages", "1000000");
    props.put("queue.enqueue.timeout.ms", "20000000");

    ProducerConfig config = new ProducerConfig(props);
    Producer<String, String> producer = new Producer<String, String>(config);
    return producer;
  }

  public static void sendOne(Producer<String, String> producer, String topic) throws InterruptedException {
    KeyedMessage<String, String> message1 = new KeyedMessage<String, String>(topic, "31", "test 31");
    producer.send(message1);
    Thread.sleep(5000);
    KeyedMessage<String, String> message2 = new KeyedMessage<String, String>(topic, "31", "test 32");
    producer.send(message2);
    Thread.sleep(5000);
    KeyedMessage<String, String> message3 = new KeyedMessage<String, String>(topic, "31", "test 33");
    producer.send(message3);
    Thread.sleep(5000);
    KeyedMessage<String, String> message4 = new KeyedMessage<String, String>(topic, "31", "test 34");
    producer.send(message4);
    Thread.sleep(5000);
    KeyedMessage<String, String> message5 = new KeyedMessage<String, String>(topic, "31", "test 35");
    producer.send(message5);
    Thread.sleep(5000);
    producer.close();
  }

}
