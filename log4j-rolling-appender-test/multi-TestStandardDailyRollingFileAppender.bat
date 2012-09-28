rem TestStandardDailyRollingFileAppender.bat takes 2 parameters
rem first is a tag to identify the logs
rem second is the number of java.lang.Threads to use

start "JVM1" cmd.exe /k TestStandardDailyRollingFileAppender.bat JVM1 5
start "JVM2" cmd.exe /k TestStandardDailyRollingFileAppender.bat JVM2 5
start "JVM3" cmd.exe /k TestStandardDailyRollingFileAppender.bat JVM3 5
start "JVM4" cmd.exe /k TestStandardDailyRollingFileAppender.bat JVM4 5
start "JVM5" cmd.exe /k TestStandardDailyRollingFileAppender.bat JVM5 5