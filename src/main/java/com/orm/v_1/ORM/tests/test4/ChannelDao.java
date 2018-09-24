package com.orm.v_1.ORM.tests.test4;

import com.orm.v_1.ORM.logic.repositories.PagingRepository;
import com.orm.v_1.ORM.tests.test4.entites.Channel;

public interface ChannelDao extends PagingRepository<Channel> {
	
	public Channel findByNameEq (String name);

}
