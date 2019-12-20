package com.example.demoSpBoot.dto;

import javax.persistence.Embeddable;

@Embeddable
public class UploadResponse {
	private String fileName;
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	private String fileDownloadUri;

    public UploadResponse(String fileName, String fileDownloadUri) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
    }
}
