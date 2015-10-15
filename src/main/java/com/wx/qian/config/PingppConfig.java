package com.wx.qian.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by augustus on 15-10-15.
 */
@ConfigurationProperties(prefix = "pay.pingpp")
public class PingppConfig {
    private String apiKey;

    private String payId;

    private String notifyUrl;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
