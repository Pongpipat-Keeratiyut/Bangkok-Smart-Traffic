package V;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;

import M.DataManager;
import M.RainfallDB;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;

public class RainFrame extends JFrame
{
	private static final long serialVersionUID = 754896140L;
	private JPanel contentPane;
	private JTable table;

	ArrayList<RainfallDB> list;
	private TableColumnAdjuster tableColumnAdjuster;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					RainFrame frame = new RainFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public RainFrame()
	{
		setType(Type.POPUP);
		setTitle("RainFall Frame");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAcc = new JLabel("RainFall Table :");
		lblAcc.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblAcc.setBounds(23, 11, 145, 20);
		contentPane.add(lblAcc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 46, 540, 71);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		tableColumnAdjuster = new TableColumnAdjuster(table);
		
		load();
	}
	
	public void load()
	{
		list = DataManager.getAllRainFall();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("road id");
		model.addColumn("rainy start time");
		model.addColumn("rainy end time");
		model.addColumn("rainfall (mm.)");
		
		for (RainfallDB a : list)
		{
			model.addRow(new Object[]
					{ a.rain_id, a.road_id, a.rain_time_start, a.rain_time_end, a.rainfall  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_ColumnFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
}
