package viewer;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Tienda.View.MainViewTienda;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JPanel menuPanel;
	JPanel headerPanel;
	JPanel principalPanel;
	
	public MainWindow() {
		super("Gecko");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initComponent();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(headerPanel, BorderLayout.NORTH);
		this.getContentPane().add(menuPanel, BorderLayout.WEST);
		this.getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		/*
		JLabel lnorth = new JLabel("Region norte");
		lnorth.setBackground(Color.BLUE);
		lnorth.setOpaque(true);
		this.getContentPane().add(lnorth, BorderLayout.NORTH);
		*/
	}

	private void initComponent() {
		menuPanel = initMenuPanel();
		headerPanel = initHeaderPanel();
		principalPanel = new MainViewTienda("estonohacenadaporahora"); 
	}

	private JPanel initMenuPanel() {
		JPanel panel = new JPanel();

		BoxLayout la = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(la);
		
		JButton buttonTienda = new JButton("Tienda");
		panel.add(buttonTienda);
		buttonTienda.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(new JLabel(" "));

		JButton buttonBiblio = new JButton("Biblioteca");
		buttonBiblio.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonBiblio);
		panel.add(new JLabel(" "));

		JButton buttonCom = new JButton("Comunidad");
		buttonCom.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonCom);
		
		panel.add(new JLabel(" "));

		JButton buttonSoporte = new JButton("Soporte");
		buttonSoporte.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonSoporte);

		panel.add(new JLabel(" "));

		return panel;
	}

	private JPanel initHeaderPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		JPanel aux = new JPanel();
		aux.setLayout(new BoxLayout(aux,BoxLayout.Y_AXIS));
		JButton buttonIcon = new JButton(new ImageIcon("images.png"));
		aux.add(buttonIcon);
		aux.add(new JLabel((" ")));
		panel.add(aux);
		panel.add(new JLabel(" "));
		panel.add(new JLabel("Usuario"));
		return panel;
	}
	
}
