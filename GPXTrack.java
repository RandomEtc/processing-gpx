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
import java.util.Vector;

/** a collection of GPXTrackSeg objects, with type-safe convenience methods for adding/removing/getting track segments */
public class GPXTrack extends Vector {

  public String name;

  public GPXTrack(XML xmltrk) {
    this.name = xmltrk.getChild("name").getContent();
    for (XML child : xmltrk.getChildren("trkseg")) {
      this.addTrackSeg(new GPXTrackSeg(child));
    }
  }

  public GPXTrackSeg getTrackSeg(int i) {
    return (GPXTrackSeg)elementAt(i);
  }

  public void addTrackSeg(GPXTrackSeg trkseg) {
    addElement(trkseg);
  }

  public void removeTrackSeg(GPXTrackSeg trkseg) {
    removeElement(trkseg);
  }

}
