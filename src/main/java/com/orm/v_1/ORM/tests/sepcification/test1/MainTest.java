package com.orm.v_1.ORM.tests.sepcification.test1;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.ORM.configuration.OrmConfig;
import com.orm.v_1.ORM.datastructure.Page;
import com.orm.v_1.ORM.datastructure.PageRequest;
import com.orm.v_1.ORM.logic.repositories.OrmRepository;
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
			
			OrmRepository<Channel> channelDao = (OrmRepository<Channel>) OrmConfig.buildRepository(Channel.class);
			
			Specification specification1 = Specification.builder()
					.addCondition("name", "RTS 1", Comparator.Equality)
					.addCondition(Operator.OR, "lcn", 100, Comparator.LessThanEquality)
					.build();
			
			Optional<List<Channel>> optChannels = channelDao.findBySpecification(specification1);
			System.out.println(optChannels);
			
			Specification specification2 = Specification.builder()
					.addCondition("name", "RTS 2", Comparator.Equality)
					.addCondition(Operator.OR, "lcn", 20, Comparator.GreaterThanEquality)
					.addCondition(Operator.AND, "lcn", 30, Comparator.LessThan)
					.build();
			
			Optional<List<Channel>> optChannels2 = channelDao.findBySpecification(specification2);
			System.out.println(optChannels2);

			Specification specification3 = Specification.builder()
					.addCondition("name", "RTS 2", Comparator.Equality)
					.addCondition(Operator.AND, "created", new Date(), Comparator.After)
					.build();
			
			Optional<List<Channel>> optChannels3 = channelDao.findBySpecification(specification3);
			System.out.println(optChannels3);
			
			Specification specification4 = Specification.builder()
					.addCondition("name", "RTS 2", Comparator.Equality)
					.addCondition(Operator.AND, "created", new Date(), Comparator.Before)
					.build();
			
			Optional<List<Channel>> optChannels4 = channelDao.findBySpecification(specification4);
			System.out.println(optChannels4);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
