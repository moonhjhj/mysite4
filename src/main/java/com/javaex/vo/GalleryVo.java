package com.javaex.vo;

public class GalleryVo {

	private int no;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	public GalleryVo(int no, String filePath, String orgName, String saveName, long fileSize) {
		super();
		this.no = no;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	public GalleryVo(String filePath, String orgName, String saveName, long fileSize) {
		super();
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	public GalleryVo() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName
				+ ", fileSize=" + fileSize + "]";
	}
	
	
}
