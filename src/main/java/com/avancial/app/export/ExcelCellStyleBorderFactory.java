package com.avancial.app.export;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelCellStyleBorderFactory {

   private Workbook workbook;

   public ExcelCellStyleBorderFactory(Workbook workbook) {
      this.workbook = workbook;
   }

   /**
    * Classe composée de quatre types de bordures, utilisée comme clé dans la map mapStyleBorders.<br>
    * 
    * @author heloise.guillemaud
    *
    */
   private class CellStyleBorders {
      private CellStyle cellStyle;
      private short     borderTop;
      private short     borderBottom;
      private short     borderLeft;
      private short     borderRight;

      public CellStyleBorders(CellStyle cellStyle, short borderTop, short borderBottom, short borderLeft, short borderRight) {
         super();
         this.cellStyle = cellStyle;
         this.borderTop = borderTop;
         this.borderBottom = borderBottom;
         this.borderLeft = borderLeft;
         this.borderRight = borderRight;
      }

      @Override
      public boolean equals(Object obj) {
         CellStyleBorders cellStyleBorders = (CellStyleBorders) obj;
         return cellStyleBorders.cellStyle.equals(this.cellStyle) && cellStyleBorders.borderTop == this.borderTop
               && cellStyleBorders.borderBottom == this.borderBottom && cellStyleBorders.borderLeft == this.borderLeft
               && cellStyleBorders.borderRight == this.borderRight;
      }

   }

   /**
    * Map référençant des styles associés à une couleur de fond
    */
   private Map<CellStyleBorders, XSSFCellStyle> mapStyleBorders = new HashMap<>();

   /**
    * Retourne un style identique à celui en source, avec les bordures telles qu'indiquées en paramètre. On cherche d'abord ce style dans la map
    * mapStyleBorders, et s'il n'y est pas, le style est cloné et on lui ajoute les bordures, puis on l'ajoute à la map. Cela empêche de devoir
    * re-créer un style à chaque fois qu'on en a besoin.
    * 
    * @return
    */
   public XSSFCellStyle setBorders(CellStyle sourceStyle, short borderTop, short borderBottom, short borderLeft, short borderRight) {
      CellStyleBorders cle = new CellStyleBorders(sourceStyle, borderTop, borderBottom, borderLeft, borderRight);
      XSSFCellStyle res = this.mapStyleBorders.get(cle);
      if (res == null) {
         res = (XSSFCellStyle) this.workbook.createCellStyle();
         res.cloneStyleFrom(sourceStyle);
         res.setBorderTop(borderTop);
         res.setBorderBottom(borderBottom);
         res.setBorderLeft(borderLeft);
         res.setBorderRight(borderRight);
         this.mapStyleBorders.put(cle, res);
      }
      return res;
   }

}
