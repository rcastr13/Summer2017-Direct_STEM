package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	
	TextField domainOne = new TextField();
	TextField domainTwo = new TextField();
	TextField domainThree = new TextField();
	
	PingIP pingTest = new PingIP();
	
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
		one.setFont(Font.font ("helvetica", 16));
		
		Text two = new Text("2nd Domain:");
		two.setLayoutX(10);
		two.setLayoutY(85);
		two.setFont(Font.font ("helvetica", 16));
		
		Text three = new Text("3rd Domain:");
		three.setLayoutX(10);
		three.setLayoutY(125);
		three.setFont(Font.font ("helvetica", 16));
		
		root.getChildren().addAll(one, two, three);
	
	}
	
	public void createTextField(Pane root) {
		
		domainOne.setMaxWidth(200);
		domainOne.setLayoutX(115);
		domainOne.setLayoutY(30);
		
		
		domainTwo.setMaxWidth(200);
		domainTwo.setLayoutX(115);
		domainTwo.setLayoutY(70);
		
		domainThree.setMaxWidth(200);
		domainThree.setLayoutX(115);
		domainThree.setLayoutY(110);
	
		root.getChildren().addAll(domainOne, domainTwo, domainThree);
		
	}
	
	public void createPingButton(Pane root) {
		
		Button btn = new Button("Start Test");	 
		btn.setStyle("-fx-font:	16 helvetica; -fx-base: green;");
		btn.setLayoutX(175);
		btn.setLayoutY(150);
		
		setPingBtnOnClick(root, btn);
		
		root.getChildren().add(btn);
	}
	
	public void setPingBtnOnClick(Pane root, Button btn) {
		
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				
				String one = domainOne.getText();
				String two = domainTwo.getText();
				String three = domainThree.getText();
				
				pingTest.setDomainOne(one);
				pingTest.setDomainTwo(two);
				pingTest.setDomainThree(three);
				
				stopScreen(root);
			}
		});
	}
	
	public void stopScreen(Pane root) {
		
		root.getChildren().clear();
		
		createStopButton(root);
		
		pingTest.runSystemCommand("ping " + pingTest.getDomainOne() + ".com -t", pingTest.getDomainOne() + ".txt");
		pingTest.runSystemCommand("ping " + pingTest.getDomainTwo() + ".com -t", pingTest.getDomainTwo() + ".txt");
		pingTest.runSystemCommand("ping " + pingTest.getDomainThree() + ".com -t", pingTest.getDomainThree() + ".txt");
		
	}
	
	public void createStopButton(Pane root) {
		Button btn = new Button("Stop");
		btn.setStyle("-fx-font:	24 helvetica; -fx-base: red;");
		
		btn.setLayoutX(105.5);
		btn.setLayoutY(100);
		
		stopBtnOnClick(root, btn);
		
		root.getChildren().add(btn);
	}
	
	public void stopBtnOnClick(Pane root, Button btn) {
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				pingTest.setStop(true);
				showGraph();			
			}
		});
	}
	
	public void showGraph() {
		Stage stage = new Stage();
		stage.setTitle("Ping Graph");
		
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		xAxis.setLabel("Time (Secs)");
		
		final LineChart<Number, Number> lineChart =
				new LineChart<Number, Number>(xAxis, yAxis);
		
		lineChart.setTitle("Stuff");
		XYChart.Series series1 = new XYChart.Series();
		XYChart.Series series = new XYChart.Series();
		series.setName("More stuff");
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series1.getData().add(new XYChart.Data(3, 15));
        series1.getData().add(new XYChart.Data(4, 24));
        series1.getData().add(new XYChart.Data(5, 34));
        series1.getData().add(new XYChart.Data(6, 36));
        series1.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        
        Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().addAll(series, series1);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
