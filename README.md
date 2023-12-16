![Java_Task_Scheduling](https://socialify.git.ci/walidbosso/Java_Task_Scheduling/image?description=1&descriptionEditable=Distribution%20and%20execution%20of%20tasks%20across%20multiple%20nodes%20It%20enhances%20the%20efficiency%20of%20large-scale%20computation.&font=Source%20Code%20Pro&forks=1&issues=1&language=1&name=1&owner=1&pattern=Formal%20Invitation&pulls=1&stargazers=1&theme=Auto)


<p align="center">
<a href="https://github.com/walidbosso/Java_Task_Scheduling">
<img src="https://raw.githubusercontent.com/khoa083/khoa/main/Khoa_ne/img/Rainbow.gif" width="60%"/> 

<div align="center">
  
  [![GitHub WidgetBox](https://github-widgetbox.vercel.app/api/profile?username=walidbosso&data=followers,repositories,stars,commits&theme=nautilus)](https://github.com/walidbosso/Java_Task_Scheduling)

  <div/>
<a href="https://github.com/walidbosso/Java_Task_Scheduling">
  <img src="https://raw.githubusercontent.com/khoa083/khoa/main/Khoa_ne/img/Rainbow.gif" width="60%"/>
  <a/>
</a>

</p>

# Java Task Scheduling System:

## <details><summary> Presentation:</summary>
 This project introduces a Java-based Distributed Task Scheduling System for <b> parallel</b> data(Image & Matrix) processing using `Threads`. 
 
 Efficient task scheduling optimizes resource allocation and timeframes. 
</details>

## <details><summary> What does my project do exactly:</summary>
 Clients can easily submit various tasks like *Filter, Convolution, and Matrix*, executed via `RMI` on the server. 
 
 Server further breaks down tasks through `TCP` to slaves, then compiles outcomes, and then sends the result back to the client.
 
 Slaves play a pivotal role by executing assigned sub-tasks.
 
</details>

## <details><summary> Instructions: </summary> 
 Put all 5 config.txt files+ image+ kernel.txt in Desktop
 
 **Inside folder "src" open 8 of Git-Bash in each 1 of type these lines:**  
 ### --
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


## Extra
### A port open error:
```bash
jps #To see all javaprocess along with PID
```
```bash
taskkill /PID 5032 /F #To terminate in case it was needed, 5032 is just an example.
```
</details>

### More ressources: 

 In case you didn't want to do this manually and felt lazy, check the folder called `Xtra`, I included C codes where it can automatize this work, just fix the Path to each C file in each C code, compile each one of them. When you finish, run **TaskManagement_Process.exe**, this will run all the 8 commands I mentioned above automatically.
 
 Now whenever you feel like trying the project again just run **TaskManagement_Process.exe**, no need to run 8 commands every single time, consider creating a link to the .exe file instead and add an icon.

 Contact me in [LinkedIn](https://www.linkedin.com/in/walidbosso) for questions. 

<br>

----------------------
> >  <br/> &copy; *by Walid BOUSSOU*   🇲🇦 😄 <br/>  
----------------------


![GitHub last commit (by committer)](https://img.shields.io/github/last-commit/walidbosso/JEE_Sales_MANAGEMENT?style=social)

![GitHub License](https://img.shields.io/github/license/walidbosso/JEE_Sales_MANAGEMENT?style=social)
