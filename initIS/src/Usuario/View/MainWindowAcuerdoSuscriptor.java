package Usuario.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Usuario.Control.ControllerUsuario;
import Usuario.Control.EventoUsuario;

public class MainWindowAcuerdoSuscriptor extends JPanel{
	private static final long serialVersionUID = 1L;

	private JLabel acuerdoSuscriptor;
	private JCheckBox ok;
	//private JLabel aceptar;
	private JButton boton;
	
	private ControllerUsuario _cu;
	
	public MainWindowAcuerdoSuscriptor (ControllerUsuario cu) {
		
		_cu = cu;
		
		initGUI();
		this.setVisible(true);
	}
	
	private void initGUI() {
		//configPanel();
		createAcuerdoSuscriptor();
	}
	
	private void createAcuerdoSuscriptor() {
		
		JPanel generalPanel = new JPanel();
        BoxLayout generalLayout = new BoxLayout(generalPanel, BoxLayout.Y_AXIS);
        generalPanel.setLayout(generalLayout);
        
        String texto = 	"<html><body><b>ACUERDO DE USUARIO<br>"
        			+ "ULTIMA ACTUALIZACION: 26 de abril de 2019<b><br>"
					+ "<br>Bienvenido a Geckos. Este acuerdo rige el acceso<br>"
					+ "y uso por su parte de nuestra plataforma<br>"
					+ "\nAl utilizar los servicios de Geckos, usted acepta estas<br>"
					+"condiciones generales. Si no las acepta, no deberia instalar<br>"
					+"ni utilizar los servicios de Geckos.<br>"
					+"<br>Indice:<br>"
					+"Cuenta de usuario<br>"
					+"Licencia<br>"
					+"Contenidos y Derechos<br>"
					+"Condiciones Generales<br>"
					+"...<br><br></body></html>";
        
        acuerdoSuscriptor = new JLabel(texto); 
        
        JScrollPane acuerdo = new JScrollPane(acuerdoSuscriptor, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        acuerdo.getVerticalScrollBar().setUnitIncrement(15);
        
        ok = new JCheckBox("He leido y acepto el acuerdo");
       
        boton = new JButton ("CONTINUAR");
        boton.addActionListener(new continuarButton());
        
        
        generalPanel.add(acuerdo);
        generalPanel.add(ok);
        generalPanel.add(boton);
		
        this.add(generalPanel);
	}


	class continuarButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (ok.isSelected()) {
				_cu.evento(EventoUsuario.AcuerdoSuscriptor, null);
        		firePropertyChange("AcuerdoSuscriptor", null, null);
        	}
			else {
				String tipoError = "Debes aceptar el acuerdo para continuar";
				JOptionPane.showMessageDialog(MainWindowAcuerdoSuscriptor.this, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}