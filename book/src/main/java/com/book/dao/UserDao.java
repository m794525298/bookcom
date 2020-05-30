package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.book.common.Coder;
import com.book.common.DataBaseConnector;
import com.book.controller.SendEmailCaptchaController;
import com.book.pojo.UserBean;

public class UserDao {

	public static int admin_insert(UserBean user) {
		//0:成功 1:數据庫錯誤 2:已存在用戶
		
		int res = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {
			Statement st = DataBaseConnector.getStatement();
			rs = st.executeQuery("Select USER_ID from user where USER_ACCOUNT='" + user.getAccount() + "' or " +"USER_EMAIL='" + user.getEmail() + "'");
			if (rs.next()) return 2;
			
			String sql = "insert into user(USER_NICKNAME, USER_ACCOUNT, USER_PASSWORD, USER_ICON, USER_EMAIL, USER_IDENTITY) value(?,?,?,?,?,?)";
			
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getNickname());
			pstmt.setString(2, user.getAccount());
			pstmt.setString(3, Coder.encrypted(user.getPassword()));
			pstmt.setString(4, user.getIcon());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getIdentity());
			
			res = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			if (res > 0 && rs.next()) {
				sql="update user set USER_MD5ID='"+ Coder.encrypted(rs.getString(1)) + "' where USER_ID=" + rs.getString(1);	//sql语句
				st.executeUpdate(sql);
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
				
		return (res > 0) ? 0 : 1 ;
	}
	
	public static int admin_update(UserBean user) {
		//0:成功 1:數据庫錯誤 2:用户不存在
		
		int res = 0;
		PreparedStatement pstmt= null;
		
		try {
			String sql = "update user set USER_NICKNAME=?, USER_ACCOUNT=?, USER_PASSWORD=?, USER_ICON=?, USER_EMAIL=?, USER_IDENTITY=? where USER_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);

			pstmt.setString(1, user.getNickname());
			pstmt.setString(2, user.getAccount());
			pstmt.setString(3, Coder.encrypted(user.getPassword()));
			pstmt.setString(4, user.getIcon());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getIdentity());
			pstmt.setString(7, user.getId());
			
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
	
	public static int admin_delete(String id) {
		//0:成功 1:數据庫錯誤 2:用户不存在
		
		int res = 0;
		PreparedStatement pstmt= null;
		
		try {
			String sql = "delete from user where USER_ID=?";
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
				String sql = "select count(*) from user where USER_NICKNAME like ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, "%" + keywords + "%");
			} else {
				String sql = "select count(*) from user";
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
	public static ArrayList<UserBean> admin_selectAll(int cpage, int count, String keywords) {
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			if (keywords != null) {
				String sql = "select * from user where USER_NICKNAME like ? order by USER_ID asc limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, "%" + keywords + "%");
				pstmt.setInt(2, (cpage - 1) * count);
				pstmt.setInt(3, count);

			} else {
				String sql = "select * from user order by USER_ID asc limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setInt(1, (cpage - 1) * count);
				pstmt.setInt(2, count);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserBean user = new UserBean(
						rs.getString("USER_ID"),
						rs.getString("USER_NICKNAME"),
						rs.getString("USER_ACCOUNT"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_ICON"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_IDENTITY"),
						rs.getString("USER_MD5ID")
				);
				list.add(user);
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
	public static UserBean admin_selectByID(String id) {
		
		ResultSet rs = null;
		UserBean user = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from user where USER_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new UserBean(
						rs.getString("USER_ID"),
						rs.getString("USER_NICKNAME"),
						rs.getString("USER_ACCOUNT"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_ICON"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_IDENTITY"),
						rs.getString("USER_MD5ID")
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

		return user;
	}
	
public static UserBean admin_signIn(String account, String password) {
		
		ResultSet rs = null;
		UserBean user = null;
		PreparedStatement ps = null;
		try {
			String sql = "select * from user where USER_ACCOUNT=? and USER_PASSWORD=?";
			ps = DataBaseConnector.getPreparedStatement(sql);
			ps.setString(1, account);
			ps.setString(2, Coder.encrypted(password));
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				user = new UserBean(
						rs.getString("USER_ID"),
						rs.getString("USER_NICKNAME"),
						rs.getString("USER_ACCOUNT"),
						rs.getString("USER_PASSWORD"),
						rs.getString("USER_ICON"),
						rs.getString("USER_EMAIL"),
						rs.getString("USER_IDENTITY"),
						rs.getString("USER_MD5ID")
				);
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static int saveEmailCaptcha(String email, String captcha) {
		//0:成功	1:数据库错误
		
		int res = 0;
		PreparedStatement pstmt= null;
		try {
			String sql = "insert captcha(CAPTCHA_EMAIL, CAPTCHA_CAPTCHA) value(?, ?)";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, captcha);
			
			res = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		return (res > 0) ? 0 : 1;
	}
	
	public static Vector<String> getCaptcha(String email) {
		//0:成功	1:数据库错误
		
		Vector<String> res = new Vector<String>();
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		try {
			String sql = "select CAPTCHA_CAPTCHA from captcha where CAPTCHA_EMAIL=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);

			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			for (int i = 0; rs.next(); ++i) {
				res.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
		
		return res;
	}
	
	public static ResultSet login(String account,String password) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select * from user where USER_ACCOUNT ='" + account + "' And USER_PASSWORD = '" + Coder.encrypted(password) + "';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet getUser(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select * from user where USER_MD5ID ='" + id +   "';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	public static int regsist(String account,String username,String password,String email) {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_ACCOUNT ='" + account + "' OR USER_EMAIL = '" + email +"';");
			if (rs.next()) return 2;
			rs.previous();
			String sql="insert into user(USER_ACCOUNT,USER_PASSWORD,USER_EMAIL,USER_NICKNAME) values(?,?,?,?) ";//sql语句
			st.close();

			PreparedStatement pstmt= DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1 , account);
			pstmt.setString(2 , Coder.encrypted(password));
			pstmt.setString(3 , email);
			pstmt.setString(4, username);
			int res = pstmt.executeUpdate();
			pstmt.close();
			
			if (res > 0) {
				Statement st1 = DataBaseConnector.getStatement();
				ResultSet rs1 = st1.executeQuery("Select USER_ID from user where USER_ACCOUNT ='" + account + "';");
				while(rs1.next()) {
					sql="update user set USER_MD5ID = '"+ Coder.encrypted(rs1.getString("USER_ID")) + "' where USER_ACCOUNT='"+account+"';";//sql语句
					st1.executeUpdate(sql);
					break;
				}
				st1.close();
			}
			
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static boolean validUser(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID ='" + id + "';");
			if (rs.next()) {
				rs.previous();
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static int updatedUserData(String userId,String userName) {
		//0:成功 1:數据庫錯誤
		try {
			Statement st = DataBaseConnector.getStatement();
			String sql="update user set USER_NICKNAME = '" +userName+ "'where USER_MD5ID = '"+ userId +"';";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static String searchUserNickNameById(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID ='" + id + "';");
			return rs.getString("USER_NICKNAME");
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static int updatedUserIcon(String userId,String icon) {
		try {
			Statement st = DataBaseConnector.getStatement();
			String sql="update user set USER_ICON = '"+ icon +"' where USER_MD5ID = '"+ userId +"';";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int updatedUserPassword(String userId,String oldPassword,String newPassword) {
		//0:成功 1:數据庫錯誤
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_MD5ID ='" + userId + "' And USER_PASSWORD ='"+Coder.encrypted(oldPassword)+"';");
			if (!rs.next()) return 1;
			rs.previous();
			String sql="update user set USER_PASSWORD = '" +Coder.encrypted(newPassword)+ "'where USER_MD5ID = '"+ userId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int updatedUserPasswordByEmail(String email,String newPassword) {
		//0:成功 1:數据庫錯誤
		try {
			Statement st = DataBaseConnector.getStatement();
			System.out.println("breakPoint");
			if (checkEmail(email) == 1) return 1;
			System.out.println("breakPoint");
			String sql="update user set USER_PASSWORD = '"  + Coder.encrypted(newPassword)+ "' where USER_Email = '"+email+"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			System.out.println("breakPoint");
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int checkEmail(String email) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_EMAIL ='" + email +"';");
			
			if (!rs.next()) return 1;
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int checkAccount(String account) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_MD5ID from user where USER_ACCOUNT ='" + account +"';");
			if (rs.next()) return 0;
			return 1;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	
	public static ResultSet getUserName(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID ='" + id +"';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet getFollow(String id) {
		ResultSet rs = null;
		try {
			Statement st = DataBaseConnector.getStatement();
			rs = st.executeQuery("Select USER_FOLLOWERNUM, USER_FOLLOWINGNUM from user where USER_MD5ID ='" + id + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return rs;
	}
	
	public static ResultSet getHotUser() {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_MD5ID,USER_ICON,USER_NICKNAME,USER_FOLLOWERSNUM from user Order by USER_FOLLOWERSNUM limit 4;");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
}
