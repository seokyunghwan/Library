package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import libraryMain.LibraryMain;
import libraryMain.MainPage;
import memberInfo.MemberInfoDAO;
import javax.swing.UIManager;

public class LoginWindow extends JFrame {
   static JPanel panel_1;

  
   private JTextField txtId;
   private JPasswordField txtPw;
   public LoginWindow() throws ClassNotFoundException, SQLException {
     MemberInfoDAO memInfo = new MemberInfoDAO();
      getContentPane().setLayout(null);
      setBounds(50, 50, 500, 600);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      panel_1 = new JPanel();
      panel_1.setBounds(0, 26, 484, 498);
      getContentPane().add(panel_1);
      panel_1.setLayout(null);
      
      
      txtId = new JTextField();
      txtId.setBounds(145, 97, 203, 27);
      txtId.setText("aa");
      panel_1.add(txtId);
      txtId.setColumns(10);
      
      txtPw = new JPasswordField();
      txtPw.setBounds(145, 155, 203, 27);
      txtPw.setText("1234");
      panel_1.add(txtPw);
      txtPw.setColumns(10);
      
      JLabel lblNewLabel = new JLabel("ID");
      lblNewLabel.setBounds(95, 103, 38, 15);
      panel_1.add(lblNewLabel);
      
      JLabel lblPw = new JLabel("PW");
      lblPw.setBounds(95, 161, 38, 15);
      panel_1.add(lblPw);
      
      JCheckBox adminCB = new JCheckBox("admin");
      adminCB.setSelected(true);
      adminCB.setBounds(305, 200, 115, 23);
      panel_1.add(adminCB);
      
      //로그인 버튼
		JButton lwLoginB = new JButton("\uB85C\uADF8\uC778");
		lwLoginB.setBackground(UIManager.getColor("Button.light"));
		lwLoginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == lwLoginB) {
					String id2 = txtId.getText();
					String pw2 = txtPw.getText();
					
					if (memInfo.mem_Login(id2, pw2)) {
						try {
							LibraryMain.loginVO = memInfo.getAllInfo(id2, "").get(0);
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if (adminCB.isSelected()) {//admin 클릭되어 있으면
							if(LibraryMain.loginVO.getAdmin()==1) {
							JOptionPane.showMessageDialog(null, "로그인완료");	
						 	MainPage.loginOK(adminCB.isSelected());
							dispose();
								}							
							else
								JOptionPane.showMessageDialog(null, "관리자 권한이 없습니다.");
							} //admin check - end
						
						
						else if (!adminCB.isSelected()) {	//admin 클릭 안되어있으면	
						JOptionPane.showMessageDialog(null, "로그인완료");
						MainPage.loginOK(adminCB.isSelected());
						dispose();
						}	//admin check(x) - end
					} else {
						try {
							MainPage.lw = new LoginWindow();
						} catch (ClassNotFoundException | SQLException e1) {

							e1.printStackTrace();
						}
					}
               
//               getContentPane().setVisible(false);

               

            
               /*
                * 로그인 구현
                * 
                */
               
            }
         }}
      );
      lwLoginB.setBounds(107, 245, 97, 23);
      panel_1.add(lwLoginB);
      
   
      //회원가입 panel로 이동 버튼
      JButton joinB = new JButton("\uD68C\uC6D0\uAC00\uC785");
      joinB.setBackground(UIManager.getColor("Button.light"));
      joinB.setBounds(251, 245, 97, 23);
      panel_1.add(joinB);
      joinB.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
//            setVisible(false);
            panel_1.updateUI();
            
            try {
               setContentPane(new Join(panel_1));
            } catch (ClassNotFoundException e1) {
               
               e1.printStackTrace();
            } catch (SQLException e1) {
               
               e1.printStackTrace();
            }
            
            
         }
      });
   }

}