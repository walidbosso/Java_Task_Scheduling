package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskScheduler extends UnicastRemoteObject implements TaskSchedulerInterface {

    Object result ;
   
    Taskqueue taskQueue;
    int ID;
    MatrixTask matrixtask;
    FilterTask filtertask ;
    CovolutionTask covolutiontask;
    ArrayList<TaskResult> BD;

    public TaskScheduler(Taskqueue taskQueue, ArrayList<TaskResult> BD) throws RemoteException {
        this.taskQueue = taskQueue;
        this.BD = BD;
    }

    @Override
    public int submitTask(Task task) throws RemoteException {

        try {

            taskQueue.add(task);
            if ("Classes.CovolutionTask" == task.getClass().getName()) {
                covolutiontask = (CovolutionTask) task;
                ID = covolutiontask.ID;
                 
            }
            if ("Classes.MatrixTask" == task.getClass().getName()) {
                matrixtask = (MatrixTask) task;
                ID = matrixtask.ID;
                
            }
            if ("Classes.FilterTask" == task.getClass().getName()) {
                filtertask = (FilterTask) task;
                ID = filtertask.ID;
                
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(TaskScheduler.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
               
        return ID;
    }
    
    @Override
    public Object getResult(int taskId) throws RemoteException {
        
        
        
        
        for (int i = 0; i < BD.size(); i++) {
            if (taskId == BD.get(i).getTaskId()) {
                result = BD.get(i).getResult();
                System.out.println("show your result ");
                BD.remove(i);
               
            }
        } 
        
       
        return result;
    }
 

}
