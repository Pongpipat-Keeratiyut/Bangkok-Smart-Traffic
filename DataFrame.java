package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class DataFrame extends JFrame
{
	private static final long serialVersionUID = 754896133L;
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
					DataFrame frame = new DataFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public DataFrame()
	{
		setTitle("Traffic Data");
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        });
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAccident = new JButton("Accident");
		btnAccident.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccidentFrame f = new AccidentFrame();
				f.setVisible(true);
			}
		});
		btnAccident.setBounds(32, 46, 158, 23);
		contentPane.add(btnAccident);

		JButton btnCongestedDuration = new JButton("Congested Duration");
		btnCongestedDuration.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CongestedFrame f = new CongestedFrame();
				f.setVisible(true);
			}
		});
		btnCongestedDuration.setBounds(200, 46, 158, 23);
		contentPane.add(btnCongestedDuration);

		JButton btnTrafficDirection = new JButton("Traffic Direction");
		btnTrafficDirection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrafficDirectionFrame f = new TrafficDirectionFrame();
				f.setVisible(true);
			}
		});
		btnTrafficDirection.setBounds(32, 90, 158, 23);
		contentPane.add(btnTrafficDirection);

		JButton btnTrafficVolume = new JButton("Traffic Volume");
		btnTrafficVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrafficVolumeFrame f = new TrafficVolumeFrame();
				f.setVisible(true);
			}
		});
		btnTrafficVolume.setBounds(200, 90, 158, 23);
		contentPane.add(btnTrafficVolume);
		
		JButton btnRainInformation = new JButton("Rain Information");
		btnRainInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RainFrame f = new RainFrame();
				f.setVisible(true);
			}
		});
		btnRainInformation.setBounds(32, 134, 158, 23);
		contentPane.add(btnRainInformation);
		
		JButton btnWaterLevel = new JButton("Water Level Information");
		btnWaterLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WaterLevelFrame f = new WaterLevelFrame();
				f.setVisible(true);
			}
		});
		btnWaterLevel.setBounds(200, 134, 158, 23);
		contentPane.add(btnWaterLevel);
		
		JLabel lblClickMenuData = new JLabel("Click to see data:");
		lblClickMenuData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClickMenuData.setBounds(32, 11, 116, 14);
		contentPane.add(lblClickMenuData);
		
	}
}
