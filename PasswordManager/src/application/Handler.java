/*This class handles Main.fxml, NewPassword.fxml and ForgotPassword.fxml
 */
package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Handler {

	@FXML
	private PasswordField input;
	@FXML
	private Text text;
	private Scene scene;
	private Stage stage;
	@FXML
	private TextField key;
	@FXML
	private TextField newPassword;
	@FXML
	private TextField newText;
	@FXML
	private Label label;

	public void submit(ActionEvent e) throws Exception {
		if (checkPassword(input)) {
			switchScenes(e, "Table.fxml");
		} else {
			text.setFill(Color.RED);
			text.setText("Incorrect Password");
		}
	}

	public void forgotPassword(ActionEvent e) throws Exception {
		switchScenes(e, "ForgotPassword.fxml");
	}

	public void submitKey(ActionEvent e) throws Exception {
		if (checkKey(key)) {
			switchScenes(e, "NewPassword.fxml");
		} else {
			// re enter password
			text.setFill(Color.RED);
			text.setText("Incorrect Key");
		}
	}

	public void submitNewPassword(ActionEvent e) throws Exception {
		String key = MasterPasswordDAO.generateKey();
		label.setVisible(true);
		newText.setVisible(true);
		newText.setText(key);
		if (MasterPasswordDAO.exception) {
			MasterPasswordDAO.setDetails(newPassword.getText(), key);
		}
	}

	public void returnToMain(ActionEvent e) throws IOException {
		switchScenes(e, "Main.fxml");
	}

	public void switchScenes(ActionEvent event, String fxmlName) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public boolean checkPassword(PasswordField input) throws Exception {
		boolean login = false;
		String password = Hash.toHexString(Hash.getSHA(input.getText()));
		if (password.equals(MasterPasswordDAO.getDetails().master))
			login = true;
		return login;
	}

	public boolean checkKey(TextField key) throws Exception {
		boolean login = false;
		String input = Hash.toHexString(Hash.getSHA(key.getText()));
		if (input.equals(MasterPasswordDAO.getDetails().key))
			login = true;
		return login;
	}

}
