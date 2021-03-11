package bookInfo;

import java.sql.Date;

public class BookInfoVO {
	private String book_num;
	private String book_title;
	private String author;
	private String company;
	private int inventory;
	private String pos;
	private Date L_Date;
	private Date R_Date;
	private String mem_id;
	
	public BookInfoVO(String book_num, String book_title,
			String author, String company,
			int inventory, String pos, Date L_Date, Date R_Date, String mem_id) {
		this.book_num = book_num;
		this.book_title = book_title;
		this.author = author;
		this.company = company;
		this.inventory = inventory;
		this.pos = pos;
		this.L_Date = L_Date;
		this.R_Date = R_Date;
		this.mem_id = mem_id;
	}
	
	public BookInfoVO(){}

	public String getBook_num() {
		return book_num;
	}

	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Date getL_Date() {
		return L_Date;
	}

	public void setL_Date(Date l_Date) {
		L_Date = l_Date;
	}

	public Date getR_Date() {
		return R_Date;
	}

	public void setR_Date(Date r_Date) {
		R_Date = r_Date;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
}
