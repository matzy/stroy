
var arr = [{"u":"http:\/\/lacunae.comicgenesis.com\/","d":"Lacunae - The Book of Missing Spaces - Monday, August 21, 2006","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:50:49Z","n":"","a":"openCage"},{"u":"http:\/\/www.intershadows.com\/","d":"Intershadows","t":["comic-page-1","unrated","never","3"],"dt":"2006-08-23T16:50:14Z","n":"","a":"openCage"},{"u":"http:\/\/vagrantvivian.comicgenesis.com\/","d":"Vagrant Vivian- the treasure you don't have to dig up!","t":["comic-page-1","3","never"],"dt":"2006-08-23T16:50:03Z","n":"","a":"openCage"},{"u":"http:\/\/pele.comicgenesis.com\/","d":"Deity Permit - Updated on OHCRAPHIATUS - Saturday, April 23, 2005","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:49:12Z","n":"","a":"openCage"},{"u":"http:\/\/vreakerz.comicgenesis.com\/","d":"Vreakerz - Saturday, August 5, 2006","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:44:09Z","n":"","a":"openCage"},{"u":"http:\/\/pimpette.ca\/","d":"PIMPETTE","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:25:42Z","n":"","a":"openCage"},{"u":"http:\/\/brokenglass.comicgenesis.com\/","d":"Broken Glass","t":["comic-page-1","unrated","never"],"dt":"2006-08-23T16:24:57Z","n":"{img: \"http:\/\/brokenglass.comicgenesis.com\/images\/window-banner.png\"}","a":"openCage"},{"u":"http:\/\/www.nozzman.com\/","d":":: Nozzman - cartoons and illustrations ::","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T12:13:38Z","n":"","a":"openCage"},{"u":"http:\/\/www.little-gamers.com\/index.php","d":"Little Gamers","t":["comic-page-1","2","never"],"dt":"2006-08-04T12:06:19Z","n":"","a":"openCage"},{"u":"http:\/\/thebunnysystem.com\/","d":"The Bunny System","t":["comic-page-1","unrated","never","2"],"dt":"2006-08-04T12:05:17Z","n":"","a":"openCage"},{"u":"http:\/\/twolumps.keenspot.com\/","d":"Two Lumps: The Adventures of Ebenezer and Snooch","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T12:04:56Z","n":"","a":"openCage"},{"u":"http:\/\/www.argus-online.nl\/","d":"www. argus-online .nl","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T11:33:39Z","n":"","a":"openCage"},{"u":"http:\/\/www.explosm.net\/comics\/620\/","d":"Explosm.net - Comics: Cyanide and Happiness","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T11:24:37Z","n":"","a":"openCage"},{"u":"http:\/\/www.cartoondiarree.nl\/","d":"Michiel's cartoondiarree","t":["comic-page-1","unrated","never"],"dt":"2006-08-04T11:24:22Z","n":"","a":"openCage"},{"u":"http:\/\/www.wulffmorgenthaler.com\/","d":"Home | wulffmorgenthaler.com","t":["comic-page-1","unrated","1"],"dt":"2006-08-04T11:24:02Z","n":"","a":"openCage"}];


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
    var t1 = tabby.tabby( arr );

    t1.column( {
        header: "name",
        accessor: function( bmark ) {
            return bmark.d;
        },
        sort: tabby.alphaNum(
                function( bmark) {
                    return bmark.d
                })
    });

    t1.column({
        header: "url",
        accessor: function( bmark ) {
            return bmark.u;
        },
    });

    t1.column( {
        header: "rating",
        hook: function( sel, elem ) {
            $(sel).raty({
                start: bookmark.getRating( elem ),
                path: "../lib/raty/img",
//                        onClick: function(score){
//    						link.setRating( $(this), jsn, score );
//    						//ddlicious.applyFilter();
//                        }
            });
        },
        sort: tabby.alphaNum(
                    function( bmark) {
                        return bookmark.getRating( bmark )
                    }),
    });

    t1.zebra().build( "#here" );
});


