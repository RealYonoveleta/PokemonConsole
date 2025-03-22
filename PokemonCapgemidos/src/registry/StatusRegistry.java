package registry;

import java.util.HashMap;
import java.util.Map;

import status.Burn;
import status.Flinch;
import status.Paralysis;
import status.Status;

public class StatusRegistry {
	
    private static final Map<String, Class<? extends Status>> statusMap = new HashMap<>();

    static {
        registerStatus("burn", Burn.class);
        registerStatus("flinch", Flinch.class);
        registerStatus("paralysis", Paralysis.class);
    }

    public static void registerStatus(String name, Class<? extends Status> statusClass) {
        statusMap.put(name, statusClass);
    }

    public static Class<? extends Status> getStatusClass(String name) {
        return statusMap.get(name);
    }
	

}
