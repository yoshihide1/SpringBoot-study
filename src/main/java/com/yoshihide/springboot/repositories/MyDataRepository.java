package com.yoshihide.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoshihide.springboot.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
	public Optional<MyData> findById(Long name);

//	public List<MyData> findByNameLike(String name);// "%" + str + "%"
//
//	public List<MyData> findByIdIsNotNullOrderByIdDesc();// IDで降順 OrderByNameAscでnameで昇順
//
//	public List<MyData> findBy_AgeGreaterThan(Integer age);// 数値、引数よりも大きい値を取得、LessThanで小さい値
//
//	public List<MyData> findByAgeBetween(Integer age1, Integer age2);// 二つの引数の範囲内の値
}
