package com.zsh.libpermissiom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ZhouShaohua on 2018/8/18.
 */
public class ConvertUtil {

    /*package*/
    static Set<String> StrArrayToSet(String... strings) {
        Set<String> result = new HashSet<>();
        for (String s : strings) {
            if (result.contains(s)) {
                continue;
            }
            result.add(s);
        }
        return result;
    }

    /*package*/
    static String[] SetToStrArray(Set<String> set) {
        String[] strs = new String[set.size()];
        for (int i = 0; i < set.size(); i++) {

        }
        int index = 0;
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            strs[index] = iterator.next();
            index++;
        }
        return strs;
    }
}
