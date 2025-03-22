package parse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import move.Move;
import move.MoveImpl;

public class MoveParser extends AbstractParser<Move> {

	private String moveFile;
	
	public MoveParser(String moveFile) {
		this.moveFile = moveFile;
	}

	@Override
	public List<Move> parse() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
	    // Parse the JSON from the file into a List of MoveImpl objects
	    List<Move> moves = gson.fromJson(new FileReader(moveFile), new TypeToken<List<MoveImpl>>() {}.getType());
	    
	    return moves;
	}

}
