package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 754896130L;
	private JPanel contentPane;
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{

					try
					{					
				        UIManager.setLookAndFeel(
				            UIManager.getSystemLookAndFeelClassName());
					} catch (UnsupportedLookAndFeelException e)
					{
						JOptionPane.showMessageDialog(null, "ไม่สามารถใช้งานหน้าตาของระบบได้");
					} catch (ClassNotFoundException e)
					{
						JOptionPane.showMessageDialog(null, "ไม่พบคลาสที่ระบุ");
					} catch (InstantiationException e)
					{
						JOptionPane.showMessageDialog(null, "ไม่สามารถสร้าง Object ได้");
					} catch (IllegalAccessException e)
					{
						JOptionPane.showMessageDialog(null, "การเข้าถึง Object ไม่ถูกต้อง");
					}
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame()
	{
		setTitle("MENU");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame f = new UserFrame();
				f.setVisible(true);
			}
		});
		btnUser.setBounds(219, 40, 89, 23);
		contentPane.add(btnUser);

		JButton btnRoad = new JButton("Road");
		btnRoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RoadFrame f = new RoadFrame();
				f.setVisible(true);
			}
		});
		btnRoad.setBounds(546, 40, 89, 23);
		contentPane.add(btnRoad);

		JButton btnDevice = new JButton("Device");
		btnDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeviceFrame f = new DeviceFrame();
				f.setVisible(true);
			}
		});
		btnDevice.setBounds(219, 95, 89, 23);
		contentPane.add(btnDevice);

		JButton btnData = new JButton("Data");
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataFrame f = new DataFrame();
				f.setVisible(true);
			}
		});
		btnData.setBounds(546, 95, 89, 23);
		contentPane.add(btnData);
		
		JButton btnInform = new JButton("Inform");
		btnInform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformFrame f = new InformFrame();
				f.setVisible(true);
			}
		});
		btnInform.setBounds(219, 154, 89, 23);
		contentPane.add(btnInform);
		
		JLabel lblNewLabel = new JLabel("ข้อมูลผู้ใช้โปรแกรม:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(67, 43, 161, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ข้อมูลที่อยู่ของถนน:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(375, 43, 161, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ข้อมูลอุปกณ์ที่ใช้งาน:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(67, 98, 161, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ข้อมูลต่างๆสำหรับผู้ใช้:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(375, 98, 161, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ข้อมูลจราจรที่ต้องการแจ้ง:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(67, 157, 161, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("ออกจากระบบการใช้งาน:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(375, 157, 161, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        LoginFrame f = new LoginFrame();
		        f.setVisible(true);
			}
		});
		btnLogout.setBounds(546, 154, 89, 23);
		contentPane.add(btnLogout);
	}
}
