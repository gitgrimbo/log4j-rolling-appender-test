log4j-rolling-appender-test
===========================

To run the batch files, the LOG4J_JAR and LOG4J_EXTRAS_JAR env variables must be set to point to the respective jars.

E.g.

<pre>set log4j_jar=c:\.m3\repository\log4j\log4j\1.2.15\log4j-1.2.15.jar</pre>

----------

Output of the log files uses a tag such as JVM1[0], where this means the JVM process that ran the tests was called JVM1, and the Thread index is 0.

So in the multi tests we use a tag JVM1..JVM5.

----------

The multi-TestExtrasRollingFileAppender.bat script will run the TestExtrasRollingFileAppender.bat script in 5 separate JVMs.

Sample console command:

<pre>\log4j-rolling-appender-test\log4j-rolling-appender-test>java -classpath target\classes;target\test-classes;c:\.m3\repository\log4j\log4j\1.2.15\log4j-1.2.15.jar;c:\.m3\repository\log4j\apache-log4j-extras\1.1\apache-log4j-extras-1.1.jar grimbo.test.log4j.rollingappender.TestExtrasRollingFileAppender JVM3 5</pre>

Sample console output:

<pre>
log4j: reset attribute= "false".
log4j: Threshold ="null".
log4j: Retreiving an instance of org.apache.log4j.Logger.
log4j: Setting [extras-RollingFileAppender] additivity to [false].
log4j: Level value for extras-RollingFileAppender is  [all].
log4j: extras-RollingFileAppender level set to ALL
log4j: Class name: [org.apache.log4j.rolling.RollingFileAppender]
log4j: Setting property [fileNamePattern] to [org.apache.log4j.rolling.RollingFileAppender.%d{yyyy-MM-dd-HH-mm}.log].
log4j: Parsing layout of class: "org.apache.log4j.PatternLayout"
log4j: Setting property [conversionPattern] to [%c{1} - %m%n].
log4j: setFile called: org.apache.log4j.rolling.RollingFileAppender.2012-09-28-23-12.log, true
log4j: setFile ended
log4j: Adding appender named [RollingFileAppender] to category [extras-RollingFileAppender].
log4j: Retreiving an instance of org.apache.log4j.Logger.
log4j: Setting [console] additivity to [false].
log4j: Level value for console is  [all].
log4j: console level set to ALL
log4j: Class name: [org.apache.log4j.ConsoleAppender]
log4j: Parsing layout of class: "org.apache.log4j.PatternLayout"
log4j: Setting property [conversionPattern] to [[%d][%p][%C]%m%n].
log4j: Adding appender named [consoleAppender] to category [console].
log4j: Level value for root is  [off].
log4j: root level set to OFF
log4j: Adding appender named [consoleAppender] to category [root].
</pre>

Sample files written:

<pre>
28/09/2012  23:13         2,099,901 org.apache.log4j.rolling.RollingFileAppender.2012-09-28-23-12.log
28/09/2012  23:14         2,903,295 org.apache.log4j.rolling.RollingFileAppender.2012-09-28-23-13.log
28/09/2012  23:14         2,200,471 org.apache.log4j.rolling.RollingFileAppender.2012-09-28-23-14.log
</pre>

But can result in corruption. E.g.:

<pre>
extras-RollingFileAppender - JVM1[1] started at 1348870364921ms
extras-RollingFileAppender - JVM1[3] started at 1348870364921ms
extras-RollingFileAppender - JVM1[0] started at 1348870364921ms
extras-RollingFileAppender - JVM1[0] 0
extras-RollingFileAppender - JVM1[1] 0
rted at 1348870364921ms
extras-RollingFileAppender - JVM2[4] started at 1348870364922ms
</pre>

and:

<pre>
extras-RollingFileAppender - JVM4[2] 16714
extras-RollingFileApextras-RollingFileAppendextras-RollingFileAppender - JVM4[0] 16768
extras-RollingFileAppender - JVM4[0] 16769
</pre>

and:

<pre>
extras-RollingFileAppender - JVM1[1] 16712
ollingFileAppender - JVM4[0] 16728
extras-RollingFileAppender - JVM4[3] 16658
</pre>

and empty lines:

<pre>
extras-RollingFileAppender - JVM2[3] 12
extras-RollingFileAppender - JVM4[3] 5

extras-RollingFileAppender - JVM5[3] 3

extras-RollingFileAppender - JVM4[3] 6
extras-RollingFileAppender - JVM3[1] 9
</pre>

----------

The multi-TestStandardDailyRollingFileAppender.bat script will run the TestStandardDailyRollingFileAppender.bat script in 5 separate JVMs.

Sample console command:

<pre>\log4j-rolling-appender-test\log4j-rolling-appender-test>java -classpath target\classes;target\test-classes;c:\.m3\repository\log4j\log4j\1.2.15\log4j-1.2.15.jar grimbo.test.log4j.rollingappender.TestStandardDailyRollingFileAppender JVM4 5</pre>

Sample console output:

<pre>
log4j: reset attribute= "false".
log4j: Threshold ="null".
log4j: Retreiving an instance of org.apache.log4j.Logger.
log4j: Setting [standard-DailyRollingFileAppender] additivity to [false].
log4j: Level value for standard-DailyRollingFileAppender is  [all].
log4j: standard-DailyRollingFileAppender level set to ALL
log4j: Class name: [org.apache.log4j.DailyRollingFileAppender]
log4j: Setting property [file] to [org.apache.log4j.DailyRollingFileAppender.log].
log4j: Setting property [datePattern] to ['.'yyyy-MM-dd-HH-mm].
log4j: Parsing layout of class: "org.apache.log4j.PatternLayout"
log4j: Setting property [conversionPattern] to [[%d][Thread:%t[%p]%m
].
log4j: setFile called: org.apache.log4j.DailyRollingFileAppender.log, true
log4j: setFile ended
log4j: Appender [DailyRollingFileAppender] to be rolled every minute.
log4j: Adding appender named [DailyRollingFileAppender] to category [standard-DailyRollingFileAppender].
log4j: Retreiving an instance of org.apache.log4j.Logger.
log4j: Setting [console] additivity to [false].
log4j: Level value for console is  [all].
log4j: console level set to ALL
log4j: Class name: [org.apache.log4j.ConsoleAppender]
log4j: Parsing layout of class: "org.apache.log4j.PatternLayout"
log4j: Setting property [conversionPattern] to [[%d][%p][%C]%m%n].
log4j: Adding appender named [consoleAppender] to category [console].
log4j: Level value for root is  [off].
log4j: root level set to OFF
log4j: Adding appender named [consoleAppender] to category [root].
log4j:ERROR Failed to rename [org.apache.log4j.DailyRollingFileAppender.log] to [org.apache.log4j.DailyRollingFileAppender.log.2012-09-28-23-23].
log4j: setFile called: org.apache.log4j.DailyRollingFileAppender.log, false
log4j: setFile ended
log4j:ERROR Failed to rename [org.apache.log4j.DailyRollingFileAppender.log] to [org.apache.log4j.DailyRollingFileAppender.log.2012-09-28-23-24].
log4j: setFile called: org.apache.log4j.DailyRollingFileAppender.log, false
log4j: setFile ended
</pre>

Sample file written (NOTE - only one file as when the rename fails, the same file is cleared and written to):

<pre>
28/09/2012  23:25           403,346 org.apache.log4j.DailyRollingFileAppender.log
</pre>

Corruption of file (This is from the start of the log file, to show that after the log file has been cleared and re-written to. I.e. what should have been the first log entries have been replaced with the 25389th log entry of the 5th Thread of JVM3, etc):

<pre>
[2012-09-28 23:25:00,002][Thread:JVM3[4][DEBUG]JVM3[4] 25389
[2012-09-28 23:25:00,002][Thread:JVM3[0][DEBUG]JVM3[0] 25264
[2012-09-28 23:25:00,002][Thread:JVM3[3][DEBUG]JVM3[3] 25392
</pre>

and:

<pre>
[2012-09-28 23:25:05,842][Thread:JVM3[3][DEBUG]JVM3[3] ended after 120001ms
[2012-09-28 23:25:05,845][Thread:JVM3[0][DEBUG]JVM3[0] ended after 120006ms
:25:05,786][Thread:JVM1[1][DEBUG]JVM1[1] 26613
[2012-09-28 23:25:05,787][Thread:JVM1[2][DEBUG]JVM1[2] 26693
[2012-09-28 23:25:05,789][Thread:JVM1[0][DEBUG]JVM1[0] 26751
</pre>
