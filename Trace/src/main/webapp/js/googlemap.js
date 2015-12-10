var map;

function initialize() {
    var mapProp = {
        center: new google.maps.LatLng(37.4955208,127.0262779),
        zoom: 17,
        disableDefaultUI: true,
        mapTypeId:google.maps.MapTypeId.ROADMAP

    };

    map = new google.maps.Map(document.getElementById('map'), mapProp);

    var input = /** @type {!HTMLInputElement} */
    (document.getElementById('pac-input'));

    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);

    var infowindow = new google.maps.InfoWindow();
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29),
        animation: google.maps.Animation.DROP
    });

    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    window.onresize = function () {
        lastCenter = map.getCenter();
        google.maps.event.trigger(map, 'resize');
        map.setCenter(lastCenter);
    }

    autocomplete.addListener('place_changed', function() {
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
          window.alert("Autocomplete's returned place contains no geometry");
          return;
        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
          map.fitBounds(place.geometry.viewport);
        } else {
          map.setCenter(place.geometry.location);
          map.setZoom(17);  // Why 17? Because it looks good.
        }

        marker.setPosition(place.geometry.location);
        marker.setVisible(true);
    });
}


function drawPolyLine(){
    var drawingManager = new google.maps.drawing.DrawingManager({
    drawingMode: google.maps.drawing.OverlayType.MARKER,
    drawingControl: true,
    drawingControlOptions: {
      position: google.maps.ControlPosition.TOP_CENTER,
      drawingModes: [
        google.maps.drawing.OverlayType.MARKER,
        google.maps.drawing.OverlayType.CIRCLE,
        google.maps.drawing.OverlayType.POLYGON,
        google.maps.drawing.OverlayType.POLYLINE,
        google.maps.drawing.OverlayType.RECTANGLE
      ]
    },
    markerOptions: {icon: 'img/logo.png'},
    circleOptions: {
      fillColor: '#ffff00',
      fillOpacity: 1,
      strokeWeight: 5,
      clickable: false,
      editable: true,
      zIndex: 1
    }
  });
  drawingManager.setMap(map);
}
//    var x = new google.maps.LatLng(37.4955208,127.0262779);
//    var stavanger = new google.maps.LatLng(37.4955208,127.0262779);
//    var amsterdam = new google.maps.LataLng(37.4955208,129.0262779);
//    var london = new google.maps.LatLng(39.4955208,127.0262779);
//    
//    var myTrip = [stavanger, amsterdam, london];
//    var flightPath = new google.maps.Polyline({
//        path:myTrip,
//        strokeColor:"#0000FF",
//        strokeOpacity:0.8,
//        strokeWeight:2
//    });
//    
//    flightPath.setMap(map);
//}