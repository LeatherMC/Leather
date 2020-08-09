package net.leathermc.leather;

import com.google.gson.Gson;
import joptsimple.OptionParser;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;

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

		// Load Mods
		val gameDirList = gameDir.listFiles();
		if (gameDirList != null) {
			for (File file : gameDirList) {
				if (file.isDirectory() && file.getName().equals("mods")) {
					val modsList = file.listFiles();
					if (modsList != null) {
						for (File modFile : modsList) {
							if (modFile.getName().endsWith(".jar") && modFile.isFile()) {
								val url = modFile.toURI().toURL();

								// Load mod jar
								val cl = (URLClassLoader)ClassLoader.getSystemClassLoader();
								val urlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
								urlMethod.setAccessible(true);
								urlMethod.invoke(cl, url);
								System.out.println(url);

								val ucl = new URLClassLoader(new URL[]{url});
								//  read config file
								val modConfig = ucl.getResourceAsStream("leather.mod.json");
								if (modConfig != null) {
									String configText = IOUtils.toString(modConfig, Charset.defaultCharset());

									//  parse config file
									val config = new Gson().fromJson(configText, ModConfig.class);

									//  load mod
									try {
										val modInit = (ModInitializer)ucl.loadClass(config.getMainClass()).newInstance();
										modInit.onInitialize();
										// TODO: Set up server-side and client-side initializers
									} catch (Exception e) {
										if (e.getClass() == ClassNotFoundException.class) {
											throw new ClassNotFoundException("Could not find mod initializer class: " + config.getMainClass());
										}
										throw e;
									}
									val mod = Mod.builder().enabled(true).id(config.getId()).name(config.getName()).description(config.getDescription()).perms(0).build();
									System.out.println(mod.getId());
									System.out.println(mod.getName());
									System.out.println(mod.getDescription());
								}
							}
						}
					}
				}
			}
		}

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
	}
}
