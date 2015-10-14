package com.wx.qian.oauth.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.qian.common.utils.HttpClientUtil;
import com.wx.qian.redis.config.WxConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>
 * 微信公众号内获取openId,不显示用户授权页面
 *
 * @author <a href="mailto:qwx890206@gmail.com">wxqian</a>
 * @version v1.0, 2015/10/14
 */
@Controller
@RequestMapping("/oauth")
public class WxOauthController {

    private Logger LOGGER = LoggerFactory.getLogger(WxOauthController.class);

    @Autowired
    private WxConfig wxConfig;

    private static String WEIXIN_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base";

    private static final String WEIXIN_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private static final String BASE_URL = "http://your-base-url/";

    /**
     * 可以用 BASE_URL/oauth/init?url=encode来获取openId
     *
     * @param url
     * @return
     */
    @RequestMapping("/init")
    public String init(String url) {
        String redirectUrl = null;
        try {
            redirectUrl = String.format(WEIXIN_AUTHORIZE_URL, this.wxConfig.getAppId(), BASE_URL + "/oauth/weixin?url=" + URLEncoder.encode(url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "redirect:" + url;
        }
        return "redirect:" + redirectUrl;
    }


    /**
     * 返回openid
     *
     * @param url
     * @param code
     * @return
     */
    @RequestMapping("/weixin")
    public String weixin(String url, String code) {
        JSONObject json = request(WEIXIN_TOKEN_URL, this.wxConfig.getAppId(), this.wxConfig.getAppSecret(), code);
        String openId = json.getString("openid");
        return openId;
    }

    private JSONObject request(String url, String... args) {
        try {
            String requestUrl = String.format(url, args);
            String result = StringUtils.trim(HttpClientUtil.httpGet(requestUrl));
            if (StringUtils.isNotEmpty(result)) {
                JSONObject json = JSON.parseObject(result);
                if (!json.containsKey("errcode")) {
                    return json;
                }
                LOGGER.error("Weixin request error url: {} error: {}", requestUrl, json.toJSONString());
            }
            return new JSONObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
