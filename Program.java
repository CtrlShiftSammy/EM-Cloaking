import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.lang.*;

public class Program 
{
    public static int j_target(int j, int k, int width, int height) 
    {   
        double x = j - (width / 2.0);
        double y = k - (height / 2.0);
        double x_target, y_target;
        double a = width / 16.0;
        double b = width / 8.0;
        double c = width / 4.0;
        double d = height / 4.0;
        double e = height / 8.0;
        if (((Math.abs(x) / c) + (Math.abs(y) / d)) > 1.0)
        {
            x_target = x;
            y_target = y;
        }
        else if (((Math.abs(x) / a) + (Math.abs(y) / d)) > 1.0)
        {
            x_target = (x - (x >= 0 ? 1 : -1) * a * (1 - (y >= 0 ? 1 : -1) * y / d)) * (c - b) / (c - a) + (x >= 0 ? 1 : -1) * b * (1 - (y >= 0 ? 1 : -1) * y / d);
            y_target = y;
        }
        else
        {
            x_target = b * x / a;
            y_target = (d - e) * y / d + (1 - Math.abs(x) / a) * e * (y >= 0 ? 1 : -1);
        }
        return (int)(x_target + width / 2.0);
    }
    public static int k_target(int j, int k, int width, int height) 
    {   
        double x = j - (width / 2.0);
        double y = k - (height / 2.0);
        double x_target, y_target;
        double a = width / 16.0;
        double b = width / 8.0;
        double c = width / 4.0;
        double d = height / 4.0;
        double e = height / 8.0;
        if (((Math.abs(x) / c) + (Math.abs(y) / d)) > 1.0)
        {
            x_target = x;
            y_target = y;
        }
        else if (((Math.abs(x) / a) + (Math.abs(y) / d)) > 1.0)
        {
            x_target = (x - (x >= 0 ? 1 : -1) * a * (1 - (y >= 0 ? 1 : -1) * y / d)) * (c - b) / (c - a) + (x >= 0 ? 1 : -1) * b * (1 - (y >= 0 ? 1 : -1) * y / d);
            y_target = y;
        }
        else
        {
            x_target = b * x / a;
            y_target = (d - e) * y / d + (1 - Math.abs(x) / a) * e * (y >= 0 ? 1 : -1);
        }
        return (int)(y_target + height / 2.0);
    }
    public static void main(String[] args) 
    {
        double pi = 4 * Math.atan(1.0);
        BufferedImage image;
        int width;
        int height;
        int iterations;
        int time = 15;
        double dt = 0.04; // (1/25)
        iterations = (int) (time / dt);
        try 
        {
            String destination = "/media/HardDrive/Projects/EM_Cloaking/";
            File input = new File(destination + "BaseImage.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            for (int i = 0; i < iterations; i++) 
            {
                for (int j = 0; j < width; j++) 
                {
                    for (int k = 0; k < height; k++) 
                    {
                        Color newColor = new Color(128, 0, 128);
                        if (Math.abs(j - width /  2.0) / (width / 8) + Math.abs(k - height /  2.0) / (height / 8) < 1)
                        {
                            newColor = new Color(41, 233, 144);
                        }
                        image.setRGB(j, k, ((int) (newColor.getRGB())));
                    }
                }
                for (int j = 0; j < width; j++) 
                {
                    for (int k = 0; k < height; k++) 
                    {   
                        double omega = 0.1;
                        double A = Math.sin((10.0 * pi / width) * j - omega * i);
                        int red = (int) (255.0 * (1 + A) / 2.0);
                        int green = 0;
                        int blue = (int) (255.0 * (1 - A) / 2.0);
                        Color newColor = new Color(red, green, blue);
                        image.setRGB(j_target(j, k, width, height), k_target(j, k, width, height), ((int) (newColor.getRGB())));
                    }
                }
                System.out.println("Rendered Frame" + (i + 1) + ".jpg");
                File output = new File(destination + "Renders/Frame" + (i + 1) + ".jpg");
                ImageIO.write(image, "jpg", output);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
