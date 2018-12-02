package com.orm.v_1.ORM.tests.sepcification.test1;

import com.orm.v_1.ORM.configuration.OrmConfig;
import com.orm.v_1.ORM.logic.repositories.CrudRepository;
import com.orm.v_1.ORM.logic.repositories.OrmRepository;
import com.orm.v_1.ORM.logic.repositories.PagingAndSortingRepository;
import com.orm.v_1.ORM.logic.repositories.Repository;
import com.orm.v_1.ORM.tests.sepcification.test1.entites.Channel;

public class MainTest2 {
	
	public static void main(String[] args) {
		try {
			String path = "/Users/igoricelic/Documents/workspace-sts-3.8.4.RELEASE/ORM/src/main/java/com/orm/v_1/ORM/tests/sepcification/test1/config.orm";
			OrmConfig.setConfiguration(path);
			
			Repository<Channel> repoChannelDao = (Repository<Channel>) OrmConfig.buildRepository(Channel.class);
			
			CrudRepository<Channel> crudChannelDao = (CrudRepository<Channel>) OrmConfig.buildRepository(Channel.class);
			
			PagingAndSortingRepository<Channel> pagingChannelDao = (PagingAndSortingRepository<Channel>) OrmConfig.buildRepository(Channel.class);
			
			OrmRepository<Channel> ormChannelDao = (OrmRepository<Channel>) OrmConfig.buildRepository(Channel.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
