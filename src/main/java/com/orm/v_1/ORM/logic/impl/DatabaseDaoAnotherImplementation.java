package com.orm.v_1.ORM.logic.impl;

import java.util.List;
import java.util.Optional;

import com.orm.v_1.ORM.datastructure.Page;
import com.orm.v_1.ORM.datastructure.PageRequest;
import com.orm.v_1.ORM.datastructure.SortRequest;
import com.orm.v_1.ORM.logic.repositories.ProxyRepository;
import com.orm.v_1.ORM.queryspecification.Specification;

public class DatabaseDaoAnotherImplementation<T> implements ProxyRepository<T> {

	@Override
	public Optional<T> findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<T>> findBySpecification(Specification specification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Page<T>> findBySpecification(Specification specification, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countBySpecification(Specification specification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBySpecification(Specification specification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsBySpecification(Specification specification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(SortRequest sortRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> findAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> findAll(PageRequest pageRequest, SortRequest sortRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteOne(T object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteMode(List<T> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T saveOne(T object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean saveMore(List<T> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T updateOne(T object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateMore(List<T> objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByNativeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean executeNativeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T executePreparedQueryOne(String preparedQuery, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> executeByPreparedQueryMore(String preparedQuery, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
