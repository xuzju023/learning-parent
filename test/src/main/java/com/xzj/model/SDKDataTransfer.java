package com.xzj.model;

import java.io.Serializable;

/**
 * 概述：封装的原始数据结构
 *
 * @author maoyuanming
 * @date 2019-08-16
 * @version v1.0.0
 */
public class SDKDataTransfer implements Serializable {
    
    /** 密钥： 没有加密则此值为空*/
    private String secretKey;

    /** 服务器时间：upload/config的服务器的时间 */
    private long serverTime;

    /** 压缩类型：0没有压缩，1，gzip */
    private int compressType;

    /** 请求原始信息 */
    private byte[] reqInfo;

    /** 请求类型：1：config 2：upload */
    private int reqType;

    /** 附加信息：格式规定(key冒号value分号key冒号value...)： key:value;key:value... */
    private String additionalInfo;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public int getCompressType() {
        return compressType;
    }

    public void setCompressType(int compressType) {
        this.compressType = compressType;
    }

    public byte[] getReqInfo() {
        return reqInfo;
    }

    public void setReqInfo(byte[] reqInfo) {
        this.reqInfo = reqInfo;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "SDKDataTransfer{" +
                "secretKey='" + secretKey + '\'' +
                ", serverTime=" + serverTime +
                ", compressType=" + compressType +
                ", reqType=" + reqType +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", reqInfo={...}" +
                '}';
    }
}
