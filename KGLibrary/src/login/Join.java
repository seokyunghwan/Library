package login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import libraryMain.MainPage;
import memberInfo.MemberInfoDAO;
import javax.swing.UIManager;

public class Join extends JPanel {
	private JTextField txtId;
	private JPasswordField txtPw;
	private JPasswordField txtPw_1;
	private JTextField txtName;
	private JTextField txtTel;
	
	public Join(JPanel panel_1) throws ClassNotFoundException, SQLException {
		setLayout(null);
		MemberInfoDAO memInfo = new MemberInfoDAO();
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel.setBounds(187, 38, 77, 21);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(102, 91, 57, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PW");
		lblNewLabel_2.setBounds(102, 176, 57, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PW\uD655\uC778");
		lblNewLabel_3.setBounds(102, 269, 57, 15);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC774\uB984");
		lblNewLabel_4.setBounds(102, 345, 57, 15);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uC5F0\uB77D\uCC98");
		lblNewLabel_5.setBounds(102, 427, 57, 15);
		add(lblNewLabel_5);
		
		txtId = new JTextField();
		txtId.setText("\uC0AC\uC6A9\uD560 ID");
		txtId.setBounds(226, 88, 116, 21);
		add(txtId);
		txtId.setColumns(10);
		
		txtPw = new JPasswordField();
		txtPw.setEchoChar('*');
		txtPw.setBounds(226, 173, 116, 21);
		add(txtPw);
		txtPw.setColumns(10);
		
		
		txtPw_1 = new JPasswordField();
		txtPw_1.setEchoChar('*');
		txtPw_1.setBounds(226, 266, 116, 21);
		add(txtPw_1);
		txtPw_1.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("\uC774\uB984");
		txtName.setBounds(226, 342, 116, 21);
		add(txtName);
		txtName.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setText("010-0000-0000");
		txtTel.setBounds(226, 424, 116, 21);
		add(txtTel);
		txtTel.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					if(txtPw.getText().equals(txtPw_1.getText())) {
					String id = txtId.getText();
					String pw = txtPw.getText();
					String name = txtName.getText();
					String num = txtTel.getText();
					JOptionPane.showMessageDialog(null, "회원가입 완료");
					
					memInfo.new_mem(id, pw, name, num);
					
					MainPage.lw.getContentPane().setVisible(false);		
					MainPage.lw.setContentPane(panel_1);						
					}					
					else {
						JOptionPane.showMessageDialog(null, "비밀번호 확인");
					}
//					new LoginWindow().setVisible(true);
					
//					LibraryMain.lw.getContentPane().setVisible(false);
//					LibraryMain.lp.setVisible(true);
//					LibraryMain.lw.setContentPane(panel_1);
					
					
					
//					lp.panel_1.setVisible(true);
//					LibraryMain.showLogin().setVisible(true);
					
				}
			}
		});
		btnNewButton.setBounds(178, 515, 97, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uC911\uBCF5\uD655\uC778");
		btnNewButton_1.setBackground(UIManager.getColor("Button.light"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_1) {
				String ch = txtId.getText();
				memInfo.id_check(ch);
				}
				
			}
		});
		btnNewButton_1.setBounds(378, 87, 86, 23);
		add(btnNewButton_1);

	}
}
