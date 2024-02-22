package kr.co.khm.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.khm.mapper.FilesMapper;
import kr.co.khm.util.FilesUtil;
import kr.co.khm.vo.FilesVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FilesService {

	@Autowired
	String uploadFolder; // 업로드 폴더 변수명에 경로는 root-context에 지정해놨음

	@Autowired
	FilesMapper filesMapper;

	// 파일 insert
	public List<FilesVO> uploadFile(MultipartFile[] uploadFile, String folder) {
		
		log.info("uploadFile -> uploadFile : " + uploadFile[0].getOriginalFilename());
		
		List<FilesVO> filesVOList = new ArrayList<>();
		
		// 년/월/일 폴더 호출
		// java.io임
		File uploadPath = new File(uploadFolder, folder + "/" + FilesUtil.getFolder());
		if (uploadPath.exists() == false) { // 존재하지 않으면
			uploadPath.mkdirs(); // 지정된 경로에 모든 폴더(디렉토리)를 생성하는 메서드
		}
		
		// 파일 번호 생성
		int filesSeq = filesMapper.getFilesSeq();
		log.info("uploadFile -> filesSeq : " + filesSeq);
		
		// uploadFile 변수(리스트)에 담긴 사진들(multipartFile 객체) 포문 돌림
		// 순회하면서 사진들의 원본파일명을 가져와서 할당해줌
		for (MultipartFile multipartFile : uploadFile) {
			String uploadFileName = multipartFile.getOriginalFilename();
			
			// 중복값 방지를 위해 UUIE 유틸을 통해 임의의값을 uuid에 할당함
			// 그리고 임의의 파일명에 언더바를 더하고 원본파일명까지 더해서 할당한다.
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("uploadFile -> uploadFileName : " + uploadFileName);
		
			// 파일 객체(saveFile)를 생성해서 매개변수에 업로드경로, 최종 파일명을 객체에 할당함
			File saveFile = new File(uploadPath, uploadFileName);
			
			// 트라이캐치 쓰는 이유는 실제로 disk에 저장되는 부분이고
			// 파일 복사를 해야해서 중요해서 써주는듯
			try {
				multipartFile.transferTo(saveFile);
				// 사진들에 접근해서 transferTo로 업로드 처리해주고
				// saveFile객체에는 위에서 경로랑 파일명 다 할당해줬었음
				
				FilesVO filesVO = new FilesVO();
				filesVO.setFilesSeq(filesSeq); // 파일 PK 세팅
				filesVO.setFilesNo(0); // 파일 순번 세팅(insert할때 selectKey로 늘릴거, 한번의 파일seq에 파일 여러장 들어갈 수 있으니)
				filesVO.setFilesStrgPath(uploadPath + "\\" + uploadFileName); // 저장경로(업로드경로 + \\ + 파일명 세팅)
				
				filesVO.setFilesStrgName(FilesUtil.getFolder().replaceAll("\\\\", "/") + "/" + uploadFileName);
				// getFolder가 File.separator때문에 2024\\02\\07 이렇게 나오는데
				// replaceAll을 통해서 "\\\\" 4개니까 4개 찾아서 /로 바꿔주고 파일명 앞에도 / 추가하면
				// 2024/02/07/uuiderandomeasdfasdf_사진명.jpg 이런식으로 나오게됨
				
				filesVO.setFilesOrgnlName(multipartFile.getOriginalFilename()); // 원본 파일명 세팅
				filesVO.setFilesExtn(uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1));
				// 파일확장자에(파일명에서 substring으로 잘라온뒤 파일명의 마지막"." 쩜 뒤에부터 찾아와서 세팅해줌)
				filesVO.setFilesSize(multipartFile.getSize()); // 파일 크기 세팅
				
				log.info("filesVO : " + filesVO);
				
				this.filesMapper.insertFiles(filesVO); // 파일 인서트 DB처리
				filesVOList.add(filesVO); // 처리된 내용을 리스트에 추가
				log.info("filesVOList : " + filesVOList);
			} catch(IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
		}
		return filesVOList;
	}

}
