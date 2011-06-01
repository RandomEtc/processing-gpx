/*

  Copyright (c) 2006 Tom Carden

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

import java.util.Vector;
import java.util.Iterator;
import nanoxml.XMLElement;

/** a collection of GPXTrackSeg objects, with type-safe convenience methods for adding/removing/getting track segments */
public class GPXTrack extends Vector {

  public String name;

  public GPXTrack(XMLElement xmltrk) {
    Iterator trkIter = xmltrk.getChildren().iterator();
    while (trkIter.hasNext()) {
      XMLElement trkdata = (XMLElement)trkIter.next();
      if (trkdata.getName().equals("name")) {
        this.name = trkdata.getContent();
      }
      else if (trkdata.getName().equals("trkseg")) {
        this.addTrackSeg(new GPXTrackSeg(trkdata));
      }
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
