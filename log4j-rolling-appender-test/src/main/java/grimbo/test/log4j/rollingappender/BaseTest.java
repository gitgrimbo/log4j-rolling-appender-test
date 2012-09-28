package grimbo.test.log4j.rollingappender;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class BaseTest {
    String instanceName;
    String loggerName;
    int numThreads;

    public BaseTest(String instanceName, String loggerName, int numThreads) {
        this.instanceName = instanceName;
        this.loggerName = loggerName;
        this.numThreads = numThreads;
    }

    public void test() {
        Logger logger = Logger.getLogger(loggerName);

        Thread[] threads = new Thread[numThreads];
        int TWO_MINS_MILLIS = 1000 * 60 * 2;
        int runTimeMillis = TWO_MINS_MILLIS;
        for (int i = 0; i < numThreads; i++) {
            String name = instanceName + "[" + i + "]";
            Worker worker = new Worker(name, logger, runTimeMillis);
            threads[i] = new Thread(worker, name);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    static void initLog4J(String resourceName) {
        URL url = BaseTest.class.getResource(resourceName);
        new DOMConfigurator().configure(url);
    }

    static class Worker implements Runnable {
        String name;
        Logger log;
        long start;
        int runTimeMillis = 1000 * 60 * 2;
        int count = 0;

        public Worker(String name, Logger log, int runTimeMillis) {
            this.name = name;
            this.log = log;
            this.runTimeMillis = runTimeMillis;
        }

        public void run() {
            start = System.currentTimeMillis();
            log.debug(name + " started at " + start + "ms");
            while (System.currentTimeMillis() - start < runTimeMillis) {
                log.debug(name + " " + count);
                count++;
                int ms = (int) (Math.random() * 10d);
                sleep(ms);
            }
            log.debug(name + " ended after " + (System.currentTimeMillis() - start) + "ms");
        }

        void sleep(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
