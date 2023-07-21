
package Classes;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class MatrixTask implements Serializable,Task {
    int ID ;
    int[][] matrix1 ;
    int[][] matrix2 ;
    int[][] matrix3;
    Object Resultmatrix ;
    public MatrixTask(int ID , int[][] matrix1 ,    int[][] matrix2 ) throws RemoteException{
    this.matrix1 = matrix1 ;
    this.matrix2 = matrix2 ;
    this.ID = ID ;
    }
   @Override
    public void execute() {
       matrix3 = new int[matrix1.length][matrix1[0].length];
        for(int i=0 ; i<matrix1.length;i++)
            for(int j=0 ; j<matrix1[0].length;j++){
              matrix3[i][j] =   matrix1[i][j] + matrix2[i][j] ;}
        
        Resultmatrix = matrix3;
   }  
}
