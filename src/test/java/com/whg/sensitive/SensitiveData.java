package com.whg.sensitive;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SensitiveData {

    static TireTree tt;

    static {
        Set<String> set = new HashSet<>();
        String sens = FileUtil.readString("sens.txt", StandardCharsets.UTF_8);
        String[] split = sens.split("、");
        for (String s : split) {
            set.add(s.trim());
        }
        tt = new TireTree(new ArrayList<>(set), 256, 3);
    }

    public static void main(String[] args) {
        List<String> lines = ListUtil.of("朱德", "Crestbone");
        for (String line : lines) {
            List<String> strings = tt.find(line.trim());
            if(!strings.isEmpty()) {
                System.out.println(line + "\t" + strings);
            }
        }
    }

}
