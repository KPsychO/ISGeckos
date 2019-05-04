package Usuario.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

//import Formulario.Control.FormularioDTO;
import Usuario.Control.UsuarioDTO;
//import IncidenciasMejoras.Control.IncidenciasDAOJSON;
//import IncidenciasMejoras.Control.IncidenciasMejorasDTO;
//import Tienda.View.MainViewTienda;

public class MainWindowModificarCuenta extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JLabel avatarLabel;
	private JFileChooser avatarButton;
	private JLabel paisLabel;
	private JTextArea pais;
	private JLabel descripcionLabel;
	private JTextArea descripcion;
	private JButton ok;
	
	public MainWindowModificarCuenta () {
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		//configPanel();
		createModificarCuenta();
	}
	
	private void createModificarCuenta() {
		
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.Y_AXIS);
        generalPanel.setLayout(generalLayout);
        int sizex = 200;
        
        //AVATAR
        JPanel avatarPanel = new JPanel();
        BoxLayout avatarLayout = new BoxLayout(avatarPanel, BoxLayout.X_AXIS);
        avatarPanel.setLayout(avatarLayout);
        
        /*avatarLabel = new JLabel();
        avatarLabel.setPreferredSize(new Dimension(200,200));
        avatarLabel.setText("AVATAR");
        */
        
        ImageIcon img = new ImageIcon("./src/resources/usuario.png");
        avatarLabel = new JLabel(img, JLabel.CENTER);
       // panel.add(avatarLabel);
        
        avatarButton = new JFileChooser();
     
        avatarPanel.add(avatarLabel);
        avatarPanel.add(avatarButton);
        
        //PAIS
        JPanel paisPanel = new JPanel();
        BoxLayout paisLayout = new BoxLayout(paisPanel, BoxLayout.X_AXIS);
        paisPanel.setLayout(paisLayout);
        
        paisLabel = new JLabel();
        paisLabel.setPreferredSize(new Dimension(200,20));
        paisLabel.setText("PAIS DE RESIDENCIA:  ");
        
        pais = new JTextArea();
        pais.setPreferredSize(new Dimension(sizex,25));
        pais.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        paisPanel.add(paisLabel);
        paisPanel.add(pais);
        
        //DESCRIPCION
        JPanel descripcionPanel = new JPanel();
        BoxLayout descripcionLayout = new BoxLayout(descripcionPanel, BoxLayout.X_AXIS);
        descripcionPanel.setLayout(descripcionLayout);
        
        descripcionLabel = new JLabel();
        descripcionLabel.setPreferredSize(new Dimension(200,20));
        descripcionLabel.setText("DESCRIPCION:  ");
        
        descripcion = new JTextArea();
        descripcion.setPreferredSize(new Dimension(sizex,sizex));
        descripcion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        descripcionPanel.add(descripcionLabel);
        descripcionPanel.add(descripcion);
        
        ok = new JButton ("GUARDAR CAMBIOS");
        
        generalPanel.add(avatarPanel);
        generalPanel.add(paisPanel);
        generalPanel.add(descripcionPanel);
        //generalPanel.add(descripcion);
        generalPanel.add(ok);
        
        JScrollPane full = new JScrollPane(generalPanel, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        full.getVerticalScrollBar().setUnitIncrement(15);
        this.add(full);
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}