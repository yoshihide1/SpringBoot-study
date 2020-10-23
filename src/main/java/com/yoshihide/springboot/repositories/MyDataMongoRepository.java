package com.yoshihide.springboot.repositories;

import java.util.List;

//追加
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//ここまで
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yoshihide.springboot.MyDataMongo;

@Repository
public interface MyDataMongoRepository extends MongoRepository<MyDataMongo, Long> {

	public List<MyDataMongo> findByNameLike(String s);

	// 追加
//	public Optional<MyDataMongo> findById(long id);

	public Page<MyDataMongo> findAll(Pageable pageable);
	// ここまで

}
