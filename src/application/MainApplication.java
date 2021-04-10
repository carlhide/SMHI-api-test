package application;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import root.RootController;
import root.RootView;

/**
 * 
 * @author carlhidestal
 *
 */
public class MainApplication extends Application {

	private static final String TITLE = "CRM TREE";
	private static final String EXEC_MESSAGE = "1. Launching application.Main \t - \t start()";
	protected double yOffset;
	protected double xOffset;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//------ Teststage ------//
		
		
		Stage testStage = new Stage();
		AnchorPane testRoot = new AnchorPane();
		Scene testScene = new Scene(testRoot, 1000,600);
//		Scene testScene = new Scene(testRoot);

		testStage.setScene(testScene);
		testStage.setTitle("SMHI API");
		testStage.show();
		
		makeWindowDraggable(testRoot, testStage);
		RootView rView = new RootView();
		rView.loadGUI(testRoot);

	}

	private void makeWindowDraggable(Pane root, Stage primaryStage) {
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = primaryStage.getX() - event.getScreenX();
				yOffset = primaryStage.getY() - event.getScreenY();
			}
		});

		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() + xOffset);
				primaryStage.setY(event.getScreenY() + yOffset);
			}
		});

		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				root.setFocusTraversable(true);
			}

		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}