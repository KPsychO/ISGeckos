package Biblioteca.Control;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Usuario.Control.UsuarioDTO;

public class BibliotecaDTO {

	private static BibliotecaControllerFacade _bc;
	
	private String _userId;
	private List<JuegoEnPropiedadDTO> _juegosEnBiblioteca = new ArrayList<JuegoEnPropiedadDTO>();
	@SuppressWarnings("unused")
	private List<BibliotecaDTO> _bibliotecas;
	
	public BibliotecaDTO(List<JuegoEnPropiedadDTO> games) {
		this._userId = "Generic";
		this._juegosEnBiblioteca = games;
	}
	
	public BibliotecaDTO(List<JuegoEnPropiedadDTO> games, UsuarioDTO user) {
		this._userId = user.get_user_id();
		this._juegosEnBiblioteca = games;
	}
	
	public BibliotecaDTO(BibliotecaDTO dto) {
		this._userId = dto.get_userId();
		this._juegosEnBiblioteca = dto.get_juegosEnBiblioteca();
	}
	
	public BibliotecaDTO(UsuarioDTO dto) {
		_userId = dto.get_user_id();
		createJuegosEnBiblioteca();
	}

	public BibliotecaDTO(JSONObject biblioteca) {

		_userId = biblioteca.getString("_userId");
		_juegosEnBiblioteca = _bc.getOwnedGames(biblioteca.getJSONArray("_gamesList"));
	}

	public String get_userId() {
		return _userId;
	}

	@SuppressWarnings("static-access")
	private void createJuegosEnBiblioteca() {
		List<JuegoEnPropiedadDTO> _listOwnedGames = new ArrayList <JuegoEnPropiedadDTO>();
		
		_listOwnedGames = this._bc.getOwnedGames(this._userId);
		
		for(JuegoEnPropiedadDTO i : _listOwnedGames)
			this._juegosEnBiblioteca.add(i);		
	}
	
	public JSONArray juegosEnBibliotecaJSON() {
		JSONArray arr = new JSONArray();
		
		for(JuegoEnPropiedadDTO i : this._juegosEnBiblioteca) {
			arr.put(i.JuegoEnPropiedadToJSON());
		}
		return null;
	}
	
	public List<JuegoEnPropiedadDTO> get_juegosEnBiblioteca() {
		return _juegosEnBiblioteca;
	}
	
	public JuegoEnPropiedadDTO getJuego(JuegoEnPropiedadDTO juego) {
		for (JuegoEnPropiedadDTO i : this._juegosEnBiblioteca)
			if (i.get_id().equals(juego.get_id()))
				return i;
		return null;
	}
	
	public void anadirJuego(JuegoEnPropiedadDTO juego) {
		this._juegosEnBiblioteca.add(juego);
	}
	
	public void instalarJuego(JuegoEnPropiedadDTO juego) {
		for(JuegoEnPropiedadDTO i : this._juegosEnBiblioteca)
			if(i.get_id().equals(juego.get_id())) {
				i.set_installed(true);
				i.set_actVersion(i.get_version());
				System.out.println("Instalado juego: " + i.get_title());
			}
	}

	public void actualizarJuego(JuegoEnPropiedadDTO juego) {
		for(JuegoEnPropiedadDTO i : this._juegosEnBiblioteca)
			if(i.get_id().equals(juego.get_id())) {
				i.set_actVersion(i.get_version());
				System.out.println("Actualizado juego: " + i.get_title());
				break;
			}
	}

	public void ejecutarJuego(JuegoEnPropiedadDTO juego) {
		for(JuegoEnPropiedadDTO i : this._juegosEnBiblioteca)
			if(i.get_id() == juego.get_id()) {
				i.set_hoursPlayed(i.get_hoursPlayed() + 1);
				i.set_lastEx("now");
			}		
	}
	
}
