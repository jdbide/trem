package com.avancial.app.fileImport;

public class FileTypeNotExpectedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public FileTypeNotExpectedException(String fileName, String expectedType) {
        super("Le fichier donné : '" + fileName + "' n'est pas du type attendu : " + expectedType);
    }
}
