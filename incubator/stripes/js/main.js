// time: houerly / daily / 3per week / weekly / rare / never 
// once / hibernate / dead / archive 


//
//// create the create method (js the good parts)
//if (typeof Object.create != "function") {
//    Object.create = function(o){
//        var F = function(){
//        }
//        F.prototype = o;
//        return new F();
//    }
//}

// feed json api result per url
// u  - url
// d  - description
// t  - array of tags
// dt - timestamp of bookmark
// n  - notes
// a  - user
//
// eg: {"u":"http:\/\/nice.delicious.com\/","d":"basetags","t":["basetags","news-apple","comic","news-java","stroy","ddlicious-basetags"],"dt":"2011-02-10T09:43:58Z","n":"","a":"openCage"}
//

// enables tablesorter sort by spaces
// spaces are invisible on the page
// add parser through the tablesorter addParser method 
$.tablesorter.addParser({
    // set a unique id 
    id: "rating",
    is: function(s){
        // return false so this parser is not auto detected 
        return false;
    },
    format: function(s){
        return s;
    },
    // set type, either numeric or text 
    type: 'text'
});

var arrayReplace = function( arr, from, to ) {
	var na = [];
	
	for ( var i = 0; i < arr.length; i++ ) {
		if ( arr[i] == from ) {
			na.push(to);
		} else {
			na.push( arr[i]);
		}
	}
	
	return na;
}


var dropDownBuilder = function( place, vals, id, deflt, action ) {
	
	var sel = "<select id=\""+ id +"\" name=\"freq\">";
	
    for (var f = 0; f < vals.length; f++) {
		sel += "<option>" + vals[f] +"</option>";
    }		
	
	sel += "</select>"	

	$("#" + place).html(sel);
	
	if (action) {
		(function(){
			var idd = id;
			$("#" + idd).change(function(){
				action($("#" + idd).val());
			});
		})();
	}
	
	var pos = $.inArray( deflt, vals );
	if ( pos > -1 ) {
		$("#" + id).val(deflt);
	}

	$("#" + id).uniform();
};

var frequency = function() {
	var that = {};
	
	that.kinds = ["unknown", "never", "rare", "weekly", "mwf", "daily", "houerly" ];
	
	that.extract = function(bookmark) {
        var tags = bookmark.t;
		
        for (var f = 0; f < that.kinds.length; f++) {
            if ($.inArray( that.kinds[f], tags) > -1) {
                return that.kinds[f];
            }
        }
        
        return that.kinds[0];		
	}
	
	that.isOne = function(val) {
		return ($.inArray( val, that.kinds ) > -1);
	}
	
	
	return that;	
}();

/**
 * utilities without context
 */
var dnUtils = function() {
	
	var that = {};
	
    /**
     * extract rating from tags
     * a rating is one of [1,2,3,4,5]
     * @param {Object} jsn
     */
    that.getRating = function(jsn){
        var tags = jsn.t;
        for (var rating = 5; rating > 0; rating--) {
            if ($.inArray("" + rating, tags) > -1) {
                return rating;
            }
        }
        
        return 0;
    };
	
	    
    /**
     * Create a string to be used in a Ajax call to set the rating
     * rating is a tag [1,2,3,4,5]
     * make sure that only the numer is changed but all other tags stay
     * the separater is a space, i.e. %20
     * @param {Object} jsn the link info as it comes from 'get'
     * @param {Object} newRating
     */
    that.setRatingTag = function(jsn, newRating){
        var tags = jsn.t;
        var newTags = "" + newRating;
        
        for (var idx = 0; idx < tags.length; idx++) {
            if (isNaN(tags[idx])) {
                newTags += "%20" + tags[idx];
            }
        }
        
        return newTags;
    };
    
    /**
     * create a table from an array of objects
     * @param {Object} headers list of header names
     * @param {Object} accesors list of functions transformin an object into a table cell value
     * @param {Object} obj the array of objects
     */
    that.mkTable3 = function(headers, accesors, arr, indices){
        var res = "<table id=\"thetable\" class=\"tablesorter\">\n";
        
        /* header */
        res += "<thead><tr>\n";
        for (var i = 0; i < headers.length; i++) {
            res += "<th>" + headers[i] + "</th>";
        }
        res += "</tr></thead>\n";
        
        /* body */
        res += "<tbody>\n";
        
        for (var i = 0; i < indices.length; i++) {
            res += "<tr>";
            for (var a = 0; a < accesors.length; a++) {
                res += "<td>" + accesors[a](arr[indices[i]], i) + "</td>";
            }
            res += "</tr>\n"
        }
        
        res += "</tbody></table>\n";
        
        $("foot").append(res);
        
        return res;
    };
    
    /**
     * does the string 'src' start with 'starts'
     * @param {Object} src
     * @param {Object} starts
     */
    that.startsWith = function(src, starts){
        return src.substr(0, starts.length) === starts;
    };
	    
    that.spaces = ["", " ", "  ", "   ", "    ", "     "];
	
	that.isImage = function( url ) {		
		return url.match(/(png$|jpg$|gif$|jpeg$)/i)		
	}
	
	// Read a page's GET URL variables and return them as an associative array.
	that.getUrlVars = function() {
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	        hash = hashes[i].split('=');
	        vars.push(hash[0]);
	        vars[hash[0]] = hash[1];
	    }
	    return vars;
	}
	
	that.caseInsensitiveEquals = function( a, b ) {
		return a.toLowerCase() == b.toLowerCase();
	}
	
	return that;

}();

var paged = function() {
	var that = {};
	
	that.page = 0;
	var index = 0;
	
	that.bookmarks = [];
	that.tag = "foo";
	
	
	that.init = function() {
		that.page = 0;
		index = 0;
		that.bookmarks = [];
		that.tag = "foo;"	
	}
	
	that.isPaged = function() {
		return that.page > 0 || index > 90;	
	}
	
	that.clean = function() {}
	
	
	that.append = function( more ) {
		for ( var i = 0; i < more.length; i++ ) {
			var bookmark = more[i];
						
			if ( !bookmark.d.match(/basetags|ddlicious-default/i)) {
				that.bookmarks.push( bookmark);
			}						
						
		}

//		that.bookmarks = that.bookmarks.concat( more );
	
		index = more.length;	
	
	}
	
	var nextPaged = function() {
		index++;
		if ( index >= 90 ) {
			index = 0;
			that.page++;	
		}
	}
	
	that.pagify = function( bookmark ) {
		
		if (!that.isPaged() ) {
			return;
		}
		
		if ( $.inArray( that.tag, bookmark.t ) < 0 )  {
			// already paged
			return;	
		}
		
		nextPaged();
		
		var tags = bookmark.t;
		bookmark.t = [];
		for ( var i = 0; i < tags.length; i++ ) {
			if ( tags[i] == that.tag ) {
				bookmark.t.push( that.tag + "-page-" + that.page );
			} else {
				bookmark.t.push( tags[i]);
			}
		}		
	}
	
	
	return that;	
}();



var filter = function() {
	var that = {};
	
	that.ratingLevel = 3;
	that.ratingCompare = "at least";
	that.frequencyLevel = "mwf";
	that.frequencyCompare = "is";
	
	that.random = false;
	
	that.filter = function( bookmark ) {
		if ( !compy.fct( that.ratingCompare)( dnUtils.getRating( bookmark), that.ratingLevel )) {
			return false;
		}				

		if ( !compy.fct( that.frequencyCompare)( frequency.extract( bookmark), that.frequencyLevel, frequency.kinds )) {
			return false;
		}				
		
		if ( that.random ) {
			if ( Math.random() < 0.8 ) {
				return false;
			}
		}
		
		return true;				
	}
	
	return that;	
}();



var ddlicious = function() {
	
	var that = {};

	// name of the project
	var name = "DDlicious"; 
	var tagOfBaseTags = name + "-basetags";
	var defaultDescription = name + "-default";

		
    var links = [];
    var indices = [];
	that.baseTags = [];
	that.defaultTag = "stroy";
	
	var userName = (function() {
		var un = dnUtils.getUrlVars()["username"];
		if ( !un.match( /\#$/)) {
			return un;
		}
		
		return un.slice( 0, un.length - 1 );
	})();
	
	
	var ratingLevel = 3;
	var ratingCompare = function( x, y ) {
		return x >= y;
	}
	var frequencey = "";
	
	that.getLinks = function() {
		return links;
	}
	
	

	/**
	 * make the pictures valid drop targets for images
	 * first supress the normal browser drop handling and than add own drop handler
	 */
	var addImgTarget = function() {
        for (var idx = 0; idx < indices.length; idx++) {
			// closure to encapsulate result[idx] 
			(function(){
				var iidx = idx;
				var jsn = links[indices[idx]];
				var jj = indices[idx];
				
				$( "#imgTarget-" + idx  )
					.bind("dragenter", ignoreDrag)
					.bind('dragover', ignoreDrag)
					.bind('drop', function(e) {
					    ignoreDrag(e);
					    
					    var dt = e.originalEvent.dataTransfer;
						
						var imgUrl = dt.getData("url");
						
						if (dnUtils.isImage(imgUrl)) {
							link.setImg($(this), links[jj], imgUrl);
							$("#imgTarget-" + iidx ).replaceWith( "<img class=\"oclink\" id=\"imgTarget-"+ iidx + "\" src=\"" + imgUrl + "\" height=\"50px\"/>");
						} else {
							alert( "not a picture " + imgUrl  );
						}
					});
			})()
		}		
	};
    
	/**
	 * add click handlers to the rating stars
	 */
    var addRatingClick = function(){
        for (var idx = 0; idx < indices.length; idx++) {
            // closure to encapsulate result[idx] 
            (function(){
                var jsn = links[indices[idx]];
				                
                $("#raty-" + idx).raty({
                    start: dnUtils.getRating(jsn),
                    path: "img",
                    onClick: function(score){			
						link.setRating( $(this), jsn, score );			
						//ddlicious.applyFilter(); 
                    }
                });
            })()
        }
        
    };
    
    
    that.applyFilter = function(){

//		if ( typeof filter == "number" ) {
//			alert( "function please");
//		}		
    
        indices = [];
        
        for (var idx = 0; idx < links.length; idx++) {
			if ( filter.filter( links[idx] )) {
                indices.push(idx);
            }
        }
        
		// set open "all" button
        
        $("#all").unbind("click");
        $("#all2").unbind("click");
        $("#all3").unbind("click");
		
        if (indices.length > 20) {
			$("#all center").replaceWith("<center>open " + 20 + "/" + indices.length + " bookmarks</center>");
			
			$("#all2").show();
			
			if (indices.length > 40) {
				$("#all2 center").replaceWith("<center>open next" + 20 + " bookmarks</center>");
				$("#all3 center").replaceWith("<center>open next" + 20 + " bookmarks</center>");
				$("#all3").show();
			}
			else {
				$("#all2 center").replaceWith("<center>open " + 20 + "/" + (indices.length - 20) + " bookmarks</center>");
				$("#all3").hide();
			}
		} else {
	        $("#all center").replaceWith("<center>open all " + indices.length + " bookmarks</center>");
			$("#all2").hide();		
			$("#all3").hide();		
		}
		
        
        $("#all").click(function(){
            for (var i = 0; i < indices.length && i < 20; i++) {
                open(links[indices[i]].u);
            }
        });
		
        $("#all2").click(function(){
            for (var i = 20; i < indices.length && i < 40; i++) {
                open(links[indices[i]].u);
            }
        });
		
        $("#all3").click(function(){
            for (var i = 40; i < indices.length && i < 60; i++) {
                open(links[indices[i]].u);
            }
        });
		
		// --
		
		$( "#stats center" ).replaceWith( "<center>" + indices.length + " of " + links.length + "</center>");
		
        
        $("#herebelinks").html( dnUtils.mkTable3(
			["Name", "Image", "Rating", "Frequency", "Tags", "Edit"], 
			[function(jsn, idx){
            	return "<div class=\"oclink\" id=\"table-title-" + idx +  "\"><a href=\"" + jsn.u + "\"><h3>" + jsn.d + "</h3></a></div>";
        	}, 
			function(jsn, idx){
				
				var img = link.getImage( jsn );
				if ( img == "" ) {
					return "<img id=\"imgTarget-"+ idx + "\" src=\"img/drop.png\"/>" ;	
				}
				
	            return "<img class=\"oclink\" id=\"imgTarget-"+ idx + "\" src=\"" + img + "\" height=\"50px\"/>";
	        }, 
			function(jsn, idx){
	            var rating = dnUtils.getRating(jsn);
	            return "<div id=\"raty-" + idx + "\" ></div>" + dnUtils.spaces[rating];
	        },
			function( jsn, idx ) {				
	            return "<div id=\"bmfreq-" + idx +"\"></div>";				
			},
			function( jsn, idx ) {				
	            return "<p>"+ jsn.t.join(",") +"</p>";				
			},
			function( bookmark, idx ) {
				return "<a href=\"#data-" + idx +"\" class=\"btn\" id=\"edit-"+ idx +"\">Edit</a>" +
				"<div style=\"display:none\"><div id=\"data-" + idx + "\" >"+ link.mkForm( bookmark, idx ) + "</div></div>" +
				"<a href=\"#datadel-" + idx +"\" class=\"btn\" id=\"delete-"+ idx +"\">Delete</a>" +
				"<div style=\"display:none\"><div id=\"datadel-" + idx + "\" >"+ link.mkDelForm( bookmark, idx ) + "</div></div>";
			}

			], 
			links, 
			indices));
        
		for( var i = 0; i < indices.length; i++ ) {
			var idx = indices[i]; 
			var frq = frequency.extract( links[idx]);
			(function(){
				var bookmark = links[idx];
				var ii = i;
				dropDownBuilder("bmfreq-" + i, frequency.kinds, "drop-bmfreq-" + i, frq, function(val){
					link.setFrequency(bookmark, val);
					//ddlicious.applyFilter(); // TODO check effect
				});
				
				$("#edit-" + i).fancybox({
					"width" : 550,
					"height" : 300,
					//"modal" : true,
					'centerOnScroll' : true,
					'autoDimensions'	: false
				});

				$("#submit-" + ii ).click( function() {
//					alert( "--- " + $("#title-" + ii ).val() + " +++ " + $("#title-" + ii ).text() );
					
					bookmark.d = $("#title-" + ii ).val();
					bookmark.u = $("#url-" + ii ).val();

					$( "#table-title-" + ii ).replaceWith( 
						"<div class=\"oclink\" id=\"table-title-" + ii +  "\"><a href=\"" + bookmark.u + "\"><h3>" + bookmark.d + "</h3></a></div>");
					
					link.store( bookmark );  
				});
				
				$("#delete-" + i).fancybox({
					"width" : 500,
					"height" : 300,
					//"modal" : true,
					'centerOnScroll' : true,
					'autoDimensions'	: false
				});

				$("#submitDel-" + ii ).click( function() {
//					alert( "--- " + $("#title-" + ii ).val() + " +++ " + $("#title-" + ii ).text() );
					
					link.del( bookmark );  
					// ddlicious.applyFilter(); TODO reload 
				});
				
			})();				
		}


        
        // apply tablesorter
        // 2nd column (1) unsortabel
        // 3rd column (2) has rating, sort them by spaces by using the rating parser	
        $("#thetable").tablesorter({
            headers: { 1: { sorter: false }, 2: { sorter: "rating" }},
            widgets: ['zebra']
        });
        
        $(".oclink").hover(function(){
            $(this).toggleClass("over");
        });
		
		addRatingClick();
		addImgTarget();
        
    };
    
    that.processLinks = function(res){
        links = res;
        that.applyFilter();
    };


	// get the tags used here 
	// not the attribute tags as 1,2,3
	//
	// after the tags are retrieved (async) execute the paramter function
	// forcing synchronous mode does not work for jsonp
	//
	// note: the method uses the feeds api so that the result is JSON
	//
	that.getBaseTags = function( fct ) {

	// not possible becouse jsonp		
//		 $.ajax({
//	         url:    	"http://feeds.delicious.com/v1/json/openCage/basetags?count=1", 
//	         success: 	function(result) {
//			 				//alert("hallo " + result[0].t + " - " + result[0].u );
//							var tags = result[0].t;
//							for (var i = 0; i < tags.length; i++) {
//								alert(tags[i]);
//								if (tags[i] != "basetags") {
//									baseTags.push(tags[i]);
//								}
//							}
//							alert( "aaa " + baseTags );
//	         			},
//			error:      function( err ) {
//							alert( "some error " + err  );
//						},						
//	         async:  	false,
//			 dataType: 	"jsonp",
//  			 // data: 		"callback=?"
//	    });          
		
		var cmd = "http://feeds.delicious.com/v1/json/" + userName + "/" + tagOfBaseTags + "?count=2";
		
		$.getJSON( cmd, "callback=?", function(result) {
						
				defaultTag = "";
				baseTags = [];		
										
				for ( var t = 0; t < result.length; t++ ) {
					
					//alert(result[t].d + " "+ t + " " + result[t].t );
					
					if ( dnUtils.caseInsensitiveEquals( result[t].d, defaultDescription ))  {
						
						var tags = result[t].t;
						for (var i = 0; i < tags.length; i++) {
							//alert( tags[i]);
							if (!dnUtils.caseInsensitiveEquals( tags[i], "basetags") && !dnUtils.caseInsensitiveEquals( tags[i], tagOfBaseTags )) {
								defaultTag = tags[i]; 
							}
						}						
						
					} else {
						var tags = result[t].t;
						for (var i = 0; i < tags.length; i++) {
							if ( !dnUtils.caseInsensitiveEquals( tags[i], "basetags") && !dnUtils.caseInsensitiveEquals( tags[i], tagOfBaseTags )) {
								baseTags.push(tags[i]);
							}
						}						
					}
				}
			
				baseTags.sort();
			
				//alert( baseTags );
				//alert( defaultTag );
			
				fct();
		});
		

		
	};
	
	that.applySettings = function( andThen ) {
		that.getBaseTags( function() {

			dropDownBuilder( "tag-selection", baseTags, "some42", defaultTag, function( tag ) {
				that.getLinks2( tag  );
			}  );
		
			var list = "<ul class=\"sf-menu\">";
				
			list += "<li><a href=\"#\">tag</a>"

			list += "<ul>";
		
			for ( var i = 0; i < baseTags.length; i++ ) {
				list += "<li><a href=\"#\" id=\"tag-"+ baseTags[i] + "\">" + baseTags[i] + "</a></li>";
			}
			
			list += "</ul></li>";

			list += "<li><a href=\"#\">settings</a></li>"
			list += "<li><a href=\"#\">help</a></li>"

			list += "</ul>";

			//alert( list);						
			
			$("#herebemenu").html(list);

			for ( var i = 0; i < baseTags.length; i++ ) {
				(function() {
					var tag = baseTags[i];
					$("#tag-" + tag).click( function() {
						that.getLinks2(tag);
					});
					})();
			}
			
			
			andThen( defaultTag );
		});
		
	};
	
//	that.getLinks = function( tag ) {
//		
//	    $.getJSON("http://feeds.delicious.com/v1/json/" + userName + "/" + tag + "?count=100&page=2", "callback=?", that.processLinks);
//	    
//	    
//	    $("#target").bind('dragenter', ignoreDrag).bind('dragover', ignoreDrag).bind('drop', drop)
//
//	}

	var getPages = function( res ) {
		
		if ( res.length == 0 && paged.page > 0 ) {
			paged.page--;
			
						
			that.processLinks( paged.bookmarks );			
		    $("#target").bind('dragenter', ignoreDrag).bind('dragover', ignoreDrag).bind('drop', drop); // TODO
		}
		else {
			paged.append( res );
			
			paged.page++;
			var current = paged.page;
		
			setTimeout( $.getJSON("http://feeds.delicious.com/v1/json/" + userName + "/" + paged.tag + "-page-" + current + "?count=100", "callback=?", getPages),
			            1500);
		}				
			
	}

	that.getLinks2 = function( tag ) {
		paged.init();
		paged.tag = tag;
		
		$.getJSON("http://feeds.delicious.com/v1/json/" + userName + "/" + tag + "?count=100", "callback=?", getPages );
	}
	
	
	return that;
    
    
}();


var link = function() {
	var that= {};

	that.store =  function( jsn ) {
		
		paged.pagify( jsn );
		
		var cmd = "https://api.del.icio.us/v1/posts/add";
		
		cmd += "?url=" + escape(jsn.u);
		cmd += "&description=" + escape(jsn.d);
		cmd += "&tags=" + jsn.t.join("%20");
		
		if (jsn.n) {
			cmd += "&extended=" + jsn.n;
		}

		//alert( cmd );
		
		$.getJSON( cmd , "callback=?", function(res) {
			alert(res);
		});		
		
	};
	
	that.del = function( bookmark ) {
		var cmd = "https://api.del.icio.us/v1/posts/delete";		
		cmd += "?url=" + escape( bookmark.u);
		$.getJSON( cmd , "callback=?", function(res) {
			alert(res);
		});		
	}
	
	that.getImage = function( bookmark ) {
		try {
			// read the json object from the notes
			var notes = eval("(" + bookmark.n + ")");
			return notes.img;
		} catch ( err ) {
			//alert( "error in eval of " + err + " -- " + bookmark.n );
			// not a json object
			return "";
		}
	}
	
	that.setRating = function( node, jsn, rating ) {

		//	    node.html( "<div id=\"raty-" + i + "\" ></div>" + dnUtils.spaces[rating]);

		//alert( node.child );

        var tags = jsn.t;
        
		jsn.t = [];
        for (var idx = 0; idx < tags.length; idx++) {
            if (isNaN(tags[idx])) {
                jsn.t.push( tags[idx]);
            }
        }
		
		jsn.t.push( rating );
		
		that.store(jsn);        
	};
	
	that.setImg = function( node, jsn, img ) {
		
		jsn.n = "{img: \"" + img + "\"}";
		
		that.store(jsn); 
	};
	
	that.setFrequency = function( bookmark, newFreq ) {
		// remove all frequencies from tags
        var tags = bookmark.t;
		
		bookmark.t = [];
        for (var idx = 0; idx < tags.length; idx++) {
			if ( !frequency.isOne( tags[idx])) {
                bookmark.t.push( tags[idx]);					
			}
		}
		// add new
        bookmark.t.push( newFreq );
		that.store(bookmark); 

		
	};
	
	that.mkForm = function( bookmark, idx ) {
		
//		return "<div><label width=100px>Title</label><input width=400px type=\"text\" value=\""+ bookmark.d +"\"></div>" +
//		"<div><label width=100px>Url</label><input width=400px type=\"text\" value=\""+ bookmark.u +"\"></div>";
		
		var frm = "<form>" +
          "<div class=\"box\">" +
            "<h1>Bookmark Data</h1>" +
            "<label>" +
               "<span>Title</span>"+
               "<input type=\"text\" class=\"input_text\" name=\"name\" id=\"title-" + idx + "\" value=\""+ bookmark.d +"\"/>" +
            "</label>" +
             "<label>"+
               "<span>Url</span>" +
               "<input type=\"text\" class=\"input_text\" name=\"url\" id=\"url-" + idx +"\"/ value=\""+ bookmark.u +"\"/>" +
            "</label>" +
             "<label>"+
               "<input type=\"button\" class=\"button\" id=\"submit-" + idx +"\"value=\"Save\" />" +
            "</label>" +
           "</div>" +
          "</form>";
	
	return frm;
	}

	that.mkDelForm = function( bookmark, idx ) {
		
		var frm = "<form>" +
          "<div class=\"box\">" +
            "<h1>Delete Bookmark</h1>" +
            "<label>" +
               "<span>Title</span>"+
               "<div>"+ bookmark.d +"</div>" +
            "</label>" +
             "<label>"+
               "<span>Url</span>" +
               "<div>"+ bookmark.u +"</div>" +
            "</label>" +
             "<label>"+
               "<input type=\"button\" class=\"button\" id=\"submitDel-" + idx +"\"value=\"Yes\" />" +
            "</label>" +
           "</div>" +
          "</form>";
	
	return frm;
	}

	
	return that;
	
}();



//var tableSorterExtraction = function( cell ) {
//	var id = $(cell).attr("id");
//	if ( !startsWith( id, "raty-")) {
//		return cell;	
//	}	
//	
//	var clazz = $(cell).attr("class");
//	
//	return clazz;	
//	
//}



var ignoreDrag = function(e){
    e.originalEvent.stopPropagation();
    e.originalEvent.preventDefault();
}

function drop(e){
    ignoreDrag(e);
    
    //alert(e);
    var dt = e.originalEvent.dataTransfer;
    //alert(dt);
    //var files = dt.files;
    //alert(files);
    
    alert(dt.types);
    alert(dt.getData("text/uri-list"));
    alert(dt.getData("text/plain"));
    alert(dt.getData("url"));
    
    //  if(dt.files.length > 0){
    //    var file = dt.files[0];
    //    alert(file.name);
    //  }
}

$(document).ready(function(){
		
	if (!dnUtils.getUrlVars()["username"] == "") {	
	
		ddlicious.applySettings(function(dflt){
		
			$("ul.sf-menu").superfish({
				delay: 500, // one second delay on mouseout 
				animation: {
					opacity: 'show',
					height: 'show'
				}, // fade-in and slide-down animation 
				speed: 'normal', // ,                          // faster animation speed 
				autoArrows: true,
				dropShadows: false // disable drop shadows 
			});
			
			$(".toggle-button").click(function(){
				$(this).toggleClass("down");
			});
			
			
			ddlicious.getLinks2(dflt);
			
			$("#raty-filter").raty({
				start: filter.ratingLevel,
				onClick: function(x){
					filter.ratingLevel = x;
					ddlicious.applyFilter();
				},
				showCancel: true
			});
			

			dropDownBuilder( "rating-comp", compy.vals, "ratingcomp", filter.ratingCompare, function( val ) {
				filter.ratingCompare = val;
				if ( val == "any") {
					$("#raty-filter").hide();	
				} else {
					$("#raty-filter").show();						
				}			
				ddlicious.applyFilter();					
			})
			
			dropDownBuilder( "frequency-comp", compy.vals, "freqcomp", filter.frequencyCompare, function(val ){
				filter.frequencyCompare = val;
				if ( val == "any") {
					$("#frequency-filter").hide();	
				} else {
					$("#frequency-filter").show();						
				}			
				ddlicious.applyFilter();					
			})
			dropDownBuilder( "frequency-filter", frequency.kinds, "freqselector", filter.frequencyLevel, function( val ) {
				filter.frequencyLevel = val;
				ddlicious.applyFilter();					
			}  );
			
			
			$("#pagify").click( function() {
				if (paged.isPaged()) {
					
					var sp = 0;
					
					var bms = ddlicious.getLinks();
					
					for( var i = 0; i < bms.length; i++ ) {
						var bookmark = bms[i];						
						if ($.inArray(paged.tag, bookmark.t) < 0) {
						
//							if ($.inArray("comic-page-7", bookmark.t) >= 0 && sp < 30) {
//								bookmark.t = arrayReplace(bookmark.t, "comic-page-7", "comic");
//								
//								setTimeout(link.store(bookmark), 1500);
//								sp++;
//							}
							
						// already paged
						}
						else {
							setTimeout(link.store(bookmark), 1500);
						}
					}
					
//					$.each(ddlicious.getLinks(), function(idx, bookmark){
////							$("#stt center").replaceWith( "<center>working on " + bookmark.d + "<center>");
//							if ( $.inArray( paged.tag, bookmark.t ) < 0 )  {
//								// already paged
//								return;	
//							}
//							
//		//					$("#state center").replaceWith( "<center>working on " + bookmark.d + "<center>");
//							
//							setTimeout(link.store( bookmark ), 1500);
//	//						ddlicious.applyFilter();							
//						});
				}						
			});
		});
	}
});
