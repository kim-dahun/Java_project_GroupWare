package com.sangsang.menu.controller;

import java.util.List;

import com.sangsang.menu.model.Department;



public interface MenuDeptDao {

	Department read(String search);
	
	List<Department> read(); // 모든 부서 리스트를 불러올 때 사용
	
	int writeAcc(Department dept); // 신규 부서 추가
	
	int deleteAcc(Department dept); // 기존 부서 삭제
	
	int ModifiedAcc(Department dept); // 기존 부서 수정
	
	boolean checkDupledept(Department dept);
}
