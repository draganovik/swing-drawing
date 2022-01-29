package it68_2019.dp.drawing;

import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLightLaf;

import it68_2019.dp.drawing.controllers.DrawingController;
import it68_2019.dp.drawing.models.DrawingModel;
import it68_2019.dp.drawing.views.DrawingFrame;

public class Main {

	public static void main(String[] args) {
		FlatLightLaf.setup();
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();

		frame.getView().setModel(model);

		DrawingController controller = new DrawingController(model, frame);

		frame.setController(controller);

		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
