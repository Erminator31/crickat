


function wh_location(mapId, newLocation)
{
    var string_loc = "https://maps.google.com/maps?q=" + newLocation + "&output=embed";
    var map = document.getElementById(mapId);
    map.src = string_loc;
    // alert("You requested the new location: " + map.src);
    document.getElementById(mapId).src = document.getElementById(mapId).src;
}
