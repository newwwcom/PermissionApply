package com.zsh.libpermissionwarp.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ZhouShaohua on 2018/8/21.
 */
public class ConvertUtil {

    public static List<String> StrArrayToList(String... strings) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            if (result.contains(s)) {
                continue;
            }
            result.add(s);
        }
        return result;
    }

    public static String[] ListToStrArray(List<String> set) {
        String[] strs = new String[set.size()];
        for (int i = 0; i < set.size(); i++) {
            strs[i] = set.get(i);
        }
        return strs;
    }
}
