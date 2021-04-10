package root.dashboard;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import application.Controller;
import application.JSONParse;
import application.Viewable;
import javafx.scene.layout.Pane;
import application.JSONParse;

public class DashboardView extends Viewable<DashboardController> {

	private static final String FXML_PATH = "Dashboard.fxml";
	private Pane parent;

	public DashboardView() throws IOException {

	}

	/**
	 * 
	 * @param parameterKey - what to measure
	 * @param stationKey
	 * @param timeKey
	 */

	private String loadApiData(String parameterKey, String stationKey, String timeKey) {
		try {
			JSONParse openDataMetobsReader = new JSONParse();
//			String parameterKey = openDataMetobsReader.getParameters(); // Get all parameters
//			String stationKey = openDataMetobsReader.getStationNames("3"); // Get all stations measuring this parameter
//			String periodName = openDataMetobsReader.getPeriodNames("3", "52350"); // Get all periods
			String jsonData = openDataMetobsReader.getData(parameterKey, stationKey, timeKey); // Get data with keys
			JSONObject jsonObject = new JSONObject(jsonData); // Convert to JSON object
			String data = jsonObject.getJSONArray("value").getJSONObject(0).getString("value"); // Extract  the value of the parameter
			return data;

		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void initiateData() {
		getController().setDirectionText(
				loadApiData(JSONParse.WIND_DIRECTION, JSONParse.STATION_MALMO_A, JSONParse.PERIOD_LATEST_HOUR)
						+ " degrees");
		getController().setVelocityText(
				loadApiData(JSONParse.WIND_VEOLOCITY, JSONParse.STATION_MALMO_A, JSONParse.PERIOD_LATEST_HOUR)
						+ " m/s");
	}

	public void pullNewJsonFromStation(String station) {
		String direction = null;
		String velocity = null;
		try {
			direction = loadApiData(JSONParse.WIND_DIRECTION, station, JSONParse.PERIOD_LATEST_HOUR);
			velocity = loadApiData(JSONParse.WIND_VEOLOCITY, station, JSONParse.PERIOD_LATEST_HOUR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (direction == null)
			getController().setDirectionText("No directional data");
		else
			getController().setDirectionText(direction + " degrees");
		if (velocity == null)
			getController().setVelocityText("No velocity data");
		else
			getController().setVelocityText(velocity + " m/s");
	}

	public void loadGUI(Pane parent) throws IOException {
		this.parent = parent;
		loadGUI(parent, FXML_PATH);
		getController().setView(this);
		initiateData();
	}

}
