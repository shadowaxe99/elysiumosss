package com.elysiumos.database;

import java.util.Map;
import java.util.StringJoiner;

public class QueryBuilder {

    public static String buildInsertQuery(String tableName, Map<String, Object> fields) {
        StringJoiner fieldNames = new StringJoiner(", ", "(", ")");
        StringJoiner fieldValues = new StringJoiner(", ", "(", ")");
        
        fields.forEach((key, value) -> {
            fieldNames.add(key);
            fieldValues.add(formatValue(value));
        });
        
        return "INSERT INTO " + tableName + " " + fieldNames.toString() + " VALUES " + fieldValues.toString() + ";";
    }

    public static String buildUpdateQuery(String tableName, Map<String, Object> fields, String whereClause) {
        StringJoiner fieldSets = new StringJoiner(", ");
        
        fields.forEach((key, value) -> fieldSets.add(key + " = " + formatValue(value)));
        
        return "UPDATE " + tableName + " SET " + fieldSets.toString() + " WHERE " + whereClause + ";";
    }

    public static String buildSelectQuery(String tableName, String[] columns, String whereClause) {
        StringJoiner columnNames = new StringJoiner(", ");
        
        for (String column : columns) {
            columnNames.add(column);
        }
        
        return "SELECT " + columnNames.toString() + " FROM " + tableName + " WHERE " + whereClause + ";";
    }

    public static String buildDeleteQuery(String tableName, String whereClause) {
        return "DELETE FROM " + tableName + " WHERE " + whereClause + ";";
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value == null) {
            return "NULL";
        } else {
            return value.toString();
        }
    }
}