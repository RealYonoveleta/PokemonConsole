package com.yonoveleta.pokemon.registry;

import com.yonoveleta.pokemon.status.Status;

public class StatusRegistry extends AbstractRegistry<Status> {
	
	private static final StatusRegistry INSTANCE = new StatusRegistry();
	
	private StatusRegistry() {
		super();
	}
	
	public static StatusRegistry getInstance() {
		return INSTANCE;
	}

}
