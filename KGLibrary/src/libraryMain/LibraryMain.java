package libraryMain;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import memberInfo.MemberInfoVO;

public class LibraryMain extends JFrame {
	
	public static MemberInfoVO loginVO = new MemberInfoVO();
	public static String loginId = loginVO.getMem_id();
	public static String loginPw = loginVO.getMem_pw();
	public static String loginName = loginVO.getMem_name();
	public static String loginTel = loginVO.getMem_tel();

	/**
	 * Launch the application.
	 */

	public MainPage mp = null;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryMain mainPanel = new LibraryMain();
					mainPanel.setTitle("KGµµ¼­°ü");
					mainPanel.mp = new MainPage(mainPanel);
					mainPanel.add(mainPanel.mp);
				
					mainPanel.setVisible(true);
					mainPanel.setAlwaysOnTop(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public LibraryMain() throws ClassNotFoundException, SQLException {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 950, 700);
	}
	public void change(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		revalidate();
		repaint();
	}
}
