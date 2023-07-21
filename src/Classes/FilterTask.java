
package Classes;


import static Classes.CovolutionTask.splitImage;
import static Classes.CovolutionTask.mergeImages;
import static Classes.TaskSchedulerServer.sockets;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class FilterTask implements Task, Serializable {

    int ID;
    Object result;
    byte[] inputImage;
    BufferedImage image;
    byte[] filter ;
   
    BufferedImage outputimage1, outputimage2, outputimage3, outputimage4;
    
    public FilterTask(int ID, byte[] inputImage , byte[] filter1) {
        this.ID = ID;
        this.inputImage = inputImage;
        this.filter = filter1;
    }
    
     static void send_Image(BufferedImage image, Socket socket, String filter) throws IOException {
        // send filter name
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(filter);
        out.flush();
        // send image
        OutputStream output = socket.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        output.write(size);
        output.write(byteArrayOutputStream.toByteArray());
        output.flush();
        System.out.println("sending ...."+filter);
    }

    //------------------receive-----------------//
    static BufferedImage receive_Image(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        System.out.println("Reading: " + System.currentTimeMillis());
        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size1 = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        byte[] imageAr = new byte[size1];
        inputStream.read(imageAr);
        BufferedImage outputImage = ImageIO.read(new ByteArrayInputStream(imageAr));
        System.out.println("Received from from slave ");

        return outputImage;
    }

    @Override
    public void execute() {
        System.out.println(" start execute");

        try {
            InputStream in = new ByteArrayInputStream(inputImage);
            image = ImageIO.read(in);
            String color = new String(filter, StandardCharsets.UTF_8);
            
            BufferedImage image1 = splitImage(image, 1);
            send_Image(image1, sockets.get(0),color);
            outputimage1 = receive_Image(sockets.get(0));
            
           BufferedImage image2 = splitImage(image, 2);
            send_Image(image2, sockets.get(1),color);
            outputimage2 = receive_Image(sockets.get(1));

            BufferedImage image3 = splitImage(image, 3);
            send_Image(image3, sockets.get(2),color);
            outputimage3 = receive_Image(sockets.get(2));

            BufferedImage image4 = splitImage(image, 4);
            send_Image(image4, sockets.get(3),color);
            outputimage4 = receive_Image(sockets.get(3));
            
            Thread.sleep(500);
            result = mergeImages(outputimage1, outputimage2, outputimage3, outputimage4);
          
        } catch (IOException ex) {
            Logger.getLogger(FilterTask.class.getName()).log(Level.SEVERE, null, ex);
      } catch (InterruptedException ex) {
            Logger.getLogger(FilterTask.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
