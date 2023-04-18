package com.sangsang.menu.controller;

import java.util.List;

import com.sangsang.menu.model.Department;
import com.sangsang.menu.model.Position;



public interface MenuPosDao {

	Position read(String search);
	
	List<Position> read(); // 모든 직급 리스트를 불러올 때 사용
	
	int writePos(Position pos); // 신규 직급 추가
	
	int deletePos(Position pos); // 기존 직급 삭제

	
	
	
}
