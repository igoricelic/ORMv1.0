package com.orm.v_1.ORM.tests.test4;

import com.orm.v_1.ORM.configuration.OrmConfig;
import com.orm.v_1.ORM.datastructure.Page;
import com.orm.v_1.ORM.datastructure.PageRequest;
import com.orm.v_1.ORM.tests.test4.entites.Channel;

public class MainTest {
	
	public static void main(String[] args) {
		try {
			String path = "/Users/igoricelic/Documents/workspace-sts-3.8.4.RELEASE/ORM/src/main/java/com/orm/v_1/ORM/tests/test4/config.orm";
			OrmConfig.setConfiguration(path);
			
			ChannelDao channelDao = (ChannelDao) OrmConfig.buildProxy(ChannelDao.class, Channel.class);
			
			Page<Channel> page1 = channelDao.findAll(PageRequest.build(0, 5));
			System.out.println(page1);
			
			Page<Channel> page2 = channelDao.findAll(PageRequest.build(1, 5));
			System.out.println(page2);
			
			Page<Channel> page3 = channelDao.findAll(PageRequest.build(0, 100));
			System.out.println(page3);
			
			Page<Channel> page4 = channelDao.findAll(PageRequest.build(8, 100));
			System.out.println(page4);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
