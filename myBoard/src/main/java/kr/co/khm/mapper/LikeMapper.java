package kr.co.khm.mapper;

import java.util.Map;

public interface LikeMapper {

	/** 좋아요 중복 체크
	 * @param map
	 * @return
	 */
	int likeCheck(Map<String, Object> map);

	
	/**게시글 좋아요 시 Likes 테이블에 insert
	 * @param map
	 */
	void insertLike(Map<String, Object> map);


	/**
	 * 게시글 좋아요 시 Check를 1로 올려서 중복 방지
	 * @param freeSeq
	 */
	void updateLike(int freeSeq);

	
	/**게시글 좋아요 수 중복 방지
	 * @param map
	 */
	void updateLikeCheck(Map<String, Object> map);


	/**게시글 좋아요 취소 시 Likes 테이블 0
	 * @param map
	 */
	void updateLikeCheckCancel(Map<String, Object> map);


	/**
	 * 게시글 좋아요 - 1
	 * @param freeSeq
	 */
	void updateLikeCancel(int freeSeq);

	
	/** 게시글 취소 시 likes 테이블 삭제
	 * @param map
	 */
	void deleteLike(Map<String, Object> map);







}
