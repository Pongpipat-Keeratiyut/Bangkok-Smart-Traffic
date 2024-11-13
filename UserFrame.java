package V;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.UserDB;
import M.UserManager;
import common.GlobalData;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame
{
	private static final long serialVersionUID = 754896130L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_username;
	private JTextField textField_password;
	private JComboBox<String> comboBox;
	private JTable table;
	ArrayList<UserDB> list;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public UserFrame()
	{
		setTitle("User Management");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 955, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 595, 526);
		contentPane.add(scrollPane);				
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String username = table.getValueAt(index, 1).toString();
				String password = table.getValueAt(index, 2).toString();

				String usertype = table.getValueAt(index, 3).toString();	
				//System.out.println(userTypeObj);
			    textField_id.setText("" + id);
			    textField_username.setText("" + username);
			    textField_password.setText("" + password);
			    comboBox.setSelectedItem(usertype);	
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		
		JLabel lblUserID = new JLabel("id");
		lblUserID.setBounds(628, 25, 49, 14);
		contentPane.add(lblUserID);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.LIGHT_GRAY);
		textField_id.setBounds(687, 25, 180, 20);
		contentPane.add(textField_id);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(628, 63, 49, 14);
		contentPane.add(lblUsername);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(687, 63, 180, 20);
		contentPane.add(textField_username);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(628, 106, 49, 14);
		contentPane.add(lblPassword);
		
		textField_password = new JTextField();
		textField_password.setColumns(10);
		textField_password.setBounds(687, 106, 180, 20);
		contentPane.add(textField_password);
		
		JLabel lblUsertype = new JLabel("usertype");
		lblUsertype.setBounds(628, 151, 49, 14);
		contentPane.add(lblUsertype);
		
		comboBox = new JComboBox<String>();
		comboBox.setEditable(false);
		comboBox.setBounds(687, 147, 180, 22);
		comboBox.addItem("Commander");
		comboBox.addItem("Officer");
		comboBox.setSelectedItem("Officer");
		contentPane.add(comboBox);
		
		JButton btnSaveNew = new JButton("SAVE NEW");
		btnSaveNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GlobalData.CurrentUser_usertype .equals("commander"))
				{
					UserDB x = new UserDB(0, textField_username.getText().trim(), textField_password.getText().trim(),
							comboBox.getSelectedItem().toString().trim()) ;
					UserManager.saveNewUser(x);
					load();
					
					textField_id.setText("");
					textField_username.setText("");
					textField_password.setText("");
					
					JOptionPane.showMessageDialog(UserFrame.this, "finish!!");
				}else {
					JOptionPane.showMessageDialog(UserFrame.this, "You can not add new user.");
				}
			}
		});
		btnSaveNew.setBounds(646, 196, 89, 23);
		contentPane.add(btnSaveNew);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GlobalData.CurrentUser_usertype .equals("commander"))
				{
					UserDB x = new UserDB( Integer.parseInt( textField_id.getText().trim() ), textField_username.getText().trim(), textField_password.getText().trim(),
							comboBox.getSelectedItem().toString().trim()) ;
					
					UserManager.editUser(x);
					load();					
					textField_id.setText("");
					textField_username.setText("");
					textField_password.setText("");

					JOptionPane.showMessageDialog(UserFrame.this, "finish!!");
				}else {
					JOptionPane.showMessageDialog(UserFrame.this, "You can not edit.");
				}		
			}
		});
		btnEdit.setBounds(646, 230, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GlobalData.CurrentUser_usertype .equals("commander"))
				{		
					if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(UserFrame.this,
							"Do you want to delete ?", "DELETE ?", JOptionPane.OK_CANCEL_OPTION))
					{
						UserDB x = new UserDB(Integer.parseInt( textField_id.getText().trim() ), textField_username.getText().trim(), textField_password.getText().trim(),
								comboBox.getSelectedItem().toString().trim()) ;
						UserManager.deleteUser(x);
						load();				
						textField_id.setText("");
						textField_username.setText("");
						textField_password.setText("");
						JOptionPane.showMessageDialog(UserFrame.this, "finish!!");
					}
				}else {
					JOptionPane.showMessageDialog(UserFrame.this, "You can not delete.");
				}
			}
		});
		btnDelete.setBounds(646, 264, 89, 23);
		contentPane.add(btnDelete);		
		load();
	}
	
	public void load()
	{
		list = UserManager.getAllUser();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("usertype");

		for (UserDB u : list)
		{
			model.addRow(new Object[]
			{ u.user_id, u.username, u.password, u.user_type });
		}
		table.setModel(model);
		
	}
}
