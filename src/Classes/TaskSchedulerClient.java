package Classes;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.rmi.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskSchedulerClient {

    static float[] kernel = new float[9];

    static int[][] matrix1 = {{10, 20, 30},
    {1, 2, 3},
    {1, 2, 3}};

    static int[][] matrix2 = {{10, 20, 30},
    {1, 2, 3},
    {1, 2, 3}};

    static int taskId;
    static int[][] matrixResult;
    static BufferedImage outputImage;

    static public void showImage(BufferedImage image, String sk_or_rmi) throws IOException {
        JLabel picLabel = new JLabel(new ImageIcon(image));
        JFrame frame = new JFrame(sk_or_rmi);
        frame.setSize(image.getWidth(), image.getHeight());
        frame.setVisible(true);
        JPanel panel = new JPanel();
        panel.setSize(image.getWidth(), image.getHeight());
        panel.setVisible(true);
        panel.add(picLabel);
        frame.add(panel);

    }

    public static void AfficherMtarix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }

    public static byte[] Tobyte(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] imagebyte = baos.toByteArray();
        return imagebyte;
    }
    //--------- read kernel from file -----------//

    static float[] readKernel(File kernelfile) {
        BufferedReader reader = null;
        float[] table = new  float[9];
        try {
            reader = new BufferedReader(new FileReader(kernelfile));
            String input = null;
            
            while ((input = reader.readLine()) != null) {
                String nums[] = input.trim().split("\\s+");
                for (int i = 0; i < 9; i++) {
                    table[i] = Float.parseFloat(nums[i]);
                }
                break;
            }

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    public static byte[] floatToByte(float[] input) {
        byte[] ret = new byte[input.length * 4];
        for (int x = 0; x < input.length; x++) {
            ByteBuffer.wrap(ret, x * 4, 4).putFloat(input[x]);
        }
        return ret;
    }

    public static void main(String[] args) throws IOException, NotBoundException {
        try {
            Random r = new Random();

            TaskSchedulerInterface task
                    = (TaskSchedulerInterface) Naming.lookup("rmi://localhost:13190/task");

            // Submit a task to the server
            System.out.println("start client ...");

            /**
             * ************************ matrix example
             * *****************************
             */
            taskId = task.submitTask(new MatrixTask(r.nextInt(10000 - 1000) + 1000, matrix1, matrix2));
            System.out.println(" Submitted task with ID " + taskId);
            matrixResult = (int[][]) task.getResult(taskId);
            System.out.println("La somme des deux votre matrices est :");
            AfficherMtarix(matrixResult);
            /**
             * ************************ end example
             * *****************************
             */
 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
