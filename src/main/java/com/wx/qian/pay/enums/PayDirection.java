package com.wx.qian.pay.enums;

/**
 * Created by augustus on 15-10-15.
 */
public enum PayDirection {
    POSITIVE("正向"),NEGATIVE("反向");

    private String title;

    PayDirection(String title){
        this.title = title;
    }
}
