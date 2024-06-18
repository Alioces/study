package org.example.studentjavaweb.test.JDBC;

import org.example.studentjavaweb.test.javaBean.ImageBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBean {
    @FunctionalInterface
    public interface SqlCode {
       PreparedStatement sqlCode(Connection connection) throws SQLException;
    }
    private PreparedStatement sql;
    private final Connection connection;

    /**
     * 创建数据库的连接
     */
    public DataBean(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","88888888");


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建指定数据库的连接
     * @param dataBean 指定的数据库名称
     */
    public DataBean(String dataBean){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + dataBean, "root", "88888888");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 运行自定义的 sql 查询语句
     * @param code 自定义的 sql 查询逻辑
     * @return 运行 sql 查询的结果集
     */
    public ResultSet executeSelect(SqlCode code) throws SQLException {
        sql = code.sqlCode(connection);
        return sql.executeQuery();
    }

    /**
     * 运行自定义的 sql 更新语句
     * @param code 自定义的 sql 查询逻辑
     * @return 运行 sql 更新的结果
     */
    public int executeUpdate(SqlCode code) throws SQLException {
        sql = code.sqlCode(connection);
        return sql.executeUpdate();
    }

    /**
     * 查询数据库中所有的图片条目信息
     * @return 条目的结果集
     */
    public ResultSet selectAll() throws SQLException {
        return this.executeSelect((connection1 -> connection.prepareStatement("select * from imageInfo")));
    }

    /**
     * 向数据库填加一个完整的数据条目
     * @param bean 对应 数据库的 ImageBean 对象
     * @return 返回一个整数值 如果为 0 则表示插入失败
     */
    public int insertImageBean(ImageBean bean) throws SQLException {
        return this.executeUpdate(connection1 -> {
            PreparedStatement sql = connection.prepareStatement(
                    "insert into imageInfo(imageCategoryID,imageName,imageAddress,imageAddTime) values(?,?,?,?)"
            );
            sql.setInt(1,bean.getCategoryID());
            sql.setString(2,bean.getName());
            sql.setString(3,bean.getAddress());
            sql.setString(4,bean.getTime());
            return sql;
        });
    }

    /**
     * 将通过该类操作得到的结果集合,转换成集合
     * @param resultSet 需要转换的结果集合
     * @return 包含了ImageBean 对象的 ArrayList 集合
     * @throws SQLException 数据库错误
     */
    public List<ImageBean> toList(ResultSet resultSet) throws SQLException {
        List<ImageBean> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(new ImageBean(
                    resultSet.getInt("imageID"),
                    resultSet.getString("imageName"),
                    resultSet.getInt("imageCategoryID"),
                    resultSet.getString("imageAddress"),
                    resultSet.getString("imageAddTime")
            ));
        }

        return list;
    }

    public void close() throws SQLException {
        if (sql != null && !sql.isClosed()){
            sql.close();
        }

        connection.close();
    }
}
