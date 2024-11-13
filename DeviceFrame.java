package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import M.DeviceDB;
import M.DeviceManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeviceFrame extends JFrame
{
	private static final long serialVersionUID = 754896132L;
	private JPanel contentPane;
	private JTextField textField_DeviceID;
	private JTextField textField_RoadID;
	private JTextField textFieldDeviceName;
	private JTextField textFieldDeviceTypeID;
	private JTextField textFieldDeviceType_description;
	private ImagePanel imagePanel;
	ArrayList<DeviceDB> list;
	private JTable table;
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
					DeviceFrame frame = new DeviceFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}


	public DeviceFrame()
	{
		setTitle("Device Management");
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1133, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 25, 590, 515);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() < 1) return;
				int index = table.getSelectedRow();
				
				int device_id = Integer.parseInt(table.getValueAt(index, 0).toString());
				int road_id = Integer.parseInt(table.getValueAt(index, 1).toString());
				String device_name = table.getValueAt(index, 2).toString();
				int device_type_id = Integer.parseInt(table.getValueAt(index, 3).toString());
				String device_type_description = table.getValueAt(index, 4).toString();
				
				BufferedImage  img  = list.get(index).device_type_image;
				if(img != null)
				{
					imagePanel.setImage(img);
				}
				
				textField_DeviceID.setText("" + device_id);
				textField_RoadID.setText("" + road_id);
				textFieldDeviceName.setText(device_name);
				textFieldDeviceTypeID.setText("" + device_type_id);
				textFieldDeviceType_description.setText(device_type_description);
				
			}
		});
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		
		tableColumnAdjuster = new TableColumnAdjuster(table);
		
		JLabel lblDeviceid = new JLabel("device id");
		lblDeviceid.setBounds(710, 25, 107, 14);
		contentPane.add(lblDeviceid);
		
		textField_DeviceID = new JTextField();
		textField_DeviceID.setEditable(false);
		textField_DeviceID.setColumns(10);
		textField_DeviceID.setBackground(Color.LIGHT_GRAY);
		textField_DeviceID.setBounds(841, 25, 180, 20);
		contentPane.add(textField_DeviceID);
			
		JLabel lblRoadID = new JLabel("road id");
		lblRoadID.setBounds(710, 63, 107, 14);
		contentPane.add(lblRoadID);
		
		textField_RoadID = new JTextField();
		textField_RoadID.setColumns(10);
		textField_RoadID.setBounds(841, 63, 180, 20);
		contentPane.add(textField_RoadID);
	
		JLabel lblDeviceName = new JLabel("device name");
		lblDeviceName.setBounds(710, 106, 107, 14);
		contentPane.add(lblDeviceName);
		
		textFieldDeviceName = new JTextField();
		textFieldDeviceName.setColumns(10);
		textFieldDeviceName.setBounds(841, 106, 180, 20);
		contentPane.add(textFieldDeviceName);

		JLabel lblDeviceTypeID = new JLabel("device type id");
		lblDeviceTypeID.setBounds(710, 151, 107, 14);
		contentPane.add(lblDeviceTypeID);
		
		textFieldDeviceTypeID = new JTextField();
		textFieldDeviceTypeID.setColumns(10);
		textFieldDeviceTypeID.setBounds(841, 151, 180, 20);
		contentPane.add(textFieldDeviceTypeID);

		JLabel lblDeviceTypeDescription = new JLabel("device type description");
		lblDeviceTypeDescription.setBounds(710, 192, 127, 14);
		contentPane.add(lblDeviceTypeDescription);
		
		textFieldDeviceType_description = new JTextField();
		textFieldDeviceType_description.setBackground(Color.LIGHT_GRAY);
		textFieldDeviceType_description.setEditable(false);
		textFieldDeviceType_description.setColumns(10);
		textFieldDeviceType_description.setBounds(841, 189, 180, 20);
		contentPane.add(textFieldDeviceType_description);
		
		JButton btnSaveNewDevice = new JButton("SAVE NEW DEVICE");
		btnSaveNewDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        boolean roadIDValid = textField_RoadID.getText().trim().matches("[-+]?\\d*\\.?\\d+");
		        boolean deviceTypeIDValid = textFieldDeviceTypeID.getText().trim().matches("[1-6]");

		        if (!roadIDValid) 
		        {
		            JOptionPane.showMessageDialog(DeviceFrame.this, "Please enter a valid Road ID (number).");
		            textField_RoadID.requestFocus();
		            textField_RoadID.selectAll();
		        } 
		        else if (!deviceTypeIDValid) 
		        {
		            JOptionPane.showMessageDialog(DeviceFrame.this, "Please enter a valid Device Type ID (1-6).");
		            textFieldDeviceTypeID.requestFocus();
		            textFieldDeviceTypeID.selectAll();
		        } 
		        else 
		        {				
					DeviceDB x = new DeviceDB();
					x.device_id = 0;
					x.road_id = Integer.parseInt( textField_RoadID.getText().trim() );
					x.device_name = textFieldDeviceName.getText().trim();
					x.device_type_id = Integer.parseInt( textFieldDeviceTypeID.getText().trim() );
					x.device_type_description = textFieldDeviceType_description.getText().trim();
					x.device_type_image = (BufferedImage)imagePanel.getImage();		
					
					DeviceManager.saveDevice(x);
					load();
					textField_DeviceID.setText("");
					textField_RoadID.setText("");
					textFieldDeviceName.setText("");
					textFieldDeviceTypeID.setText("");
					textFieldDeviceType_description.setText("");
					
					JOptionPane.showMessageDialog(DeviceFrame.this, "finish!!");
		        }
			}
		});
		btnSaveNewDevice.setBounds(730, 460, 129, 23);
		contentPane.add(btnSaveNewDevice);
		
		
		JButton btnDeleteDevice = new JButton("DELETE DEVICE");
		btnDeleteDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(DeviceFrame.this,
						"Do you want to delete ?", "DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
					DeviceDB x = new DeviceDB();
					x.device_id = Integer.parseInt( textField_DeviceID.getText().trim() );
					x.road_id = Integer.parseInt( textField_RoadID.getText().trim() );
					x.device_name = textFieldDeviceName.getText().trim();
					x.device_type_id = Integer.parseInt( textFieldDeviceTypeID.getText().trim() );
					x.device_type_description = textFieldDeviceType_description.getText().trim();
					x.device_type_image = (BufferedImage)imagePanel.getImage();
					
					DeviceManager.deleteDevice(x);
					load();
					
					textField_DeviceID.setText("");
					textField_RoadID.setText("");
					textFieldDeviceName.setText("");
					textFieldDeviceTypeID.setText("");
					textFieldDeviceType_description.setText("");
					
					imagePanel.setImage(null);

					JOptionPane.showMessageDialog(DeviceFrame.this, "finish!!");
				}
			}
		});
		btnDeleteDevice.setBounds(869, 460, 127, 23);
		contentPane.add(btnDeleteDevice);
		
		imagePanel = new ImagePanel();
		imagePanel.setBounds(768, 249, 190, 180);
		contentPane.add(imagePanel);
		
		load();
	}
	
	public void load()
	{
		list = DeviceManager.getAllDevice();
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("device id");
		model.addColumn("road id");
		model.addColumn("device name");
		model.addColumn("device type id");
		model.addColumn("device type description");

		for (DeviceDB c : list)
		{
			model.addRow(new Object[]
			{ c.device_id, c.road_id, c.device_name, c.device_type_id, c.device_type_description});
		}
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CenterRenderer_DeviceFrame());		
		tableColumnAdjuster.adjustColumns();
	}
}


class CenterRenderer_DeviceFrame extends DefaultTableCellRenderer
{ 
	private static final long serialVersionUID = 123456791L;
	private final int[] centeredColumns = {0, 1, 3};

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
