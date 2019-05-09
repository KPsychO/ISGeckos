package Juego.Control;

public class SingletonJuegoDAO {
	private static JuegoDAO game;
	
	private static void createInstance() {
		game = new JuegoDAOJSON();
	}
	
	public static JuegoDAO  getInstance() {
		if(game == null)
			createInstance();
		return game;
	}
}
