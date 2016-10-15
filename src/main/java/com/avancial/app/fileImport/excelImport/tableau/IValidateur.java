package com.avancial.app.fileImport.excelImport.tableau;

public interface IValidateur<T> {
   
   public boolean validate(T object);

}
