package application;

import java.io.FileNotFoundException;

import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	public static JSONReader json = new JSONReader();
	public static CSVReader csv = new CSVReader();
	public static TableView<eachRowBean> table = new TableView();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException, ParseException, java.text.ParseException {
		csv.ReadCSV();
		json.ReadJSON();


		TableColumn<eachRowBean, String> row1 = new TableColumn<>("OrderDate");
		row1.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

		TableColumn<eachRowBean, String> row2 = new TableColumn<>("Region");
		row2.setCellValueFactory(new PropertyValueFactory<>("Region"));

		TableColumn<eachRowBean, String> row3 = new TableColumn<>("Rep1");
		row3.setCellValueFactory(new PropertyValueFactory<>("Rep1"));

		TableColumn<eachRowBean, String> row4 = new TableColumn<>("Rep2");
		row4.setCellValueFactory(new PropertyValueFactory<>("Rep2"));

		TableColumn<eachRowBean, String> row5 = new TableColumn<>("Item");
		row5.setCellValueFactory(new PropertyValueFactory<>("Item"));

		TableColumn<eachRowBean, String> row6 = new TableColumn<>("Units");
		row6.setCellValueFactory(new PropertyValueFactory<>("Units"));

		TableColumn<eachRowBean, String> row7 = new TableColumn<>("UnitCost");
		row7.setCellValueFactory(new PropertyValueFactory<>("UnitCost"));

		TableColumn<eachRowBean, String> row8 = new TableColumn<>("Total");
		row8.setCellValueFactory(new PropertyValueFactory<>("Total"));

		table.getColumns().addAll(row1, row2, row3, row4, row5, row6, row7, row8);

		Button jsonButton = new Button();
		jsonButton.setText("Show JSON");
		jsonButton.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				ObservableList<eachRowBean> jsonData = FXCollections.observableArrayList(json.jsonRows);
				table.setItems(jsonData);

			}
		});

		Button csvButton = new Button();
		csvButton.setText("Show CSV");
		csvButton.setOnAction(new EventHandler() {

			@Override
			public void handle(Event arg0) {
				ObservableList<eachRowBean> csvData = FXCollections.observableArrayList(csv.rows);
				table.setItems(csvData);

			}
		});

		VBox vbox = new VBox();
		vbox.getChildren().add(table);
		vbox.getChildren().addAll(jsonButton, csvButton);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
