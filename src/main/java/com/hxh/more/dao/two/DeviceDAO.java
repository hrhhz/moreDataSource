package com.hxh.more.dao.two;

import com.hxh.more.pojo.Device;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeviceDAO {

    @Select("select * from devices")
    List<Device> findAll();
}
