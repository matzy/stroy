/**
 * @author SPF
 */

    var bookmark = function() {
        var that = {};

        that.getRating = function( bmark ) {
            var tags = bmark.t;
            for ( var i = 1; i <= 5; i++ ) {
                if ( $.inArray( "" + i, tags ) > -1) {
                    return i;
                }
            }

            return 0;
        }

        return that;
    }();


$(document).ready(function(){

	var arr = [{"u":"http:\/\/lacunae.comicgenesis.com\/","d":"Lacunae - The Book of Missing Spaces - Monday, August 21, 2006","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:50:49Z","n":"","a":"openCage"},{"u":"http:\/\/www.intershadows.com\/","d":"Intershadows","t":["comic-page-1","unrated","never","3"],"dt":"2006-08-23T16:50:14Z","n":"","a":"openCage"},{"u":"http:\/\/vagrantvivian.comicgenesis.com\/","d":"Vagrant Vivian- the treasure you don't have to dig up!","t":["comic-page-1","3","never"],"dt":"2006-08-23T16:50:03Z","n":"","a":"openCage"},{"u":"http:\/\/pele.comicgenesis.com\/","d":"Deity Permit - Updated on OHCRAPHIATUS - Saturday, April 23, 2005","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:49:12Z","n":"","a":"openCage"},{"u":"http:\/\/vreakerz.comicgenesis.com\/","d":"Vreakerz - Saturday, August 5, 2006","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:44:09Z","n":"","a":"openCage"},{"u":"http:\/\/pimpette.ca\/","d":"PIMPETTE","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:25:42Z","n":"","a":"openCage"},{"u":"http:\/\/brokenglass.comicgenesis.com\/","d":"Broken Glass","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:24:57Z","n":"{img: \"http:\/\/brokenglass.comicgenesis.com\/images\/window-banner.png\"}","a":"openCage"},{"u":"http:\/\/www.nozzman.com\/","d":":: Nozzman - cartoons and illustrations ::","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T12:13:38Z","n":"","a":"openCage"},{"u":"http:\/\/www.little-gamers.com\/index.php","d":"Little Gamers","t":["comic-page-1","2","never"],"dt":"2006-08-04T12:06:19Z","n":"","a":"openCage"},{"u":"http:\/\/thebunnysystem.com\/","d":"The Bunny System","t":["comic-page-1","unrated","never","2"],"dt":"2006-08-04T12:05:17Z","n":"","a":"openCage"},{"u":"http:\/\/twolumps.keenspot.com\/","d":"Two Lumps: The Adventures of Ebenezer and Snooch","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T12:04:56Z","n":"","a":"openCage"},{"u":"http:\/\/www.argus-online.nl\/","d":"www. argus-online .nl","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T11:33:39Z","n":"","a":"openCage"},{"u":"http:\/\/www.explosm.net\/comics\/620\/","d":"Explosm.net - Comics: Cyanide and Happiness","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T11:24:37Z","n":"","a":"openCage"},{"u":"http:\/\/www.cartoondiarree.nl\/","d":"Michiel's cartoondiarree","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T11:24:22Z","n":"","a":"openCage"},{"u":"http:\/\/www.wulffmorgenthaler.com\/","d":"Home | wulffmorgenthaler.com","t":["comic-page-1","unrated","1"],"dt":"2006-08-04T11:24:02Z","n":"","a":"openCage"}];
	
	var t1 = tabby.tabby( arr ).
				headers(["name", "url", "logo" ,"rating", "idx"]).
				accessors( [
					function( row ) {
						return row.d;
					},
					function(row){
						return row.u;
					},
					function( row ) {
					    try {
					        var pic = eval('('+ row.n +')').img;
            	            return "<img src=\"" + pic + "\" height=\"50px\"/>";
					    } catch (err ) {}
					},
					undefined,
					function(row) {
						return $.inArray( row, arr );
					}
					]).
				zebra();

	t1.sortFcts([t1.alphaNumAcc( 0 ), undefined, undefined, tabby.alphaNum( bookmark.getRating ), t1.alphaNumAcc( 4 )]);

    t1.hook( [undefined, undefined, undefined, function( sel, row ) {
             $(sel).raty({
                        start: bookmark.getRating( row )
                        ,
                        path: "../lib/raty/img",
//                        onClick: function(score){
//    						link.setRating( $(this), jsn, score );
//    						//ddlicious.applyFilter();
//                        }
                    });


    }, undefined ]);


	t1.build("#here");

    (function(){
        var text = "";
        $( "#toggle" ).click( function() {

            if ( text == "" ) {
                text = "e";
            } else if ( text == "e"){
                text = "en"
            } else if ( text == "en") {
                text = "en G";
            }

            alert( text );

            t1.filter( function( elem ) {
                return elem.d.indexOf( text ) > -1;
            })
        });
    })();
	
	//alert( t1.build());
	
	//used to apply alternating row styles  

//	function zebraRows(selector, className)  
//	{  
//	  $(selector).removeClass(className).addClass(className);  
//	}  
//	
//	zebraRows('tabby tbody tr:odd td', 'odd');  
	
//	var t1 = tabby([], []);
//	alert( t1.build() );
//	alert( tabby( [1,2,3], ["a","b","x"] ).build());
//	alert( t1.build() );
});