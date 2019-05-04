package Biblioteca.Control;

import org.json.JSONArray;
import org.json.JSONObject;

public class JuegoEnPropiedadDAOJSON implements JuegoEnPropiedadDAO{
	public JSONObject JuegoEnPropiedadToJSON(JuegoEnPropiedadDTO game) {
		
		JSONObject gameDetails = new JSONObject();

		gameDetails.put("_id", game.get_id());
		gameDetails.put("_title", game.get_title());
		gameDetails.put("_price", game.get_price());
		gameDetails.put("_pegi", game.get_pegi());
		gameDetails.put("_descLong", game.get_descLong());
		gameDetails.put("_descShort", game.get_descShort());
		gameDetails.put("_date", game.get_date());
		gameDetails.put("_version", game.get_version());
		gameDetails.put("_versionNotes", game.get_notes());
		JSONArray genres = new JSONArray();
		genres.put(game.get_genres());
		JSONArray achievements = new JSONArray();
		achievements.put(game.get_achievements());
		gameDetails.put("_genres", genres);
		gameDetails.put("_achievements", achievements);
		gameDetails.put("_hoursPlayed", game.get_hoursPlayed());
		gameDetails.put("_lastEx", game.get_lastEx());
		gameDetails.put("_actVers", game.get_actVersion());
		gameDetails.put("_installed", game.is_installed());
		
		return gameDetails;
	}
}
