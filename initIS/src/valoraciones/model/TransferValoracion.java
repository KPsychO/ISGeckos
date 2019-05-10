package valoraciones.model;

import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import valoraciones.factories.BuildersTextTypes;
import valoraciones.factories.SingletonBuilderMultimediaFactory;
import valoraciones.factories.SingletonBuilderTextFactory;
import valoraciones.model.multimedia.Multimedia;
import valoraciones.model.text.Text;

public class TransferValoracion {
	private int id;
	private UsuarioDTO user;
	private JuegoDTO game;
	private Text titulo;
	private Text valoracion;
	private int puntuacion;
	private Multimedia multimedia;
	private String date;
	
	/**
	 * 
	 * @param id
	 * @param userID
	 * @param gameID
	 * @param titulo
	 * @param valoracion
	 * @param puntuacion
	 * @param multimedia
	 */
	public TransferValoracion(int id, UsuarioDTO user, JuegoDTO game, Text titulo, Text valoracion, int puntuacion, Multimedia multimedia, String date) {
		this.id = id;
		this.user = user;
		this.game = game;
		this.titulo = titulo;
		this.valoracion = valoracion;
		this.puntuacion = puntuacion;
		this.multimedia = multimedia;
		this.date = date;
	}
	
	/**
	 * 
	 * @param userID
	 * @param gameID
	 */
	public TransferValoracion(UsuarioDTO user, JuegoDTO game) {
		this.user = user;
		this.game = game;
	}
	
	public TransferValoracion(Object[] data) {
		this.setFromStore(data);
	}

	public String[] getToStore() {
		String[] s = new String[8];
		
		s[0] = id+"";
		s[1] = user.get_user_id();
		s[2] = game.get_id();
		s[3] = titulo.getText();
		s[4] = valoracion.getText();
		s[5] = puntuacion+"";
		if(multimedia!=null)
			s[6] = multimedia.getToStore();
		else
			s[6] = "none";
		s[7] = date;
		
		return s;
	}
	
	private void setFromStore(Object[] s) {
		id = Integer.valueOf( (String) s[0] );
		
		user = new UsuarioDTO((String)s[1]);
		game = new JuegoDTO((String)s[2]);
		
		titulo = SingletonBuilderTextFactory.getSingletonInstance().createInstance(new Object[] {BuildersTextTypes.TITULO,s[3]});
		valoracion = SingletonBuilderTextFactory.getSingletonInstance().createInstance(new Object[] {BuildersTextTypes.VALORACION,s[4]});
		puntuacion = Integer.valueOf( (String) s[0] );

		if( ((String)s[6]).equals("none"))
			s[6] = "none";
		else
			multimedia = SingletonBuilderMultimediaFactory.getSingletonInstance().createInstance(((String)s[6]).split("-"));
		
		date = (String)s[7];
	}
	
	public void setTitulo(Text titulo) {
		this.titulo = titulo;
	}
	
	public void setValoracion(Text valoracion) {
		this.valoracion = valoracion;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public Text getTitulo() {
		return this.titulo;
	}
	
	public Text getValoracion() {
		return this.valoracion;
	}
	
	public int getPuntuacion() {
		return this.puntuacion;
	}
	
	public Multimedia getMultimedia() {
		return this.multimedia;
	}
	
	public UsuarioDTO getUser() {
		return this.user;
	}
	
	public JuegoDTO getGame() {
		return this.game;
	}
	
	public int getID() {
		return id;
	}

	public String getDate() {
		return this.date;
	}

	public void setID(int lastValoracion) {
		this.id = lastValoracion+1;		
	}
}
