package org.example.pattern;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 迭代器模式，可以使用生产者和消费者类似的模式
 */
public class IteratorPatternExample {


    public static void main(String[] args) {

        Container c = new DataRepository();

        Iterator iterator = c.getIterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            // do biz

            if (next == null) {
                return;
            }
        }

    }


}

/**
 * 理想状态，对上层调用来说，有数据就提供数据，可以边获取数据，边提供操作.消费者不知道什么时候完全获取数据？
 */
class DataRepository implements Container {

    @Override
    public Iterator getIterator() {
        return null;
    }

    private class DataIterator implements Iterator {
        private final Queue<String> datas = new LinkedList<>();
        private final AtomicInteger status = new AtomicInteger(0);
        private final CountDownLatch dl = new CountDownLatch(1);

        private final Lock lock = new ReentrantLock();


        // 使用线程池获取数据

        private void initData() {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    status.set(1);
                    dl.countDown();
                    int i = 0;
                    while (true) {
                        i++;
                        datas.add("data_" + i);
                        if (i == 1000) {
                            status.set(2);
                        }
                    }
                }
            });


        }


        @Override
        public boolean hasNext() {
            int i = status.get();
            if (i == 0) {
                try {
                    dl.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return datas.isEmpty();
        }

        @Override
        public Object next() {
            return datas.remove();
        }
    }
}

interface Iterator {
    boolean hasNext();

    Object next();
}

interface Container {
    public Iterator getIterator();
}


