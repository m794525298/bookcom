package com.book.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import com.book.common.Coder;
import com.book.common.DataBaseConnector;
import com.book.pojo.PostBean;

public class PostDao {
	
	public static int admin_insert(PostBean post) {
		//0:成功 1:數据庫錯誤 2:不存在用户
		int res = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs2 = st.executeQuery("select USER_ID from user where USER_MD5ID='" + post.getPublisherId() + "'");
			if(!rs2.next())
				return 2;
			
			String sql = "insert into post(POST_COVER, POST_CONTENT, POST_POSTTITLE, POST_BOOKTYPE, POST_BOOKTITLE, POST_BOOKAUTHOR, POST_PUBLISHERID) value(?,?,?,?,?,?,?)";
			
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, post.getCover());
			pstmt.setString(2, post.getContent());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getBookType());
			pstmt.setString(5, post.getBookTitle());
			pstmt.setString(6, post.getBookAuthor());
			pstmt.setString(7, Coder.encrypted(post.getPublisherId()));
			
			res = pstmt.executeUpdate();
			
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
	
	public static int admin_update(PostBean post) {
		//0:成功 1:數据庫錯誤 2:帖子不存在
		
		int res = 0;
		PreparedStatement pstmt= null;
		
		try {
			String sql = "update post set POST_COVER=?, POST_CONTENT=?, POST_POSTTITLE=?, "
					+ "POST_BOOKTYPE=?, POST_BOOKTITLE=?, POST_BOOKAUTHOR=?, POST_PUBLISHERID=?, POST_ISEXIST=? where POST_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);

			pstmt.setString(1, post.getCover());
			pstmt.setString(2, post.getContent());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getBookType());
			pstmt.setString(5, post.getBookTitle());
			pstmt.setString(6, post.getBookAuthor());
			pstmt.setString(7, Coder.encrypted(post.getPublisherId()));
			pstmt.setString(8, post.getIsExist());
			pstmt.setString(9, post.getId());			
			
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
		
		return (res > 0) ? 0 : 2;
	}
	
	public static int admin_delete(String id) {
		//0:成功 1:數据庫錯誤 2:帖子不存在
		
		int res = 0;
		PreparedStatement pstmt= null;
		
		try {
			String sql = "update post set POST_ISEXIST=1 where POST_ID=? and POST_ISEXIST=0";
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
				String sql = "select count(*) from post where POST_POSTTITLE like ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, "%" + keywords + "%");
			} else {
				String sql = "select count(*) from post";
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
	 * 通过关键字查找帖子列表
	 * @param cpage 当前页数
	 * @param count 显示个数
	 * @param keywords 搜索关键字
	 * @return 返回指定的帖子集
	 */
	public static ArrayList<PostBean> admin_selectAll(int cpage, int count, String keywords) {
		ArrayList<PostBean> list = new ArrayList<PostBean>();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			if (keywords != null) {
				String sql = "select * from post where POST_POSTTITLE like ? limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, "%" + keywords + "%");
				pstmt.setInt(2, (cpage - 1) * count);
				pstmt.setInt(3, count);

			} else {
				String sql = "select * from post limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setInt(1, (cpage - 1) * count);
				pstmt.setInt(2, count);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostBean post = new PostBean(
						rs.getString("POST_ID"),
						rs.getString("POST_TIME"),
						rs.getString("POST_COVER"),
						rs.getString("POST_CONTENT"),
						rs.getString("POST_POSTTITLE"),
						rs.getString("POST_BOOKTYPE"),
						rs.getString("POST_BOOKTITLE"),
						rs.getString("POST_BOOKAUTHOR"),
						rs.getString("POST_PUBLISHERID"),
						rs.getString("POST_ISEXIST")
				);
				list.add(post);
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
	 * 通过ID查找帖子
	 * @param id 指定ID
	 * @return 返回帖子实体
	 */
	public static PostBean admin_selectByID(String id) {
		
		ResultSet rs = null;
		PostBean post = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from post where POST_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				post = new PostBean(
						rs.getString("POST_ID"),
						rs.getString("POST_TIME"),
						rs.getString("POST_COVER"),
						rs.getString("POST_CONTENT"),
						rs.getString("POST_POSTTITLE"),
						rs.getString("POST_BOOKTYPE"),
						rs.getString("POST_BOOKTITLE"),
						rs.getString("POST_BOOKAUTHOR"),
						rs.getString("POST_PUBLISHERID"),
						rs.getString("POST_ISEXIST")
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

		return post;
	}
	
	private static boolean saveCover(String path , String icon) {
		return Coder.saveBase64Image(path, icon);
	}
	
	public static int insertPost(PostBean post) throws SQLException {
		//0:成功 1:數据庫錯誤 2:不存在用户 3:存储图片失败
		int res = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs2 = st.executeQuery("select USER_ID from user where USER_MD5ID='" + post.getPublisherId() + "'");
			if(!rs2.next())
				return 2;
			
			String sql = "insert into post(POST_CONTENT, POST_POSTTITLE, POST_BOOKTYPE, POST_BOOKTITLE, POST_BOOKAUTHOR, POST_PUBLISHERID) value(?,?,?,?,?,?)";
			
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, post.getContent());
			pstmt.setString(2, post.getTitle());
			pstmt.setString(3, post.getBookType());
			pstmt.setString(4, post.getBookTitle());
			pstmt.setString(5, post.getBookAuthor());
			pstmt.setString(6, post.getPublisherId());
			
			res = pstmt.executeUpdate();

			if(res > 0) {
				rs = pstmt.getGeneratedKeys();
				if(!rs.next())
					throw new SQLException();
				
				String path = System.getProperty("user.home")+"/book/src/main/webapp/Cover/" + rs.getLong(1) +".png";
				
				System.out.println(path);
				
				if(saveCover(path, post.getCover())) {
					Statement st2 = DataBaseConnector.getStatement();
					res = st2.executeUpdate("update post set POST_COVER='Cover/" + rs.getLong(1) + ".png' where POST_ID='" + rs.getLong(1) + "'");
				} else {
					return 3;
				}
			} else {
				return 1;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return (res > 0) ? rs.getInt(1) : 0;
	}
	
	public static int updatePost(PostBean post) {
		//0:成功 1:數据庫錯誤 2:帖子不存在
		
		int res = 0;
		PreparedStatement pstmt= null;
		
		try {
			String sql = "update post set POST_COVER=?, POST_CONTENT=?, POST_POSTTITLE=?, "
					+ "POST_BOOKTYPE=?, POST_BOOKTITLE=?, POST_BOOKAUTHOR=? where POST_PUBLISHERID=? and POST_ID=? and POST_ISEXIST=0";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);

			pstmt.setString(1, post.getCover());
			pstmt.setString(2, post.getContent());
			pstmt.setString(3, post.getTitle());
			pstmt.setString(4, post.getBookType());
			pstmt.setString(5, post.getBookTitle());
			pstmt.setString(6, post.getBookAuthor());
			pstmt.setString(7, post.getPublisherId());
			pstmt.setString(8, post.getId());
			
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
		
		return (res > 0) ? 0 : 2;
	}
	
public static ResultSet selectPostByID(String id) {
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select * from post where POST_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}
	
	public static boolean deletePost(String postId,String userId) {
		if (!UserDao.validUser(userId)) return false;
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select POST_POSTTITLE from post where POST_ID ='" + postId + "' And POST_PUBLISHERID ='" + userId + "' And POST_ISEXIST = 0;");
			if (!rs.next()) return false;
			String sql="update post set POST_ISEXIST = 1 where POST_ID = '"+postId+"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static ResultSet getPostByUser(String userId) {
		Statement st = DataBaseConnector.getStatement();
		try {
			System.out.println(userId);
			ResultSet rs = st.executeQuery("Select * from post where POST_PUBLISHERID ='" + userId +"' And POST_ISEXIST = 0 order by POST_TIME desc;");
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	public static ResultSet searchPost(String keyword) {
		Statement st = DataBaseConnector.getStatement();
		String sql;
		try {
			if (keyword.isEmpty()) {
				sql = "Select * from post where POST_ISEXIST = 0;";
			}else {
				sql ="Select * from post where POST_POSTTITLE like  '%"+keyword+"%' And POST_ISEXIST = 0;";
			}
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet searchPost(String keyword,String bookType) {
		Statement st = DataBaseConnector.getStatement();
		String sql;
		try {
			if (keyword.isEmpty()) {
				sql = "Select * from post where POST_BOOKTYPE = "+ bookType +" And POST_ISEXIST = 0;";
			}else {
				sql =" Select * from post where POST_POSTTITLE like  '%"+keyword+"%' and POST_BOOKTYPE = "+ bookType +" And POST_ISEXIST = 0;";
			}
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static ResultSet searchPostByNickName(String keyword) {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select post.* from post,user where post.POST_PUBLISHERID = user.USER_MD5ID and user.USER_NICKNAME like '%"+keyword+"%' And POST_ISEXIST = 0;");
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static ResultSet getHotPost(String page) {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select * from post where POST_ISEXIST = 0 Order by POST_COMMENTNUM DESC");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static int getPostNum() {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select POST_ID as count from post where POST_ISEXIST = 0;");
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public static ResultSet getPostDetail(String postId) {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select * from post where POST_ID = "+postId+" And POST_ISEXIST = 0;");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static int addCommentNum(String postId) {
		// 0:成功, 1:数据库错误, 2:帖子不存在
		
		int res = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Statement st = DataBaseConnector.getStatement();
			rs = st.executeQuery("select * from post where POST_ID = " + postId + " And POST_ISEXIST = 0");
			if(!rs.next())
				return 2;
			
			String sql = "update post set POST_COMMENTNUM=POST_COMMENTNUM+1 where POST_ID=? and POST_ISEXIST=0";
			pstmt = DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1, postId);
			res = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 1;
		}
		
		return (res > 0) ? 0 : 1;
	}
	
	public static int minusCommentNum(String postId) {
		// 0:成功, 1:数据库错误, 2:帖子不存在
		
		int res = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Statement st = DataBaseConnector.getStatement();
			rs = st.executeQuery("select * from post where POST_ID = " + postId + " And POST_ISEXIST = 0");
			if(!rs.next())
				return 2;
			
			String sql = "update post set POST_COMMENTNUM=POST_COMMENTNUM-1 where POST_ID=? and POST_ISEXIST=0";
			pstmt = DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1, postId);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 1;
		}
		
		return (res > 0) ? 0 : 1;
	}
}
