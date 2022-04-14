package com.whg.ijvm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 * 切割TexturePacker打包的文件，例如img下的characters图片
 */
public class SplitTexturePacker {

    private static final String DIR = "D:\\whg\\temp";

    public static void main(String[] args) throws IOException {
        File dir = new File(DIR);
        if(!dir.exists()){
            System.err.println("dirPath ["+DIR+"] not exists!");
            return;
        }
        Collection<File> jsonFiles =  FileUtils.listFiles(dir, new String[]{"json"}, false);
        for(File jsonFile: jsonFiles){
            String jsonName = jsonFile.getName();
            String name = jsonName.substring(0, jsonName.lastIndexOf(".json"));
            splitFor(name);
            System.out.println(jsonName);
        }
    }

    private static void splitFor(String name) throws IOException {
        // String name = "characters";

        String jsonPath = DIR+"\\"+name+".json";
        File jsonFile = new File(jsonPath);
        String jsonStr = FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8);
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        // System.out.println(jsonObj);
        JSONArray jsonArr = jsonObj.getJSONArray("textures").getJSONObject(0).getJSONArray("frames");
        // System.out.println(jsonArr);

        String imgPath = DIR+"\\"+name+".png";
        BufferedImage img = ImageIO.read(new FileInputStream(imgPath));
        File splitDir = new File(DIR+"\\"+name);
        if(!splitDir.exists()){
            if(!splitDir.mkdir()){
                System.err.println("created directory ["+splitDir.getName()+"] error!");
                return;
            }
        }

        for(int i=0;i<jsonArr.size();i++){
            JSONObject frame = jsonArr.getJSONObject(i);
            String fileName = frame.getString("filename");
            JSONObject sprite = frame.getJSONObject("frame");
            int x = sprite.getInteger("x");
            int y = sprite.getInteger("y");
            int w = sprite.getInteger("w");
            int h = sprite.getInteger("h");

            // System.out.println(String.format("x:%d, y:%d, w:%d, h:%d", x, y, w, h));
            BufferedImage newImg = cat(x, y, w, h, img);
            ImageIO.write(newImg, "PNG", new File(DIR+"\\"+name+"\\"+fileName));
        }
    }

    @Deprecated
    private static void splitImage() throws IOException {
        //String originalImg = "C:\\img\\split\\a380_1280x1024.jpg";
        String originalImg = "F:\\images\\school\\-11ff2b32525fd5f2.jpg";
        // 读入大图
        File file = new File(originalImg);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);

        // 分割成4*4(16)个小图
        int rows = 4;
        int cols = 4;
        int chunks = rows * cols;

        // 计算每个小图的宽度和高度
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;

        int count = 0;
        BufferedImage[] imgs = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //设置小图的大小和类型
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                //写入图像内容
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }

        // 输出小图
        for (int i = 0; i < imgs.length; i++) {
            //ImageIO.write(imgs[i], "jpg", new File("C:\\img\\split\\img" + i + ".jpg"));
            ImageIO.write(imgs[i], "jpg", new File("E:\\temp" + i + ".jpg"));
        }

        System.out.println("完成分割！");
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
