package movepool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import move.Move;
import move.MoveRepository;

public class MovePoolRepository {
	
	private static final MoveRepository moveRepository = MoveRepository.getInstance();

	private static final MovePoolRepository instance = new MovePoolRepository();
	private final Map<String, List<MoveLevel>> moveList = new HashMap<>();

	private MovePoolRepository() {
		loadMoveData();
	}

	public static MovePoolRepository getInstance() {
		return instance;
	}

	private void loadMoveData() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/movepool_data.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(":");
				String pokemonName = parts[0];
				List<MoveLevel> moves = new ArrayList<>();

				for (String moveEntry : parts[1].split(",")) {
					String[] moveParts = moveEntry.split("-");
					Move move = moveRepository.getMove(moveParts[0]);
					int level = Integer.parseInt(moveParts[1]);
					moves.add(new MoveLevel(move, level));
				}

				moveList.put(pokemonName, moves);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<MoveLevel> getMovesForPokemon(String pokemonName) {
		return moveList.getOrDefault(pokemonName, new ArrayList<>());
	}
}
