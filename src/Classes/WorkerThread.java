package Classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class WorkerThread implements Runnable {

    Taskqueue taskQueue;
    ArrayList<TaskResult> BD;

    public WorkerThread() {

    }

    public WorkerThread(Taskqueue taskqueue, ArrayList<TaskResult> BD) {
        this.taskQueue = taskqueue;
        this.BD = BD;
    }

    @Override
    public synchronized void run() {
        while (true) {

            System.out.println(" start worker .....");
            try {
                Task task = taskQueue.take();

                if ("Classes.MatrixTask" == task.getClass().getName()) {
                    MatrixTask task1 = (MatrixTask) task;
                    task1.execute();
                    System.out.println("done execute ");
                    BD.add(new TaskResult(task1.ID, task1.Resultmatrix));
                    System.out.println(" task added to BD matrix ");
                }

                if ("Classes.CovolutionTask" == task.getClass().getName()) {

                    CovolutionTask task2 = (CovolutionTask) task;
                    task2.execute();
                    File output1 = new File("C:\\Users\\USER\\Documents\\NetBeansProjects\\End_of_module\\src\\Classes\\convolutionresult.jpg");
                    ImageIO.write((BufferedImage) task2.result, "jpg", output1);
                    System.out.println("done execute ");
                     ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write((BufferedImage) task2.result, "jpg", baos);
                    task2.result = baos.toByteArray();

                    BD.add(new TaskResult(task2.ID, task2.result));
                    System.out.println(" task added to BD image " + task2.ID + " " + task2.result);
                   
                }
                if ("Classes.FilterTask" == task.getClass().getName()) {

                    FilterTask task3 = (FilterTask) task;
                    task3.execute();
                    File output1 = new File("C:\\Users\\USER\\Documents\\NetBeansProjects\\End_of_module\\src\\Classes\\"+task3.ID+"");
                    ImageIO.write((BufferedImage) task3.result, "jpg", output1);
                    System.out.println("done execute ");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write((BufferedImage) task3.result, "jpg", baos);
                    task3.result = baos.toByteArray();

                    BD.add(new TaskResult(task3.ID, task3.result));
                    System.out.println(" task added to BD image " + task3.ID + " " + task3.result);
                    
                }

            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
