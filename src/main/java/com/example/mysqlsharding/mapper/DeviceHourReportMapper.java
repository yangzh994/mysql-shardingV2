package com.example.mysqlsharding.mapper;


import com.example.mysqlsharding.anno.Sharding;
import com.example.mysqlsharding.model.DeviceHourReport;
import com.example.mysqlsharding.model.DeviceHourReportExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
@Sharding
public interface DeviceHourReportMapper {
    long countByExample(DeviceHourReportExample example);

    int deleteByExample(DeviceHourReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeviceHourReport record);

    int insertSelective(DeviceHourReport record);

    List<DeviceHourReport> selectByExample(DeviceHourReportExample example);

    DeviceHourReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeviceHourReport record, @Param("example") DeviceHourReportExample example);

    int updateByExample(@Param("record") DeviceHourReport record, @Param("example") DeviceHourReportExample example);

    int updateByPrimaryKeySelective(DeviceHourReport record);

    int updateByPrimaryKey(DeviceHourReport record);

    int batchInsert(@Param("list") List<DeviceHourReport> list);

    int batchInsertSelective(@Param("list") List<DeviceHourReport> list, @Param("selective") DeviceHourReport.Column... selective);

    int filingAbnormal(@Param("endTime") Date endTime);
    int deletAbnormalFiled(@Param("endTime") Date endTime);

    int filingPushMsg(@Param("endTime") Date endTime);
    int deletPushMsgFiled(@Param("endTime") Date endTime);


    @Select("SELECT * FROM STA_DEVICE_HOUR_REPORT LIMIT 100000,10")
    List<DeviceHourReport> selectDeviceHourReportByLimit();
}