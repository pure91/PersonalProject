package kr.co.khm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.khm.mapper.LikeMapper;
import kr.co.khm.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	LikeMapper likeMapper;
	
	/**
	 * 좋아요 중복 체크 
	 */
	@Override
	public int likeCheck(int freeSeq, String usersId) {
		return likeMapper.likeCheck(freeSeq, usersId);
	}

	/**
	 * 게시글 좋아요 시 Likes 테이블에 insert
	 */
	@Override
	public void insertLike(int freeSeq, String usersId) {
		Map<String, Object> map = new HashMap<>();
		map.put("usersId", usersId);
		map.put("freeSeq", freeSeq);
		likeMapper.insertLike(freeSeq, usersId);
	}

	
}