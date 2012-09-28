rem TestExtrasRollingFileAppender.bat takes 2 parameters
rem first is a tag to identify the logs
rem second is the number of java.lang.Threads to use

start "JVM1" cmd.exe /k TestExtrasRollingFileAppender.bat JVM1 5
start "JVM2" cmd.exe /k TestExtrasRollingFileAppender.bat JVM2 5
start "JVM3" cmd.exe /k TestExtrasRollingFileAppender.bat JVM3 5
start "JVM4" cmd.exe /k TestExtrasRollingFileAppender.bat JVM4 5
start "JVM5" cmd.exe /k TestExtrasRollingFileAppender.bat JVM5 5