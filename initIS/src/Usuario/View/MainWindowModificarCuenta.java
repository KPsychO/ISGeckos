package Usuario.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Usuario.Control.ControllerUsuario;
import Usuario.Control.EventoUsuario;
import Usuario.Control.UsuarioDTO;

public class MainWindowModificarCuenta extends JPanel {
	private static final long serialVersionUID = 1L;

	private UsuarioDTO _dto;
	
	private JLabel avatarLabel;
	private JFileChooser avatarFile;
	private JLabel paisLabel;
	private JTextArea pais;
	private JLabel descripcionLabel;
	private JTextArea descripcion;
	private JButton ok;
	
	private ControllerUsuario _cu;
	
	public MainWindowModificarCuenta (UsuarioDTO dto, ControllerUsuario cu) {
		_cu = cu;
		_dto = dto;
		initGUI();
		//this.setVisible(true);
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
 
        //PONER COMO AVATAR LA IMAGEN SELECCIONADA EN EL JFILECHOOSER
        avatarFile = new JFileChooser("./src/resources"); 
        avatarFile.addActionListener(new cambiarAvatar());
        
        String path = _dto.getAvatarPath();
		ImageIcon img;
		
		if (path == null) {
			img = new ImageIcon("./src/resources/usuario.png");
		}
		
		else {
			img = new ImageIcon(path);
		}
        
        avatarLabel = new JLabel(img, JLabel.CENTER);
        avatarLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        avatarLabel.setPreferredSize(new Dimension(120, 120));
        
        avatarPanel.add(avatarLabel);
        avatarPanel.add(avatarFile);
        
        
        //PAIS
        JPanel paisPanel = new JPanel();
        BoxLayout paisLayout = new BoxLayout(paisPanel, BoxLayout.X_AXIS);
        paisPanel.setLayout(paisLayout);
        
        paisLabel = new JLabel();
        paisLabel.setPreferredSize(new Dimension(200,20));
        paisLabel.setText("Pais de residencia:  ");
        
        pais = new JTextArea(_dto.get_country());
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
        descripcionLabel.setText("Descripcion:  ");
        
        descripcion = new JTextArea(_dto.get_desc());
        descripcion.setPreferredSize(new Dimension(sizex,sizex));
        descripcion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        descripcion.setWrapStyleWord(true);
        descripcion.setLineWrap(true);
        
        descripcionPanel.add(descripcionLabel);
        descripcionPanel.add(descripcion);
        
        ok = new JButton ("GUARDAR CAMBIOS");
        ok.addActionListener(new guardarButton());
        
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
	
	class guardarButton implements ActionListener {
		public void actionPerformed(ActionEvent e){  
			if (!pais.getText().isEmpty())
				_dto.set_country(pais.getText());
			if (!descripcion.getText().isEmpty())
				_dto.set_desc(descripcion.getText());
			
			_cu.eliminarUsuario(_dto);
			_cu.storeUser(_dto);
			_cu.evento(EventoUsuario.PerfilUsuario, _dto);
		} 
	}
	
	class cambiarAvatar implements ActionListener {
		public void actionPerformed(ActionEvent e){  
		 File archivo = avatarFile.getSelectedFile();
	        if (archivo != null) {
	        	_dto.setAvatarPath(archivo.getPath());
	        }
	        
	        else
	        	JOptionPane.showMessageDialog(null, "Por favor, seleccione un archivo");
		}
		
	}
	

}
