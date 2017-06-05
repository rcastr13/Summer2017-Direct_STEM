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

/**
 * Continued JavaFX implementations.
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Thread and File IO implementations.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage){	
		/**
		 * The box which contains everything.
		 */
		Pane root = new Pane();
		
		/**
		 * Creates the layout of the form using
		 * the root.
		 */
		createText(root);
		createTextField(root);
		createPingButton(root);
		
		/**
		 * Area to place the form.
		 */
		Scene scene = new Scene(root, 300, 200);
		
		/**
		 * Sets the scene to be shown on the pane.
		 */
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Texts.
	 */
	public void createText(Pane root){
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
		
		/**
		 * Places all text onto pane.
		 */
		root.getChildren().addAll(one, two, three);
	}
	
	/**
	 * Input fields.
	 */
	public void createTextField(Pane root){
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

		/**
		 * Places all text fields onto pane.
		 */
		root.getChildren().addAll(domainOne, domainTwo, domainThree);
	}
	
	/**
	 * Button.
	 */
	public void createPingButton(Pane root){
		Button btn = new Button("Start Test");	 
		btn.setLayoutX(200);
		btn.setLayoutY(150);
		
		SetPingBtnOnClick btnClick = new SetPingBtnOnClick();

//		btnClick(domainOne.getText());
//		btnClick(domainTwo.getText());
//		btnClick(domainThree.getText());

		//setPingBtnOnClick(root, btn);

		btn.setOnAction(btnClick);

		/**
		 * Places button onto pane.
		 */
		root.getChildren().add(btn);
	}
	
	///**
	// * Event handler.
	// */
	//public void setPingBtnOnClick(Pane root, Button btn) {
	//	HandlerClass handler = new HandlerClass();
	//
	//	btn.setOnAction(new EventHandler<ActionEvent>(){
	//		@Override
	//		public void handle(ActionEvent e){
	//			//Code here.
	//		}
	//	});
	//}
	
	public static void main(String[] args){
		launch(args);
	}
}

/**
 * Event handler.
 */
class SetPingBtnOnClick implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent e){
		/*
		 * Take in the domain names and save to each respective file name.
		 */
		runSystemCommand(domainOne.getText(), "domain1.txt");
		runSystemCommand(domainTwo.getText(), "domain2.txt");
		runSystemCommand(domainThree.getText(), "domain3.txt");
	}
	
	//FIXME Figure out what is wrong here.
	public static void runSystemCommand(String command, String fileName){
		new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					/*
					 * Take 3 domains and start ping simultaneously.
					 */
					Process p = Runtime.getRuntime().exec(command);
					BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

					String s = "";

					while((s = br.readLine()) != null){
						writeToFiles(s, fileName);
					}
				}catch(IOException e){
					System.out.println(e);
				}
			}
		}).start();
	}

	public static void writeToFile(String s, String fileName){
		try{
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);

			out.println(s);
			out.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
}
