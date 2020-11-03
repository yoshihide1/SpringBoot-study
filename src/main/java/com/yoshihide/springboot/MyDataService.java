package com.yoshihide.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoshihide.springboot.mapper.MyDataMapper;

@Service
public class MyDataService {
// MyBatis

	@Autowired
	MyDataMapper myDataMapper;

	@Transactional(readOnly = true)
	public List<MyData> userList() {
		return myDataMapper.findAll();
	}

	@Transactional
	public List<MyData> saveUser(String name, String mail, int age) {
		return myDataMapper.save(name, mail, age);
	}

	public List<MyData> findName(String str) {
		return myDataMapper.findByName(str);
	}

}
