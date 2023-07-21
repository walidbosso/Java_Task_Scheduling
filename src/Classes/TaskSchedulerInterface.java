package Classes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
   
   // interface d RMI hiya contract bin client o server 
public interface TaskSchedulerInterface extends Remote {
    
     // had les deux methodes homa li ghaywsaom client ms mn baed maneamlolom override
   public int submitTask(Task task) throws RemoteException ;
   public Object getResult(int taskId) throws RemoteException ;
   
}
