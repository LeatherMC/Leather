package net.leathermc.leather;

import joptsimple.OptionParser;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;
import java.lang.reflect.Method;

public class Launch {
	@SneakyThrows
	public static void main(String[] args) {
		// Handle args
		//  Parse args/options
		val parser = new OptionParser();
		parser.allowsUnrecognizedOptions();
		val gameDirOpt = parser.accepts("gameDir").withRequiredArg().ofType(File.class);
		val options = parser.parse(args);
		val gameDir = (File)options.valueOf(gameDirOpt);

		// Load Minecraft
		//  Get class loader
		val loader = ClassLoader.getSystemClassLoader();
		//  Main class
		Class<?> mc = loader.loadClass("net.minecraft.client.main.Main");
		//  Main method
		Method method = mc.getMethod("main", String[].class);
		//  Make main method accessible
		method.setAccessible(true);
		//  Invoke main method
		method.invoke(null, (Object) args);

		// Load Mods
		val gameDirList = gameDir.listFiles();
		if (gameDirList != null) {
			for (File file : gameDirList) {
				if (file.isDirectory() && file.getName().equals("mods")) {
					val modsList = file.listFiles();
					if (modsList != null) {
						for (File modFile : modsList) {
							if (modFile.getName().endsWith(".jar") && modFile.isFile()) {
								//Mod.builder().enabled(true).id(/* mod id */).name(/* mod name */).perms(0).build();
							}
						}
					}
				}
			}
		}
	}
}
