package com.orm.v_1.ORM.tests.test4;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.ORM.configuration.OrmConfig;
import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.dsl.DslProviderService;
import com.orm.v_1.ORM.queryspecification.dsl.DslProviderServiceImpl;
import com.orm.v_1.ORM.tests.test4.entites.Channel;

public class TestQueryDsl {
	
	public static void main(String[] args) {
		try {
			String path = "/Users/igoricelic/Documents/workspace-sts-3.8.4.RELEASE/ORM/src/main/java/com/orm/v_1/ORM/tests/test4/config.orm";
			OrmConfig.setConfiguration(path);
			
			ChannelDao channelDao = (ChannelDao) OrmConfig.buildProxy(ChannelDao.class, Channel.class);
			
			DslProviderService dslProviderService = new DslProviderServiceImpl();
			
			Specification specification1 = dslProviderService.provide("name:RTS 1");
			Optional<List<Channel>> channels1 = channelDao.findBySpecification(specification1);
			
			Specification specification2 = dslProviderService.provide("name~R");
			Optional<List<Channel>> channels2 = channelDao.findBySpecification(specification2);
			
			Specification specification3 = dslProviderService.provide("name~R,lcn:>100");
			Optional<List<Channel>> channels3 = channelDao.findBySpecification(specification3);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
