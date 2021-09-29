package com.hxh.more.dao.one;

import com.hxh.more.dao.two.DeviceDAO;
import com.hxh.more.pojo.Device;
import com.hxh.more.pojo.HaloLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HaloLinkDAOTest {
    @Autowired
    private HaloLinkDAO haloLinkDAO;

    @Autowired
    private DeviceDAO deviceDAO;

    @Test
    public void getAll() {
        List<HaloLink> haloLinks = haloLinkDAO.listAll();
        haloLinks.forEach(System.out::println);
        List<Device> all = deviceDAO.findAll();
        all.forEach(System.out::println);
    }

}