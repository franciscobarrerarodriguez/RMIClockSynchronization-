package test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.AllConstants;
import views.JFrameClient;

public class MainClient {

	public static void main(String[] args) {

		JDialog jDialog = new JDialog();
		jDialog.setLayout(new BorderLayout());
		jDialog.setSize(400, 80);
		jDialog.setLocationRelativeTo(null);
		JPanel jPanelCenter = new JPanel(new GridLayout(1, 2));
		jPanelCenter.add(new JLabel("Type", JLabel.CENTER));
		JComboBox<String> jComboBoxType = new JComboBox<>();
		jComboBoxType.addItem(AllConstants.NORMAL_CLIENT);
		jComboBoxType.addItem(AllConstants.TIME_SERVER_CLIENT);
		jPanelCenter.add(jComboBoxType);
		jDialog.add(jPanelCenter, BorderLayout.CENTER);
		JButton jButton = new JButton("Continue");
		jDialog.add(jButton, BorderLayout.SOUTH);
		jDialog.setVisible(true);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(jButton)) {
					JFrameClient jFrameClient = new JFrameClient((String) jComboBoxType.getSelectedItem());
					jFrameClient.setVisible(true);
				}
			}
		});
	}
}
