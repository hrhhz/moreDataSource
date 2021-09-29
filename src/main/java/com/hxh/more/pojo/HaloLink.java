package com.hxh.more.pojo;

import lombok.Data;

@Data
public class HaloLink {

    private Integer linkId;

    private String linkDesc;

    private String linkName;

    private String linkPic;

    private String linkUrl;

    @Override
    public String toString() {
        return "HaloLink{" +
                "linkId=" + linkId +
                ", linkDesc='" + linkDesc + '\'' +
                ", linkName='" + linkName + '\'' +
                ", linkPic='" + linkPic + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }
}
