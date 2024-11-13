package V;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import M.RoadDB;
import M.RoadManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoadFrame extends JFrame
{
	private static final long serialVersionUID = 754896131L;
	ArrayList<RoadDB> list;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_road_id;
	private JTextField textField_user_id;
	private JTextField textField_road_name;
	private JTextField textField_sub_district;
	private JTextField textField_district;
	private JTextField textField_province;
	private JTextField textField_postal_code;
	
	private TableColumnAdjuster tableColumnAdjuster;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					RoadFrame frame = new RoadFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public RoadFrame()
	{
		setTitle("Road Management");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 980, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 631, 343);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		table.setBackground(Color.white);
		
		tableColumnAdjuster = new TableColumnAdjuster(table);
		
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int road_id = Integer.parseInt(table.getValueAt(index, 0).toString());
				int user_id = Integer.parseInt(table.getValueAt(index, 1).toString());
				String road_name = table.getValueAt(index, 2).toString();
				String sub_district = table.getValueAt(index, 3).toString();
				String district = table.getValueAt(index, 4).toString();
				String province = table.getValueAt(index, 5).toString();
				String postal_code = table.getValueAt(index, 6).toString();
				
				textField_road_id.setText("" + road_id);
				textField_user_id.setText("" + user_id);
				textField_road_name.setText("" + road_name);
				textField_sub_district.setText("" + sub_district);
				textField_district.setText("" + district);
				textField_province.setText("" + province);
				textField_postal_code.setText("" + postal_code);
			}
		});

		JLabel lblRoadID = new JLabel("road id");
		lblRoadID.setBounds(663, 11, 65, 14);
		contentPane.add(lblRoadID);

		textField_road_id = new JTextField();
		textField_road_id.setEditable(false);
		textField_road_id.setBackground(Color.LIGHT_GRAY);
		textField_road_id.setBounds(738, 11, 182, 20);
		contentPane.add(textField_road_id);
		textField_road_id.setColumns(10);

		JLabel lblUserId = new JLabel("user id");
		lblUserId.setBounds(663, 51, 65, 14);
		contentPane.add(lblUserId);
		
		textField_user_id = new JTextField();
		textField_user_id.setColumns(10);
		textField_user_id.setBounds(738, 51, 182, 20);
		contentPane.add(textField_user_id);

		JLabel lblRoadName = new JLabel("road name");
		lblRoadName.setBounds(663, 94, 65, 14);
		contentPane.add(lblRoadName);
		
		textField_road_name = new JTextField();
		textField_road_name.setColumns(10);
		textField_road_name.setBounds(738, 94, 182, 20);
		contentPane.add(textField_road_name);
		
		JLabel lblSubdistrict = new JLabel("sub-district");
		lblSubdistrict.setBounds(663, 139, 65, 14);
		contentPane.add(lblSubdistrict);
		
		textField_sub_district = new JTextField();
		textField_sub_district.setColumns(10);
		textField_sub_district.setBounds(738, 139, 182, 20);
		contentPane.add(textField_sub_district);

		JLabel lblDistrict = new JLabel("district");
		lblDistrict.setBounds(663, 181, 65, 14);
		contentPane.add(lblDistrict);

		textField_district = new JTextField();
		textField_district.setColumns(10);
		textField_district.setBounds(738, 181, 182, 20);
		contentPane.add(textField_district);

		JLabel lblProvince = new JLabel("province");
		lblProvince.setBounds(663, 224, 65, 14);
		contentPane.add(lblProvince);

		textField_province = new JTextField();
		textField_province.setColumns(10);
		textField_province.setBounds(738, 224, 182, 20);
		contentPane.add(textField_province);

		JLabel lblPostalCode = new JLabel("postal code");
		lblPostalCode.setBounds(663, 269, 65, 14);
		contentPane.add(lblPostalCode);

		textField_postal_code = new JTextField();
		textField_postal_code.setColumns(10);
		textField_postal_code.setBounds(738, 269, 182, 20);
		contentPane.add(textField_postal_code);

		JButton btnSaveNew = new JButton("SAVE NEW");
		btnSaveNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean userIDValid = textField_user_id.getText().trim().matches("[-+]?\\d*\\.?\\d+");
		        boolean postalCodeValid = textField_postal_code.getText().trim().matches("[-+]?\\d*\\.?\\d+");

		        if (!userIDValid) 
		        {
		            JOptionPane.showMessageDialog(RoadFrame.this, "Please enter a valid User ID (number).");
		            textField_user_id.requestFocus();
		            textField_user_id.selectAll();
		        } 
		        else if (!postalCodeValid) 
		        {
		            JOptionPane.showMessageDialog(RoadFrame.this, "Please enter a valid Postal Code (number)..");
		            textField_postal_code.requestFocus();
		            textField_postal_code.selectAll();
		        } 
		        else 
		        {			
					RoadDB x = new RoadDB( 0, Integer.parseInt( textField_user_id.getText().trim() ), textField_road_name.getText().trim(),
							textField_sub_district.getText().trim(), textField_district.getText().trim(), textField_province.getText().trim(),
							textField_postal_code.getText().trim() );
					RoadManager.saveNewRoad(x);
					load();
					textField_road_id.setText("");
					textField_user_id.setText("");
					textField_road_name.setText("");
					textField_sub_district.setText("");
					textField_district.setText("");
					textField_province.setText("");
					textField_postal_code.setText("");
	
					JOptionPane.showMessageDialog(RoadFrame.this, "finish!!");
		        }
			}
		});
		btnSaveNew.setBounds(738, 320, 89, 23);
		contentPane.add(btnSaveNew);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RoadDB x = new RoadDB(Integer.parseInt(textField_road_id.getText()), 
						Integer.parseInt( textField_user_id.getText().trim() ), textField_road_name.getText().trim(),
						textField_sub_district.getText().trim(), textField_district.getText().trim(), textField_province.getText().trim(),
						textField_postal_code.getText().trim() );
				RoadManager.editRoad(x);
				load();
				textField_road_id.setText("");
				textField_user_id.setText("");
				textField_road_name.setText("");
				textField_sub_district.setText("");
				textField_district.setText("");
				textField_province.setText("");
				textField_postal_code.setText("");

				JOptionPane.showMessageDialog(RoadFrame.this, "finish!!");
			}
		});
		btnEdit.setBounds(429, 365, 73, 23);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(RoadFrame.this,
						"Do you want to delete ?", "DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
					RoadDB x = new RoadDB(Integer.parseInt(textField_road_id.getText()), 
							Integer.parseInt( textField_user_id.getText().trim() ), textField_road_name.getText().trim(),
							textField_sub_district.getText().trim(), textField_district.getText().trim(), textField_province.getText().trim(),
							textField_postal_code.getText().trim() );
					RoadManager.deleteRoad(x);
					load();
					textField_road_id.setText("");
					textField_user_id.setText("");
					textField_road_name.setText("");
					textField_sub_district.setText("");
					textField_district.setText("");
					textField_province.setText("");
					textField_postal_code.setText("");

					JOptionPane.showMessageDialog(RoadFrame.this, "finish!!");
				}
			}
		});
		btnDelete.setBounds(534, 365, 73, 23);
		contentPane.add(btnDelete);
		
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
}

class CenterRenderer_RoadFrame extends DefaultTableCellRenderer {
	 private static final long serialVersionUID = 123456790L;
	 private final int[] centeredColumns = {0, 1, 6};

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