package Classes;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static Classes.TaskSchedulerServer.sockets;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.nio.FloatBuffer;

public class CovolutionTask implements Task, Serializable, Runnable {

    int ID;
    static float[] kernel;
    byte[] kernelB;
    BufferedImage outputimage1;
    BufferedImage outputimage2;
    BufferedImage outputimage3;
    BufferedImage outputimage4;
    byte[] inputImage;
    BufferedImage image;
    Object result;

    static String Convolution = "Convolution";

    //static Socket socket ;
    public CovolutionTask(int ID, byte[] inputImage, byte[] kernelB) {
        this.ID = ID;
        this.inputImage = inputImage;
        this.kernelB = kernelB;
    }

    static float[] Tofloat(byte[] kernelB) {
        FloatBuffer floatBuffer = ByteBuffer.wrap(kernelB).asFloatBuffer();
        float[] floatArray = new float[kernelB.length / 4];
        floatBuffer.get(floatArray);
        kernel = floatArray;
        return kernel;
    }

    public static float[] byteToFloat(byte[] input) {
        float[] ret = new float[input.length / 4];
        for (int x = 0; x < input.length; x += 4) {
            ret[x / 4] = ByteBuffer.wrap(input, x, 4).getFloat();
        }
        return ret;
    }

    static void send_Image(BufferedImage image, Socket socket) throws IOException {
        // send String 
        PrintWriter out1 = new PrintWriter(socket.getOutputStream(), true);
        out1.println(Convolution);

        OutputStream output = socket.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        output.write(size);
        output.write(byteArrayOutputStream.toByteArray());

        // send kernel
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeInt(kernel.length);
        for (float f : kernel) {
            dos.writeFloat(f);
        }

        System.out.println("sending ....");
    }

    static BufferedImage receive_Image(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        System.out.println("Reading: " + System.currentTimeMillis());
        byte[] sizeAr = new byte[8];
        inputStream.read(sizeAr);
        int size1 = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        byte[] imageAr = new byte[size1];
        inputStream.read(imageAr);
        BufferedImage image3 = ImageIO.read(new ByteArrayInputStream(imageAr));
        System.out.println("Received ");
        return image3;
    }

    //---------------- split image ------------//
    static BufferedImage splitImage(BufferedImage im, int square) {
        BufferedImage image = null;
        if (im == null) {
            return null;
        }
        switch (square) {
            case 1:
                image = im.getSubimage(1, 1, im.getWidth() / 2, im.getHeight() / 2);
                return image;
            case 2:
                image = im.getSubimage(im.getWidth() / 2, 0, im.getWidth() / 2, im.getHeight() / 2);
                return image;
            case 3:
                image = im.getSubimage(0, im.getHeight() / 2, im.getWidth() / 2, im.getHeight() / 2);
                return image;
            case 4:
                image = im.getSubimage(im.getWidth() / 2, im.getHeight() / 2, im.getWidth() / 2, im.getHeight() / 2);
                return image;
        }
        return image;
    }

    ////--------------  merger  images ----------------//
    static BufferedImage mergeImages(BufferedImage topLeft, BufferedImage topRight, BufferedImage bottomLeft, BufferedImage bottomRight) throws IOException {
        int width = topLeft.getWidth() + topRight.getWidth();
        int height = topLeft.getHeight() + topRight.getHeight();
        BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = mergedImage.getGraphics();
        graphics.drawImage(topLeft, 1, 1, null);
        graphics.drawImage(topRight, width / 2, 1, null);
        graphics.drawImage(bottomLeft, 1, height / 2, null);
        graphics.drawImage(bottomRight, width / 2, height / 2, null);

        return mergedImage;
    }

    @Override
    public void run() {

    }

    @Override
    public void execute() {
        try {

            System.out.println(" start execute");

            InputStream in = new ByteArrayInputStream(inputImage);
            image = ImageIO.read(in);
            kernel = byteToFloat(kernelB);

            BufferedImage image1 = splitImage(image, 1);
            send_Image(image1, sockets.get(0));
            outputimage1 = receive_Image(sockets.get(0));

            BufferedImage image2 = splitImage(image, 2);
            send_Image(image2, sockets.get(1));
            outputimage2 = receive_Image(sockets.get(1));

            BufferedImage image3 = splitImage(image, 3);
            send_Image(image3, sockets.get(2));
            outputimage3 = receive_Image(sockets.get(2));

            BufferedImage image4 = splitImage(image, 4);
            send_Image(image4, sockets.get(3));
            outputimage4 = receive_Image(sockets.get(3));

            result = mergeImages(outputimage1, outputimage2, outputimage3, outputimage4);

        } catch (IOException ex) {
            Logger.getLogger(CovolutionTask.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
