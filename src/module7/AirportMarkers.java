package module7;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarkers extends CommonMarkers {
	public static List<SimpleLinesMarker> routes;
	PImage img;
	public AirportMarkers(Feature city,PImage img) {
		
		super(((PointFeature)city).getLocation(), city.getProperties());
		this.img=img;
		
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		//pg.fill(200,102,0);
		//pg.ellipse(x, y, 5, 5);
		
		
		pg.pushStyle();
		
		pg.image(img, x-18, y-18);
		/*
	    pg.noStroke();
	    pg.fill(200, 200, 0, 100);
	    pg.ellipse(x, y, 40, 40);
	    pg.fill(255, 100);
	    pg.ellipse(x, y, 30, 30);
	    */
	    pg.popStyle();
		
		
	}
	
	public String getTitle() {
		return (String) getProperty("CityName");	
		
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		 // show rectangle with title
		String title = getTitle();
		pg.pushStyle();
		
		pg.rectMode(PConstants.CORNER);
		
		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y + 15, pg.textWidth(title) +6, 18, 5);
		
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(0);
		pg.text(title, x + 3 , y +18);
		// show routes
		pg.popStyle();
		
	}
	
}
