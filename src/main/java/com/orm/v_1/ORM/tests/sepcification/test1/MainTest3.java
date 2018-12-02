package com.orm.v_1.ORM.tests.sepcification.test1;

import com.orm.v_1.ORM.configuration.OrmConfig;
import com.orm.v_1.ORM.logic.repositories.OrmRepository;
import com.orm.v_1.ORM.queryspecification.Specification;
import com.orm.v_1.ORM.queryspecification.enums.Comparator;
import com.orm.v_1.ORM.queryspecification.enums.Operator;
import com.orm.v_1.ORM.tests.sepcification.test1.entites.Channel;

public class MainTest3 {
	
	public static void main(String[] args) {
		try {
			String path = "/Users/igoricelic/Documents/workspace-sts-3.8.4.RELEASE/ORM/src/main/java/com/orm/v_1/ORM/tests/sepcification/test1/config.orm";
			OrmConfig.setConfiguration(path);
			
			
			OrmRepository<Channel> channelDao = (OrmRepository<Channel>) OrmConfig.buildRepository(Channel.class);
			
			Specification specification1 = Specification.builder()
					.addCondition("name", "RTS 1", Comparator.Equality)
					.addCondition(Operator.OR, "lcn", 100, Comparator.LessThanEquality)
					.build();
			
			channelDao.findBySpecification(specification1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
