package com.action;

import com.db.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Search {

    private int totalRecords;
    private List<String> records;

    public Search() {
        try {
            String s[] = null;

            Statement stmt = DBConnection.getStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PBook");

            records = new ArrayList<>();

            while (rs.next()) {
                records.add(rs.getString(2));
            }

            String[] str = new String[records.size()];
            Iterator it = records.iterator();

            int i = 0;
            while (it.hasNext()) {
                String p = (String) it.next();
                str[i] = p;
                i++;
            }
            totalRecords = records.size();

        } catch (Exception ex) {

        }
    }

    public List<String> getData(String query) {
        String record = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<>();

        for (int i = 0; i < totalRecords; i++) {
            record = records.get(i).toLowerCase();
            if (record.startsWith(query)) {
                matched.add(records.get(i));
            }
        }
        return matched;
    }
}
