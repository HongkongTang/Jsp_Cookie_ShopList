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
 * 商品的业务逻辑类
 * @author Vision_TXG
 *
 */
public class ItemsDAO {
	//获得所有的商品信息
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
			//释放数据集对象
			if(rs!=null){
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//释放语句对象
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
	
	//通过商品编号获得商品资料：
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
			//释放数据集对象
			if(rs!=null){
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//释放语句对象
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
	//获取最近浏览的前五条信息
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
