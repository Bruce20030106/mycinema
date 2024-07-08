package com.example.mycinema.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MovieGenre {
    ACTION(1, "动作"),
    DRAMA(2, "剧情"),
    COMEDY(3, "喜剧"),
    HORROR(4, "恐怖"),
    SCIFI(5, "科幻"),
    ROMANCE(6, "爱情"),
    THRILLER(7, "惊悚"),
    ANIMATION(8, "动画"),
    DOCUMENTARY(9, "纪录片"),
    FANTASY(10, "奇幻"),
    ADVENTURE(11, "冒险"),
    MYSTERY(12, "悬疑"),
    CRIME(13, "犯罪"),
    MUSICAL(14, "音乐"),
    WAR(15, "战争"),
    BIOGRAPHY(16, "传记");

    @EnumValue
    private final int value;

    @JsonValue
    private final String desc;

    MovieGenre(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
