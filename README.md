# <summary> Java Task Scheduling System:</summary> 

## <details><summary> Presentation:</summary>
- This project introduces a Java-based Distributed Task Scheduling System for <b> parallel</b> data(Image & Matrix) processing using `Threads`. 
- Efficient task scheduling optimizes resource allocation and timeframes. 
</details>

## <details><summary> What does my project do exactly:</summary>
- Clients can easily submit various tasks like *Filter, Convolution, and Matrix*, executed via `RMI` on the server. 
- Server further breaks down tasks through `TCP` to slaves, then compiles outcomes, and then sends the result back to the client.
- Slaves play a pivotal role by executing assigned sub-tasks.
</details>

## <details><summary> Instructions: </summary> 
- Put all 5 config.txt files+ image+ kernel.txt in Desktop
- **Inside folder "src" open 8 of Git-Bash in each 1 of type these lines:** 
```bash
# Compile Java files
1. javac Classes/*.java 

# Starts the slaves listening TCP
2. java Classes.SlaveTask config0.txt
3. java Classes.SlaveTask config1.txt
4. java Classes.SlaveTask config2.txt
5. java Classes.SlaveTask config3.txt

# Open the RMI port
6. rmiregistry 13190

# Start the server, creates multiple workers which works in parallel
7. java Classes.TaskSchedulerServer config.txt

# Open the GUI
8. java Classes.Tasks
```


- Extra:
```bash
jps #To see all javaprocess along with PID
```
```bash
taskkill /PID 5032 /F #To terminate in case it was needed, 5032 is just an example.
```
</details>

----------------------
> >  <br/> &copy; *by Walid BOUSSOU*   🇲🇦 😄 <br/>  
----------------------


