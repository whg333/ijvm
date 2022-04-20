package com.whg.ijvm;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 * 先自己手动拷贝目录，指定拷贝后的目录，执行此程序替换包名，避免手动一个个文件替换修改
 * TODO 可以把拷贝目录也加上，目前是自己手动拷贝才执行此程序替换包名
 */
public class CopyChapter {

    public static void main(String[] args) throws IOException {
        if(args.length <= 0){
            throw new RuntimeException("第一个参数为目录路径，例如 D:\\copyTest\\ch08");
        }
        String dir = args[0];
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            throw new RuntimeException("要拷贝的目录["+dir+"]不存在");
        }
        Collection<File> files = FileUtils.listFiles(dirFile, new String[]{"java"}, true);
        for(File file: files){
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            // System.out.println(content);
            content = content.replaceAll("ch10", "ch11");
            System.out.println(file.getName());
            FileUtils.write(file, content, StandardCharsets.UTF_8);
        }
    }

}
