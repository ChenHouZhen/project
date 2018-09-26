package com.chenhz.tpos.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 职位表
 * </p>
 *
 * @author chz
 * @since 2018-09-26
 */
@TableName("tb_position")
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 职位名称
     */
    private String name;
    /**
     * 职位类型编号
     */
    private Integer positionTypeId;
    /**
     * 所有职位类型编号
     */
    private String positionTypeAllId;
    /**
     * 省编号
     */
    private Integer province;
    /**
     * 市编号
     */
    private Integer city;
    /**
     * 区编号
     */
    private Integer conty;
    /**
     * 工作性质
     */
    private Integer workNature;
    /**
     * 工作经验
     */
    private Integer workExperience;
    /**
     * 学历
     */
    private Integer educationExperience;
    /**
     * 薪资最小值
     */
    private Integer minSalary;
    /**
     * 薪资最大值
     */
    private Integer maxSalary;
    /**
     * 公司编号
     */
    private Integer companyId;
    /**
     * 标签id组 最多5个
     */
    private String labelIds;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 刷新时间
     */
    private Date refreshTime;
    /**
     * 面试人数
     */
    private Integer interviewNum;
    /**
     * -1:禁用 0:关闭 1正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 修改人
     */
    private Long modifiedUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPositionTypeId() {
        return positionTypeId;
    }

    public void setPositionTypeId(Integer positionTypeId) {
        this.positionTypeId = positionTypeId;
    }

    public String getPositionTypeAllId() {
        return positionTypeAllId;
    }

    public void setPositionTypeAllId(String positionTypeAllId) {
        this.positionTypeAllId = positionTypeAllId;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getConty() {
        return conty;
    }

    public void setConty(Integer conty) {
        this.conty = conty;
    }

    public Integer getWorkNature() {
        return workNature;
    }

    public void setWorkNature(Integer workNature) {
        this.workNature = workNature;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }

    public Integer getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(Integer educationExperience) {
        this.educationExperience = educationExperience;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }

    public Integer getInterviewNum() {
        return interviewNum;
    }

    public void setInterviewNum(Integer interviewNum) {
        this.interviewNum = interviewNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(Long modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @Override
    public String toString() {
        return "Position{" +
        ", id=" + id +
        ", name=" + name +
        ", positionTypeId=" + positionTypeId +
        ", positionTypeAllId=" + positionTypeAllId +
        ", province=" + province +
        ", city=" + city +
        ", conty=" + conty +
        ", workNature=" + workNature +
        ", workExperience=" + workExperience +
        ", educationExperience=" + educationExperience +
        ", minSalary=" + minSalary +
        ", maxSalary=" + maxSalary +
        ", companyId=" + companyId +
        ", labelIds=" + labelIds +
        ", userId=" + userId +
        ", refreshTime=" + refreshTime +
        ", interviewNum=" + interviewNum +
        ", status=" + status +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", modifiedTime=" + modifiedTime +
        ", modifiedUser=" + modifiedUser +
        "}";
    }
}
