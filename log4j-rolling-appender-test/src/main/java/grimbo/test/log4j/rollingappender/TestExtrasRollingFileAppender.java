package grimbo.test.log4j.rollingappender;

public class TestExtrasRollingFileAppender extends BaseTest {
    public TestExtrasRollingFileAppender(String instanceName, String loggerName, int numThreads) {
        super(instanceName, loggerName, numThreads);
    }

    public static void main(String[] args) {
        int numThreads = 5;
        String instanceName = args[0];
        if (args.length > 1) {
            numThreads = Integer.parseInt(args[1], 10);
        }

        BaseTest.initLog4J("/TestExtrasRollingFileAppender.log4j.xml");

        TestStandardDailyRollingFileAppender t = new TestStandardDailyRollingFileAppender(instanceName, "extras-RollingFileAppender", numThreads);
        t.test();
    }
}
