package V;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;

import M.DataManager;
import M.WaterLevelDB;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;

public class WaterLevelFrame extends JFrame
{
	private static final long serialVersionUID = 754896141L;
	private JPanel contentPane;
	private JTable table;

	ArrayList<WaterLevelDB> list;
	private TableColumnAdjuster tableColumnAdjuster;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					WaterLevelFrame frame = new WaterLevelFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public WaterLevelFrame()
	{
		setType(Type.POPUP);
		setTitle("Water Level Frame");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAcc = new JLabel("Water Level Table :");
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
		list = DataManager.getAllWaterLevel();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("road id");
		model.addColumn("water level start time");
		model.addColumn("water level end time");
		model.addColumn("water level (cm.)");
		
		for (WaterLevelDB a : list)
		{
			model.addRow(new Object[]
					{ a.water_level_id, a.road_id, a.water_level_time_start, a.water_level_time_end, a.water_level  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_ColumnFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
}

