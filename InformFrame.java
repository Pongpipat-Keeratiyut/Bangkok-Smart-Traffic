package V;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import M.*;
import common.GlobalData;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.io.Serializable;

public class InformFrame extends JFrame implements Serializable
{
	private static final long serialVersionUID = 754896134L;
	private boolean isSelected = false;
	
	ArrayList<InformDetail> list;
	ArrayList<InformDetail> searchRoadList;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_inform;
	private JButton btnSelect;
	private JButton btnSaveInform;
	
	private TableColumnAdjuster tableColumnAdjuster;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					InformFrame frame = new InformFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public InformFrame()
	{
		setTitle("Inform to people");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1275, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 1241, 166);
		contentPane.add(scrollPane);
		
		JLabel lblSelectRoadData = new JLabel("เลือกถนนที่ต้องการดู :");
		lblSelectRoadData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectRoadData.setBounds(10, 32, 145, 20);
		contentPane.add(lblSelectRoadData);
	
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		
		tableColumnAdjuster = new TableColumnAdjuster(table);
		
		btnSelect = new JButton("Select Road");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 

				SearchRoadFrame ss = new SearchRoadFrame();
				ss.setSelectRoad(new SelectRoadI()
				{
					@Override
					public void select(RoadDB x)
					{
						setSelectRoad(x);
					}
				});
				ss.setVisible(true);
			} 
		});   
		btnSelect.setBounds(144, 33, 108, 23);
		contentPane.add(btnSelect);

		JLabel lblInform = new JLabel("แจ้งข้อมูลสำหรับผู้สัญจร :");
		lblInform.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInform.setBounds(190, 307, 161, 22);
		contentPane.add(lblInform);
		
		textField_inform = new JTextField();
		textField_inform.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_inform.setBounds(353, 310, 297, 20);
		contentPane.add(textField_inform);
		textField_inform.setColumns(10);
		
		btnSaveInform = new JButton("Inform");
		btnSaveInform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSelected)
				{
					 JOptionPane.showMessageDialog(InformFrame.this, "Please select road to see data before inform.");
					 textField_inform.requestFocus();
				}
				else
				{
					GlobalData.saveInform.suggest_id = 0;
					GlobalData.saveInform.suggestion = textField_inform.getText().trim();		
					
					InformManager.saveSuggestData(GlobalData.saveInform);
											
					JOptionPane.showMessageDialog(InformFrame.this, "Successfully!!");
					
					load();
					
					isSelected = false;
					textField_inform.setText("");
				}	
			}
		});
		btnSaveInform.setBounds(671, 309, 108, 23);
		contentPane.add(btnSaveInform);
		
		
		load();
		
	}

	public void load()
	{
		list = InformManager.getAllData();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("road id");
		model.addColumn("road name");
		model.addColumn("accident");
		model.addColumn("congested time");
		model.addColumn("cause of congesttion");
		model.addColumn("rainfall(mm.)");
		model.addColumn("water level(cm.)");
		
		for (InformDetail i : list)
		{
			model.addRow(new Object[]{ 
					i.roadID, i.roadName, i.accident, i.congested_time,
					i.congested, i.rainfall, i.waterlevel  });			
		}
		
		table.setModel(model);
		
		table.setDefaultRenderer(Object.class, new CenterRenderer());
		
		tableColumnAdjuster.adjustColumns();
		
	}

	public void setSelectRoad(RoadDB xRoad)
	{
		searchRoadList = InformManager.getSearchRoad(xRoad.road_id);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("road id");
		model.addColumn("road name");
		model.addColumn("accident");
		model.addColumn("congested time");
		model.addColumn("cause of congesttion");
		model.addColumn("rainfall(mm.)");
		model.addColumn("water level(cm.)");
		
		for (InformDetail i : searchRoadList)
		{
			model.addRow(new Object[]{ 
					i.roadID, i.roadName, i.accident, i.congested_time,
					i.congested, i.rainfall, i.waterlevel  });		
		}
		
		
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer());
		tableColumnAdjuster.adjustColumns();
		
		isSelected = true;
	}

}


class CenterRenderer extends DefaultTableCellRenderer 
{
	private static final long serialVersionUID = 123456789L;
    private final int[] centeredColumns = {0, 3, 5, 6};

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
