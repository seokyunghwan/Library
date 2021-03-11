package libraryMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bookInfo.BookInfoDAO;
import bookInfo.BookInfoVO;
import memberInfo.MemberInfoDAO;
import javax.swing.UIManager;

public class MyBook extends JPanel {
   private JTable table;
   DefaultTableModel model = null;
   LibraryMain mainPanel;

   /**
    * Create the panel.
    * 
    * @param mainPanel
    * 
    * @throws SQLException
    * @throws ClassNotFoundException
    */
   public MyBook(LibraryMain mainPanel, String id) throws ClassNotFoundException, SQLException {
      this.mainPanel = mainPanel;
      BookInfoDAO bookdao = new BookInfoDAO();
      MemberInfoDAO midao = new MemberInfoDAO();
      BookInfoVO bivo = new BookInfoVO();
      ArrayList<BookInfoVO> bookvo;
      int a = 0;

      setLayout(null);

      JLabel lblNewLabel = new JLabel("도서 대여 현황");
      lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
      lblNewLabel.setBounds(145, 5, 255, 56);
      add(lblNewLabel);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(81, 109, 319, 85);
      add(scrollPane);

//      String [][] data = null;
      String[] headings = { "제목", "대여일", "반납일" };
      model = new DefaultTableModel(headings, 0) {
         public boolean isCellEditabel(int i, int c) {
            return false;
         }
      };
      try {
         model.setNumRows(0);
         bookvo = bookdao.getAllInfo("", "");
         for (int i = 0; i < bookvo.size(); i++) {
            if (id.equals(bookvo.get(i).getMem_id())) {

               String[] p = new String[3];
               p[0] = bookvo.get(i).getBook_title();
               p[1] = bookvo.get(i).getL_Date().toString();
               p[2] = bookvo.get(i).getR_Date().toString();
               model.addRow(p);
            }
         }
      } catch (SQLException e1) {
         e1.printStackTrace();
      }

      JTable table = new JTable(model);
      scrollPane.setViewportView(table);

      JButton btnNewButton = new JButton("확인");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
            if(LibraryMain.loginVO.getMem_id().equals(id)) {
               mainPanel.change(new MyPage(mainPanel));
            } else {
               mainPanel.change(new MemMgmt(mainPanel));
            }
            } catch(Exception e1) {
               e1.printStackTrace();
            }
         }
      });
      btnNewButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            MemMgmt p;
            try {
               p = new MemMgmt(null);
            } catch (ClassNotFoundException | SQLException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }

         }
      });
      btnNewButton.setBackground(UIManager.getColor("Button.light"));
      btnNewButton.setForeground(Color.BLACK);
      btnNewButton.setFont(new Font("굴림", Font.BOLD, 14));
      btnNewButton.setBounds(210, 237, 74, 34);
      add(btnNewButton);

   }

}