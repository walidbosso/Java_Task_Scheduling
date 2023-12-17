package Classes;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TaskSchedulerServer {

    private static final int NUM_WORKER_THREADS = 8;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(NUM_WORKER_THREADS);
    public static Taskqueue taskQueue = new Taskqueue();
    static ArrayList<TaskResult> BD = new ArrayList<>();
   
    public static List<Integer> slavesPorts = new ArrayList<Integer>();
    public static List<String> slavesIP = new ArrayList<String>();
    public static List<Socket> sockets = new ArrayList<Socket>();
    public static File file;
    // Constuct 
    public TaskSchedulerServer() {
        threadPool = Executors.newFixedThreadPool(NUM_WORKER_THREADS);
        taskQueue = new Taskqueue();

    }

    public static void start() throws InterruptedException {

        // Start worker threads to process tasks from the queue
        for (int i = 0; i < NUM_WORKER_THREADS; i++) {
            threadPool.submit(new WorkerThread(taskQueue, BD));
        }

    }
    // ------------ read master comfig file

    public static void readConfigFile() {
        BufferedReader bufferedReader = null;
        String line = "";
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                System.out.println("start reading ");
                while ((line = bufferedReader.readLine()) != null) {

                    String[] row = line.split(";");
                    if ("SlaveIp".equals(row[0])) {
                        slavesIP.add(row[1]);
                       
                    } else {
                        
                        System.exit(-1);

                    }
                     line = bufferedReader.readLine();

                     row = line.split(";");

                    if ("SlavePort".equals(row[0])) {
                        slavesPorts.add(Integer.parseInt(row[1]));
                    } else {
                        System.exit(-1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("file doesn't exist");
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
       

        try {

            TaskSchedulerInterface skeleton = new TaskScheduler(taskQueue, BD);
            Naming.rebind("rmi://localhost:13190/task", skeleton);
            System.out.println("Server is ready ...");
            
           String path = "C:\\Users\\User\\Desktop\\";
           path += args[0];
           file = new File(path);
           readConfigFile();
         
         for (int i = 0; i < slavesPorts.size(); i++) {
              sockets.add(new Socket(slavesIP.get(i) , slavesPorts.get(i)));
              System.out.println(slavesIP.get(i)+" "+slavesPorts.get(i));
         }
          start();  

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
