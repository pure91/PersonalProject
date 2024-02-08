package kr.co.khm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;


import kr.co.khm.service.FilesService;
import kr.co.khm.vo.FilesVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김형민
 * @category 모든 파일들의 컨트롤러
 * @since 2024.02.07
 */

@Slf4j
@Controller
public class FilesController {
	
	@Autowired
	String uploadFolder;
	
	@Autowired
	FilesService filesService;

	// 파일 업로드
	// 파일 객체, 기본키 데이터?
	public List<FilesVO> uploadFile(MultipartFile[] uploadFile, String folder){
		
		List<FilesVO> filesVOList = filesService.uploadFile(uploadFile, folder);
		
		return filesVOList;
	}
	
	// 파일 다운로드
	// springframework.core.io의 Resource임
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName){
		log.info("downloadFile -> fileName : " + fileName);
		
		// [ 요청 파일] core.io임
		// 파일의 경로(.../upload) + 파일명
		// resource 객체가 요청된 서버의 경로를 찾아들어감
		Resource resource = new FileSystemResource(uploadFolder + fileName);
		log.info("downloadFile -> path : " + (uploadFolder + fileName));
		
		// [파일명 정보]
		String resourceName = resource.getFilename();
		log.info("resourceName : " + resourceName);
		
		// [ 응답 상태 ]
		// springframework.http임
		// header 영역에 첨부파일을 보내는 응답은 spring에서 알아서 해줌
		HttpHeaders headers = new HttpHeaders();
		
		// 파일명이 한글일때 한글처리하고 UTF-8을 ISO로 한글처리하기
		// why? 파일명이 한글일 경우 일부 브라우저에서 파일을 제대로 해석하지 못할때가 있음
		// 이를 방지하고자 ISO-8859-1로 인코딩함
		try {
			headers.add("Content-Disposition", "attachment;fileName=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		
		// resource : 파일, headers : 파일명 정보, HttpStatus.OK : 상태 200(성공)
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
