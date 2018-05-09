package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.FileVo;

@Service
public class FIleUploadService {

	@Autowired
	private GalleryDao galleryDao;
	
	public int restore(MultipartFile file) {
		String saveDir = "C:\\Users\\aran0\\Desktop\\BIT\\Spring_mybatis\\upload";
		
		//오리지날 파일명  =>사용자가 준 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		//확장자  =>
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName :" + exName);
		
		
		//저장파일명  =>중북되지 않게 이름 만들어내는 로직 필요(어떠한 경우도 겹치면 안됨)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName :" + saveName);
		
		//파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize :" + fileSize);
		
		FileVo fileVo = new FileVo(filePath, orgName, saveName, fileSize);
		System.out.println(fileVo.toString());
		
		//다오 연결 DB저장
		
		
		//파일 서버 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bout= new BufferedOutputStream(out); //buffer 껴주면 빠름
			
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		return fileVo;
		
		return galleryDao.insert(fileVo);
	}
	
	
	public List<FileVo> getList(){
		
		
		return galleryDao.getList();
	}
}
