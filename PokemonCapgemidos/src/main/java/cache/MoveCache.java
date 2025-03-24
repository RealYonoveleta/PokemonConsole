package cache;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import move.Move;
import parse.MoveParser;

public class MoveCache {
	
    private static MoveCache instance = new MoveCache();
    private static MoveParser moveParser;
    private Map<String, Move> moveMap;

    private MoveCache() {
        moveMap = new HashMap<>();
        moveParser = new MoveParser("data/moves.json");
        loadMovesFromParser();
    }

    public static MoveCache getInstance() {
        return instance;
    }

    // Method to add moves to the cache
    public void addMove(String moveName, Move move) {
        moveMap.put(moveName, move);
    }

    // Method to retrieve a move by name
    public Move getMove(String moveName) {
        return moveMap.get(moveName);
    }

    // Method to check if a move exists
    public boolean hasMove(String moveName) {
        return moveMap.containsKey(moveName);
    }
    
 // Method to get all moves
    public Set<Move> getAllMoves() {
        return new HashSet<>(moveMap.values());
    }


    // Method to load moves from a JSON file or parser
    public void loadMovesFromParser() {
    	try {
    		List<Move> moves = moveParser.parse();
            for (Move move : moves) {
                addMove(move.getName(), move);
            }
    	} catch(JsonIOException e) {
    		e.printStackTrace();
    	} catch(JsonSyntaxException e) {
    		e.printStackTrace();
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
}

