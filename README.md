# Java_Task_Scheduling

##Instructions:
- Put all 5 config.txt files+ image+ kernel.txt in Desktop

- Inside folder "src" open 8 of Git-Bash in each 1 of these lines type these lines:
1. javac Classes/*.java
2. java Classes.SlaveTask config0.txt
3. java Classes.SlaveTask config1.txt
4. java Classes.SlaveTask config2.txt
5. java Classes.SlaveTask config3.txt

6. rmiregistry 13190
7. java Classes.TaskSchedulerServer config.txt
8. java Classes.Tasks

-------------------
Extra:
jps : to see all javaprocess along with PID 
taskkill /PID 5032 /F : to terminate
//5032exemple
