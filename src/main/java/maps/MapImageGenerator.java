package maps;

import com.google.maps.*;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.List;

public class MapImageGenerator {
    private static final String API_KEY = "AIzaSyB9SGUNS3n_HQ0MZD23DM1Wak-JCvt0Zw0";

    public static String getDirectionsUrl(List<String> coordinates) {
        if (coordinates.size() < 2) {
            throw new IllegalArgumentException("At least two coordinates are required for directions.");
        }

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        List<LatLng> waypoints = new ArrayList<>();
        for (String coordinate : coordinates) {
            String[] latLng = coordinate.split(",");
            double lat = Double.parseDouble(latLng[0].trim());
            double lng = Double.parseDouble(latLng[1].trim());
            waypoints.add(new LatLng(lat, lng));
        }

        try {
            DirectionsApiRequest request = DirectionsApi.newRequest(context)
                    .origin(waypoints.get(0))
                    .destination(waypoints.get(waypoints.size() - 1))
                    .waypoints(waypoints.subList(1, waypoints.size() - 1).toArray(new LatLng[0]))
                    .mode(TravelMode.DRIVING);

            DirectionsResult result = request.await();

            if (result.routes != null && result.routes.length > 0) {
                String encodedPolyline = result.routes[0].overviewPolyline.getEncodedPath();

                String staticMapUrl = "https://maps.googleapis.com/maps/api/staticmap?";
                staticMapUrl += "size=800x1800";
                staticMapUrl += "&path=enc:" + encodedPolyline;

                // Add markers for all waypoints
                int label = 65; // ASCII code for 'A'
                for (LatLng waypoint : waypoints) {
                    staticMapUrl += "&markers=color:red%7Clabel:" + (char) label + "%7C" + waypoint.lat + "," + waypoint.lng;
                    label++;
                }

                staticMapUrl += "&key=" + API_KEY;

                return staticMapUrl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
