package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class User_ownedGames {

	public User_ownedGames() {
		
		List<String> gamelist = new ArrayList<String>();
		List<String> userlist = new ArrayList<String>();

		try {
			InputStream input = new FileInputStream("./src/resources/Games.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				gamelist.add(oJ.getString("_id"));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			InputStream input = new FileInputStream("./src/resources/Users.txt");
			JSONArray jsonInput = new JSONArray(new JSONTokener(input));
			for (Object o : jsonInput) {
				
				JSONObject oJ = new JSONObject(new JSONTokener(o.toString()));
				userlist.add(oJ.getString("_user_id"));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JSONObject owned = new JSONObject();
		
		for (String u : userlist) {
			
			List<String> ownedbyu = new ArrayList<String>();
			
			for (int i = 0; i < (int)(Math.random() * 50); i++) {
				
				ownedbyu.add(gamelist.get(i));				
				
			}
			
			owned.put(u, ownedbyu);
			
		}
		
		System.out.println(owned);

	}

}
