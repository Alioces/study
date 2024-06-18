package org.example.studentjavaweb.test.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageCategory {
    public static List<String> imageCategory = new ArrayList<>();

    static {
        DataBean dataBean = new DataBean();
        try {
            ResultSet resultSet = dataBean.executeSelect(connection ->{
                return connection.prepareStatement("select categoryName from imagecategory;");
            });

            while (resultSet.next()){
                imageCategory.add(resultSet.getString("categoryName"));
            }

            dataBean.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
