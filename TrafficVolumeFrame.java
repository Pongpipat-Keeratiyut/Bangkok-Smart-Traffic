package V;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import M.DataManager;
import M.TrafficVolumeDB;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;

public class TrafficVolumeFrame extends JFrame
{
	private static final long serialVersionUID = 754896139L;
	private JPanel contentPane;
	private JTable table;

	ArrayList<TrafficVolumeDB> list;
	private TableColumnAdjuster tableColumnAdjuster;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TrafficVolumeFrame frame = new TrafficVolumeFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public TrafficVolumeFrame()
	{
		setType(Type.POPUP);
		setTitle("Traffic Volume Frame");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAcc = new JLabel("Traffic Volume Table :");
		lblAcc.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblAcc.setBounds(31, 11, 145, 20);
		contentPane.add(lblAcc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 46, 720, 254);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		tableColumnAdjuster = new TableColumnAdjuster(table);	
		
		load();
	}
	
	public void load()
	{
		list = DataManager.getAllTrafficVolume();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("volume id");
		model.addColumn("road id");
		model.addColumn("volume start record");
		model.addColumn("volume end record");
		model.addColumn("volume of vehicles");
		
		for (TrafficVolumeDB a : list)
		{
			model.addRow(new Object[]
					{ a.volume_id, a.road_id, a.volume_time_start, a.volume_time_end, a.volume_of_vehicles  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_ColumnFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
}

class CenterRenderer_ColumnFrame extends DefaultTableCellRenderer
{ 
	private static final long serialVersionUID = 123456795L;
    private final int[] centeredColumns = {0, 1, 2, 3, 4};

    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
    																					int row, int column) 
    {
        java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        for (int centeredColumn : centeredColumns) {
            if (column == centeredColumn) {
                setHorizontalAlignment(JLabel.CENTER);
                break;
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }
        }
        return cellComponent;
    }
}
