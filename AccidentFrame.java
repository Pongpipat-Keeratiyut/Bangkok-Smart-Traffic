package V;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import M.AccidentDB;
import M.DataManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;

public class AccidentFrame extends JFrame
{
	private static final long serialVersionUID = 754896136L;
	private JPanel contentPane;
	private JTable table;

	ArrayList<AccidentDB> list;
	private TableColumnAdjuster tableColumnAdjuster;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					AccidentFrame frame = new AccidentFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}


	public AccidentFrame()
	{
		setType(Type.POPUP);
		setTitle("Accident Frame");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAcc = new JLabel("Accident Table :");
		lblAcc.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblAcc.setBounds(23, 11, 145, 20);
		contentPane.add(lblAcc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 42, 734, 58);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		tableColumnAdjuster = new TableColumnAdjuster(table);
		
		
		load();
	}
	

	public void load()
	{
		list = DataManager.getAllAccidents();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("device id");
		model.addColumn("road id");
		model.addColumn("time occur");
		model.addColumn("accident description");
		

		for (AccidentDB a : list)
		{
			model.addRow(new Object[]
					{ a.accident_id, a.device_id, a.road_id, a.time_occur, a.accident_description  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_AccidentFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
}

class CenterRenderer_AccidentFrame extends DefaultTableCellRenderer
{ 
	private static final long serialVersionUID = 123456792L;
	private final int[] centeredColumns = {0, 1, 2};

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
