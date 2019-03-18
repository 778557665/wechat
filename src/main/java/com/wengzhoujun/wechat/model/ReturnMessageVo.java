package com.wengzhoujun.wechat.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "xml")
public class ReturnMessageVo {

    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    @JacksonXmlProperty(localName = "CreateTime")
    private Long createTime;

    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;

    @JacksonXmlProperty(localName = "Content")
    private String content;

    public ReturnMessageVo() {
    }

    public ReturnMessageVo(String toUserName, String fromUserName, Long createTime, String msgType, String content) {
        this.toUserName = "<![CDATA[" + toUserName + "]]";
        this.fromUserName = "<![CDATA[" + fromUserName + "]]";
        this.createTime = createTime;
        this.msgType = "<![CDATA[" + msgType + "]]";
        this.content = "<![CDATA[" + content + "]]";
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = "<![CDATA[" + toUserName + "]]";
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = "<![CDATA[" + fromUserName + "]]";
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
        this.msgType = "<![CDATA[" + msgType + "]]";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = "<![CDATA[" + content + "]]";
    }

    @Override
    public String toString() {
        return "ReturnMessageVo{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
