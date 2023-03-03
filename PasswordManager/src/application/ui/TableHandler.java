/* This is the controller class for TableHandler.fxml. This 
   class displays the values in the table and adds functionality to the add, update, delete, refresh buttons.
 */

package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TableHandler {

	@FXML
	private TableColumn<Entry, Integer> colId;
	@FXML
	private TableColumn<Entry, String> colSoftware;
	@FXML
	private TableColumn<Entry, String> colUsername;
	@FXML
	private TableColumn<Entry, String> colPassword;
	@FXML
	private TableView<Entry> entryTable;

	public void initialize() throws Exception {
		colId.setCellValueFactory(cellData -> cellData.getValue().getID2().asObject());
		colSoftware.setCellValueFactory(cellData -> cellData.getValue().getSoftware2());
		colUsername.setCellValueFactory(cellData -> cellData.getValue().getUsername2());
		colPassword.setCellValueFactory(cellData -> cellData.getValue().getPassword2());
		ObservableList<Entry> entryList = EntryDAO.getRecords();
		populateTable(entryList);
	}

	public void populateTable(ObservableList<Entry> entryList) {
		entryTable.setItems(entryList);
	}

	public void refreshTable(ActionEvent e) throws Exception {
		ObservableList<Entry> entryList = EntryDAO.getRecords();
		populateTable(entryList);
	}

	public void addPassword(ActionEvent e) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("AddPassword.fxml"));
		Stage stage1 = new Stage();
		Scene scene = new Scene(root);
		stage1.setTitle("Add Password");
		stage1.setScene(scene);
		stage1.setResizable(false);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.show();
	}

	public void removePassword(ActionEvent e) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("RemovePassword.fxml"));
		Stage stage1 = new Stage();
		Scene scene = new Scene(root);
		stage1.setTitle("Remove Password");
		stage1.setScene(scene);
		stage1.setResizable(false);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.show();
	}

	public void updatePassword(ActionEvent e) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("UpdatePassword.fxml"));
		Stage stage1 = new Stage();
		Scene scene = new Scene(root);
		stage1.setTitle("Update Password");
		stage1.setScene(scene);
		stage1.setResizable(false);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.show();
	}

}
