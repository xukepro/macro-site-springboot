package com.xuke.macrosite.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "发表评论的用户id")
    private Integer fromId;

    @ApiModelProperty(value = "评论所在文章id")
    private Integer aid;

    @ApiModelProperty(value = "该评论所在评论id,最多两层")
    private Integer parentId;

    @ApiModelProperty(value = "接收用户评论id")
    private Integer toId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fromId=").append(fromId);
        sb.append(", aid=").append(aid);
        sb.append(", parentId=").append(parentId);
        sb.append(", toId=").append(toId);
        sb.append(", content=").append(content);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}