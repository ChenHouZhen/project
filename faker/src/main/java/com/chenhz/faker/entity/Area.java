package com.chenhz.faker.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 城市区域表
 * </p>
 *
 * @author chz
 * @since 2018-09-20
 */
@TableName("tb_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    /**
     * 父级编号
     */
    private Integer parentId;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private Long modifiedUser;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 0未删除 1删除
     */
    private Integer delFlag;
    /**
     * 0不是 1是
     */
    private Integer isHot;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(Long modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    @Override
    public String toString() {
        return "Area{" +
        ", id=" + id +
        ", name=" + name +
        ", parentId=" + parentId +
        ", level=" + level +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", modifiedUser=" + modifiedUser +
        ", modifiedTime=" + modifiedTime +
        ", delFlag=" + delFlag +
        ", isHot=" + isHot +
        "}";
    }
}
