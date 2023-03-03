/*This class adds functionality to open new windows upon clicking the add, update, delete buttons
  It also calls methods the update the database 
 */

package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ButtonHandler {

	@FXML
	private TextField idUpdate;
	@FXML
	private TextField softwareUpdate;
	@FXML
	private TextField usernameUpdate;
	@FXML
	private TextField passwordUpdate;

	@FXML
	private Label labelUpdate;
	@FXML
	private TextField idRemove;
	@FXML
	private Label labelRemove;

	@FXML
	private TextField softwareAdd;
	@FXML
	private TextField usernameAdd;
	@FXML
	private TextField passwordAdd;
	@FXML
	private Label labelAdd;

	public void addToDB(ActionEvent e) throws Exception {
		Entry entry = new Entry(softwareAdd.getText(), usernameAdd.getText(), passwordAdd.getText());
		EntryDAO.addEntry(entry);
		labelAdd.setVisible(true);
	}

	public void removeFromDB(ActionEvent e) throws Exception {
		try {
			int idIn = Integer.parseInt(idRemove.getText());
			if (EntryDAO.removeEntry(idIn) != 0) {
				labelRemove.setVisible(true);
				labelRemove.setTextFill(Color.GREEN);
				labelRemove.setText("Password Removed");

			} else {
				labelRemove.setVisible(true);
				labelRemove.setTextFill(Color.RED);
				labelRemove.setText("Entry Does Not Exist");
			}
		} catch (NumberFormatException ex) {
			labelRemove.setText("Enter numbers only");
			labelRemove.setTextFill(Color.RED);
			labelRemove.setVisible(true);
		}

	}

	public void updateDB(ActionEvent e) throws Exception {
		Entry entry = new Entry(softwareUpdate.getText(), usernameUpdate.getText(), passwordUpdate.getText());
		try {
			entry.id = Integer.parseInt(idUpdate.getText());
			EntryDAO.updateEntry(entry);
			labelUpdate.setTextFill(Color.GREEN);
			labelUpdate.setVisible(true);
			labelUpdate.setText("Update Successful");

		} catch (SQLException ex) {
			labelUpdate.setTextFill(Color.RED);
			labelUpdate.setVisible(true);
			labelUpdate.setText("Update Unsuccessful");
		} catch (NumberFormatException ex) {
			labelUpdate.setTextFill(Color.RED);
			labelUpdate.setVisible(true);
			labelUpdate.setText("Enter Numbers For ID");
		}
	}

}
