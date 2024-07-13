package com.example.mycinema.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringToListUtil {
    public static List<Long> stringToList(String s){
        List<Long> collect = Arrays.stream(s.split(",")).map(Long::valueOf).collect(Collectors.toList());
        return collect;
    }

}
