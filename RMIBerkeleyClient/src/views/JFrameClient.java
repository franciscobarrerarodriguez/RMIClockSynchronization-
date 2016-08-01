package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import logic.Client;
import tools.AllConstants;

@SuppressWarnings("serial")
public class JFrameClient extends JFrame implements ActionListener {

	private String typeClient;

	private JMenuBar jMenuBar;
	private JMenuItem jMenuItemConnect, jMenuItemExit;

	private JPanel jPanelEast, jPanelCenter, jPanelSouth;

	private JLabel jLabelHour, jLabelMinute, jLabelSecond, jLabelNumberOfClients, jlLabelMessage;

	private JDialogClient jDialogClient;

	private Client client;

	public JFrameClient(String typeClient) {

		this.typeClient = typeClient;

		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setTitle(this.typeClient);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.setJMenuBar(this.jMenuBar = new JMenuBar());
		JMenu jMenuOptions = new JMenu("Options");
		this.jMenuBar.add(jMenuOptions);
		this.jMenuItemConnect = new JMenuItem("Connect");
		jMenuItemConnect.addActionListener(this);
		jMenuOptions.add(jMenuItemConnect);
		jMenuOptions.add(new JSeparator());
		this.jMenuItemExit = new JMenuItem("Exit");
		jMenuItemExit.addActionListener(this);
		jMenuOptions.add(jMenuItemExit);

		this.jPanelCenter = new JPanel();
		this.jPanelSouth = new JPanel(new BorderLayout());
		this.jPanelSouth.setBorder(BorderFactory.createEtchedBorder());
		JPanel jPanel1 = new JPanel(new GridLayout(1, 2));
		jPanel1.add(new JLabel("Clients", JLabel.CENTER));
		jPanel1.add(this.jLabelNumberOfClients = new JLabel(""));
		this.jPanelSouth.add(this.jlLabelMessage = new JLabel("Ready..."), BorderLayout.WEST);		
		this.jPanelSouth.add(jPanel1, BorderLayout.EAST);

		this.add(this.jPanelSouth, BorderLayout.SOUTH);

		switch (this.typeClient) {
		case AllConstants.NORMAL_CLIENT:
			this.normalClient();
			break;
		case AllConstants.TIME_SERVER_CLIENT:
			this.timeClient();
			break;
		}
	}

	public void timeClient() {
		JScrollPane jScrollPane = new JScrollPane(this.jPanelEast = new JPanel());
		this.jPanelEast.setBorder(BorderFactory.createEtchedBorder());
		this.add(jScrollPane, BorderLayout.EAST);
		this.jPanelCenter.setLayout(new GridLayout(1, 5));
		this.jPanelCenter.add(this.jLabelHour = new JLabel("0", JLabel.CENTER));
		this.jPanelCenter.add(new JLabel(":", JLabel.CENTER));
		this.jPanelCenter.add(this.jLabelMinute = new JLabel("0", JLabel.CENTER));
		this.jPanelCenter.add(new JLabel(":", JLabel.CENTER));
		this.jPanelCenter.add(this.jLabelSecond = new JLabel("0", JLabel.CENTER));
		this.add(this.jPanelCenter, BorderLayout.CENTER);
	}

	public void normalClient() {
		this.jPanelCenter.setLayout(new GridLayout(1, 5));
		this.jPanelCenter.add(this.jLabelHour = new JLabel("0", JLabel.CENTER));
		this.jPanelCenter.add(new JLabel(":", JLabel.CENTER));
		this.jPanelCenter.add(this.jLabelMinute = new JLabel("0", JLabel.CENTER));
		this.jPanelCenter.add(new JLabel(":", JLabel.CENTER));
		this.jPanelCenter.add(this.jLabelSecond = new JLabel("0", JLabel.CENTER));
		this.add(this.jPanelCenter, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.jMenuItemConnect)) {
			this.jDialogClient = new JDialogClient(this);
			this.jDialogClient.setVisible(true);
		}
		if (e.getSource().equals(this.jMenuItemExit)) {
			System.exit(0);
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String type) {
		this.typeClient = type;
	}

	public JLabel getjLabelHour() {
		return jLabelHour;
	}

	public void setjLabelHour(JLabel jLabelHour) {
		this.jLabelHour = jLabelHour;
	}

	public JLabel getjLabelMinute() {
		return jLabelMinute;
	}

	public void setjLabelMinute(JLabel jLabelMinute) {
		this.jLabelMinute = jLabelMinute;
	}

	public JLabel getjLabelSecond() {
		return jLabelSecond;
	}

	public void setjLabelSecond(JLabel jLabelSecond) {
		this.jLabelSecond = jLabelSecond;
	}

	public JLabel getjLabelNumberOfClients() {
		return jLabelNumberOfClients;
	}

	public void setjLabelNumberOfClients(JLabel jLabelNumberOfClients) {
		this.jLabelNumberOfClients = jLabelNumberOfClients;
	}

	public JLabel getJlLabelMessage() {
		return jlLabelMessage;
	}

	public void setJlLabelMessage(JLabel jlLabelMessage) {
		this.jlLabelMessage = jlLabelMessage;
	}

}
