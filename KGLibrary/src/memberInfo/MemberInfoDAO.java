package memberInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dBconn.DBconn;

public class MemberInfoDAO {
   private Connection con; //전역변수
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   
   public MemberInfoDAO() throws ClassNotFoundException, SQLException {
       con = new DBconn().getConnection();    //DB 연결
   }

//getAllInfo()   
public ArrayList<MemberInfoVO> getAllInfo(String txtId, String txtName) throws SQLException {
   ArrayList<MemberInfoVO> memList = new ArrayList<MemberInfoVO>();
   String sql = "select * from member";
   if(!txtId.isEmpty() && txtName.isEmpty()) {
      sql += " where mem_id = ?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, txtId);
   } else if(txtId.isEmpty() && !txtName.isEmpty()) {
      sql += " where mem_Name = ?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, txtName);
   } else {
      pstmt = con.prepareStatement(sql);
   }
   
   
   
   rs = pstmt.executeQuery();
   
   while(rs.next()) {
      String mem_id = rs.getString("mem_id");
      String mem_name = rs.getString("mem_name");
      String mem_pw = rs.getString("mem_pw");
      String mem_tel = rs.getString("mem_tel");
      Date L_date = rs.getDate("L_date");
      Date R_date = rs.getDate("R_date");
      int admin = rs.getInt("admin");
      
      MemberInfoVO mvo = new MemberInfoVO(mem_id , mem_name, mem_pw, mem_tel, L_date, R_date, admin);
      memList.add(mvo);
      } return memList;
   } //getAllinfo() - end

//delete
public boolean delete_mem(String mem_id, String mem_pw){
	   String sql = "delete from member where mem_id = ? and mem_pw = ?";
	   try {
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, mem_id);
	      pstmt.setString(2, mem_pw);
	      pstmt.executeUpdate();
	   } catch(SQLException e) {
	      System.out.println("delete error");
	      return false;
	   } return true;
	}   //delete end //delete end

//update 
public boolean update_mem(String mem_id, String mem_pw, String mem_tel, String mem_name, String purpose) {
	String sql = null;
   if(purpose.equals("admin")) {
	   sql = "update member set admin = 1 where mem_id = ?";
   } else if(purpose.equals("memberChange"))
	   sql = "update member set tel = ? where mem_id = ?";
   try {
      pstmt = con.prepareStatement(sql);
//      pstmt.setString(1, mem_pw);
      pstmt.setString(1, mem_id);
      pstmt.executeUpdate();
   } catch(SQLException e) {
      System.out.println("update error");
      return false;
   } return true;
}   //update end


//insert
public boolean insert_mem(String mem_id, String mem_name, String mem_pw, String mem_tel, String book_num, 
      String sL_date, String sR_date) {
   String sql = "insert into member values(?,?,?,?,?,?,?,?)";
   try {
      pstmt = con.prepareStatement(sql);      
      pstmt.setString(1, mem_id);
      pstmt.setString(2, mem_name);
      pstmt.setString(3, mem_pw);
      pstmt.setString(4, mem_tel);
      int l_year = Integer.parseInt(sL_date.substring(0,4))-1900;
        int l_month=Integer.parseInt(sL_date.substring(4,6))-1;
        int l_day = Integer.parseInt(sL_date.substring(6,8));
      Date L_date = new Date(l_year, l_month, l_day);
      pstmt.setDate(5, L_date);
      int r_year = Integer.parseInt(sR_date.substring(0,4))-1900;
        int r_month=Integer.parseInt(sR_date.substring(4,6))-1;
        int r_day = Integer.parseInt(sR_date.substring(6,8));
      Date R_date = new Date(r_year, r_month, r_day);
      pstmt.setDate(6, R_date);
      pstmt.executeQuery();      
   } catch(SQLException e) {
      System.out.println("insert error");
      return false;
   }return true;
}   //insert end

//회원가입
public boolean new_mem(String id, String pw, String name, String tel) {
   boolean flag = false;
   String sql = "insert into member(mem_id, mem_pw, mem_name, mem_tel) "
         +"values(?,?,?,?)";
   try {   
   pstmt = con.prepareStatement(sql);
   pstmt.setString(1, id);
   pstmt.setString(2, pw);
   pstmt.setString(3, name);
   pstmt.setString(4, tel);
   int a = pstmt.executeUpdate();
   if(a==1)
      flag = true;
   else
      flag = false;   
   } catch (SQLException e) {
      e.printStackTrace();
   }return flag;
} //회원가입  end check ok


//로그인
@SuppressWarnings("unused")
public boolean mem_Login(String id2, String pw2) {
   boolean flag = false;
   int sw = 0;
   ArrayList<String> idList = new ArrayList<>();
   ArrayList<String> pwList = new ArrayList<>();
   
   String sql = "select mem_id, mem_pw from member where mem_id = ? and mem_pw = ?";
   try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id2);
      pstmt.setString(2, pw2);
      pstmt.executeQuery();
      rs = pstmt.executeQuery();      
      
      while(rs.next()) {
         String id = rs.getString("mem_id");
         idList.add(id);
      }
      if(idList.isEmpty()) {
         sw = 1 ;
      }
      else {
         flag = true;
         sw = 0;
         }   
      if(sw==1)
         JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 확인하세요");
   } catch(SQLException e) {
      e.printStackTrace();
   }return flag;
}   //로그인 end check ok


//아이디 중복확인
public boolean id_check(String ch) {
   ArrayList<String> list = new ArrayList<>();
   boolean flag = false;
   String sql = "select mem_id from member";
   try {
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
         String check = rs.getString("mem_id");
         list.add(check);
      }
      int sw = 0;
   if(sw==0){
      for(String l : list) {
         if(ch.equals(l)) { 
            JOptionPane.showMessageDialog(null, "아이디 중복");
            flag = false;
            sw=0;
            break;
            }
         else sw=1;
      }
   }
   if(sw==1)
      JOptionPane.showMessageDialog(null, "아이디사용가능");
   
   } catch(SQLException e) {
      e.printStackTrace();
   }return flag;
}

//회원 관리 조회
public boolean mem_search(String mem_id, String mem_name) {
   String i = mem_id;
   String b = mem_name;
   String sql = "select mem_name 이름, mem_num 연락처, soo 도서 " + "대여, L_date 대여일, R_date 반납일 from member"
         +"where mem_id like '"+i+"%' or mem_id like '%"+i+"%' or mem_id like '%"+i+"' ormem_name like'"+b+"%'"
         +" or mem_name like '%"+b+"%' or mem_name like'%"+b+"' or (mem_id like 'i%' and mem_name like'"+b+"%') or "
         +"(mem_id like '"+i+"%' and mem_name like '%"+b+"%') or (mem_id like '"+i+"%' and mem_name like'%"+b+"') "
         +"or (mem_id like '%"+i+"%' and mem_name like'"+b+"%') or (mem_id like '%"+i+"%' and mem_name like '%"+b+"%') "
         +"or (mem_id like '%"+i+"%' and mem_name like'%"+b+"') or (mem_id like '%"+i+"' and mem_name like'"+b+"%') "
         +"or (mem_id like '%"+i+"' and mem_name like '%"+b+"%') or (mem_id like '%"+i+"' and mem_name like'%"+b+"' )";
   try {
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
         rs.getString("meme_name");
         rs.getString("mem_num");
         rs.getInt("soo");
         rs.getDate("L_date");
         rs.getDate("R_date");
      }
   } catch(SQLException e) {
      return false;
   }return true;
} //회원 관리 조회 end


//회원 정보 변경
public boolean set_mem(String a, String b, String c, String d) {
 String sql = "update member set mem_pw = ?, mem_name = ?, mem_tel = ? where mem_id = ?";
 try {
    pstmt = con.prepareStatement(sql);
    pstmt.setString(1, a);
    pstmt.setString(2, b);
    pstmt.setString(3, c);
    pstmt.setString(4, d);
    pstmt.executeUpdate();
 } catch(SQLException e) {
    e.printStackTrace();
    return false;
 }
 return true;
}   //회원 정보 변경 - end
   
         
}