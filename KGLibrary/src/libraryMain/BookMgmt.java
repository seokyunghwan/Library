package libraryMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bookInfo.BookInfoDAO;
import bookInfo.BookInfoVO;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class BookMgmt extends JPanel {
	private JTextField txtBookTitle;
	private JTextField txtAuthor;
	private JTable table;
	private JTextField txtMemId;
	DefaultTableModel model;
	ArrayList<BookInfoVO> bvo;
	LibraryMain mainPanel;

	/**
	 * Create the panel.
	 * 
	 * @param mainPanel
	 */
	public BookMgmt(LibraryMain mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(null);

		JLabel lblNewLabel = new JLabel("\uB3C4\uC11C \uAD00\uB9AC");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 41));
		lblNewLabel.setBounds(344, 28, 201, 70);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uB3C4\uC11C\uBA85");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 17));
		lblNewLabel_1.setBounds(228, 111, 62, 18);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uC800\uC790");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.BOLD, 17));
		lblNewLabel_2.setBounds(228, 166, 62, 18);
		add(lblNewLabel_2);
		 
		JLabel lblid = new JLabel("\uD68C\uC6D0ID");
	      lblid.setFont(new Font("±¼¸²", Font.BOLD, 17));
	      lblid.setBounds(228, 215, 62, 18);
	      add(lblid);

		txtBookTitle = new JTextField();
		txtBookTitle.setBounds(354, 109, 191, 24);
		add(txtBookTitle);
		txtBookTitle.setColumns(10);

		txtAuthor = new JTextField();
		txtAuthor.setBounds(354, 164, 191, 24);
		add(txtAuthor);
		txtAuthor.setColumns(10);
		
		 txtMemId = new JTextField();
	      txtMemId.setColumns(10);
	      txtMemId.setBounds(354, 214, 191, 24);
	      add(txtMemId);

		JButton bookSearchB = new JButton("\uAC80\uC0C9");
		bookSearchB.setForeground(SystemColor.menuText);
		bookSearchB.setBackground(UIManager.getColor("Button.light"));
		bookSearchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bookSearchB) {
					BookInfoDAO bi;
					model.setNumRows(0);
					try {
						bi = new BookInfoDAO();
						  if (txtMemId.getText().isEmpty()) {
			                     bvo = bi.getAllInfo(txtBookTitle.getText(), txtAuthor.getText());
			                  } else
			                     bvo = bi.getAllInfo(txtMemId.getText());
						for (int i = 0; i < bvo.size(); i++) {
							String[] p = new String[5];
							p[0] = bvo.get(i).getBook_title();
							p[1] = bvo.get(i).getAuthor();
							p[2] = bvo.get(i).getCompany();
							p[3] = Integer.toString(bvo.get(i).getInventory());
							p[4] = bvo.get(i).getPos();
							model.addRow(p);
						}
					} catch (ClassNotFoundException | SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		bookSearchB.setFont(new Font("±¼¸²", Font.BOLD, 17));
		bookSearchB.setBounds(481, 251, 115, 36);
		add(bookSearchB);

		JButton bookInsert = new JButton("\uC0C8 \uCC45 \uB4F1\uB85D");
		bookInsert.setBackground(UIManager.getColor("Button.light"));
		bookInsert.setForeground(SystemColor.menuText);
		bookInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == bookInsert) {
					mainPanel.change(new NewBook(mainPanel));
				}
			}
		});
		bookInsert.setFont(new Font("±¼¸²", Font.BOLD, 17));
		bookInsert.setBounds(625, 251, 160, 36);
		add(bookInsert);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 322, 692, 160);
		add(scrollPane);

		String[] headings = new String[] { "Á¦¸ñ", "ÀúÀÚ", "ÃâÆÇ»ç", "Àç°í", "À§Ä¡" };
		model = new DefaultTableModel(headings, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		table = new JTable(model);

		scrollPane.setViewportView(table);

		JButton btnNewButton_3 = new JButton("\uC0AD \uC81C");
		btnNewButton_3.setForeground(SystemColor.menuText);
		btnNewButton_3.setBackground(UIManager.getColor("Button.light"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookInfoDAO bi2 = null;
				try {
					bi2 = new BookInfoDAO();
					bi2.delete(bvo.get(table.getSelectedRow()).getBook_num());
					int rowIndex2 = table.getSelectedRow();
					if (rowIndex2 == -1)
						return;
					model.removeRow(rowIndex2);

					bvo.remove(rowIndex2);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("±¼¸²", Font.BOLD, 17));
		btnNewButton_3.setBounds(546, 511, 115, 47);
		add(btnNewButton_3);

		JButton button = new JButton("\uB300 \uC5EC");
		button.setForeground(SystemColor.textText);
		button.setBackground(UIManager.getColor("Button.light"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookInfoDAO bi3;
				try {
					bi3 = new BookInfoDAO();
					String input = JOptionPane.showInputDialog("¾ÆÀÌµð¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
					bi3.update2(bvo.get(table.getSelectedRow()).getBook_num(), input);

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("±¼¸²", Font.BOLD, 17));
		button.setBounds(186, 511, 115, 47);
		add(button);
		
		JButton btnNewButton = new JButton("\uB4A4\uB85C");
		btnNewButton.setForeground(SystemColor.textText);
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.change(new AdminMain(mainPanel));
			}
		});
		btnNewButton.setBounds(50, 62, 97, 23);
		add(btnNewButton);

		JButton returnB = new JButton("\uBC18 \uB0A9");
	      returnB.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            BookInfoDAO bi4;
	            try {
	            bi4 = new BookInfoDAO();
	            bi4.update3(bvo.get(table.getSelectedRow()).getMem_id());
	         } catch (ClassNotFoundException | SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         }
	      });
	      returnB.setFont(new Font("±¼¸²", Font.BOLD, 17));
	      returnB.setBounds(380, 511, 115, 47);
	      add(returnB);
	}
}