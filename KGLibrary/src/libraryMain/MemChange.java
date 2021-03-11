package libraryMain;

import java.awt.Color;
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

import memberInfo.MemberInfoDAO;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MemChange extends JPanel {
	private JPasswordField txtPW;
	private JPasswordField txtPWC;
	private JTextField txtName;
	private JTextField txtTel;

	LibraryMain mainPanel;

	/**
	 * Create the panel.
	 * 
	 * @param mainPanel
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MemChange(LibraryMain mainPanel) throws ClassNotFoundException, SQLException {
		String a = LibraryMain.loginVO.getMem_pw();
		String b = LibraryMain.loginVO.getMem_name();
		String c = LibraryMain.loginVO.getMem_tel();
		String d = LibraryMain.loginVO.getMem_id();
		this.mainPanel = mainPanel;
		MemberInfoDAO memdao = new MemberInfoDAO();
		setLayout(null);

		JLabel lblNewLabel = new JLabel("회원정보 변경");
		lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 30));
		lblNewLabel.setBounds(328, 49, 222, 59);
		add(lblNewLabel);

		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 13));
		lblPw.setBounds(331, 158, 57, 15);
		add(lblPw);

		JLabel lblPw_1 = new JLabel("PW 확인");
		lblPw_1.setBounds(331, 183, 57, 15);
		add(lblPw_1);

		JLabel label_2 = new JLabel("이름");
		label_2.setBounds(331, 208, 57, 15);
		add(label_2);

		JLabel label_3 = new JLabel("연락처");
		label_3.setBounds(331, 233, 57, 15);
		add(label_3);

		txtPW = new JPasswordField();
		txtPW.setBounds(400, 158, 116, 21);
		add(txtPW);

		txtPW.setColumns(10);

		txtPWC = new JPasswordField();
		txtPWC.setColumns(10);
		txtPWC.setBounds(400, 180, 116, 21);
		add(txtPWC);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(400, 205, 116, 21);
		txtName.setText(b);
		add(txtName);

		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(400, 230, 116, 21);
		txtTel.setText(c);
		add(txtTel);

		JButton btnNewButton = new JButton("수정 완료");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtPW.getText().equals(txtPWC.getText()) && !txtPW.getText().isEmpty()) {
					memdao.set_mem(txtPW.getText(), txtName.getText(), txtTel.getText(), d);
					JOptionPane.showMessageDialog(null, "회원정보 변경 완료");
					try {
						mainPanel.change(new MyPage(mainPanel));
					} catch (ClassNotFoundException | SQLException e) {
						
						e.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요");
			}
		});
		btnNewButton.setForeground(SystemColor.menuText);
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 13));
		btnNewButton.setBounds(322, 286, 97, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uCDE8\uC18C");
		btnNewButton_1.setForeground(SystemColor.menuText);
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton_1.setBackground(UIManager.getColor("Button.light"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.change(new MyPage(mainPanel));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(453, 286, 97, 23);
		add(btnNewButton_1);

	}

}