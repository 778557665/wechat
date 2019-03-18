package com.wengzhoujun.wechat.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name="IpWhite")
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"toUserName","fromUserName","createTime","msgType","content","msgId"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class Message implements Serializable{

    private static final long serialVersionUID = 7786111704039948377L;

    @XmlAttribute
    private String ToUserName;

    @XmlAttribute
    private String FromUserName;

    @XmlAttribute
    private Long CreateTime;

    @XmlAttribute
    private String MsgType;

    @XmlAttribute
    private String Content;

    @XmlAttribute
    private Long MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                ", Content='" + Content + '\'' +
                ", MsgId=" + MsgId +
                '}';
    }
}
