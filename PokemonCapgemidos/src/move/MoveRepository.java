package move;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import type.Type;

public class MoveRepository {
	
	private static final String DATA_FILE = "data/move_data.txt";
	private static final Map<String, Move> moveCache = new HashMap<>();

	private static void loadMoveData() {
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				
				if(data.length >= 5) {
					String originalName = data[0].trim();
					String nameKey = originalName.toLowerCase();
					int power = Integer.parseInt(data[1]);
					int pps = Integer.parseInt(data[2]);
					Type type = Type.valueOf(data[3].toUpperCase());
					MoveType moveType = MoveType.valueOf(data[4].toUpperCase());
					
					moveCache.put(nameKey, new MoveImpl(originalName, power, pps, type, moveType));
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading pokemon data: " + e.getMessage());
		}
	}
	
	public static Move getMove(String name) {
		if (moveCache.isEmpty()) {
			loadMoveData();
		}

		return moveCache.getOrDefault(name.toLowerCase(), null);
	}

}
