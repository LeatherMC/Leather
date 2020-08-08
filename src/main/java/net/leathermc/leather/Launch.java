package net.leathermc.leather;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import joptsimple.OptionParser;
import lombok.SneakyThrows;
import lombok.val;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class Launch implements ITweaker {
	private List<String> args;

	@SneakyThrows
	@Override
	public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
		this.args = new ArrayList<>(args);
		this.args.add("--version");
		this.args.add(GProps.getName() + "-" + GProps.getVersion());

		val parser = new OptionParser();
		parser.allowsUnrecognizedOptions();
		val nonOption = parser.nonOptions();
		val options = parser.parse(String.valueOf(args));

		System.out.println("\nArguments:");
		for (String s : this.args) {
			System.out.println(s);
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
		return this.args.toArray(new String[this.args.size()]);
	}
}
