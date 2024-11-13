package V;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.RoadDB;
import M.RoadManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class SearchRoadFrame extends JFrame
{
	private static final long serialVersionUID = 754896135L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_search;
	private JButton btnSelect;
	private JButton btnSearch;
	SelectRoadI xSelectRoadI;
	ArrayList<RoadDB> list;
	private TableColumnAdjuster tableColumnAdjuster;
	private boolean isSearchFine_1 = false;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SearchRoadFrame frame = new SearchRoadFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public SearchRoadFrame()
	{
		setTitle("Fine The Road");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 670, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 641, 168);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		tableColumnAdjuster = new TableColumnAdjuster(table);
		
		textField_search = new JTextField();
		textField_search.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_search.setBounds(10, 47, 257, 20);
		contentPane.add(textField_search);
		textField_search.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
				if (table.getRowCount() == 1)
				{
					xSelectRoadI.select(list.get(0));
					isSearchFine_1 = true;
				}
					
			}
		});
		btnSearch.setBounds(295, 46, 89, 23);
		contentPane.add(btnSearch);
		
		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 0 && isSearchFine_1 == false)
				{
					JOptionPane.showMessageDialog(SearchRoadFrame.this, "Please Select 1 row");
					return;
				}
				
				if (xSelectRoadI != null)
				{
					if (list != null)
					{
						if(isSearchFine_1 == false) 
						{
							xSelectRoadI.select(list.get(table.getSelectedRow()));
						}
						isSearchFine_1 = false;
						setVisible(false);
					}
				}
			
			}
		});
		btnSelect.setBounds(394, 46, 89, 23);
		contentPane.add(btnSelect);
		
		JLabel lblfillRoadName = new JLabel("กรอกช่วงของถนนที่ต้องการค้นหา (แยกถึงแยก) :");
		lblfillRoadName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblfillRoadName.setBounds(10, 21, 281, 14);
		contentPane.add(lblfillRoadName);
		
		load();
	}
	
	public void load()
	{
		list = RoadManager.getAllRoad();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("user id");
		model.addColumn("road name");
		model.addColumn("sub district");
		model.addColumn("district");
		model.addColumn("province");
		model.addColumn("postal code");

		for (RoadDB r : list)
		{
			model.addRow(new Object[]
					{ r.road_id, r.user_id, r.road_name, r.sub_district, r.district, r.province, r.postal_code  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_RoadFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
	public void search()
	{
		list = RoadManager.searchRoad(textField_search.getText());
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("user id");
		model.addColumn("road name");
		model.addColumn("sub district");
		model.addColumn("district");
		model.addColumn("province");
		model.addColumn("postal code");

		for (RoadDB r : list)
		{
			model.addRow(new Object[]
					{ r.road_id, r.user_id, r.road_name, r.sub_district, r.district, r.province, r.postal_code  });
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_RoadFrame());
		tableColumnAdjuster.adjustColumns();
	}
	
	public void setSelectRoad(SelectRoadI x)
	{
		xSelectRoadI = x;
	}
}

interface SelectRoadI
{
	public void select(RoadDB x);
}