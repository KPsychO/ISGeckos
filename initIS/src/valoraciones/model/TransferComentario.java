package valoraciones.model;



import Juego.Control.JuegoDTO;
import Usuario.Control.UsuarioDTO;
import valoraciones.factories.BuildersTextTypes;
import valoraciones.factories.SingletonBuilderTextFactory;
import valoraciones.model.text.Text;

public class TransferComentario {
	private int id;
	private int valoracionID;
	private Text comentario;
	private UsuarioDTO user;
	private JuegoDTO game;
	private String date;
	
	public TransferComentario(int id, int valoracionID, Text comentario, UsuarioDTO user, JuegoDTO game, String date) {
		this.id = id;
		this.valoracionID = valoracionID;
		this.comentario = comentario;
		this.user = user;
		this.game = game;
		this.date = date;
	}
	
	public TransferComentario(int valoracionID, UsuarioDTO user, JuegoDTO game) {
		this.valoracionID = valoracionID;
		this.user = user;
		this.game = game;
	}
	
	public TransferComentario(Object[] data) {
		this.setFromStore(data);
	}

	private void setFromStore(Object[] s) {
		id = Integer.valueOf( (String)s[0] );
		valoracionID =  Integer.valueOf( (String)s[1] );
		comentario = SingletonBuilderTextFactory.getSingletonInstance().createInstance(new Object[] {BuildersTextTypes.COMENTARIO, s[2] });

		user = new UsuarioDTO((String)s[3]);
		game = new JuegoDTO((String)s[4]);

		date = (String)s[5];
	}
	
	public String[] getToStore() {
		String[] s = new String[6];
		
		s[0] = id+"";
		s[1] = valoracionID+"";
		s[2] = comentario.getText();
		s[3] = user.get_user_id();
		s[4] = game.get_id();
		s[5] = date;
		
		return s;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setComentario(Text comentario) {
		this.comentario = comentario;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public Text getComentario() {
		return this.comentario;
	}
	
	public UsuarioDTO getUser() {
		return this.user;
	}
	
	public JuegoDTO getGame() {
		return this.game;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getValoracionID() {
		return this.valoracionID;
	}
	
	public int getID() {
		return this.id;
	}
}
