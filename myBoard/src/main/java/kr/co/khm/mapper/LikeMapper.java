package kr.co.khm.mapper;

public interface LikeMapper {

	/** 좋아요 중복 체크
	 * @param freeSeq
	 * @param usersId
	 * @return
	 */
	int likeCheck(int freeSeq, String usersId);

	
	/** 게시글 좋아요 시 Likes 테이블에 insert
	 * @param freeSeq
	 * @param usersId
	 * @return
	 */
	void insertLike(int freeSeq, String usersId);

}
