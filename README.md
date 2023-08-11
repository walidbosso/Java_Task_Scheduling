# Java_Task_Scheduling

## Presentation:
- Task scheduling is an essential part of any organization's workflow. 
- It helps manage the allocation of resources, ensure that tasks are completed on time and minimize waste.
So The objective of our project is to develop a Distributed Task Scheduling System that allows a **parallel processing** of data(images, matrix) sets using MapReduce tasks in a Java environment.
- The clients can submit the tasks (FilterTask, ConvolutionTask, MatrixTask) to the server to be  executed using **RMI**, and then receive the result from the server.
- The server send sub-tasks (after spliting the main task) **using TCP** to the slaves, and then receive the sub-tasks result.
- The slaves excute the sub-tasks received from the server.

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
