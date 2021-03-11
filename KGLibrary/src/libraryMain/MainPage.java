package libraryMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import login.LoginWindow;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MainPage extends JPanel {
	
	/**
	 * Create the panel.
	 */
	static JButton loginB;
	public static LoginWindow lw;
	private static LibraryMain mainPanel;
	
	public MainPage(LibraryMain mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(null);
		
		setBounds(0, 0, 950, 700);
		JLabel mainLabel = new JLabel("KG\uB3C4\uC11C\uAD00");
		mainLabel.setForeground(new Color(0, 0, 0));
		mainLabel.setBounds(0, 72, 950, 35);
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(mainLabel.getFont().deriveFont(mainLabel.getFont().getStyle() | Font.BOLD, 30f));
		add(mainLabel);

		
		//도서 검색 버튼
		JButton bookSearchB = new JButton("\uB3C4\uC11C \uAC80\uC0C9");
		bookSearchB.setForeground(Color.BLACK);
		bookSearchB.setBackground(UIManager.getColor("Button.light"));
		
		bookSearchB.setFont(new Font("굴림", Font.BOLD, 17));
		bookSearchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==bookSearchB) {
					
						try {
							mainPanel.change(new BookSearch(mainPanel));
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
			}
		});
		bookSearchB.setBounds(210, 258, 146, 50);
		add(bookSearchB);
		
		//열람실 현황 버튼
		JButton readingRoomB = new JButton("\uC5F4\uB78C\uC2E4 \uD604\uD669");
		readingRoomB.setForeground(Color.BLACK);
		readingRoomB.setBackground(UIManager.getColor("Button.light"));
		readingRoomB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.change(new Room(mainPanel));
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		readingRoomB.setFont(new Font("굴림", Font.BOLD, 17));
		readingRoomB.setBounds(570, 258, 146, 50);
		add(readingRoomB);
		
		
		//로그인 window 띄움
		
		loginB = new JButton("\uB85C\uADF8\uC778");
		loginB.setForeground(Color.BLACK);
		loginB.setBackground(UIManager.getColor("Button.light"));
//		loginB = new JButton();

		loginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==loginB) {
					try {
						lw = new LoginWindow();
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					lw.setVisible(true);
					
				}
			}

		});
		loginB.setFont(new Font("굴림", Font.BOLD, 17));
		loginB.setBounds(390, 395, 146, 50);
		add(loginB);
		
		
	}
	public static void loginOK(Boolean b){
		if(b) {
			mainPanel.change(new AdminMain(mainPanel));
		} else {
			mainPanel.change(new UserMain(mainPanel));
//			LibraryMain.frame.getCard().show(LibraryMain.frame.getMainPanel(), "um");
		}
	}
}
