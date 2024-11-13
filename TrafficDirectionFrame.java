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
import M.TrafficDirectionsDB;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;

public class TrafficDirectionFrame extends JFrame
{
	private static final long serialVersionUID = 754896138L;
	private JPanel contentPane;
	private JTable table;

	ArrayList<TrafficDirectionsDB> list;
	private TableColumnAdjuster tableColumnAdjuster;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TrafficDirectionFrame frame = new TrafficDirectionFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public TrafficDirectionFrame()
	{
		setType(Type.POPUP);
		setTitle("Traffic Direction Frame");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAcc = new JLabel("Traffic Direction Table :");
		lblAcc.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblAcc.setBounds(23, 11, 145, 20);
		contentPane.add(lblAcc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 42, 744, 254);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		tableColumnAdjuster = new TableColumnAdjuster(table);
		
		load();
	}
	
	public void load()
	{
		list = DataManager.getAllTrafficDirection();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("road id");
		model.addColumn("direction start record");
		model.addColumn("direction end record");
		model.addColumn("volume direction");
		model.addColumn("direction name");

		for (TrafficDirectionsDB t : list)
		{
			model.addRow(new Object[]
					{ t.direction_id, t.road_id, t.direction_time_start, t.direction_time_end, t.volume_of_direction, t.direction_name  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_DirFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
}

class CenterRenderer_DirFrame extends DefaultTableCellRenderer
{ 
	private static final long serialVersionUID = 123456794L;
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
