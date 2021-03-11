package libraryMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bookInfo.BookInfoDAO;
import bookInfo.BookInfoVO;
import memberInfo.MemberInfoDAO;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class BookSearch extends JPanel {
	private JTextField txtBookTitle;
	private JTextField txtAuthor;
	DefaultTableModel model;
	ArrayList<BookInfoVO> bvo;
	private JTable table;
	private LibraryMain mainPanel;
	
	/**
	 * Create the panel.
	 * @param mainPanel 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public BookSearch(LibraryMain mainPanel) throws ClassNotFoundException, SQLException {
		MemberInfoDAO memInfo = new MemberInfoDAO();
		this.mainPanel = mainPanel;
		setLayout(null);
		setVisible(true);
		JLabel lblNewLabel = new JLabel("\uB3C4\uC11C \uAC80\uC0C9");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(327, 34, 159, 48);
		add(lblNewLabel);

		txtBookTitle = new JTextField();
		txtBookTitle.setBounds(360, 92, 159, 33);
		add(txtBookTitle);
		txtBookTitle.setColumns(10);

		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(360, 154, 159, 33);
		add(txtAuthor);

		JLabel lblNewLabel_1 = new JLabel("\uB3C4\uC11C\uBA85");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(229, 92, 81, 33);
		add(lblNewLabel_1);

		JLabel label = new JLabel("\uC800\uC790");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(229, 154, 81, 33);
		add(label);

		JButton bookSearchB = new JButton("\uAC80\uC0C9");
		bookSearchB.setForeground(SystemColor.menuText);
		bookSearchB.setFont(new Font("굴림", Font.BOLD, 12));
		bookSearchB.setBackground(UIManager.getColor("Button.light"));
		bookSearchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bookSearchB) {
					BookInfoDAO bi;
					try {
						model.setNumRows(0);
						bi = new BookInfoDAO();
						bvo = bi.getAllInfo(txtBookTitle.getText(), txtAuthor.getText());
						for(int i=0 ; i < bvo.size() ; i++) {
							String [] p = new String[5];
							p[0] = bvo.get(i).getBook_title();
							p[1] = bvo.get(i).getAuthor();
							p[2] = bvo.get(i).getCompany();
							p[3] = Integer.toString(bvo.get(i).getInventory());
							p[4] = bvo.get(i).getPos();
							model.addRow(p);
						}
//						System.out.println(bvo.get(0).getBook_title());

					} catch (ClassNotFoundException | SQLException e1) {

						e1.printStackTrace();
					}

				}
			}
		});
		bookSearchB.setBounds(562, 219, 131, 40);
		add(bookSearchB);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(82, 402, 637, 174);
		add(scrollPane_1);
		
		String[] headings = {"책제목", "저자", "출판사", "재고량", "위치"};
		model = new DefaultTableModel(headings, 0){
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
		scrollPane_1.setViewportView(table);
		
		JButton btnNewButton = new JButton("\uB4A4\uB85C");
		btnNewButton.setForeground(SystemColor.menuText);
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LibraryMain.loginVO.getMem_id() != null) {
					mainPanel.change(new UserMain(mainPanel));
				}
				else
					mainPanel.change(new MainPage(mainPanel));
				
			}
		});
		btnNewButton.setBounds(81, 34, 69, 48);
		add(btnNewButton);


	}
}
