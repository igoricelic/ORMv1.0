package com.orm.v_1.ORM.tests.test4;

import com.orm.v_1.ORM.logic.repositories.OrmRepository;
import com.orm.v_1.ORM.tests.test4.entites.Channel;

public interface ChannelDao extends OrmRepository<Channel> {
	
	public Channel findByNameEq (String name);

}
