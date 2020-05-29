package com.rabbitmq.client.vo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Machine {
	
	COFFEEMACHINE,
	BLENDER,
	NON;
	
	private static final List<Machine> VALUES = (List<Machine>) Collections.unmodifiableList(Arrays.asList(values()));
	
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static String randomBeforeType() {
		return VALUES.get(RANDOM.nextInt(SIZE)).toString();
	}
}
