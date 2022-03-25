package it68_2019.dp.drawing;

import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLightLaf;

public class Main {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		ApplicationFrame frame = new ApplicationFrame();

		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
