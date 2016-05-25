package com.avancial.socle.table;

import java.util.HashMap;
import java.util.Map;

/**
 * Description d'une colonne pour générer un tableau côté client
 * @author heloise.guillemaud
 *
 */
public class ColumnTable {

    private String field;
    private String title;
    private String sortable;
    private Boolean show;
    private Map<String, String> filter;
    private String fieldType;
    
    public ColumnTable(String field, String title, Boolean show, String fieldType) {
        super();
        this.field = field;
        this.title = title;
        this.show = show;
        this.fieldType = fieldType;
    }

    public ColumnTable(String field, String title, Boolean show, String filterKey, String filterValue, String fieldType) {
        super();
        this.field = field;
        this.title = title;
        this.show = show;
        this.setFilter(filterKey, filterValue);
        this.fieldType = fieldType;
    }

    public ColumnTable(String field, String title, String sortable, Boolean show, String fieldType) {
        super();
        this.field = field;
        this.title = title;
        this.sortable = sortable;
        this.show = show;
        this.fieldType = fieldType;
    }

    public ColumnTable(String field, String title, String sortable, Boolean show, String filterKey, String filterValue,
            String fieldType) {
        super();
        this.field = field;
        this.title = title;
        this.sortable = sortable;
        this.show = show;
        this.setFilter(filterKey, filterValue);
        this.fieldType = fieldType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSortable() {
        return sortable;
    }

    public void setSortable(String sortable) {
        this.sortable = sortable;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(String filterKey, String filterValue) {
        this.filter = new HashMap<>();
        this.filter.put(filterKey, filterValue);
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

}
