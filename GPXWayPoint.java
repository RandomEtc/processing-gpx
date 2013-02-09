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

import processing.data.XML;

/** simple GPX waypoint, only understands latitude, longitude, name and type
    FIXME extend parsing to understand other properties */
public class GPXWayPoint {

  public double lat,lon;
  public String name;
  public String type;

  public GPXWayPoint() {
    this.lat = 0.0;
    this.lon = 0.0;
    this.name = "";
    this.type = "";
  }

  public GPXWayPoint(double lat, double lon, String name, String type) {
    this.lat = lat;
    this.lon = lon;
    this.name = name;
    this.type = type;
  }

  /** parses a GPXWayPoint from the given XML */
  public GPXWayPoint(XML trkpt) {
    this.lat = trkpt.getDouble("lat");
    this.lon = trkpt.getDouble("lon");
    this.name = trkpt.getChild("name").getContent();
    this.type = trkpt.getChild("type").getContent();
  }

}
