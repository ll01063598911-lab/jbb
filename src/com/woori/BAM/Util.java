package com.woori.BAM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String getDateStr() { // static 메소드 특징 학습내용을 기억하세요

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        return formattedNow;   // yyyy-MM-dd HH:mm:ss 생성
    }
}