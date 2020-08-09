package net.leathermc.leather;

public interface ModInitializer {
	default void onClientInitialize() {}
	default void onServerInitialize() {}
	default void onInitialize() {}
}
