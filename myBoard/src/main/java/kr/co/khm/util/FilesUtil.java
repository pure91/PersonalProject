package kr.co.khm.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilesUtil {

	// 년/월/일 폴더 생성
	public static String getFolder() {
		// 2024-02-07 형식(format) 지정
		// 간단한 날짜 형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 현재 날짜 객체 생성(java.util 패키지)
		Date date = new Date();
		
		// 받아온 현재 날짜 객체를 2024-02-07 형식으로 변환
		String str = sdf.format(date);
		
		// 문자열에서 -를 파일 시스템의 구분자로 대체해서 return
		return str.replace("-", File.separator);
	}
	
	// 이미지인지 판단해서 썸네일 사용(지금은 사용 안할듯)
	public static boolean CheckImageType(File file) {
		// 현재 타입이 뭔지 .jpeg인지 .jpg인지 MIME타입 알아내기
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			log.info("contentType : " + contentType);
			
			// image/jpeg는 image로 시작한다. true
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 이미지가 아니라면 false
		return false;
	}
}
