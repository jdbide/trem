package com.avancial.app.export;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class ASocleReadFile {

    private String filePath;

    private FileInputStream fileInput;

    public ASocleReadFile(String filePath) {
        this.filePath = filePath;
    }

    public void start() throws Exception {
        this.fileInput = new FileInputStream(this.filePath);
    }

    public void close() throws IOException {
        if (this.fileInput != null) {
            this.fileInput.close();
        }
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileInputStream getFileInput() {
        return this.fileInput;
    }

    public void setFileInput(FileInputStream fileInput) {
        this.fileInput = fileInput;
    }
}
