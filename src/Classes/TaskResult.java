package Classes;

import java.awt.image.BufferedImage;
import java.io.File;

public class TaskResult {

    private int taskId;
    private Object result ;
    public TaskResult(int taskId, Object result) {
        this.taskId = taskId;
        this.result = result;
    }
  
    public int getTaskId() {
        return taskId;
    }

    public Object getResult() {
        return result;
    }
   
    
}

