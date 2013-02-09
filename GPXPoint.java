/*

  Copyright (c) 2006-2011 Tom Carden

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA

*/

package tomc.gpx;

import java.util.Date;
import java.text.SimpleDateFormat;
import processing.data.XML;

/** simple GPX point, only understands latitude, longitude, elevation and time
    FIXME extend parsing to understand other properties */
public class GPXPoint {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
  private static final SimpleDateFormat msdateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"); // allow millis too

  public double lat,lon;
  public double ele;
  public Date time;

  public GPXPoint() {
    this.lat = 0.0;
    this.lon = 0.0;
    this.ele = 0.0;
    this.time = new Date();
  }

  public GPXPoint(double lat, double lon, double ele, Date time) {
    this.lat = lat;
    this.lon = lon;
    this.ele = ele;
    this.time = time;
  }

  /** parses a GPXPoint from the given XML
      understands 2 time formats: ISO 8601 with and without milliseconds */
  public GPXPoint(XML trkpt) {

    // NB:- this is a bit more complex than it should be
    //      but it should handle a wider variety of broken
    //      GPX files, and let's face it that's why you're using
    //      a library instead of parsing this file yourself

    try {
      String sLat = trkpt.getString("lat");
      this.lat = Double.parseDouble(sLat);
    }
    catch (Exception e) {
      if (GPX.debug) {
        e.printStackTrace();
        System.err.println("error parsing lat in: " + trkpt);
      }
      this.lat = 0.0;
    }
    try {
      String sLon = trkpt.getString("lon");
      this.lon = Double.parseDouble(sLon);
    }
    catch (Exception e) {
      if (GPX.debug) {
        e.printStackTrace();
        System.err.println("error parsing lon in: " + trkpt);
      }
      this.lon = 0.0;
    }

    for (XML element : trkpt.getChildren()) {
      if (element.getName().equals("ele")) {
        try {
          String sEle = element.getContent();
          this.ele = Double.parseDouble(sEle);
        }
        catch (Exception e) {
          if (GPX.debug) {
            e.printStackTrace();
            System.err.println("error parsing ele in: " + trkpt);
          }
          this.ele = 0.0;
        }
      }
      else if (element.getName().equals("time")) {
        String t = element.getContent();
        try {
          if (t.length() == 20) {
            this.time = dateFormat.parse(t);
          }
          else if (t.length() == 24) {
            this.time = msdateFormat.parse(t);
          }
          else {
             this.time = new Date(Long.parseLong(t)); // try for unix time
    	  }
        }
        catch(Exception e) {
          if (GPX.debug) {
            e.printStackTrace();
            System.err.println("error parsing time in: " + trkpt);
          }
          this.time = new Date();
        }
      }
      else {
        if (GPX.debug) {
          System.err.print("skipping unknown element: ");
          System.err.println(element.getName());
        }
      }
    }

  }

}
