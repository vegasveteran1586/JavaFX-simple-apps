import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Javafx extends Application
{
	private final static int DEFAULT_WIDTH  = 250;
	private final static int DEFAULT_HEIGHT = 300;
	private final static String TITLE = "ColorsApp";
	private final static Color BG_COLOR = Color.BLACK;
	
	private Slider sliderRed;
	private Slider sliderGreen;
	private Slider sliderBlue;
	private Text textRed;
	private Text textGreen;
	private Text textBlue;
	
	private Rectangle rect;
	
	private Text textHex;
	String hexColorValue = "#ffffff";
	
	@Override public void start(Stage stage) throws Exception
	{
		Group root = new Group();
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT, BG_COLOR);
		
		textRed = createText("Red: 255", 160, 40);
		textGreen = createText("Green: 255", 160, 60);
		textBlue = createText("Red: 255", 160, 80);

		textHex = createText("Hex: " + hexColorValue, 20 , 240);
		
		sliderRed = createSlider(10, 30);
		sliderGreen = createSlider(10, 50);
		sliderBlue = createSlider(10, 70);
			
		rect = new Rectangle();
		rect.setX(20);
		rect.setY(100);
		rect.setWidth(120);
		rect.setHeight(120);
		rect.setFill(Color.WHITE);
		
		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			
			@Override public void handle(KeyEvent event) {
				
				if (new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN).match(event)) {
					
					final Clipboard clipboard = Clipboard.getSystemClipboard();
					final ClipboardContent content = new ClipboardContent();
			     		content.putString(hexColorValue);
			     		clipboard.setContent(content);
				}
			}
		});
		
		root.getChildren().addAll(sliderRed, sliderGreen, sliderBlue, textRed, textGreen, textBlue, rect, textHex);
		
		stage.setMaxWidth(DEFAULT_WIDTH);
		stage.setMaxHeight(DEFAULT_HEIGHT);

		stage.setScene(scene);
		stage.setTitle(TITLE);
		stage.show();
	}
	
	private Text createText(String name, int layoutX, int layoutY) {

		Text tempText = new Text(name);
		tempText.setLayoutX(layoutX);
		tempText.setLayoutY(layoutY);
		tempText.setFill(Color.WHITE);
		return tempText;
	}
	
	private Slider createSlider(int layoutX, int layoutY)
	{
		Slider tempSlider = new Slider(0, 255, 255);
		tempSlider.setLayoutX(layoutX);
		tempSlider.setLayoutY(layoutY);
		tempSlider.setBlockIncrement(1);
		
		tempSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				
				rect.setFill(Color.rgb
					(
						(int)sliderRed.getValue(),
						(int)sliderGreen.getValue(),
						(int)sliderBlue.getValue()
					)
				);
				textRed.setText("Red: " + (int)sliderRed.getValue());
				textGreen.setText("Green: " + (int)sliderGreen.getValue());
				textBlue.setText("Blue: " + (int)sliderBlue.getValue());

				textHex.setText("Hex: " +
					(hexColorValue = "#" + String.format
					 	(
							"%02x%02x%02x",
              (int)sliderRed.getValue(), 
							(int)sliderGreen.getValue(),
              (int)sliderBlue.getValue()
						)
					)
				);
			}
		});
		return tempSlider;
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
