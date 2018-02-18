package module7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import module6.AirportMarker;
import module6.CommonMarker;
import parsing.ParseFeed;
import processing.core.PApplet;
import processing.core.PImage;

public class AirportsMap extends PApplet  {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	List<Marker> routeList;
	List<PointFeature> features;
	PImage img;
	private CommonMarkers lastSelected;

	public void setup() {
		// setting up PAppler
	
		size(1300,800, OPENGL);
		//PImage img = loadImage("Airport.jpg");
		// setting up map and default events
		//map = new UnfoldingMap
		map = new UnfoldingMap(this, 0, 0, 1300, 800);
		MapUtils.createDefaultEventDispatcher(this, map);
		img = loadImage("icone32.png");
		// get features from airport data
		//List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
	    features = new ArrayList<PointFeature>();
		Location l1 = new Location(52.558833 , 13.288437);
		PointFeature f1 = new PointFeature(l1);
		f1.addProperty("CODE","BTL");
		f1.addProperty("CityName", "Berlin");
		features.add(f1);
		
		Location l2 = new Location(31.609798 ,-8.034776);
		PointFeature f2 = new PointFeature(l2);
		f2.addProperty("CODE","RAK");
		f2.addProperty("CityName", "MARRAKESH");
		features.add(f2);
		
		Location l3 = new Location(33.374393 , -7.584848);
		PointFeature f3 = new PointFeature(l3);
		f3.addProperty("CODE","CMN");
		f3.addProperty("CityName", "CASABLANCA");
		features.add(f3);
		
		Location l4 = new Location(50.042052 , 8.536206);
		PointFeature f4 = new PointFeature(l4);
		f4.addProperty("CODE","FRA");
		f4.addProperty("CityName", "FRANKFURT");
		features.add(f4);
		
		Location l5 = new Location(51.470022 , -0.454295);
		PointFeature f5 = new PointFeature(l5);
		f5.addProperty("CODE","LHR");
		f5.addProperty("CityName", "LONDON");
		features.add(f5);
		
		Location l6 = new Location( 42.365613 ,-71.00956);
		PointFeature f6 = new PointFeature(l6);
		f6.addProperty("CODE","BOS");
		f6.addProperty("CityName", "Boston Logan International Airport"	);
		features.add(f6);
		
		Location l7 = new Location( 40.483936 , -3.567951);
		PointFeature f7 = new PointFeature(l7);
		f7.addProperty("CODE","MAD");
		f7.addProperty("CityName", "MADRID");
		features.add(f7);
		
		Location l8 = new Location( 21.68778 , 39.143608);
		PointFeature f8 = new PointFeature(l8);
		f8.addProperty("CODE","JED");
		f8.addProperty("CityName", "JEDDAH");
		features.add(f8);
		
		Location l9 = new Location(24.512447 , 39.662249);
		PointFeature f9 = new PointFeature(l9);
		f9.addProperty("CODE","MED");
		f9.addProperty("CityName", "MADINAH");
		features.add(f9);
		
		Location l10 = new Location(31.828963 , 35.898195);
		PointFeature f10 = new PointFeature(l10);
		f10.addProperty("CODE","AMAN");
		f10.addProperty("CityName", "AMN");
		features.add(f10);
		
		Location l11 = new Location(41.974162 , -87.907321);
		PointFeature f11 = new PointFeature(l11);
		f11.addProperty("CODE","ORD");
		f11.addProperty("CityName", "CHICAGO");
		features.add(f11);
		
		Location l12 = new Location(40.641311,-73.778139);
		PointFeature f12 = new PointFeature(l12);
		f12.addProperty("CODE","JFK");
		f12.addProperty("CityName", "New York");
		features.add(f12);
		
		Location l13 = new Location(48.141296 , 11.559116);
		PointFeature f13 = new PointFeature(l13);
		f13.addProperty("CODE","MUC");
		f13.addProperty("CityName", "MUNICH");
		features.add(f13);
		
		Location l14 = new Location( 48.726243 , 2.365247);
		PointFeature f14 = new PointFeature(l14);
		f14.addProperty("CODE","ORY");
		f14.addProperty("CityName", "ORLY");
		features.add(f14);
		
		
		
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Location> airports = new HashMap<Integer, Location>();
		
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarkers m = new AirportMarkers(feature,img);
	
			m.setRadius(5);
			airportList.add(m);
			
			// put airport in hashmap with OpenFlights unique id for key
			//airports.put(Integer.parseInt(feature.getId()), feature.getLocation());
		
		}
		
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		routeList = new ArrayList<Marker>();
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airports.get(source));
				route.addLocation(airports.get(dest));
			}
			
			SimpleLinesMarker sl = new SimpleLinesMarker(route.getLocations(), route.getProperties());
		
			//System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
			//routeList.add(sl);
		}
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		//map.addMarkers(routeList);
		
		map.addMarkers(airportList);
		
	}
	
	public void draw() {
		background(0);
		map.draw();
		
	}
	@Override
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		//selectMarkerIfHover(quakeMarkers);
		selectMarkerIfHover(airportList);
		//loop();
	}
	
	// If there is a marker selected 
	private void selectMarkerIfHover(List<Marker> markers)
	{
		// Abort if there's already a marker selected
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			CommonMarkers marker = (CommonMarkers)m;
			if (marker.isInside(map,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}

}
		
		


