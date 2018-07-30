package com.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
    public static void addTextToImage(String inputImagePath,String outputPath,String text,Color color) throws IOException {
        File file = new File(inputImagePath);
        Image image = ImageIO.read(file);

        BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 =  bi.createGraphics();
        g2.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
        g2.setColor(color);
        g2.setFont(new Font("宋体",Font.BOLD,20));
        g2.drawString(text, 0, image.getHeight(null));
        g2.dispose();
        ImageIO.write(bi, "JPG", new FileOutputStream(outputPath));
    }

}
