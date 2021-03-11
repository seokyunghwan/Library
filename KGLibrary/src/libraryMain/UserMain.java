package libraryMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class UserMain extends JPanel {

	private LibraryMain mainPanel;

	/**
	 * Create the panel.
	 * 
	 * @param mainPanel
	 */
	public UserMain(LibraryMain mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(null);

		JLabel lblNewLabel = new JLabel("KG\uB3C4\uC11C\uAD00");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(326, 64, 275, 51);
		add(lblNewLabel);

		JButton bookSearchB = new JButton("\uB3C4\uC11C \uAC80\uC0C9");
		bookSearchB.setForeground(Color.BLACK);
		bookSearchB.setFont(new Font("±¼¸²", Font.BOLD, 12));
		bookSearchB.setBackground(UIManager.getColor("Button.light"));
		bookSearchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.change(new BookSearch(mainPanel));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bookSearchB.setBounds(197, 406, 110, 51);
		add(bookSearchB);

		JButton room = new JButton("\uC5F4\uB78C\uC2E4 \uD604\uD669"); // ¿­¶÷½Ç ÇöÈ²
		room.setForeground(Color.BLACK);
		room.setFont(new Font("±¼¸²", Font.BOLD, 12));
		room.setBackground(UIManager.getColor("Button.light"));
		room.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.change(new Room(mainPanel));
				} catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		room.setBounds(414, 406, 110, 51);
		add(room);

		JButton myPageB = new JButton("My Page");
		myPageB.setForeground(Color.BLACK);
		myPageB.setFont(new Font("±¼¸²", Font.BOLD, 12));
		myPageB.setBackground(UIManager.getColor("Button.light"));
		myPageB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainPanel.change(new MyPage(mainPanel));
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});

		myPageB.setBounds(629, 406, 110, 51);
		add(myPageB);

	}

}
