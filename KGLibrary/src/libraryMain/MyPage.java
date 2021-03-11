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
import javax.swing.JTable;
import javax.swing.UIManager;

import bookInfo.BookInfoDAO;
import bookInfo.BookInfoVO;
import memberInfo.MemberInfoDAO;
import roomInfo.RoomInfoDAO;
import roomInfo.RoomInfoVO;

public class MyPage extends JPanel {
	private JTable table;
	LibraryMain mainPanel;
	int count;

	/**
	 * Create the panel.
	 * 
	 * @param mainPanel
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MyPage(LibraryMain mainPanel) throws ClassNotFoundException, SQLException {
		this.mainPanel = mainPanel;
		BookInfoDAO bookdao = new BookInfoDAO();
		MemberInfoDAO memdao = new MemberInfoDAO();
		ArrayList<BookInfoVO> bookvo;
		RoomInfoDAO roomdao = new RoomInfoDAO();
		ArrayList<RoomInfoVO> roomvo;
		setLayout(null);

		JLabel lblNewLabel = new JLabel("  My Page");
		lblNewLabel.setFont(new Font("³ª´®°íµñ ExtraBold", Font.BOLD, 30));
		lblNewLabel.setBounds(400, 69, 156, 45);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC5F4\uB78C\uC2E4 \uC88C\uC11D\uBC88\uD638 :");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblNewLabel_1.setBounds(364, 232, 128, 37);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uB300\uC5EC\uC911\uC778 \uB3C4\uC11C  :");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblNewLabel_2.setBounds(346, 301, 128, 27);
		add(lblNewLabel_2);

		JButton memChangeB = new JButton("È¸¿øÁ¤º¸ º¯°æ");
		memChangeB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {  MemberInfoDAO mi = new MemberInfoDAO();
	    LibraryMain.loginVO = mi.getAllInfo(LibraryMain.loginVO.getMem_id(), "").get(0);
		mainPanel.change(new MemChange(mainPanel));
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});

		memChangeB.setBackground(UIManager.getColor("Button.light"));
		memChangeB.setForeground(Color.BLACK);
		memChangeB.setFont(new Font("±¼¸²", Font.BOLD, 14));
		memChangeB.setBounds(389, 389, 154, 21);
		add(memChangeB);

		JLabel lblNewLabel_3 = new JLabel("\uAD8C");
		lblNewLabel_3.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(554, 301, 57, 27);
		add(lblNewLabel_3);

		bookvo = bookdao.getAllInfo("", "");
		int count = 0;
		for (BookInfoVO b : bookvo) {
			if (LibraryMain.loginVO.getMem_id().equals(b.getMem_id())) {
				count++;
			}
		}
		JLabel bookCount = new JLabel(Integer.toString(count));
		bookCount.setForeground(Color.BLUE);
		bookCount.setFont(new Font("±¼¸²", Font.BOLD, 15));
		bookCount.addMouseListener(new MouseAdapter() {
			@Override
			 public void mouseClicked(MouseEvent e) {
	            try {
	               mainPanel.change(new MyBook(mainPanel, LibraryMain.loginVO.getMem_id()));
	            } catch (ClassNotFoundException | SQLException e1) {
	               
	               e1.printStackTrace();

				}
			}
		});
		bookCount.setBounds(514, 301, 40, 27);
		add(bookCount);
		
		JButton btnNewButton = new JButton("\uB4A4\uB85C");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.change(new UserMain(mainPanel));
			}
		});
		btnNewButton.setBounds(65, 69, 97, 23);
		add(btnNewButton);
		
		roomvo = roomdao.getAllInfo();
		String a = null;
		for(RoomInfoVO r : roomvo) {
		if(LibraryMain.loginVO.getMem_id().equals(r.getMem_id())) {
			a = r.getSeat_num();
		}
		}
		JLabel place = new JLabel(a); //ÀÚ¸®
		place.setFont(new Font("±¼¸²", Font.BOLD, 15));
		place.setForeground(Color.BLUE);
		place.setBounds(504, 237, 57, 26);
		add(place);
//      contentPane.add(scrollpane,BorderLayout.CENTER);

	}
}