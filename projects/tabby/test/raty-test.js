
$(document).ready(function(){
    for ( var i = 1; i < 3; i++ ) {
//        $( "#rr" + i + "-1" ).
           $( "tbody tr:nth-child("+ i  +") td:nth-child(" + (0+1) +")" ).
            raty( {
                start: i,
                path: "../lib/raty/img"
                });
    }
});