package com.avancial.app.utilitaire;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.table.ColumnTable;

public class GetDataTableColumns {

    public static List<ColumnTable> getColumns(Class<?> entity) {
        List<ColumnTable> columns = new ArrayList<>();
        for (int i = 0; i < entity.getDeclaredFields().length; i++) {
            Class<?> fieldClass = entity.getDeclaredFields()[i].getType();
            String field = entity.getDeclaredFields()[i].getName();
            String prefix = field.substring(0, 4);
            String fieldData = field.replace(prefix, prefix.toLowerCase());

            String filterValue = "text";
            if (Number.class.isAssignableFrom(fieldClass)) {
                filterValue = "number";
            }
            else if (java.sql.Date.class.isAssignableFrom(fieldClass)
                    || java.util.Date.class.isAssignableFrom(fieldClass)) {
                filterValue = "date";
            }
            ColumnTable columnTable = new ColumnTable(fieldData, field, fieldData, (!field.startsWith("id")), fieldData,
                    filterValue, fieldClass.getSimpleName());
            columns.add(columnTable);
        }
        return columns;
    }

}
