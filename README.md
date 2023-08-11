# Java_Task_Scheduling

## Presentation:
- Our project introduces a Java-based Distributed Task Scheduling System for parallel data(Image & Matrix) processing. 
- Efficient task scheduling optimizes resource allocation and timeframes. 

## What does my project do exactly:
- Clients can easily submit various tasks like Filter, Convolution, and Matrix, executed via RMI on the server. 
- Server further breaks down tasks through TCP to slaves, then compiles outcomes, and then sends the result back to the client.
- Slaves play a pivotal role by executing assigned sub-tasks.

## Instructions:  
- Put all 5 config.txt files+ image+ kernel.txt in Desktop
- **Inside folder "src" open 8 of Git-Bash in each 1 of type these lines:** 
```bash
1. javac Classes/*.java
2. java Classes.SlaveTask config0.txt
3. java Classes.SlaveTask config1.txt
4. java Classes.SlaveTask config2.txt
5. java Classes.SlaveTask config3.txt

6. rmiregistry 13190
7. java Classes.TaskSchedulerServer config.txt
8. java Classes.Tasks
```

-------------------
- Extra:
```bash
jps #To see all javaprocess along with PID
```
```bash
taskkill /PID 5032 /F #To terminate
```
> 5032 is an exemple
:smile:
