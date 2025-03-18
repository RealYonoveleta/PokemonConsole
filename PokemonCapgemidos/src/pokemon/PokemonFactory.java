package pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import type.Type;

public class PokemonFactory {
	
	private static final PokemonFactory instance = new PokemonFactory();

	private static final String DATA_FILE = "data/pokemon_data.txt";
	private static final Map<String, Pokemon> pokemonCache = new HashMap<>();
	
	public static PokemonFactory getInstance() {
		return instance;
	}

	private void loadPokemonData() {
		try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				
				if(data.length >= 8) {
					String originalName = data[0].trim();
					String nameKey = originalName.toLowerCase();
					int hp = Integer.parseInt(data[1]);
					int attack = Integer.parseInt(data[2]);
					int defense = Integer.parseInt(data[3]);
					int specialAttack = Integer.parseInt(data[4]);
					int specialDefense = Integer.parseInt(data[5]);
					int speed = Integer.parseInt(data[6]);

					List<Type> types = loadTypes(data[7]);

					pokemonCache.put(nameKey, new PokemonImpl(originalName, hp, attack, defense, specialAttack,
							specialDefense, speed, types));
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading pokemon data: " + e.getMessage());
		}
	}

	public Pokemon createPokemon(String name) {
		if (pokemonCache.isEmpty()) {
			loadPokemonData();
		}

		Pokemon pokemon = pokemonCache.get(name.toLowerCase());
		return pokemon != null ? new PokemonImpl(pokemon) : null;
	}

	private List<Type> loadTypes(String data) {
		String[] types = data.trim().split("\\|");
		return Arrays.stream(types).map(type -> Type.valueOf(type.toUpperCase())).collect(Collectors.toList());
	}

}
