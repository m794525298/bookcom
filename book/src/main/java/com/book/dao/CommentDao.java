package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.taglibs.standard.tag.common.core.ParamParent;

import com.book.common.Coder;
import com.book.common.DataBaseConnector;
import com.book.pojo.CommentBean;

public class CommentDao {
	static Statement st;
		
	public static int admin_insert(CommentBean comment) {
		//0:成功 1:數据庫錯誤 2:帖子不存在 3:被回复评论不存在  4:被回复评论不在对应帖子下
		
		int res = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {		
			Statement st1 = DataBaseConnector.getStatement();
			rs = st1.executeQuery("select POST_PUBLISHERID from post where POST_ID=" + comment.getPostId() + " and POST_ISEXIST=0");
			if (!rs.next()) return 2;
			String postPublisherId = rs.getString(1);
			
			String parentPublisherId = null;
			if(comment.getParentId() != null) {
				Statement st2 = DataBaseConnector.getStatement();
				rs = st2.executeQuery("select COMMENT_PUBLISHERID, COMMENT_POSTID from comment where COMMENT_ID=" + comment.getParentId() + " and COMMENT_ISEXIST=0");
				if (!rs.next())
					return 3;
				if(!comment.getPostId().equals(rs.getString(2)))
					return 4;
				
				parentPublisherId = rs.getString(1);
				
			}
			
			String sql = "insert into comment(COMMENT_POSTID, COMMENT_PARENTID, COMMENT_CONTENT, COMMENT_PUBLISHERID) value(?,?,?,?)";
											 
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, comment.getPostId());
			pstmt.setString(2, comment.getParentId());
			pstmt.setString(3, comment.getContent());
			pstmt.setString(4, Coder.encrypted(comment.getPublisherId()));
			
			res = pstmt.executeUpdate();
			
			if ((res > 0) && (parentPublisherId != null)) {
				rs = pstmt.getGeneratedKeys();
				if(!rs.next())
					throw new SQLException();
				
				sql = "insert into reply(COMMENT_ID, COMMENT_PARENTID, COMMENT_POSTPUBLISHERID, COMMENT_PARENTPUBLISHERID) value(?, ?, ?, ?)";	//sql语句
					   
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setLong(1, rs.getLong(1));
				pstmt.setString(2, comment.getParentId());
				pstmt.setString(3, postPublisherId);
				pstmt.setString(4, parentPublisherId);
				res = pstmt.executeUpdate();
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
	
	public static int admin_update(CommentBean comment) {
		//0:成功 1:數据庫錯誤 2:帖子不存在 3:被回复评论不存在   4:被回复评论不在对应帖子下
		
		int res = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {		
			Statement st1 = DataBaseConnector.getStatement();
			rs = st1.executeQuery("select POST_PUBLISHERID from post where POST_ID=" + comment.getPostId() + " and POST_ISEXIST=0");
			if (!rs.next()) return 2;
			String postPublisherId = rs.getString(1);
			
			String parentPublisherId = null;
			if(comment.getParentId() != null) {
				Statement st2 = DataBaseConnector.getStatement();
				rs = st2.executeQuery("select COMMENT_PUBLISHERID, COMMENT_POSTID from comment where COMMENT_ID=" + comment.getParentId() + " and COMMENT_ISEXIST=0");
				if (!rs.next()) return 3;
				if(!comment.getPostId().equals(rs.getString(2)))
					return 4;
				
				parentPublisherId = rs.getString(1);
			}
			
			String sql = "update comment set COMMENT_POSTID=?, COMMENT_PARENTID=?, COMMENT_CONTENT=?, COMMENT_PUBLISHERID=?, COMMENT_ISEXIST=? where COMMENT_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, comment.getPostId());
			pstmt.setString(2, comment.getParentId());
			pstmt.setString(3, comment.getContent());
			pstmt.setString(4, comment.getPublisherId());		
			pstmt.setString(5, comment.getIsExist());
			pstmt.setString(6, comment.getId());
			res = pstmt.executeUpdate();
	
			if ((res > 0) && (parentPublisherId != null)) {
				Statement st3 = DataBaseConnector.getStatement();
				sql = ("insert into reply(COMMENT_ID, COMMENT_PARENTID, COMMENT_POSTPUBLISHERID, COMMENT_PARENTPUBLISHERID) value(?, ?, ?, ?)");	//sql语句
				
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, comment.getId());
				pstmt.setString(2, comment.getParentId());
				pstmt.setString(3, postPublisherId);
				pstmt.setString(4, parentPublisherId);
				st3.executeUpdate(sql);
			}
		} catch (SQLException e) {
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
		
		return (res > 0) ? 0 : 2;
	}
	
	public static int admin_delete(String id) {
		//0:成功 1:數据庫錯誤 2:评论不存在
		
		int res = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {
			Statement st1 = DataBaseConnector.getStatement();
			rs = st1.executeQuery("select COMMENT_PARENTID from comment where COMMENT_ID=" + id);
			long parentId = 0;
			if (rs.next()) {
				parentId = rs.getLong(1);
			}
			
			String sql = "update comment set COMMENT_ISEXIST=1 where COMMENT_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1 , id);
			res = pstmt.executeUpdate();
			
			if(res > 0 && parentId != 0) {
				sql = "delete from reply where COMMENT_ID=? and COMMENT_PARENTID=?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setLong(2, parentId);
				pstmt.executeUpdate();
			}
			
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
				String sql = "select count(*) from comment where COMMENT_CONTENT like ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, "%" + keywords + "%");
			} else {
				String sql = "select count(*) from comment";
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
	 * 通过关键字查找评论列表
	 * @param cpage 当前页数
	 * @param count 显示个数
	 * @param keywords 搜索关键字
	 * @return 返回指定的评论集
	 */
	public static ArrayList<CommentBean> admin_selectAll(int cpage, int count, String keywords) {
		ArrayList<CommentBean> list = new ArrayList<CommentBean>();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
	
		try {
			if (keywords != null) {
				String sql = "select * from comment where COMMENT_CONTENT like ? asc limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setString(1, "%" + keywords + "%");
				pstmt.setInt(2, (cpage - 1) * count);
				pstmt.setInt(3, count);
	
			} else {
				String sql = "select * from comment limit ?, ?";
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setInt(1, (cpage - 1) * count);
				pstmt.setInt(2, count);
			}
	
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				CommentBean comment = new CommentBean(
						rs.getString("COMMENT_ID"),
						rs.getString("COMMENT_TIME"),
						rs.getString("COMMENT_POSTID"),
						rs.getString("COMMENT_PARENTID"),
						rs.getString("COMMENT_CONTENT"),
						rs.getString("COMMENT_PUBLISHERID"),
						rs.getString("COMMENT_ISEXIST")
				);
				list.add(comment);
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
	 * 通过ID查找评论
	 * @param id 指定ID
	 * @return 返回评论实体
	 */
	public static CommentBean admin_selectByID(String id) {
		
		ResultSet rs = null;
		CommentBean comment = null;
		PreparedStatement pstmt = null;
	
		try {
			String sql = "select * from comment where COMMENT_ID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
	
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				comment = new CommentBean(
						rs.getString("COMMENT_ID"),
						rs.getString("COMMENT_TIME"),
						rs.getString("COMMENT_POSTID"),
						rs.getString("COMMENT_PARENTID"),
						rs.getString("COMMENT_CONTENT"),
						rs.getString("COMMENT_PUBLISHERID"),
						rs.getString("COMMENT_ISEXIST")
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
	
		return comment;
	}
	
	public static int insertComment(CommentBean comment) {
		//0:成功 1:數据庫錯誤 2:帖子不存在 3:被回复评论不存在  4:被回复评论不在对应帖子下
		
		int res = 0;
		ResultSet rs =null;
		PreparedStatement pstmt= null;
		
		try {		
			Statement st1 = DataBaseConnector.getStatement();
			rs = st1.executeQuery("select POST_PUBLISHERID from post where POST_ID=" + comment.getPostId() + " and POST_ISEXIST=0");
			if (!rs.next()) return 2;
			String postPublisherId = rs.getString(1);
			
			String parentPublisherId = null;
			if(comment.getParentId() != null) {
				Statement st2 = DataBaseConnector.getStatement();
				rs = st2.executeQuery("select COMMENT_PUBLISHERID, COMMENT_POSTID from comment where COMMENT_ID=" + comment.getParentId() + " and COMMENT_ISEXIST=0");
				if (!rs.next())
					return 3;
				if(!comment.getPostId().equals(rs.getString(2)))
					return 4;
				
				parentPublisherId = rs.getString(1);
			}
			
			String sql = "insert into comment(COMMENT_POSTID, COMMENT_PARENTID, COMMENT_CONTENT, COMMENT_PUBLISHERID) value(?,?,?,?)";
											 
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, comment.getPostId());
			pstmt.setString(2, comment.getParentId());
			pstmt.setString(3, comment.getContent());
			pstmt.setString(4, comment.getPublisherId());
			
			res = pstmt.executeUpdate();
			
			if ((res > 0) && (parentPublisherId != null)) {
				rs = pstmt.getGeneratedKeys();
				if(!rs.next())
					throw new SQLException();
				
				sql = "insert into reply(COMMENT_ID, COMMENT_PARENTID, COMMENT_POSTPUBLISHERID, COMMENT_PARENTPUBLISHERID) value(?, ?, ?, ?)";	//sql语句
					   
				pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
				pstmt.setLong(1, rs.getLong(1));
				pstmt.setString(2, comment.getParentId());
				pstmt.setString(3, postPublisherId)	;
				pstmt.setString(4, parentPublisherId);
				System.out.println("arr1: " + postPublisherId);
				System.out.println("arr2: " + parentPublisherId);
				res = pstmt.executeUpdate();
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
	
	public static int getPostCommentNum(String postId) {
		int commentNum = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select count(*) from comment where COMMENT_POSTID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1,  postId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				commentNum = rs.getInt(1);
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

		return commentNum;
	}
	
	public static int getUserCommentNum(String userId) {
		int commentNum = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select count(*) from reply where COMMENT_POSTPUBLISHERID=? or COMMENT_PARENTPUBLISHERID=?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				commentNum = rs.getInt(1);
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

		return commentNum;
	}
	
	public static ResultSet getPostComments(String postId, String page, int count) {
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
	
		try {
			String sql = "select * from comment where Comment_POSTID=? and COMMENT_ISEXIST=0 limit ?, ?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, postId);
			pstmt.setInt(2, (Integer.valueOf(page) - 1) * count);
			pstmt.setInt(3, count);
			
			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return rs;
	}
	
	
	public static ResultSet getUserComments(String userId, String page, int count) {
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
				
		try {
			String sql = "select COMMENT_ID from reply where COMMENT_POSTPUBLISHERID=? or COMMENT_PARENTPUBLISHERID=? limit ?, ?";
			pstmt = DataBaseConnector.getConnection().prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, (Integer.valueOf(page) - 1) * count);
			pstmt.setInt(4, 2*count);
			
			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return rs;
	}
	
}
