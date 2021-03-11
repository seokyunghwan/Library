package libraryMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import bookInfo.BookInfoDAO;
import bookInfo.BookInfoVO;
import memberInfo.MemberInfoDAO;
import memberInfo.MemberInfoVO;

public class MemMgmt extends JPanel {
	private JTextField txtId;
	private JTextField txtName;
	DefaultTableModel model;
	ArrayList<MemberInfoVO> mvo = null;
	ArrayList<BookInfoVO> bvo;
	LibraryMain mainPanel;
	/**
	 * Create the panel.
	 * @param mainPanel 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public MemMgmt(LibraryMain mainPanel) throws ClassNotFoundException, SQLException {
		this.mainPanel = mainPanel;
		MemberInfoDAO mi = new MemberInfoDAO();
		int a = 0;
		String[] headings = {"¿Ã∏ß","ø¨∂Ù√≥","µµº≠ ¥Îø©","¥Îø©¿œ","π›≥≥¿œ"};
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0 \uAD00\uB9AC");
		lblNewLabel.setFont(new Font("±º∏≤", Font.BOLD, 41));
		lblNewLabel.setBounds(338, 37, 213, 45);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("±º∏≤", Font.BOLD, 19));
		lblNewLabel_1.setBounds(276, 120, 62, 18);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setFont(new Font("±º∏≤", Font.BOLD, 19));
		lblNewLabel_2.setBounds(276, 171, 62, 18);
		add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setBounds(394, 119, 169, 24);
		add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(394, 170, 169, 24);
		add(txtName);
		txtName.setColumns(10);
		
		JButton check = new JButton("\uC870 \uD68C");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
		               BookInfoDAO biDAO = new BookInfoDAO();
		               bvo = biDAO.getAllInfo();

		            } catch (ClassNotFoundException | SQLException e2) {
		               e2.printStackTrace();
		            }

				model.setNumRows(0);
				try {
					mvo = mi.getAllInfo(txtId.getText(), txtName.getText());
//					data = new String[mvo.size()][5];
//					System.out.println(mvo.get(0).getMem_id());
					for(int i=0 ; i < mvo.size() ; i++) {
						int count = 0;
						String [] p = new String[5];
						p[0] = mvo.get(i).getMem_name();
						p[1] = mvo.get(i).getMem_tel();
						for(BookInfoVO b : bvo) {
							if (b.getMem_id() != null && mvo.get(i).getMem_id() != null) {
		                        if (b.getMem_id().equals(mvo.get(i).getMem_id())) {
		                           count++;
		                        }
		                     }
		                  }
		                  p[2] = Integer.toString(count);
		                  model.addRow(p);					
					}
					} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		check.setForeground(SystemColor.menuText);
		check.setBackground(UIManager.getColor("Button.light"));
		check.setFont(new Font("±º∏≤", Font.BOLD, 19));
		check.setBounds(617, 225, 88, 45);
		add(check);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 307, 672, 155);
		add(scrollPane);
		
		
		

		String [][] data = null;
		model = new DefaultTableModel(headings,0) {
			public boolean isCellEditable(int i, int c) {
			    return false;
			} 
		};
		
		JTable table = new JTable(model);
		 table.addMouseListener(new MouseAdapter() {
	         @Override
	         public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2 && table.getSelectedColumn() == 2) {
	               try {
	                  mainPanel.change(new MyBook(mainPanel, mvo.get(table.getSelectedRow()).getMem_id()));
	               } catch (ClassNotFoundException | SQLException e1) {
	                  e1.printStackTrace();
	               }
	            }
	         }
	      });

		scrollPane.setViewportView(table);
		
		JButton deleteButton = new JButton("\uAC15\uC81C \uD0C8\uD1F4");
	      deleteButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 MemberInfoDAO mi2 = null;

	           try {
	        	 mi2 = new MemberInfoDAO();
	        	 mi2.delete_mem(mvo.get(table.getSelectedRow()).getMem_id(),
	                     mvo.get(table.getSelectedRow()).getMem_pw());
	               int rowIndex = table.getSelectedRow();
	             if(rowIndex == -1) return;
	             model.removeRow(rowIndex);
	             
	             mvo.remove(rowIndex);
//	             mi2.delete_mem(mvo.get(table.getSelectedRow()).getMem_id());
//	             model.setNumRows(0);
	             
	             
	             
	         } catch (ClassNotFoundException | SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	         }
	           
	           
//	            System.out.println(mvo.get(table.getSelectedRow()).getMem_id());
	            
//	            System.out.println(table.getSelectedRow());
	            
	         }
	      });
		
		
		deleteButton.setBackground(UIManager.getColor("Button.light"));
		deleteButton.setForeground(Color.BLACK);
		deleteButton.setFont(new Font("±º∏≤", Font.BOLD, 17));
		deleteButton.setBounds(378, 487, 113, 45);
		add(deleteButton);

		JButton adminButton = new JButton("\uAD00\uB9AC\uC790 \uAD8C\uD55C \uBD80\uC5EC");
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == adminButton) {
					mi.update_mem(mvo.get(table.getSelectedRow()).getMem_id(),"", "", "", "admin");
//					System.out.println(mvo.get(table.getSelectedRow()).getMem_id());
					mvo.get(table.getSelectedRow()).getMem_id();
					// admin ≥—∞‹º≠ admin¿Ã∏È sqlπÆ πŸ≤Ÿ±‚
				}
			}
		});
		adminButton.setForeground(SystemColor.menuText);
		adminButton.setFont(new Font("±º∏≤", Font.BOLD, 17));
		adminButton.setBackground(UIManager.getColor("Button.light"));
		adminButton.setBounds(545, 487, 169, 45);
		add(adminButton);
		
		JButton BackB = new JButton("\uB4A4\uB85C");
		BackB.setForeground(SystemColor.menuText);
		BackB.setFont(new Font("±º∏≤", Font.BOLD, 13));
		BackB.setBackground(UIManager.getColor("Button.light"));
		BackB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.change(new AdminMain(mainPanel));
			}
		});
		BackB.setBounds(43, 37, 88, 23);
		add(BackB);
		
		

	}
}
