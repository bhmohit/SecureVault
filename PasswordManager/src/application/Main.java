package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("PasswordManager/src/application/ui/Main.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Password Manager");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
