/**
 * From https://gist.github.com/madan712/4509039
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {	
		Pane root = new Pane();

		createText(root);
		createTextField(root);
		createPingButton(root);

		Scene scene = new Scene(root, 275, 250);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void createText(Pane root) {
		Text one = new Text("1st Domain:");
		one.setLayoutX(10);
		one.setLayoutY(45);
		one.setFont(Font.font ("Helvetica", 16));

		Text two = new Text("2nd Domain:");
		two.setLayoutX(10);
		two.setLayoutY(85);
		two.setFont(Font.font ("Helvetica", 16));

		Text three = new Text("3rd Domain:");
		three.setLayoutX(10);
		three.setLayoutY(125);
		three.setFont(Font.font ("Helvetica", 16));

		root.getChildren().addAll(one, two, three);
	}
	
	public void createTextField(Pane root) {
		TextField domainOne = new TextField();
		domainOne.setMaxWidth(200);
		domainOne.setLayoutX(115);
		domainOne.setLayoutY(30);

		TextField domainTwo = new TextField();
		domainTwo.setMaxWidth(200);
		domainTwo.setLayoutX(115);
		domainTwo.setLayoutY(70);

		TextField domainThree = new TextField();
		domainThree.setMaxWidth(200);
		domainThree.setLayoutX(115);
		domainThree.setLayoutY(110);
	
		root.getChildren().addAll(domainOne, domainTwo, domainThree);
		
	}
	
	public void createPingButton(Pane root) {
		
		Button btn = new Button("Start Test");	 
		btn.setLayoutX(200);
		btn.setLayoutY(150);

		setPingBtnOnClick(root, btn);

		root.getChildren().add(btn);
	}

	public void setPingBtnOnClick(Pane root, Button btn) {

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
