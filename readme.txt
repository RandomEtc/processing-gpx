
About
-----

Read and manipulate data in the GPX format. (see http://www.topografix.com/GPX/ for more).

Uses nanoxml to keep the jar size minimal, so doesn't not support namespaces or extensions.
Only basic information (lat/lon/ele/time) is parsed.

A GPX file is an xml file with a collection of GPX Tracks inside.  Each GPX Track has a name, and is a collection of GPX Track Segments, and each segment is a collection of GPX Points.  Each point has a latitude and longitude as well as an elevation and timestamp.  No other information is parsed by this library.  A GPX file can also contain waypoints, with lat/lon position and name and type.

This library has only been tested with a few different files, and I expect it will need updating to properly handle things like waypoints with elevation, and so on.  If you find a file that breaks it, please send it to me.

Usage
-----

import tomc.gpx.*;

// outside setup()
GPX gpx;

  // inside setup()
  gpx = new GPX(this);

  // when you want to load data
  gpx.parse("test.gpx"); // or a URL

  // inside draw()
  for (int i = 0; i < gpx.getTrackCount(); i++) {
    GPXTrack trk = gpx.getTrack(i);
    // do something with trk.name
    for (int j = 0; j < trk.size(); j++) {
      GPXTrackSeg trkseg = trk.getTrackSeg(j);
      for (int k = 0; k < trkseg.size(); k++) {
        GPXPoint pt = trkseg.getPoint(k);
        // do something with pt.lat or pt.lon
      }
    }
  }

  for (int i = 0; i < gpx.getWayPointCount(); i++) {
    GPXWayPoint wpt = gpx.getWayPoint(i);
    // do something with wpt.lat or wpt.lon or wpt.name or wpt.type
  }


Installation
------------

Place this folder (gpx) into your Processing libraries folder, then restart Processing.


Bugs / Requests
---------------

email tom@tom-carden.co.uk


Enjoy! TomC.


10/02/2006 09:55 GMT
--------------------

Initial release on processinghacks.com.

10/03/2007 10:40 CST
--------------------

Updated for Processing 0123+ (PApplet.reader became PApplet.createReader)
