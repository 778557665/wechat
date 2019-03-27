package com.wengzhoujun.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wengzhoujun.wechat.model.Message;
import com.wengzhoujun.wechat.model.ReturnMessageVo;
import com.wengzhoujun.wechat.service.TokenService;
import com.wengzhoujun.wechat.util.AddressUtils;
import com.wengzhoujun.wechat.util.HttpUtil;
import com.wengzhoujun.wechat.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    private final String TOKEN = "TCZH";

    private final String AesKey = "ixJh7tIRyVd1H8bSkgBzytnLMuI7brjbfTNpxLBjdWE";

    @Autowired
    private TokenService tokenService;

    @GetMapping("getToken")
    public String getToken() {
        try {
            return tokenService.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @GetMapping("test")
    public String test() {
        try {
            return "成了！";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/checkToken", method = {RequestMethod.POST, RequestMethod.GET})
    public String checkToken(HttpServletRequest request, @RequestBody Message message) {
        logger.info("[checkToken]{-----开始校验签名-----}");
        logger.info("[checkToken]{message:" + message.toString() + "}");
        String ip = IpUtils.getRealIp(request);
        logger.info("-----address-----:" + AddressUtils.getRealAddress(ip));
        logger.info("[checkToken]{ip:" + ip + "}");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        logger.info("[checkToken]{signature=" + signature + ",timestamp=" + timestamp + ",nonce=" + nonce + ",echostr=" + echostr + "}");
        // 将token、timestamp、nonce三个参数进行字典序排序,并拼接为一个字符串
        String sortStr = sort(TOKEN, timestamp, nonce);
        // 字符串进行shal加密
        String mySignature = shal(sortStr);
        logger.info("mySignature:" + mySignature);
        // 校验微信服务器传递过来的签名 和  加密后的字符串是否一致, 若一致则签名通过
        ReturnMessageVo returnMessageVo = new ReturnMessageVo();
        if (!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)) {
            String myUserName = message.getToUserName();
            String userOpenId = message.getFromUserName();
            String content = message.getContent();
            Long time = System.currentTimeMillis();
            String timeStr = time.toString().substring(0, 10);
            String replyString = "请说人话";
            try {
                replyString = smartRobotAutoReply(content);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("[checkToken]{-----出错了-----}");
            }
            returnMessageVo = new ReturnMessageVo(userOpenId, myUserName, Long.parseLong(timeStr), "text", replyString);
            logger.info("[checkToken]{returnMessageVo:" + returnMessageVo.toString() + "}");
            logger.info("[checkToken]{-----签名校验通过-----}");
            return returnMessageVo.toString();
        } else {
            logger.info("[checkToken]{-----校验签名失败-----}");
        }
        return returnMessageVo.toString();
    }

    private String smartRobotAutoReply(String content) throws Exception {
        Map<String, Object> map = new HashMap<>(2);
        map.put("key", "free");
        map.put("appid", 0);
        map.put("msg", content);
        String response = HttpUtil.httpGet(map, "http://api.qingyunke.com/api.php?");
        if (null != response) {
            JSONObject responseJson = JSONObject.parseObject(response);
            String replyString = responseJson.getString("content");
            if (null != replyString) {
                return replyString;
            }
        }
        return "我一下子反应不过来了，呜呜";
    }

    /**
     * 参数排序
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 字符串进行shal加密
     *
     * @param str
     * @return
     */
    public String shal(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
