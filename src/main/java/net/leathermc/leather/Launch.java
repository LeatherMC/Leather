package net.leathermc.leather;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import lombok.val;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class Launch implements ITweaker {
	private static String[] args;

	@SneakyThrows
	@Override
	public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
		val a = new ArrayList<String>(args);

		Launch.args = new String[a.size()];
		a.add("--version");
		a.add(GProps.getName() + "-" + GProps.getVersion());
		Launch.args = a.toArray(Launch.args);

		System.out.println("\nConverted args:");
		for (int i = 0; i < Launch.args.length; i++) {
			System.out.println(Launch.args[i]);
		}
		System.out.println("\nOld args:");
		for (String arg : a) {
			System.out.println(arg);
		}
	}

	@Override
	public void injectIntoClassLoader(LaunchClassLoader classLoader) {
		System.out.println("no");
	}

	@Override
	public String getLaunchTarget() {
		return "net.minecraft.client.main.Main";
	}

	@Override
	public String[] getLaunchArguments() {
		return args;
	}
}
