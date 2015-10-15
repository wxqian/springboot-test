package com.wx.qian.pay.enums;

/**
 * 支持手机网页，pc网页，app
 * Created by augustus on 15-10-15.
 */
public enum PayChannel {
    ALIPAY_WAP("alipay_wap")//支付宝手机网页支付
    ,WX_PUB("wx_pub")//微信公众账号支付
    ,UPACP_WAP("upacp_wap")//银联全渠道手机网页支付
    ,UPMP_WAP("upmp_wap")//银联手机网页支付
    ,BFB_WAP("bfb_wap")//百度钱包手机网页支付
    ,YEEPAY_WAP("yeepay_wap")//易宝移动端网页支付
    ,JDPAY_WAP("jdpay_wap")//京东手机网页支付
    ,ALIPAY("alipay")//支付宝app支付
    ,WX("wx")//微信app支付
    ,UPACP("upacp")//银联全渠道app支付
    ,UPMP("upmp")//银联app支付
    ,BFB("bfb")//百度钱包app支付
    ,APPLE_PAY("apple_pay")//ios
    ,ALIPAY_PC_DIRECT("alipay_pc_direct")//支付宝PC网页支付
    ,UPACP_PC("upacp_pc")//银联PC支付
    ,ALIPAY_QR("alipay_qr")//支付宝扫码支付
    ,WX_PUB_QR("wx_pub_qr")//微信公众号扫码支付
    ;
    private String title;

    PayChannel(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
