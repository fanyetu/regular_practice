package cn.fanyetu.design.structure.proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实验
 * @author user
 *
 */
public class Test {
	
	public static void main(String[] args) {
		
		/**
		 * 连接mysql数据库并查询数据
		 */
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF8";
		String username = "root";
		String password = "111111";
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			String sql = "select name,job from test_user";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				String job = rs.getString("job");
				System.out.println(name + ","+ job);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		
		
		
		
	}

}
