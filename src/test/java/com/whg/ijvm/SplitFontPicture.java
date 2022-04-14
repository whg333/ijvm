package com.whg.ijvm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 切割fonts下的图片，rectList源自其xml文件
 */
public class SplitFontPicture {

    private static class Rect{
        String name;
        int x;
        int y;
        int w;
        int h;

        public Rect(String name, int x, int y, int w, int h) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    private static final String DIR = "D:\\whg\\temp";

    public static void main(String[] args) throws Exception {
        String name = "damNum-export";
        String imgPath = DIR+"\\"+name+".png";
        BufferedImage img = ImageIO.read(new FileInputStream(imgPath));

        List<Rect> rectList = new ArrayList<>();
        rectList.add(new Rect("0.png", 6 , 8 ,5,8));
        rectList.add(new Rect("1.png", 12 , 18 ,5,8));
        rectList.add(new Rect("2.png", 12 , 9 ,5,8));
        rectList.add(new Rect("3.png", 12 , 0 ,5,8));
        rectList.add(new Rect("4.png", 6 , 17 ,5,8));
        rectList.add(new Rect("5.png", 18 , 0 ,5,8));
        rectList.add(new Rect("6.png", 6 , 0 ,5,8));
        rectList.add(new Rect("7.png", 0 , 18 ,5,8));
        rectList.add(new Rect("8.png", 0 , 9 ,5,8));
        rectList.add(new Rect("9.png", 0 , 0 ,5,8));

        for(Rect rect: rectList) {
            // System.out.println(String.format("x:%d, y:%d, w:%d, h:%d", x, y, w, h));
            BufferedImage newImg = cat(rect.x, rect.y, rect.w, rect.h, img);
            ImageIO.write(newImg, "PNG", new File(DIR + "\\" + name + "\\" + rect.name));
        }
    }

    private static BufferedImage cat(int x, int y, int wight, int hight,
                                     BufferedImage img) {
        int[] simgRgb = new int[wight * hight];
        img.getRGB(x, y, wight, hight, simgRgb, 0, wight);
        BufferedImage newImage = new BufferedImage(wight, hight,
                BufferedImage.TYPE_INT_ARGB);
        newImage.setRGB(0, 0, wight, hight, simgRgb, 0, wight);
//         try {
//                ImageIO.write(newImage, "PNG", new File("aa.png"));
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        return newImage;
    }

}
