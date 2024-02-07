package kr.co.khm.util;

import java.util.List;

import lombok.Data;

// 페이지네이션 관련 정보 + 게시글 정보
@Data
public class ArticlePage<T> {
	
	private int total; 				// 전체 글 수
	private int currentPage; 		// 현재 페이지 번호
	private int totalPage;			// 전체 페이지 번호
	private int startPage;			// 블록의 시작 페이지 번호
	private int endPage;			// 블록의 종료 페이지 번호
	private String keyword = "";	// 검색어
	private String url;				// 요청 url(주소 담아줄거임)
	private List<T> content;		// 데이터
	private String searchType;		// 검색 타입
	
	
	// 생성자 생성 : 페이지 정보를 위함
	public ArticlePage(int total, int currentPage, int size, List<T> content) {
		// size는 한 화면에 보여질 목록의 행 수
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		// 만약 전체글 수가 0이면?
		if (total == 0) {
			totalPage = 0; // 전체 페이지 번호 0
			startPage = 0; // 블록 시작번호도 0
			endPage = 0;   // 블록 종료번호도 0
		} else {
			// 글이 있다면
			totalPage = total / size; // 전체 페이지 번호에 할당한다. 전체 글 수/한화면에 보여질 목록의 행수
			
			// 전체 글수 % 한 화면에 보여질 목록의 행 수
			// 즉, 0이 아니면, 페이지를 1 증가 시킨다 => 나눈몫(/)의 나머지값(%)
			if (total % size > 0) {
				totalPage++;
			}
			
			// 시작 페이지 공식
			// 시작 페이지 = 현재페이지 / 페이지size * 페이지size + 1;
			startPage = currentPage / 5 * 5 + 1;
			
			// 만약 현재 페이지를 % 나눈몫의 값이 0과 같다면, 페이지크기를 빼준다.
			if (currentPage % 5 == 0) {
				startPage -= 5;
			}
			
			// 종료 페이지 공식
			// 종료 페이지 = 시작페이지 + (페이지 size - 1)
			endPage = startPage + 4;
			
			// 종료 페이지 번호가 전체 페이지 번호보다 클 때, 종료 페이지에 전체 페이지를 할당함
			if (endPage > totalPage) {
				endPage = totalPage;
			}
		}
	}
	
	// 페이징 블록을 자동화하기
	public String getPagingArea() {
		String pagingArea = "";
		
		pagingArea +="<div class='col-sm-12 col-md-7'>";
		pagingArea +="<div class='dataTables_paginate paging_simple_numbers' id='dataTable_paginate'>";
		pagingArea +="<ul class='pagination'>";
		pagingArea +="<li class='paginate_button page-item previous ";
		if(this.startPage<6) {
			pagingArea += "disabled"; // 현재 페이지가 6보다 작으면 다음페이지 없는걸로 비활성화
		}
		
		pagingArea +="' id='dataTable_previous'>";
		pagingArea +="<a href='"+this.url+"?searchType="+this.searchType+"&keyword="+this.keyword+"&currentPage="+(this.startPage-5)+"'";
		pagingArea +="aria-controls='dataTable' data-dt-idx='0' tabindex='0' ";
		pagingArea +="class='page-link'>Previous</a></li>";
		
		for(int pNo=this.startPage; pNo<=this.endPage; pNo++) {
			pagingArea +="<li class='paginate_button page-item ";
				if(this.currentPage == pNo) {
					pagingArea +="active"; //현재 페이지랑 시작페이지번호 같으면 활성화
				}
			pagingArea +="'>";
			pagingArea +="<a href='"+this.url+"?searchType="+this.searchType+"&keyword="+this.keyword+"&currentPage="+pNo+"'";
			pagingArea +="aria-controls='dataTable' data-dt-idx='1' tabindex='0'";
			pagingArea +="class='page-link'>"+pNo+"</a></li>";
		}
		
		pagingArea +="<li class='paginate_button page-item next ";
			if(this.endPage >= this.totalPage) {
				pagingArea +="disabled"; // 종료 페이지가 전체 페이지랑 같거니 더 크면 비활성화
			}
		pagingArea +="' id='dataTable_next'><a href='"+this.url+"?searchType="+this.searchType+"&keyword="+this.keyword+"&currentPage="+(this.startPage+5)+"' ";
		pagingArea +="aria-controls='dataTable' data-dt-idx='7' tabindex='0' ";
		pagingArea +="class='page-link'>Next</a></li></ul></div></div>";
		
		return pagingArea;
	}
}
