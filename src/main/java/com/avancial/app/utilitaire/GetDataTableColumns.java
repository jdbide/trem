package com.avancial.app.utilitaire;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    /**
     * 
     * @param entity Classe d'une entité
     * @return Liste des noms des colonnes de la table représentant l'entité
     */
    public static List<String> getNameColumns(Class<?> entity) {
        List<String> names = new ArrayList<>();
        String name;
        /* Boucle sur les champs déclarés de l'entité */
        for (Field field : entity.getDeclaredFields()) {
            name = field.getName();
            /* On récupère les annotations du champ */
            Annotation[] annotations = field.getDeclaredAnnotations();

            /*
             * On n'ajoute le champ à la liste que s'il correspond à une colonne
             * en base
             */
            boolean toAdd = false;
            for (Annotation annotation : annotations) {
                Class<?> type = annotation.annotationType();
                toAdd = toAdd || (type.isAssignableFrom(Id.class) || type.isAssignableFrom(Column.class)
                        || type.isAssignableFrom(JoinColumn.class));

                /*
                 * Dans le cas d'une colonne de jointure, on récupère son nom
                 * (plutôt que le nom de l'entité)
                 */
                if (type.isAssignableFrom(JoinColumn.class)) {
                    JoinColumn join = (JoinColumn) annotation;
                    name = join.name();
                }
            }

            if (toAdd) {
                names.add(name);
            }
        }
        return names;
    }

}
