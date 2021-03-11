package memberInfo;

import java.util.Date;

public class MemberInfoVO {
   private String mem_id;
   private String mem_name;
   private String mem_pw;
   private String mem_tel;
   private Date L_date;
   private Date R_date;
   private int admin;
   
   public MemberInfoVO() {   //생성자 디폴트
      
   }

   public MemberInfoVO(String mem_id, String mem_name, String mem_pw, String mem_tel, Date l_date, Date r_date,
         int admin) {
      super();
      this.mem_id = mem_id;
      this.mem_name = mem_name;
      this.mem_pw = mem_pw;
      this.mem_tel = mem_tel;
      L_date = l_date;
      R_date = r_date;
      this.admin = admin;
   }

   public String getMem_id() {
      return mem_id;
   }

   public void setMem_id(String mem_id) {
      this.mem_id = mem_id;
   }

   public String getMem_name() {
      return mem_name;
   }

   public void setMem_name(String mem_name) {
      this.mem_name = mem_name;
   }

   public String getMem_pw() {
      return mem_pw;
   }

   public void setMem_pw(String mem_pw) {
      this.mem_pw = mem_pw;
   }

   public String getMem_tel() {
      return mem_tel;
   }

   public void setMem_tel(String mem_tel) {
      this.mem_tel = mem_tel;
   }

   public Date getL_date() {
      return L_date;
   }

   public void setL_date(Date l_date) {
      L_date = l_date;
   }

   public Date getR_date() {
      return R_date;
   }

   public void setR_date(Date r_date) {
      R_date = r_date;
   }

   public int getAdmin() {
      return admin;
   }

   public void setAdmin(int admin) {
      this.admin = admin;
   }

   
   
   
   
}