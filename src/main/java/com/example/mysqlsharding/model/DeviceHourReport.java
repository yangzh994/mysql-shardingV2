package com.example.mysqlsharding.model;


import java.io.Serializable;
import java.util.Date;

public class DeviceHourReport  implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 变电站id
     */
    private Long substationId;

    /**
     * 监控指标
     */
    private String monitorIdx;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 平均值
     */
    private Double value;

    /**
     * 
     */
    private Double maxValue;

    /**
     * 
     */
    private Double minValue;

    /**
     * 条数
     */
    private Integer count;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * sta_device_hour_report
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     * @return id 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeviceHourReport{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", substationId=" + substationId +
                ", monitorIdx='" + monitorIdx + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", value=" + value +
                ", maxValue=" + maxValue +
                ", minValue=" + minValue +
                ", count=" + count +
                ", createDate=" + createDate +
                '}';
    }

    /**
     * 设备id
     * @return device_id 设备id
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * 设备id
     * @param deviceId 设备id
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 变电站id
     * @return substation_id 变电站id
     */
    public Long getSubstationId() {
        return substationId;
    }

    /**
     * 变电站id
     * @param substationId 变电站id
     */
    public void setSubstationId(Long substationId) {
        this.substationId = substationId;
    }

    /**
     * 监控指标
     * @return monitor_idx 监控指标
     */
    public String getMonitorIdx() {
        return monitorIdx;
    }

    /**
     * 监控指标
     * @param monitorIdx 监控指标
     */
    public void setMonitorIdx(String monitorIdx) {
        this.monitorIdx = monitorIdx == null ? null : monitorIdx.trim();
    }

    /**
     * 开始时间
     * @return start_time 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 平均值
     * @return value 平均值
     */
    public Double getValue() {
        return value;
    }

    /**
     * 平均值
     * @param value 平均值
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * 
     * @return max_value 
     */
    public Double getMaxValue() {
        return maxValue;
    }

    /**
     * 
     * @param maxValue 
     */
    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 
     * @return min_value 
     */
    public Double getMinValue() {
        return minValue;
    }

    /**
     * 
     * @param minValue 
     */
    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    /**
     * 条数
     * @return count 条数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 条数
     * @param count 条数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DeviceHourReport other = (DeviceHourReport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getSubstationId() == null ? other.getSubstationId() == null : this.getSubstationId().equals(other.getSubstationId()))
            && (this.getMonitorIdx() == null ? other.getMonitorIdx() == null : this.getMonitorIdx().equals(other.getMonitorIdx()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getMaxValue() == null ? other.getMaxValue() == null : this.getMaxValue().equals(other.getMaxValue()))
            && (this.getMinValue() == null ? other.getMinValue() == null : this.getMinValue().equals(other.getMinValue()))
            && (this.getCount() == other.getCount())
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getSubstationId() == null) ? 0 : getSubstationId().hashCode());
        result = prime * result + ((getMonitorIdx() == null) ? 0 : getMonitorIdx().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getMaxValue() == null) ? 0 : getMaxValue().hashCode());
        result = prime * result + ((getMinValue() == null) ? 0 : getMinValue().hashCode());
        result = prime * result + getCount();
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    /**
     * sta_device_hour_report
     */
    public enum Column {
        id("id"),
        deviceId("device_id"),
        substationId("substation_id"),
        monitorIdx("monitor_idx"),
        startTime("start_time"),
        endTime("end_time"),
        value("value"),
        maxValue("max_value"),
        minValue("min_value"),
        count("count"),
        createDate("create_date");

        /**
         * sta_device_hour_report
         */
        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Column(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}