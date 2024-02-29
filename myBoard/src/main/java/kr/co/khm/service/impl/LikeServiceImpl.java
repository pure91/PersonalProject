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

	
	/**
	 * 게시글 좋아요 수
	 */
	@Override
	public void updateLike(int freeSeq) {
		likeMapper.updateLike(freeSeq);
	}

	
	/**
	 * 게시글 좋아요 수 중복 방지
	 */
	@Override
	public void updateLikeCheck(int freeSeq, String usersId) {
		likeMapper.updateLikeCheck(freeSeq, usersId);
		
	}

	
	/**
	 * 게시글 좋아요 취소 시 Likes 테이블 0
	 */
	@Override
	public void updateLikeCheckCancel(int freeSeq, String usersId) {
		likeMapper.updateLikeCheckCancel(freeSeq, usersId);
		
	}

	
	/**
	 * 게시글 좋아요 - 1
	 */
	@Override
	public void updateLikeCancel(int freeSeq) {
		likeMapper.updateLikeCancel(freeSeq);
		
	}


	/**
	 * 게시글 취소 시 likes 테이블 삭제
	 */
	@Override
	public void deleteLike(int freeSeq, String usersId) {
		likeMapper.deleteLike(freeSeq, usersId);
	}
	
}
