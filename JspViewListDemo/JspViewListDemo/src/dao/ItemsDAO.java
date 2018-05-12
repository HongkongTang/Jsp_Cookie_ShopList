package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import util.DBHelper;

import entity.Items;

/**
 * ��Ʒ��ҵ���߼���
 * @author Vision_TXG
 *
 */
public class ItemsDAO {
	//������е���Ʒ��Ϣ
	public ArrayList<Items> getAllItems(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>();
		try{
			conn = DBHelper.getConnection();
			String sql = "select * from items;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getDouble("price"));
				item.setNumber(rs.getInt("number"));
				item.setPicture(rs.getString("picture"));
				
				list.add(item);
				
			}
			return list;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//�ͷ����ݼ�����
			if(rs!=null){
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//�ͷ�������
			if(stmt!=null){
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	//ͨ����Ʒ��Ż����Ʒ���ϣ�
	public Items getItemsByIdA(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBHelper.getConnection();
			String sql = "select * from items where id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			
			
			rs = stmt.executeQuery();
			if(rs.next()){
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getDouble("price"));
				item.setNumber(rs.getInt("number"));
				item.setPicture(rs.getString("picture"));
				
				return item;
			}
			else return null;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//�ͷ����ݼ�����
			if(rs!=null){
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//�ͷ�������
			if(stmt!=null){
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	//��ȡ��������ǰ������Ϣ
	public ArrayList<Items> getViewList(String list){
		
		ArrayList<Items> itemList = new ArrayList<Items>();
		if(list!=null&&list.length()>0){
			String[] arr = list.split(",");
			
			for(int i = arr.length-1;i>=Math.max(arr.length-4,0);i--){
				int id = Integer.parseInt(arr[i]);
				itemList.add(getItemsByIdA(id));
			}
			return itemList;
		}
		return null;
		
	}
	
}
