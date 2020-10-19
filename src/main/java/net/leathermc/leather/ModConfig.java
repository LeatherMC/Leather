package net.leathermc.leather;

import lombok.Getter;

@Getter
public class ModConfig {
	private short formatVersion;
	private String name;
	private String id;
	private String description;
	private EntrypointDef main;
}
