package net.leathermc.leather;

// TODO: Permissions
public enum Permissions {
	;

	Permissions(String name, String desc) {
		// do some bit math
		id = 0b1 >> ordinal();
	}

	private long id;
	private String name;
	private String desc;
}
