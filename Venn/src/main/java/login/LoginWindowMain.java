package login;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindowMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent mainNode = FXMLLoader.load(getClass().getResource("/views/LoginWindowView.fxml"));
		primaryStage.setScene(new Scene(mainNode));
		primaryStage.show();
		primaryStage.toFront();
	}

	public static void main(String[] args) {
		launch(args);
	}
}