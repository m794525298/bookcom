package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.book.common.DataBaseConnector;
import com.book.pojo.FollowBean;

public class FollowDao {

	public static int admin_insert(FollowBean follow) {
		//0:成功 1:數据庫錯誤  2:已存在关注 3:用户不存在
		
		int res = 0;
		int count = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {
			Statement st1 = DataBaseConnector.getStatement();
			rs = st1.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWER=" + follow.getFollowerId() + " and " +"FOLLOW_FOLLOWING=" + follow.getFollowingId() +";");
			if (rs.next()) return 2;
			
			Statement st2 = DataBaseConnector.getStatement();
			rs = st2.executeQuery("Select count(*) from user where USER_ID=" + follow.getFollowerId() + " or " + "USER_ID=" + follow.getFollowingId());
			if(rs.next())
				count = rs.getInt(1);
			if(count < 2)
				return 3;
			
			String sql = "insert into follow(FOLLOW_FOLLOWER, FOLLOW_FOLLOWING) value(?,?)";
			
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, follow.getFollowerId());
			pstmt.setString(2, follow.getFollowingId());
			
			res = pstmt.executeUpdate();
			
			if (res > 0) {
				Statement st3 = DataBaseConnector.getStatement();
				sql = "update user set USER_FOLLOWERSNUM = USER_FOLLOWERSNUM + 1 where USER_ID = "+ follow.getFollowerId() +";";//sql语句
				st3.executeUpdate(sql);
				Statement st4 = DataBaseConnector.getStatement();
				sql = "update user set USER_FOLLOWINGNUM = USER_FOLLOWINGNUM + 1 where USER_ID = "+ follow.getFollowingId() +";";//sql语句
				st4.executeUpdate(sql);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 1;
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return (res > 0) ? 0 : 1;
	}
	
	public static int admin_update(FollowBean follow) {
		//0:成功 1:數据庫錯誤 2:已存在关注 3:用户不存在
		
		int res = 0;
		int count = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {
			Statement st1 = DataBaseConnector.getStatement();
			rs = st1.executeQuery("Select USER_ID from follow where FOLLOW_FOLLOWER=" + follow.getFollowerId() + " and " +"FOLLOW_FOLLOWING=" + follow.getFollowingId() +";");
			if (rs.next())	return 2;
			
			Statement st2 = DataBaseConnector.getStatement();
			rs = st2.executeQuery("Select count(*) form user where USER_ID=" + follow.getFollowerId() + " or " + "USER_ID=" + follow.getFollowingId());
			if(rs.next())
				count = rs.getInt(1);
			if(count < 2)
				return 3;
			
			String sql = "update follow set FOLLOW_FOLLOWER=?, FOLLOW_FOLLOWING=? where FOLLOW_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);

			pstmt.setString(1, follow.getFollowerId());
			pstmt.setString(2, follow.getFollowingId());
			pstmt.setString(3, follow.getId());
			
			res = pstmt.executeUpdate();	
		} catch (SQLException e) {
			return 1;
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return (res > 0) ? 0 : 1;
	}
	
	public static int admin_delete(String id) {
		//0:成功 1:數据庫錯誤 2:关注不存在
		
		int res = 0;
		PreparedStatement pstmt= null;
		
		try {
			String sql = "delete from follow where FOLLOW_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1 , id);
			
			res = pstmt.executeUpdate();	
		} catch (SQLException e) {
			return 1;
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return (res > 0) ? 0 : 2;
	}
	
	/**
	 * 获取总记录数和总页数
	 * @param count		每页记录数
	 * @param keywords	搜索关键字
	 * @return
	 */
	public static int[] admin_totalPage(int count, String keywords) {
		int arr[] = { 0, 1 };

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			if (keywords != null) {
				String sql = "select count(*) from follow where FOLLOW_FOLLOWER=?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, keywords);
			} else {
				String sql = "select count(*) from follow";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr[0] = rs.getInt(1);

				if (arr[0] % count == 0) {
					arr[1] = arr[0] / count;
				} else {
					arr[1] = arr[0] / count + 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return arr;
	}
	
	/**
	 * 通过关键字查找用户列表
	 * @param cpage 当前页数
	 * @param count 显示个数
	 * @param keywords 搜索关键字
	 * @return 返回指定的用户集
	 */
	public static ArrayList<FollowBean> admin_selectAll(int cpage, int count, String keywords) {
		ArrayList<FollowBean> list = new ArrayList<FollowBean>();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			if (keywords != null) {
				String sql = "select * from follow where FOLLOW_FOLLOWER=? limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, keywords);
				pstmt.setInt(2, (cpage - 1) * count);
				pstmt.setInt(3, count);

			} else {
				String sql = "select * from follow limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setInt(1, (cpage - 1) * count);
				pstmt.setInt(2, count);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FollowBean follow = new FollowBean(
						rs.getString("FOLLOW_ID"),
						rs.getString("FOLLOW_FOLLOWER"),
						rs.getString("FOLLOW_FOLLOWING")
				);
				list.add(follow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return list;
	}
	
	/**
	 * 通过ID查找用户
	 * @param id 指定ID
	 * @return 返回用户实体
	 */
	public static FollowBean admin_selectByID(String id) {
		
		ResultSet rs = null;
		FollowBean follow = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from follow where FOLLOW_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				follow = new FollowBean(
						rs.getString("FOLLOW_ID"),
						rs.getString("FOLLOW_FOLLOWER"),
						rs.getString("FOLLOW_FOLLOWING")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt != null)
						pstmt.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return follow;
	}
	
	public static int follow(String userId,String followingId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)||!UserDao.validUser(followingId)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING ='" + followingId +"'And FOLLOW_FOLLOWER='"+userId+ "';");
			if (rs.next()) return 0;
			st.close();
			String sql="insert into follow(FOLLOW_FOLLOWING,FOLLOW_FOLLOWER) values(?,?)";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , followingId);
			pstmt.setString(2 , userId);
			pstmt.executeUpdate();
			pstmt.close();
			sql = "update user set USER_FOLLOWINGNUM = USER_FOLLOWINGNUM + 1 where USER_MD5ID = '"+ userId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			sql = "update user set USER_FOLLOWERSNUM = USER_FOLLOWERSNUM + 1 where USER_MD5ID = '"+ followingId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static int disFollow(String userId,String followerId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)||!UserDao.validUser(followerId)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING ='" + followerId +"'And FOLLOW_FOLLOWER='"+userId+ "';");
			if (!rs.next()) return 0;
			String sql="delete from follow where FOLLOW_FOLLOWING = '"+ followerId + "' And FOLLOW_FOLLOWER = '" + userId  + "';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			sql = "update user set USER_FOLLOWINGNUM = USER_FOLLOWINGNUM - 1 where USER_MD5ID = '"+ userId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			sql = "update user set USER_FOLLOWERSNUM = USER_FOLLOWERSNUM - 1 where USER_MD5ID = '"+ followerId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static boolean isFollow(String userId,String followerId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)||!UserDao.validUser(followerId)) return false;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING ='" + followerId +"'And FOLLOW_FOLLOWER='"+ userId+ "';");
			if (!rs.next()) return false;
			return true;
		}catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static ResultSet getFollowingsID(String userId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)) return null;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_FOLLOWER from follow where FOLLOW_FOLLOWING ='" + userId + "'limit 6;");
			return rs;
		}catch (SQLException e) {
			return null;
		}
	}

	public static ResultSet getUserFollowing(String userId) {
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "Select FOLLOW_FOLLOWING from follow where FOLLOW_FOLLOWER=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return rs;
	}
	
	
}
