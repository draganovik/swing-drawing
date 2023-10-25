package drawing;

import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		ApplicationFrame frame = new ApplicationFrame();

		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
