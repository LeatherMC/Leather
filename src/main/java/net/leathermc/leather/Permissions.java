package net.leathermc.leather;

// TODO: Permissions
public enum Permissions {
	LOAD("Load Mods/Resources", "Load other mods/resources"),
	RELOAD("Reload Mods/Resources", "Reload the mod/resources"),
	;

	Permissions(String name, String desc) {
		// do some bit math
		id = 0b1 >> ordinal();
	}

	private long id;
	private String name;
	private String desc;
}
