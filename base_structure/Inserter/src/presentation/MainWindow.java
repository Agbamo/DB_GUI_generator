package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;

import domain.Manager;
import persistence.TableDAO;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	CustomJPanel scrollPanel;
	JLabel lblInfo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 1;
		gbc_lblTtulo.gridy = 1;
		frame.getContentPane().add(lblTtulo, gbc_lblTtulo);
		
		scrollPanel = new CustomJPanel();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		frame.getContentPane().add(scrollPanel, gbc_scrollPane);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insert();
			}

		});
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsert.gridx = 1;
		gbc_btnInsert.gridy = 5;
		frame.getContentPane().add(btnInsert, gbc_btnInsert);
		
		lblInfo = new JLabel("");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfo.gridx = 1;
		gbc_lblInfo.gridy = 6;
		frame.getContentPane().add(lblInfo, gbc_lblInfo);
	}
	private void insert() {
		Manager.getInstance().setValuesToInsert((ArrayList<String>)scrollPanel.getInputValues());
		try {
			TableDAO.insert();
			lblInfo.setText("Insertion performed");
		}catch (Exception e) {
			lblInfo.setText("Error while performing insertion: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
