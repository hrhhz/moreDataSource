package com.hxh.more.dao.one;

import com.hxh.more.pojo.HaloLink;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HaloLinkDAO {

    @Select("select * from halo_link")
    List<HaloLink> listAll();
}
