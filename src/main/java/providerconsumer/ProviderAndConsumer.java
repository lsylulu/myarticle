package providerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProviderAndConsumer<T> {
    private final List<T> countNum = new ArrayList<>();
    private final int MAX = 10;
    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    // 生产者
    public void put(T t) {
        try {
            lock.lock();
            while (countNum.size() == MAX) {
                System.err.println("产品已满");
                producer.await();
            }
            System.out.println("产品 add");
            countNum.add(t);
            // 通知消费者线程进行消费
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 消费者
    public T get() {
        T t = null;
        try {
            lock.lock();
            while (countNum.size() == 0) {
                System.err.println("缺货");
                consumer.await();
            }
            t = countNum.remove(0);
            // 通知生产者进行生产
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        ProviderAndConsumer<String> container = new ProviderAndConsumer<String>();

        // 启消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("消费者--"+container.get());
                }
            }, "consumer_" + i).start();
        }

        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    container.put(Thread.currentThread().getName() + "" + j);
                }
            }, "产品-" + i).start();
        }
    }

}
