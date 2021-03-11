package roomInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dBconn.DBconn;

public class RoomInfoDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public RoomInfoDAO() throws ClassNotFoundException, SQLException {
		con = new DBconn().getConnection();
	}
	
	public ArrayList<RoomInfoVO> getAllInfo() throws SQLException {
		ArrayList<RoomInfoVO> roomarray = new ArrayList<RoomInfoVO>();
		String sql = "select * from Room";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			String seat_num = rs.getString("seat_num");
			int seat = rs.getInt("seat");
			String mem_id = rs.getString("mem_id");
			RoomInfoVO rvo = new RoomInfoVO(seat_num, seat, mem_id);
			roomarray.add(rvo);
		}
		return roomarray;
	}
	
	public boolean update(String seat_num, String mem_id, String string) throws SQLException {
		String sql = null;
		if(string.equals("s")) {
			sql = "update Room set seat = 1, mem_id = ? where seat_num= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, seat_num);
		} else {
			sql = "update Room set seat = 0, mem_id = null where mem_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
		}
		try {

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("update exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
