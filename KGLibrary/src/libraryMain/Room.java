package libraryMain;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import login.LoginWindow;
import roomInfo.RoomInfoDAO;
import roomInfo.RoomInfoVO;
import java.awt.Color;
import javax.swing.UIManager;

import bookInfo.BookInfoVO;

public class Room extends JPanel {
	String selected = null;
	RoomInfoDAO rdao;
	ArrayList<RoomInfoVO> rvo;
	int i = 0;
	Boolean seat = false;
	LibraryMain mainPanel;
	int count = 0;

	/**
	 * Create the panel.
	 * 
	 * @param mainPanel
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Room(LibraryMain mainPanel) throws ClassNotFoundException, SQLException {
		this.mainPanel = mainPanel;
		rdao = new RoomInfoDAO();
		ArrayList<RoomInfoVO> rvo;

		rvo = rdao.getAllInfo();

		setLayout(null);
		setBounds(0, 0, 950, 700);

		JLabel lblNewLabel = new JLabel("\uC5F4\uB78C\uC2E4 \uD604\uD669");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(0, 10, 950, 70);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC804\uCCB4 \uC88C\uC11D \uC218 :");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 19));
		lblNewLabel_1.setBounds(186, 529, 123, 36);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("21\uC11D");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 19));
		lblNewLabel_2.setBounds(332, 538, 85, 18);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\uBE48 \uC88C\uC11D \uC218 :");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 19));
		lblNewLabel_3.setBounds(508, 540, 110, 18);
		add(lblNewLabel_3);
		
		for (RoomInfoVO r  : rvo) {
			if(r.getSeat()==0) 
					count++;
		}
		

		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setText(Integer.toString(count)+"\uC11D");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 19));
		lblNewLabel_4.setBounds(648, 540, 62, 18);
		add(lblNewLabel_4);

		JPanel panel = new JPanel();
		panel.setBounds(49, 141, 838, 341);
		add(panel);
		panel.setLayout(new GridLayout(3, 7, 20, 20));

		/*
		 * if (rvo.get(0) != null) { for (RoomInfoVO r : rvo) { if
		 * (LibraryMain.loginVO.getMem_id().equals(r.getMem_id())) seat = true; } }
		 */

		JToggleButton[] seatB = new JToggleButton[21];
//		JButton bt = new JButton();

//		panel.add(bt);

		ButtonGroup bg = new ButtonGroup();

		for (i = 0; i < seatB.length; i++) {
			int j = i + 1;
			if (rvo.get(i).getSeat() == 0) { // Seat = 0 이면 원래대로 표시
				seatB[i] = new JToggleButton("A" + j);
			} else { // Seat = 1 이면
				seatB[i] = new JToggleButton("A" + j);
				seatB[i].setEnabled(false); // 자리 비 활성화
			}
			JToggleButton b = seatB[i];
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selected = b.getText();
				}
			});
			seatB[i].setFont(new Font("굴림", Font.BOLD, 17));
			bg.add(seatB[i]);

			panel.add(seatB[i]);
		}

		JButton seatSelectB = new JButton("\uC790\uB9AC \uC120\uC810"); // 자리 선점
		seatSelectB.setForeground(Color.BLACK);
		seatSelectB.setFont(new Font("굴림", Font.BOLD, 12));
		seatSelectB.setBackground(UIManager.getColor("Button.light"));

		seatSelectB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow lw = null;
				 if (LibraryMain.loginVO.getMem_id() == null) {
		               JOptionPane.showMessageDialog(null, "로그인 먼저 하세요");
		               try {
		                  lw = new LoginWindow();
		               } catch (ClassNotFoundException | SQLException e1) {

		                  e1.printStackTrace();
		               }
		               lw.setVisible(true);
				} else {
					for (RoomInfoVO r : rvo) {
						if (LibraryMain.loginVO.getMem_id().equals(r.getMem_id())) {
							seat = true;
						}
					}
					if (seat) {
						JOptionPane.showMessageDialog(null, "사용중인 자리가 있습니다");

					} else {
						try {
							rdao.update(selected, LibraryMain.loginVO.getMem_id(), "s");
						} catch (SQLException e2) {
							e2.printStackTrace();
						} // 자리 선점되면 자리 이름(selected), mem_id 넘겨서 자리 선점
						JOptionPane.showMessageDialog(null, selected + " 자리 선점");
						try {
							Room.this.mainPanel.change(new Room(mainPanel));
						} catch (ClassNotFoundException | SQLException e1) {

							e1.printStackTrace();
						}
					}
				}
			}
		});
		seatSelectB.setBounds(673, 53, 91, 45);
		add(seatSelectB);

		JButton seatCancelB = new JButton("\uC790\uB9AC \uCCA0\uD68C"); // 자리 철회 버튼
		seatCancelB.setForeground(Color.BLACK);
		seatCancelB.setFont(new Font("굴림", Font.BOLD, 12));
		seatCancelB.setBackground(UIManager.getColor("Button.light"));
		seatCancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if(LibraryMain.loginVO.getMem_id() != null) {
					rdao.update(selected, LibraryMain.loginVO.getMem_id(), "c");
					JOptionPane.showMessageDialog(null, "자리 철회 완료");
					Room.this.mainPanel.change(new Room(mainPanel));
					}

				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});
		seatCancelB.setBounds(796, 53, 91, 45);
		add(seatCancelB);
		
		
		
		
		JButton button = new JButton("\uB4A4\uB85C");	//뒤로 버튼
		button.setForeground(Color.BLACK);
		button.setFont(new Font("굴림", Font.BOLD, 12));
		button.setBackground(UIManager.getColor("Button.light"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LibraryMain.loginVO.getMem_id() != null) {
					mainPanel.change(new UserMain(mainPanel));
				}
				else
					mainPanel.change(new MainPage(mainPanel));
			}
		});
		button.setBounds(34, 29, 97, 23);
		add(button);

	}
}
