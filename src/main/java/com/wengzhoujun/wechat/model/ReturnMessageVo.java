package com.wengzhoujun.wechat.model;

public class ReturnMessageVo {

    private String toUserName;

    private String fromUserName;

    private Long createTime;

    private String msgType;

    private String content;

    public ReturnMessageVo() {
    }

    public ReturnMessageVo(String toUserName, String fromUserName, Long createTime, String msgType, String content) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
        this.content = content;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("<xml>");
        str.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
        str.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
        str.append("<CreateTime>" + createTime + "</CreateTime>");
        str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
        str.append("<Content><![CDATA[" + content + "]]></Content>");
        str.append("</xml>");
        return str.toString();
    }
}
