package com.dreaminsteam;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import com.dreaminsteam.ledcontroller.ColorFader;
import com.dreaminsteam.ledcontroller.MJSLedController;

/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details. */

public class SimpleLoopFader {

	public static final int SECONDS_TO_FADE = 4;
	
	public static void main(String[] args){
		List<MJSLedController> connectedControllers = MJSLedController.getConnectedControllers();
		Optional<MJSLedController> anyController = connectedControllers.stream().findAny();
		
		anyController.ifPresent((controller) -> {
			ColorFader fader = new ColorFader(controller);
			List<Color> randomColorList = ColorFader.getRandomColorList();
			fader.colorCycle(randomColorList, SECONDS_TO_FADE, true);
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					fader.stop();
				}
			});
		});
	}
}
