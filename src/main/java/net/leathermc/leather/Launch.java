package net.leathermc.leather;

import lombok.SneakyThrows;
import lombok.val;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Launch {
	@SneakyThrows
	public static void main(String[] args) {
		val loader = ClassLoader.getSystemClassLoader();
		Class<?> mc = loader.loadClass("net.minecraft.client.main.Main");
		Method method = mc.getMethod("main", String[].class);
		System.out.println(Arrays.toString(method.getParameters()));
		method.setAccessible(true);
		method.invoke(null, (Object) args);
	}
}
