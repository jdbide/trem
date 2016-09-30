package com.avancial.app.export;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class ExcelCellStyleColorFactory {

   private Workbook workbook;

   public ExcelCellStyleColorFactory(Workbook workbook) {
      this.workbook = workbook;
   }

   /**
    * Classe composée d'un style et d'une couleur, utilisée comme clé dans la map mapStyleColor.<br>
    * Pour cette clé, on compare les styles uniquement sur la base des valeurs de leurs attributs BorderLeft et BorderRight.
    * 
    * @author heloise.guillemaud
    *
    */
   private class CellStyleColor {
      private CellStyle cellStyle;
      private Color     color;

      public CellStyleColor(CellStyle cellStyle, Color color) {
         super();
         this.cellStyle = cellStyle;
         this.color = color;
      }

      @Override
      public boolean equals(Object obj) {
         CellStyleColor cellStyleColor = (CellStyleColor) obj;
         return cellStyleColor.cellStyle.getBorderLeft() == this.cellStyle.getBorderLeft()
               && cellStyleColor.cellStyle.getBorderRight() == this.cellStyle.getBorderRight()
               && cellStyleColor.color.getBlue() == this.color.getBlue() && cellStyleColor.color.getGreen() == this.color.getGreen()
               && cellStyleColor.color.getRed() == this.color.getRed();
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + (this.cellStyle == null ? 0 : this.cellStyle.hashCode());
         result = prime * result + (this.color == null ? 0 : this.color.hashCode());
         return result;
      }

   }

   /**
    * Map référençant des styles associés à une couleur de fond
    */
   private Map<CellStyleColor, XSSFCellStyle> mapStyleColor = new HashMap<>();

   /**
    * Ajoute une couleur de fond au style passé en paramètre. On cherche d'abord ce style dans la map mapStyleColor, et s'il n'y est pas, le style est
    * cloné et on lui ajoute la couleur, puis on l'ajoute à la map. Cela empêche de devoir re-créer un style à chaque fois qu'on en a besoin.
    * 
    * @param sourceStyle
    * @param c
    * @return
    */
   public XSSFCellStyle addColor(CellStyle sourceStyle, Color c) {
      CellStyleColor cle = new CellStyleColor(sourceStyle, c);
      XSSFCellStyle res = this.mapStyleColor.get(cle);
      if (res == null) {
         res = (XSSFCellStyle) this.workbook.createCellStyle();
         res.cloneStyleFrom(sourceStyle);
         res.setFillForegroundColor(new XSSFColor(c));
         res.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
         this.mapStyleColor.put(cle, res);
      }
      return res;
   }

}
