package net.leathermc.leather;

import lombok.Builder;
import lombok.Getter;

// TODO: Permissions
@Builder
public class Permission {
	@Getter
	private final Permissions perm;
	@Getter
	private final boolean enabled = true;
}
