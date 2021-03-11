package bookInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dBconn.DBconn;

public class BookInfoDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public BookInfoDAO() throws ClassNotFoundException, SQLException {
		con = new DBconn().getConnection();
	}
	
	public ArrayList<BookInfoVO> getAllInfo(String txtBookTitle, String txtAuthor) throws SQLException {
		ArrayList<BookInfoVO> bookarray = new ArrayList<BookInfoVO>();
		String sql = "select * from Book";
		if(!txtBookTitle.isEmpty() && !txtAuthor.isEmpty()) {
			sql += " where (book_title like lower('"+txtBookTitle+"%') and author like lower('"+txtAuthor+"%')) or (book_title like lower('"+txtBookTitle+"%') and author like lower('%"+txtAuthor+"'))"
			         +" or (book_title like lower('"+txtBookTitle+"%') and author like lower('%"+txtAuthor+"%')) or (book_title like lower('%"+txtBookTitle+"') and author like ('"+txtAuthor+"%'))"
			         +" or (book_title like lower('%"+txtBookTitle+"') and author like ('%"+txtAuthor+"')) or (book_title like lower('%"+txtBookTitle+"') and author like ('%"+txtAuthor+"%'))"
			         +" or (book_title like lower('%"+txtBookTitle+"%') and author like ('%"+txtAuthor+"%')) or (book_title like lower('%"+txtBookTitle+"%') and author like ('"+txtAuthor+"%'))"
			         +" or (book_title like lower('%"+txtBookTitle+"%') and author like ('%"+txtAuthor+"'))";
			pstmt = con.prepareStatement(sql);
		} else if(!txtBookTitle.isEmpty() && txtAuthor.isEmpty()) {
			sql += " where book_title like lower('"+txtBookTitle+"%') or book_title like lower('%"+txtBookTitle+"') or book_title like lower('%"+txtBookTitle+"%')";
			pstmt = con.prepareStatement(sql);
		} else if(txtBookTitle.isEmpty() && !txtAuthor.isEmpty()) {
			sql += " where author like lower('"+txtAuthor+"%') or author like ('%"+txtAuthor+"%') or author like ('%"+txtAuthor+"')";
			pstmt = con.prepareStatement(sql);
		} else if(txtBookTitle.isEmpty() && txtAuthor.isEmpty()) {
			pstmt = con.prepareStatement(sql);
		}
	
		
//		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			String book_num = rs.getString("book_num");
			String book_title = rs.getString("book_title");
			String author = rs.getString("author");
			String company = rs.getString("company");
			int inventory = rs.getInt("inventory");
			String pos = rs.getString("pos");
			Date L_Date = rs.getDate("L_Date");
			Date R_Date = rs.getDate("R_Date");
			String mem_id = rs.getString("mem_id");
			
			BookInfoVO bv = new BookInfoVO(book_num, book_title, author, company, inventory, pos, L_Date, R_Date, mem_id);
			bookarray.add(bv);
		}
		return bookarray;
	}
	
	public ArrayList<BookInfoVO> getAllInfo(String txtMemId) throws SQLException {
	      ArrayList<BookInfoVO> bookarray = new ArrayList<BookInfoVO>();
	      String sql = "select * from Book where mem_id = ?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, txtMemId);
	      
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	         String book_num = rs.getString("book_num");
	         String book_title = rs.getString("book_title");
	         String author = rs.getString("author");
	         String company = rs.getString("company");
	         int inventory = rs.getInt("inventory");
	         String pos = rs.getString("pos");
	         Date L_Date = rs.getDate("L_Date");
	         Date R_Date = rs.getDate("R_Date");
	         String mem_id = rs.getString("mem_id");
	         
	         BookInfoVO bv = new BookInfoVO(book_num, book_title, author, company, inventory, pos, L_Date, R_Date, mem_id);
	         bookarray.add(bv);
	      }
	      return bookarray;
	   }
	   public ArrayList<BookInfoVO> getAllInfo() throws SQLException {
	      ArrayList<BookInfoVO> bookarray = new ArrayList<BookInfoVO>();
	      String sql = "select * from Book";
	      pstmt = con.prepareStatement(sql);

	      
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	         String book_num = rs.getString("book_num");
	         String book_title = rs.getString("book_title");
	         String author = rs.getString("author");
	         String company = rs.getString("company");
	         int inventory = rs.getInt("inventory");
	         String pos = rs.getString("pos");
	         Date L_Date = rs.getDate("L_Date");
	         Date R_Date = rs.getDate("R_Date");
	         String mem_id = rs.getString("mem_id");
	         
	         BookInfoVO bv = new BookInfoVO(book_num, book_title, author, company, inventory, pos, L_Date, R_Date, mem_id);
	         bookarray.add(bv);
	      }
	      return bookarray;
	   }
	   
	public ArrayList<BookInfoVO> search(String book_title1, String author1) throws SQLException {
		ArrayList<BookInfoVO> bookarray = new ArrayList<BookInfoVO>();
		String sql = "select * from Book where book_title like initcap('%?')"
				+ "or book_title like initcap('%?%') or book_title like initcap('?%')"
				+ "or author like initcap('%?') or author like initcap('%?%')"
				+ "or author like initcap('?%') order by book_title";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book_title1);
		pstmt.setString(2, book_title1);
		pstmt.setString(3, book_title1);
		pstmt.setString(4, author1);
		pstmt.setString(5, author1);
		pstmt.setString(6, author1);
		pstmt.executeUpdate();
		rs = pstmt.executeQuery();
		while(rs.next()) {
			String book_num = rs.getString("book_num");
			String book_title = rs.getString("book_title");
			String author = rs.getString("author");
			String company = rs.getString("company");
			int inventory = rs.getInt("inventory");
			String pos = rs.getString("pos");
			Date L_Date = rs.getDate("L_Date");
			Date R_Date = rs.getDate("R_Date");
			String mem_id = rs.getString("mem_id");
			BookInfoVO bv = new BookInfoVO(book_num, book_title, author, company, inventory, pos, L_Date, R_Date, mem_id);
			if(book_title1.equals(book_title))
				bookarray.add(bv);
		}
		return bookarray;	
	}
	
	@SuppressWarnings("deprecation")
	public boolean insert(String book_num, String book_title, String author, String company,
			int inventory, String pos, String sL_Date, String sR_Date) throws ParseException {
		String sql = "insert into BOOK values('?', '?', '?', '?', '?', '?', '?', '?')";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book_num);
			pstmt.setString(2, book_title);
			pstmt.setString(3, author);
			pstmt.setString(4, company);
			pstmt.setInt(5, inventory);
			pstmt.setString(6, pos);
			int l_year = Integer.parseInt(sL_Date.substring(0,4))-1900;
	        int l_month=Integer.parseInt(sL_Date.substring(4,6))-1;
	        int l_day = Integer.parseInt(sL_Date.substring(6,8));
			Date L_Date = new Date(l_year, l_month, l_day);
			pstmt.setDate(7, L_Date);
			int r_year = Integer.parseInt(sR_Date.substring(0,4))-1900;
	        int r_month=Integer.parseInt(sR_Date.substring(4,6))-1;
	        int r_day = Integer.parseInt(sR_Date.substring(6,8));
			Date R_Date = new Date(r_year, r_month, r_day);
			pstmt.setDate(8, R_Date);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("insert exception");
			return false;
		}
		return true;
	}
	
	public boolean book_insert(String book_num, String book_title, String author, String company, int inventory, String pos) {
		String sql = "insert into Book(book_num, book_title, author, company, inventory, pos) values(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book_num);
			pstmt.setString(2, book_title);
			pstmt.setString(3, author);
			pstmt.setString(4, company);
			pstmt.setInt(5, inventory);
			pstmt.setString(6, pos);

			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("insert exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean update(String book_num, String book_title, String author, 
			String company, int inventory, String pos, String sL_Date, String sR_Date) throws ParseException {
		String sql = "update Book set book_title='?', author='?', company='?',"
				+ " inventory='?', pos='?', L_Date='?', R_Date='?' where book_num='?'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book_title);
			pstmt.setString(2, author);
			pstmt.setString(3, company);
			pstmt.setInt(4, inventory);
			pstmt.setString(5, pos);
			int l_year = Integer.parseInt(sL_Date.substring(0,4))-1900;
	        int l_month=Integer.parseInt(sL_Date.substring(4,6))-1;
	        int l_day = Integer.parseInt(sL_Date.substring(6,8));
			Date L_Date = new Date(l_year, l_month, l_day);
			pstmt.setDate(6, L_Date);
			int r_year = Integer.parseInt(sR_Date.substring(0,4))-1900;
	        int r_month=Integer.parseInt(sR_Date.substring(4,6))-1;
	        int r_day = Integer.parseInt(sR_Date.substring(6,8));
			Date R_Date = new Date(r_year, r_month, r_day);
			pstmt.setDate(7, R_Date);
			pstmt.setString(8, book_num);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("update exception");
			return false;
		}
		return true;	
	}
	
	public boolean delete(String book_num) {
		String sql = "delete from Book where book_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book_num);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("delete exception");
			return false;
		}
		return true;
	}

	public boolean update2(String book_num, String mem_id) {
	      String sql = "update book set mem_id= ?, l_date=?, r_date=? where book_num=?";
	      try {
	         Calendar cal = new GregorianCalendar();
	         Date ldate = new Date(cal.getTimeInMillis());
	         
	         cal.add(Calendar.DATE, 7);
	         Date rdate = new Date(cal.getTimeInMillis());
	         
	         
	         
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, mem_id);
	         pstmt.setDate(2, ldate);
	         pstmt.setDate(3, rdate);         
	         pstmt.setString(4, book_num);
	         pstmt.executeUpdate();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         return false;
	      }
	      return true;
	   }
	
	public boolean update3(String mem_id) {
	      String sql = "update book set mem_id=null, l_date=null, r_date=null where mem_id=?";
	      try {
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, mem_id);
	      pstmt.executeUpdate();
	   } catch (SQLException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      return false;
	   }
	   return true;
	   }


}
