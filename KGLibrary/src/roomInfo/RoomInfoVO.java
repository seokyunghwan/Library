package roomInfo;

public class RoomInfoVO {
	private String seat_num;
	private int seat;
	private String mem_id;
	
	public RoomInfoVO(String seat_num, int seat, String mem_id) {
		this.seat_num=seat_num;
		this.seat=seat;
		this.mem_id=mem_id;
	}
	
	public RoomInfoVO() {}

	public String getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(String seat_num) {
		this.seat_num = seat_num;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
