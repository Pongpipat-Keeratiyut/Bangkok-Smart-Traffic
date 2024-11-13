package V;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import M.CongestedDB;
import M.DataManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;

public class CongestedFrame extends JFrame
{
	private static final long serialVersionUID = 754896137L;
	private JPanel contentPane;
	private JTable table;

	ArrayList<CongestedDB> list;
	private TableColumnAdjuster tableColumnAdjuster;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					CongestedFrame frame = new CongestedFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public CongestedFrame()
	{
		setType(Type.POPUP);
		setTitle("Congested Time Frame");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAcc = new JLabel("Congested Time Table :");
		lblAcc.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblAcc.setBounds(10, 11, 145, 20);
		contentPane.add(lblAcc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 766, 254);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		tableColumnAdjuster = new TableColumnAdjuster(table);
				
		load();
	}
	
	public void load()
	{
		list = DataManager.getAllCongestedTime();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("road id");
		model.addColumn("congested start time");
		model.addColumn("congested end time");
		model.addColumn("congested cause");
		
		for (CongestedDB a : list)
		{
			model.addRow(new Object[]
					{ a.congested_id, a.road_id, a.congested_time_start, a.congested_time_end, a.congested_cause  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_CongestedFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
}

class CenterRenderer_CongestedFrame extends DefaultTableCellRenderer
{ 
	private static final long serialVersionUID = 123456793L;
    private final int[] centeredColumns = {0, 1, 2, 3};

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
