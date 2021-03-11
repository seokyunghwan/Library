package libraryMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class AdminMain extends JPanel {

	/**
	 * Create the panel.
	 * @param mainPanel 
	 */
	private LibraryMain mainPanel;
	public AdminMain(LibraryMain mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("KG\uB3C4\uC11C\uAD00 (\uAD00\uB9AC\uC790\uC6A9)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 17));
		lblNewLabel.setBounds(315, 43, 197, 52);
		add(lblNewLabel);
		
		JButton memberMgmt = new JButton("\uD68C\uC6D0\uAD00\uB9AC");
		memberMgmt.setBackground(UIManager.getColor("Button.light"));
		memberMgmt.setFont(new Font("±¼¸²", Font.BOLD, 12));
		memberMgmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == memberMgmt) {
					try {
						mainPanel.change(new MemMgmt(mainPanel));
					} catch (ClassNotFoundException | SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		memberMgmt.setBounds(174, 400, 167, 58);
		add(memberMgmt);
		
		JButton bookMgmt = new JButton("\uB3C4\uC11C\uAD00\uB9AC");
		bookMgmt.setFont(new Font("±¼¸²", Font.BOLD, 12));
		bookMgmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == bookMgmt) {
					mainPanel.change(new BookMgmt(mainPanel));
				}
			}
		});
		bookMgmt.setBounds(504, 400, 159, 58);
		add(bookMgmt);

	}

}
