/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;


public class SlaveTask {

    static Socket socket;
    static BufferedImage inputimage = null;
    static ServerSocket serverSocket = null;
    static BufferedImage outputimage = null;
    public static int port_slave;
    public static File file;
    static String Task;
    static float[] kernel = new float[9];
   

    static Socket receive_Image() throws IOException, ClassNotFoundException {
        // receive String
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Task = in1.readLine();
        
        InputStream inputStream = socket.getInputStream();
        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size1 = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        byte[] imageAr = new byte[size1];
        inputStream.read(imageAr);
        inputimage = ImageIO.read(new ByteArrayInputStream(imageAr));
      

        if (Task.equals("Convolution")) {
            //receive kernel
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                kernel[i] = dis.readFloat();

            }
        }

        System.out.println("Received ......" + Task);
        return socket;
    }

    static void resend(BufferedImage image) throws IOException {
        OutputStream output = socket.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        output.write(size);
        output.write(byteArrayOutputStream.toByteArray());
        output.flush();
        System.out.println("sending ....");
    }


    // gray filter
    static BufferedImage asGrayscaleImage(BufferedImage image) {
        BufferedImage gray = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = gray.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        System.out.println("gray has been applied");
        return gray;
    }

    // blue filter
    static BufferedImage BlueImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int p = image.getRGB(j, i);
                int a = (p >> 24) & 0xff;
                int r = 0;
                int g = 0;
                int b = (p & 0xff);
                p = (a << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(j, i, p);
            }
        }
        return image;
    }

    // green filter
    static BufferedImage GreenImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);

                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                // increase the green component and set the RGB value
                green = Math.min(255, green + 50);
                rgb = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, rgb);
            }
        }

        return image;
    }

    // red filter
    static BufferedImage RedImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        int[] pixels = new int[width * height * 3];
        raster.getPixels(0, 0, width, height, pixels);
        for (int i = 0; i < pixels.length; i += 3) {
            pixels[i + 1] = 0;
            pixels[i + 2] = 0;
        }
        raster.setPixels(0, 0, width, height, pixels);
        return image;
    }
    //---------------apply convolution ------------//

    static BufferedImage Convole(BufferedImage inputimage, float[] kernel) {
        Kernel kernel1 = new Kernel(3, 3, kernel);
        ConvolveOp convolution = new ConvolveOp(kernel1);
        BufferedImage outputimage = convolution.filter(inputimage, null);
        System.out.println("convolution has been applied");
        return outputimage;
    }
    // ------------ read slave comfig file

    public static void readConfigFile_Slave() {
        BufferedReader bufferedReader = null;
        String line = "";
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                System.out.println("start reading ");
                line = bufferedReader.readLine();

                String[] row = line.split(";");

                if ("port".equals(row[0])) {
                    port_slave = Integer.parseInt(row[1]);
                } else {
                    System.exit(-1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("file doesn't exist");
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        String path = "C:\\Users\\User\\Desktop\\";

        path += args[0];
        file = new File(path);

        readConfigFile_Slave();
  
        serverSocket = new ServerSocket(port_slave);
        socket = serverSocket.accept();

        while (true) {
            System.out.println("start slaves");
            socket = receive_Image();

            System.out.println(Task);
            if (Task.equals("Gray")) {
                System.out.println("start gray");
                outputimage = asGrayscaleImage(inputimage);
                resend(outputimage);
            }
            if (Task.equals("Blue")) {
                System.out.println("start blue");
                outputimage = BlueImage(inputimage);
                resend(outputimage);
            }
            if (Task.equals("Red")) {
                System.out.println("start Red");
                outputimage = RedImage(inputimage);
                resend(outputimage);
            }
            if (Task.equals("Convolution")) {
                outputimage = Convole(inputimage, kernel);
                resend(outputimage);
            }
        }
    }

}
