package com.yoshihide.springboot.mapper;

import java.util.List;
import java.util.Optional;

import com.yoshihide.springboot.MyData;

public interface MyDataMapper {
	List<MyData> findAll();

	List<MyData> findByName(String str);

	Optional<MyData> findById(long id);

	List<MyData> update(MyData mydata);

	Optional<MyData> deleteById(long id);

	List<MyData> save(String name, String mail, int age);

}
