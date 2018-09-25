package com.orm.v_1.ORM.tests.sepcification.test1;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.ORM.configuration.OrmConfig;
import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.builder.SpecificationBuilder;
import com.orm.v_1.ORM.queryspecification.enums.Comparator;
import com.orm.v_1.ORM.queryspecification.enums.Operator;
import com.orm.v_1.ORM.tests.sepcification.test1.entites.Channel;

public class MainTest {
	
	public static void main(String[] args) {
		try {
			String path = "/Users/igoricelic/Documents/workspace-sts-3.8.4.RELEASE/ORM/src/main/java/com/orm/v_1/ORM/tests/sepcification/test1/config.orm";
			OrmConfig.setConfiguration(path);
			
			ChannelDao channelDao = (ChannelDao) OrmConfig.buildProxy(ChannelDao.class, Channel.class);
			
			Specification specification1 = new SpecificationBuilder()
					.addCondition("name", "RTS 1", Comparator.Equality)
					.addCondition(Operator.OR, "lcn", 100, Comparator.LessThanEquality)
					.build();
			
			Optional<List<Channel>> optChannels = channelDao.findBySpecification(specification1);
			
			if(optChannels.isPresent()) {
				List<Channel> channels = optChannels.get();
				System.out.println(channels);
			}
			
			Specification specification2 = new SpecificationBuilder()
					.addCondition("name", "RTS 2", Comparator.Equality)
					.addCondition(Operator.OR, "lcn", 20, Comparator.GreaterThanEquality)
					.addCondition(Operator.AND, "lcn", 30, Comparator.LessThan)
					.build();
			
			Optional<List<Channel>> optChannels2 = channelDao.findBySpecification(specification2);
			
			if(optChannels2.isPresent()) {
				List<Channel> ch = optChannels2.get();
				System.out.println(ch);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
