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
import javax.swing.JTextField;
import javax.swing.UIManager;

import bookInfo.BookInfoDAO;

public class NewBook extends JPanel {
	private JTextField txtTitle;
	private JTextField txtBookNum;
	private JTextField txtAuthor;
	private JTextField txtCompany;
	private JTextField txtInventory;
	private JTextField txtPos;
	LibraryMain mainPanel;
	/**
	 * Create the panel.
	 * @param mainPanel 
	 */
	public NewBook(LibraryMain mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC0C8 \uCC45 \uB4F1\uB85D");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 41));
		lblNewLabel.setBounds(331, 40, 204, 60);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC81C     \uBAA9");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 17));
		lblNewLabel_1.setBounds(260, 124, 76, 18);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uACE0\uC720\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.BOLD, 17));
		lblNewLabel_2.setBounds(260, 179, 76, 18);
		add(lblNewLabel_2);
		
		JLabel label = new JLabel("\uC800     \uC790");
		label.setFont(new Font("±¼¸²", Font.BOLD, 17));
		label.setBounds(260, 234, 76, 18);
		add(label);
		
		JLabel label_1 = new JLabel("\uCD9C \uD310 \uC0AC");
		label_1.setFont(new Font("±¼¸²", Font.BOLD, 17));
		label_1.setBounds(260, 294, 76, 18);
		add(label_1);
		
		JLabel label_2 = new JLabel("\uC7AC    \uACE0");
		label_2.setFont(new Font("±¼¸²", Font.BOLD, 17));
		label_2.setBounds(260, 354, 76, 18);
		add(label_2);
		
		JLabel label_3 = new JLabel("\uC704    \uCE58");
		label_3.setFont(new Font("±¼¸²", Font.BOLD, 17));
		label_3.setBounds(260, 410, 76, 18);
		add(label_3);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(409, 121, 157, 24);
		add(txtTitle);
		txtTitle.setColumns(10);
		
		txtBookNum = new JTextField();
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(409, 176, 157, 24);
		add(txtBookNum);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(409, 231, 157, 24);
		add(txtAuthor);
		
		txtCompany = new JTextField();
		txtCompany.setColumns(10);
		txtCompany.setBounds(409, 291, 157, 24);
		add(txtCompany);
		
		txtInventory = new JTextField();
		txtInventory.setColumns(10);
		txtInventory.setBounds(409, 351, 157, 24);
		add(txtInventory);
		
		txtPos = new JTextField();
		txtPos.setColumns(10);
		txtPos.setBounds(409, 407, 157, 24);
		add(txtPos);
		
		JButton insert = new JButton("\uB4F1 \uB85D");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == insert) {
					try {
						BookInfoDAO bDao = new BookInfoDAO();
						try {
							bDao.book_insert(txtBookNum.getText(), txtTitle.getText(), txtAuthor.getText(), txtCompany.getText(), Integer.parseInt(txtInventory.getText()), txtPos.getText());
							JOptionPane.showMessageDialog(null, "µî·Ï ¿Ï·á");
							mainPanel.change(new BookMgmt(mainPanel));
						} catch (NumberFormatException e1) {
							
							e1.printStackTrace();
						}
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		insert.setForeground(Color.BLACK);
		insert.setBackground(UIManager.getColor("Button.light"));
		insert.setFont(new Font("±¼¸²", Font.BOLD, 19));
		insert.setBounds(277, 476, 127, 44);
		add(insert);
		
		JButton btnNewButton = new JButton("\uCDE8 \uC18C");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 19));
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.change(new BookMgmt(mainPanel));
			}
		});
		btnNewButton.setBounds(439, 476, 127, 44);
		add(btnNewButton);

	}
}
