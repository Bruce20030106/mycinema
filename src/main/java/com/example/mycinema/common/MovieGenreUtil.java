package com.example.mycinema.common;

import com.example.mycinema.enums.MovieGenre;

import java.util.HashMap;
import java.util.Map;

public class MovieGenreUtil {

    private static final Map<Integer, MovieGenre> valueMap = new HashMap<>();

    static {
        for (MovieGenre genre : MovieGenre.values()) {
            valueMap.put(genre.getValue(), genre);
        }
    }

    /**
     * 根据枚举值获取 MovieGenre 枚举
     * @param value 枚举值
     * @return 对应的 MovieGenre 枚举，如果不存在则返回 null
     */
    public static MovieGenre getByValue(int value) {
        return valueMap.get(value);
    }

    /**
     * 根据 MovieGenre 枚举获取其值
     * @param genre MovieGenre 枚举
     * @return 对应的值，如果不存在则返回 null
     */
    public static Integer getValue(MovieGenre genre) {
        return genre != null ? genre.getValue() : null;
    }
}
