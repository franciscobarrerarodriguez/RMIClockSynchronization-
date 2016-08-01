package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Client;

@SuppressWarnings("serial")
public class JDialogClient extends JDialog implements ActionListener {

	private JFrameClient jFrameClient;
	private JButton jButtonConnect;
	private JTextField jTextFieldIp, jTextFieldPort;

	public JDialogClient(JFrameClient jFrameClient) {

		this.jFrameClient = jFrameClient;

		this.setSize(400, 80);
		this.setModal(true);
		this.setLocationRelativeTo(this.jFrameClient);
		this.setTitle("Settings");
		this.setLayout(new BorderLayout());

		JPanel jPanelCenter = new JPanel(new GridLayout(2, 2));
		jPanelCenter.add(new JLabel("Ip", JLabel.CENTER));
		jPanelCenter.add(this.jTextFieldIp = new JTextField());
		jPanelCenter.add(new JLabel("Port", JLabel.CENTER));
		jPanelCenter.add(this.jTextFieldPort = new JTextField());
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(this.jButtonConnect = new JButton("Connect"), BorderLayout.SOUTH);
		this.jButtonConnect.addActionListener(this);
	}

	private boolean validate(String port) {
		try {
			@SuppressWarnings("unused")
			int var = Integer.parseInt(port);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.jButtonConnect)) {
			if ((this.jTextFieldPort.getText().length() > 0) && (this.jTextFieldIp.getText().length() > 0)) {
				if (this.validate(this.jTextFieldPort.getText())) {
					try {
						Client client = new Client(this.jTextFieldIp.getText(),
								Integer.parseInt(this.jTextFieldPort.getText()), this.jFrameClient.getTypeClient(), this.jFrameClient);
						client.getThread().start();
						this.jFrameClient.setClient(client);
						this.dispose();
					} catch (NumberFormatException | RemoteException | MalformedURLException | NotBoundException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Parametros incorrectos");
			}
		}
	}
}
