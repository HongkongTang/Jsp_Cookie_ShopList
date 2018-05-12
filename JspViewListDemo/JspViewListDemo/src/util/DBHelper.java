package util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBHelper {
	
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8";
	private static final String uasername = "root";
	private static final String password = "root";
	
	private static Connection conn = null;
	
	
	//��̬������������
	static{
		try{
			Class.forName(driver);
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		
	}
	
	//����ģʽ�������ݿ����Ӷ���
	public static Connection getConnection() throws Exception{
		
		if(conn==null){
			conn = DriverManager.getConnection(url, uasername, password);
			return conn;
		}
		
		return conn;
	}
	
	public static void main(String[] args){
		
		try{
			Connection conn = DBHelper.getConnection();
			if(conn!=null){
				System.out.println("�ɹ���������");
			}
			else {
				System.out.println("���ݿ������쳣");
			}
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
	}
}