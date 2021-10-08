
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Main extends Application {
	
	private TextField numPeso;
	private TextField numAltura;
	
	private Label peso;
	private Label kg;
	private Label altura;
	private Label cm;
	private Label imc;
	private Label numImc;
	private Label condicion;
	
	private HBox cajaPeso;
	private HBox cajaAltura;
	private HBox cajaImc;
	private VBox root;
	
	private StringProperty strPeso = new SimpleStringProperty();
	private StringProperty strAltura = new SimpleStringProperty();
	private StringProperty strImc = new SimpleStringProperty();
	private StringProperty condicionTxt = new SimpleStringProperty();
	
	private DoubleProperty cantPeso = new SimpleDoubleProperty(0);
	private DoubleProperty cantAltura = new SimpleDoubleProperty(0);
	private DoubleProperty cantImc = new SimpleDoubleProperty(0);
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		numPeso = new TextField();
		numPeso.setMaxWidth(100);
		numPeso.setAlignment(Pos.CENTER);
		
		numAltura = new TextField();
		numAltura.setMaxWidth(100);
		numAltura.setAlignment(Pos.CENTER);
		
		peso = new Label("Peso:");
		kg = new Label("kg");
		altura = new Label("Altura:");
		cm = new Label("cm");
		imc = new Label("IMC:");
		numImc = new Label();
		condicion = new Label();
		
		cajaPeso = new HBox(5);
		cajaPeso.setAlignment(Pos.CENTER);
		cajaPeso.getChildren().addAll(peso, numPeso, kg);
		
		cajaAltura = new HBox(5);
		cajaAltura.setAlignment(Pos.CENTER);
		cajaAltura.getChildren().addAll(altura, numAltura, cm);
		
		cajaImc = new HBox(5);
		cajaImc.setAlignment(Pos.CENTER);
		cajaImc.getChildren().addAll(imc, numImc);
		
		root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(cajaPeso, cajaAltura, cajaImc, condicion);

		Scene scene = new Scene(root, 320, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("IMC");
		primaryStage.show();
		
		numPeso.textProperty().bindBidirectional(strPeso);
		numAltura.textProperty().bindBidirectional(strAltura);
		numImc.textProperty().bindBidirectional(strImc);
		condicion.textProperty().bind(condicionTxt);
		
		strImc.bindBidirectional(cantImc, new NumberStringConverter());
		strPeso.bindBidirectional(cantPeso, new NumberStringConverter());
		strAltura.bindBidirectional(cantAltura, new NumberStringConverter());
		cantImc.bind(cantPeso.divide(((cantAltura.divide(100)).multiply((cantAltura.divide(100))))));
		cantImc.addListener((o, ov, nv) -> {
			double i = nv.doubleValue();
			if (i < 18.5) {
				condicionTxt.set("Bajo Peso");
			}else if (i>=18.5 && i<25){
				condicionTxt.set("Normal");
			}else if (i>=25 && i<30) {
				condicionTxt.set("Sobrepeso");
			}else {
				condicionTxt.set("Obeso");
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}