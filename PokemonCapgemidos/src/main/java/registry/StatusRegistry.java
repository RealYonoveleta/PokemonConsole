package registry;

import status.Status;

public class StatusRegistry extends AbstractRegistry<Status> {
	
	private static final StatusRegistry INSTANCE = new StatusRegistry();
	
	private StatusRegistry() {
		super();
	}
	
	public static StatusRegistry getInstance() {
		return INSTANCE;
	}

}
