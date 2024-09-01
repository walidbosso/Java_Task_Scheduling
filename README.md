![Java_Task_Scheduling](https://socialify.git.ci/walidbosso/Java_Task_Scheduling/image?description=1&descriptionEditable=Distribution%20and%20execution%20of%20tasks%20across%20multiple%20nodes%20It%20enhances%20the%20efficiency%20of%20large-scale%20computation.&font=Source%20Code%20Pro&forks=1&issues=1&language=1&name=1&owner=1&pattern=Formal%20Invitation&pulls=1&stargazers=1&theme=Auto)


<p align="center">
<a href="https://github.com/walidbosso/Java_Task_Scheduling">
<img src="https://raw.githubusercontent.com/khoa083/khoa/main/Khoa_ne/img/Rainbow.gif" width="60%"/> </a>
</p>
<div align="center">
  
  [![GitHub WidgetBox](https://github-widgetbox.vercel.app/api/profile?username=walidbosso&data=followers,repositories,stars,commits&theme=nautilus)](https://github.com/walidbosso/Java_Task_Scheduling)

  <p align="center">
<a href="https://github.com/walidbosso/Java_Task_Scheduling">
<img src="https://api.visitorbadge.io/api/visitors?path=https%3A%2F%2Fgithub.com%2Fwalidbosso%2FJava_Task_Scheduling&label=Project%20views&countColor=%23263759&style=flat-square&labelStyle=none" /></a>
</p>

<a href="https://github.com/walidbosso/Java_Task_Scheduling">
  <img src="https://raw.githubusercontent.com/khoa083/khoa/main/Khoa_ne/img/Rainbow.gif" width="60%"/>
  <a/>
</a>


<br/>
  
# Java Task Scheduling System
  
  </div>




## <details><summary> Presentation:</summary>
- This project introduces a Java-based Distributed Task Scheduling System for <b> parallel</b> data(Image & Matrix) processing using `Threads`. 
- Efficient task scheduling optimizes resource allocation and timeframes. 
</details>

## <details><summary> What does my project do exactly:</summary>
- Clients can easily submit various tasks like *Filter, Convolution, and Matrix*, executed via `RMI` on the server. All will be executed in parallel.
- Server and its workers further breaks down tasks to pieces and send them through `TCP` to slaves, then compiles outcomes when they are ready again, and then sends the result back to the client.
- Slaves play a pivotal role by executing assigned sub-tasks.
 
</details>

## <details><summary> Instructions: </summary> 
- Fork & Clone the repository.

- Install JDK if its not installed yet, when done, go to your system environement variables, Edit Path, and add "C:\Path\To\Your\JDK\bin" at the end of it.

- Put all 5 config.txt files+ image+ kernel.txt in Desktop
 
 **Inside folder "src" open 8 of Git-Bash in each 1 of type these lines:**  
<div align="left">
  
```bash
# Compile Java files
javac Classes/*.java 

# Starts the slaves listening TCP
java Classes.SlaveTask config0.txt
java Classes.SlaveTask config1.txt
java Classes.SlaveTask config2.txt
java Classes.SlaveTask config3.txt

# Open the RMI port
rmiregistry 13190

# Start the server, creates multiple workers which works in parallel
java Classes.TaskSchedulerServer config.txt

# Open the GUI
java Classes.Tasks
```
</div>

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

- In case you didn't want to do this manually and felt lazy, check the folder called `Xtra`, I included C codes where it can automatize this work, just fix the Path to each C file in each C code, compile each one of them. When you finish, run **TaskManagement_Process.exe**, this will run all the 8 commands I mentioned above automatically.
- Now whenever you feel like trying the project again just run **TaskManagement_Process.exe**, no need to run 8 commands every single time, consider creating a link to the .exe file instead and add an icon.
- Contact me in [LinkedIn](https://www.linkedin.com/in/walidbosso) for questions. 

<br>

<div align="center">
  
----------------------
> >  <br/> &copy; *by Walid BOUSSOU*   🇲🇦 😄 <br/>  
----------------------

<details>

<summary>👏 Thanks for the support </summary>

## Stargazers


<div align="center">

[![Stargazers repo roster for @walidbosso/Java_Task_Scheduling](http://reporoster.com/stars/dark/walidbosso/Java_Task_Scheduling)](https://github.com/walidbosso/Java_Task_Scheduling/stargazers)



</div>

## Forkers

<div align="center" >

[![Forkers repo roster for @walidbosso/Java_Task_Scheduling](http://reporoster.com/forks/dark/walidbosso/Java_Task_Scheduling)](https://github.com/walidbosso/Java_Task_Scheduling/network/members)

</div>

## Contributors

<a href = "https://github.com/walidbosso">
  <img src = "https://contrib.rocks/image?repo=walidbosso/Java_Task_Scheduling"/>
</a>


<br/></details><br/>

<div align="center">


![GitHub last commit (by committer)](https://img.shields.io/github/last-commit/walidbosso/Java_Task_Scheduling?style=social)

</div>
<div align="center">

![GitHub License](https://img.shields.io/github/license/walidbosso/Java_Task_Scheduling?style=social)


<a href="https://www.buymeacoffee.com/walidbosso"><img src="https://img.buymeacoffee.com/button-api/?text=Buy me a coffee&emoji=☕&slug=walidbosso&button_colour=5F7FFF&font_colour=ffffff&font_family=Poppins&outline_colour=000000&coffee_colour=FFDD00" /></a>

</div>

<a href = "https://github.com/walidbosso">
  <img src = "https://github.com/walidbosso/Python-GUI/blob/main/border.gif" width="100%"/>
</a>

<a href = "https://github.com/walidbosso">
  <img src = "https://github.com/walidbosso/Python-GUI/blob/main/ciber-coding.gif" width="100%"/>
</a>

<a href = "https://github.com/walidbosso">
  <img src = "https://github.com/walidbosso/Python-GUI/blob/main/border.gif" width="100%"/>
</a>

𝚂𝚑𝚘𝚠 𝚜𝚘𝚖𝚎 💙 𝚋𝚢 𝚜𝚝𝚊𝚛𝚛𝚒𝚗𝚐 ⭐ 𝚝𝚑𝚎 𝚛𝚎𝚙𝚘𝚜𝚒𝚝𝚘𝚛𝚢!

<br/>


<p align="center"><a href="#top"><img src="https://img.shields.io/badge/Back%20to%20top--lightgrey?style=social" alt="Back to top" height="20"/></a></p>


<!--
![Stargazers over time](https://starchart.cc/walidbosso/Java_Task_Scheduling.svg)

![Sparkline](https://stars.medv.io/walidbosso/Java_Task_Scheduling.svg)
-->
