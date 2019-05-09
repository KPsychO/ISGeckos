package valoraciones.viewer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import valoraciones.ValoracionesControllerFacade;
import valoraciones.controller.EventsValoraciones;
import valoraciones.factories.BuildersMultimediaTypes;
import valoraciones.factories.BuildersTextTypes;
import valoraciones.factories.SingletonBuilderMultimediaFactory;
import valoraciones.factories.SingletonBuilderTextFactory;
import valoraciones.model.TransferValoracion;
import valoraciones.model.multimedia.Multimedia;


public class ViewFromValoracion extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JTextField textFieldTitulo;
	private JButton buttonMultimedia;
	private JTextArea textAreaValoracion;
	private JSlider sliderPuntuacion;
	private JButton buttonPublicar;
	private JComboBox<String> comboBoxFormat;
	
	private TransferValoracion valoracion;
	private Multimedia multimedia;
	
	private ValoracionesControllerFacade controller;
	
	public ViewFromValoracion(UsuarioDTO user, JuegoDTO juego, ValoracionesControllerFacade controller) {
		this.controller = controller;
		valoracion = new TransferValoracion(user,juego);
		initComponent();
		addComponents();
	}

	private void initComponent() {
		sliderPuntuacion = new JSlider();

		sliderPuntuacion = new JSlider(JSlider.HORIZONTAL,0,5,1);
		sliderPuntuacion.setMajorTickSpacing(1);
		sliderPuntuacion.setMinorTickSpacing(1);
		sliderPuntuacion.setPaintTicks(true);
		sliderPuntuacion.setPaintLabels(true);
		
		textFieldTitulo = new JTextField();
		textAreaValoracion = new JTextArea();
		textAreaValoracion.setLineWrap(true);
		textAreaValoracion.setWrapStyleWord(true);
		
		String[] formats = {"Audio","Image","Video"};
		comboBoxFormat = new JComboBox<String>(formats);
		
		buttonMultimedia = new JButton("Multimedia");
		
		buttonMultimedia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int ret = fileChooser.showOpenDialog(getParent());
				if (ret == JFileChooser.APPROVE_OPTION) {
					//fileChooser.getSelectedFile()
					int format = 0;
					switch(comboBoxFormat.getSelectedIndex()) {
					case 0:
						format = BuildersMultimediaTypes.AUDIO;
						break;
					case 1:
						format = BuildersMultimediaTypes.IMAGES;
						break;
					case 2: 
						format = BuildersMultimediaTypes.VIDEO;
						break;
					case 3:
						JOptionPane.showMessageDialog(
								getParent(), 
								"Error. \n"+
								"Formato de multimedia desconocido", 
								"Controller run error",
								JOptionPane.INFORMATION_MESSAGE 
								);
						break;
					default:
						
					}
					multimedia = SingletonBuilderMultimediaFactory.getSingletonInstance().createInstance(new Object[] {format,fileChooser.getSelectedFile().getName(),fileChooser.getSelectedFile()});
				}
			}
			
		});
		
		buttonPublicar = new JButton("Publicar");
		
		buttonPublicar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					valoracion.setTitulo(SingletonBuilderTextFactory.getSingletonInstance().createInstance(new Object[] {BuildersTextTypes.TITULO,textFieldTitulo.getText() }));
					valoracion.setValoracion(SingletonBuilderTextFactory.getSingletonInstance().createInstance(new Object[] {BuildersTextTypes.VALORACION,textAreaValoracion.getText() }));
					valoracion.setDate(dateFormat.format(date));
					if(multimedia!=null) {
						valoracion.setMultimedia(multimedia);
					}
					valoracion.setPuntuacion(sliderPuntuacion.getValue());
					controller.actions(EventsValoraciones.PUBLICAR_VALORACION,valoracion);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(
							getParent(), 
							"Error controller run.\n"+ ex.getMessage(), 
							"Controller run error",
							JOptionPane.INFORMATION_MESSAGE 
							);
					ex.printStackTrace();
				}
				
			}
			
		});
	}
	
	private void addComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(10,10,10,10);

		c.weightx = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel("Titulo"), c);

		c.ipadx = 150;
		c.gridx = 1;
		c.gridy = 0;
		this.add(textFieldTitulo, c);
		c.ipadx = 0;
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(new JLabel("Puntuacion"), c);

		c.ipadx = 100;
		c.gridx = 1;
		c.gridy = 1;
		this.add(sliderPuntuacion, c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(new JLabel("Multimeda:"), c);

		JPanel aux = new JPanel();
		aux.add(comboBoxFormat);
		aux.add(buttonMultimedia);
		c.gridx = 1;
		c.gridy = 2;
		this.add(aux, c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.add(new JLabel("Valoracion:"), c);

		JScrollPane aux1 = new JScrollPane(textAreaValoracion);
		c.gridwidth = 2;
		c.ipady = 120;
		c.ipadx = 500;
		c.gridx = 0;
		c.gridy = 4;
		this.add(aux1, c);
		c.gridwidth = 0;
		c.ipady = 0;
		c.ipadx = 0;

		c.gridx = 3;
		c.gridy = 5;
		this.add(buttonPublicar, c);
	}
	
	
}
