package kr.co.khm.service;

public interface LikeService {

	
	/**
	 * 좋아요 중복 체크
	 * @param freeSeq
	 * @param usersId
	 * @return
	 */
	int likeCheck(int freeSeq, String usersId);

	
	/** 게시글 좋아요 시 Likes 테이블에 insert
	 * @param freeSeq
	 * @param usersId
	 */
	void insertLike(int freeSeq, String usersId);

	
	/**
	 * 게시글 좋아요 수
	 * @param freeSeq
	 */
	void updateLike(int freeSeq);


	/**
	 * 게시글 좋아요 수 중복 방지
	 * @param freeSeq
	 * @param usersId
	 */
	void updateLikeCheck(int freeSeq, String usersId);


	/** 게시글 좋아요 취소 시 Likes 테이블 0
	 * @param freeSeq
	 * @param usersId
	 */
	void updateLikeCheckCancel(int freeSeq, String usersId);


	/**
	 * 게시글 좋아요 - 1
	 * @param freeSeq
	 */
	void updateLikeCancel(int freeSeq);


	/**
	 * 게시글 취소 시 likes 테이블 삭제
	 * @param freeSeq
	 * @param usersId
	 */
	void deleteLike(int freeSeq, String usersId); 
}
