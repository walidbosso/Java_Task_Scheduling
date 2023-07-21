# Java_Task_Scheduling


Put all 5 config.txt files+ image+ kernel.txt in Desktop
Inside folder "src" open 8 of Git-Bash in each 1 of these lines type these lines:
------------------

javac Classes/*.java

java Classes.SlaveTask config0.txt
java Classes.SlaveTask config1.txt
java Classes.SlaveTask config2.txt
java Classes.SlaveTask config3.txt

rmiregistry 13190
java Classes.TaskSchedulerServer config.txt
java Classes.Tasks

-------------------
Extra:
jps : to see all javaprocess along with PID 
taskkill /PID 5032 /F : to terminate
//5032exemple
