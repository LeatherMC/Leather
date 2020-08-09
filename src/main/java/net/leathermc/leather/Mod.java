package net.leathermc.leather;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Mod {
	@Getter
	private final String name;
	@Getter
	private final String id;
	@Getter
	private final String description;
	@Getter
	private final long perms;
	@Getter @Setter @Builder.Default
	private boolean enabled = true;
}
