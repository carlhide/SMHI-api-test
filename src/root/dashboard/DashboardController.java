package root.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import root.RootView;

public class DashboardController {
	
	private DashboardView dbv;
	//---------FXML---------//
	@FXML
	private Text velocityText, directionText;
	@FXML
	private JFXButton updateButton;
	@FXML
	private JFXTextField stationField;
	
	
	public DashboardController() {
		
		
		
	}
	
	public void initialize() {
		updateButton.setOnAction(e->{
			System.out.println(stationField.getText());
			dbv.pullNewJsonFromStation(stationField.getText());
		});
	}
	
	public void setView(DashboardView dbv) {
		this.dbv = dbv;
	}
	
	public void setVelocityText(String t) {
		velocityText.setText(t);
	}
	
	public void setDirectionText(String t) {
		directionText.setText(t);
	}

}
