/**
 * @author SPF
 */

//var qsort = function( arr, cmp ) {
//	if ( arr.length < 2 ) {
//		return arr;
//	}
//	var pivot = arr[ Math.ceil(arr.length / 2 )];
//	var left = [];
//	var right = [];
//
//	for ( var i = 0; i < arr.length; i++ ) {
//		if ( cmp( arr[i], pivot ) <= 0 ) {
//			left.push(arr[i]);
//		} else {
//			right.push(arr[i]);
//		}
//	}
//
//	return qsort( left,cmp ).concat( qsort(right,cmp ));
//
//}

var tabby = function() {

    var main = {};

    var htmlTag = function( tag ) {
        var that = [];
        var clazzes = [];
        var theId;

        that.clazz = function( name ) {
            clazzes.push( name );
            return that;
        }

        that.id = function( name ) {
            theId = name;
            return that;
        }


        that.build = function() {
            var ret = "<" + tag + " ";

            for ( var i = 0; i < clazzes.length; i++ ) {
                ret += "class=\"" + clazzes[i] + "\" ";
            }

            if ( theId != undefined ) {
                ret += "id=\"" + theId + "\" ";
            }

            ret += ">";

            return ret;
        }

        return that;
    }


    main.cellDivAcc = function( row, idx  ) {
        return "<div id=\"tabby-cell-" + idx + "\"></div>"
    }

// function object style // the good parts
// makes nice tables from arrays
// follows the builder pattern
    main.tabby = function( arr ) {
        var that = {};
        var model = arr;
        var tabbyClass = 'tabby';
        var headerNames;
        var accessorFcts;
        var goZebra = false;
        var theSortFcts;
        var filterFct;
        var jqHooks;

        that.build = function( selector ) {

            if ( !validate()) {
                return;
            }

            $( selector ).replaceWith( buildHtml() );

            addHooks();

            hoverize();
    //		sort();
            //applyFilter();
            mkSortable();
            that.filter( function(x){ return true; });
            zebraize();

            return that;

        }

        var validate = function() {
            if ( accessorFcts == undefined ) {
                alert( "no accessors" );
                return false;
            }

            if ( theSortFcts != undefined && (theSortFcts.length != accessorFcts.length )) {
                alert( "wrong number of sort functions" );
                return false;
            }

            if ( headerNames != undefined && (headerNames.length != accessorFcts.length )) {
                alert( "wrong number of sort functions" );
                return false;
            }

            return true;
        }

        var addHooks = function() {
            for ( var i = 0; i < jqHooks.length; i++ ) {
                if ( jqHooks[i] != undefined ) {
                    for ( var r = 0; r < model.length; r++ ) {
                        (function() {
                            var jq = ".tabby tbody tr:nth-child("+ (r+1)  +") td:nth-child(" + (i+1) +")";
                            jqHooks[i]( jq , model[r] );
                        })();
                    }
                }
            }
        }

        that.sortFcts = function( fcts ) {
            theSortFcts = fcts;
            return that;
        }

        that.add = function( elem ) {
            model.push( elem );
            return that;
        }

    //	that.filter = function( filterFct) {
    //        filterFct
    //	    return that;
    //	}

    //	that.textFilter = function( selector ) {
    //	    textFilterSelector = selector;
    //
    //	    $(selector).keyup(function(event) {
    //            //if esc is pressed or nothing is entered
    //            if (event.keyCode == 27 || $(this).val() == '') {
    //              //if esc is pressed we want to clear the value of search box
    //              $(this).val('');
    //
    //              //we want each row to be visible because if nothing
    //              //is entered then all rows are matched.
    //              $('tbody tr').removeClass('visible').show().addClass('visible');
    //            }
    //
    //            //if there is text, lets filter
    //            else {
    //              filter('tbody tr', $(this).val());
    //            }
    //
    //            //reapply zebra rows
    //            $('.visible td').removeClass('odd');
    //            zebraRows('.visible:odd td', 'odd');
    //            }
    //         }
    //
    //	    return that;
    //	}


        var zebraize = function() {
            if ( goZebra ) {
                $( ".tabby tbody tr" ).removeClass( "odd" );
                $( ".tabby tbody tr.visible:odd" ).addClass( "odd" );
            }
        }

        var mkSortable = function() {
            if ( theSortFcts == undefined ) {
                return;
            }

            for ( var i = 0; i < theSortFcts.length; i++ ) {
                if ( theSortFcts[i] != undefined ) {
                    (function() {
                        var idx = i;
                        $( ".tabby thead th:nth-child("+ (idx + 1) +")" ).
                            click( function() {
                                var desc = $(this).hasClass( "desc" ) ? -1 : 1;
                                sort( idx, desc );
                                sortableinit( idx );
                                if ( desc == -1 ) {
                                    $(this).addClass( "asc" ).removeClass("desc");
                                } else {
                                    $(this).addClass( "desc" ).removeClass("sortable").removeClass("asc");
                                }
                                zebraize();
                            }).
                            addClass( "sortable" ) ;
                    })();
                }
            }
        }

        // reset all but the current header to default sortable
        var sortableinit = function( idx ) {
            for ( var i = 0; i < theSortFcts.length; i++ ) {
                if ( theSortFcts[i] != undefined && i != idx ) {
                    $( ".tabby thead th:nth-child("+ (i+ 1) +")" ).removeClass("asc").removeClass("desc").addClass("sortable");
                }
            }

        }


        var hoverize = function() {
            $( ".tabby tbody tr" ).hover( function() {
                $(this).toggleClass( "hovered" );
            });
        }

        that.filter = function( filterFct ) {
            var tableRows = $( ".tabby tbody > tr " ).get();

            for ( var i = 0; i < tableRows.length; i++ ) {
                if ( filterFct( domToElem(tableRows[i]))) {
                    $(tableRows[i]).removeClass("nonvisible").addClass( "visible");
                } else {
                    $(tableRows[i]).addClass( "nonvisible").removeClass("visible");
                }
            }

            zebraize();
        }

        that.hook = function( hookFcts ) {
            jqHooks = hookFcts;
            return that;
        }

        var domToElem = function( dom ) {
            var idx = parseInt( $(dom).attr("id").substring( "tabby-idx-".length ));

            if ( typeof(idx) != "number" ) {
                alert( "naN");
            }

            return model[idx];
        }

        var sort = function( idx, factor ) {
            var tableRows = $( ".tabby tbody > tr " ).get();

            var fct = getSortFct( idx );

            if ( fct == undefined ) {
                return;
            }

            tableRows.sort( function(a,b){
                return factor * fct( domToElem( a), domToElem(b));
            });

            for ( var r = 0; r < tableRows.length; r++ ) {
                $(".tabby tbody").append(tableRows[r]);
            }
        }

        that.alphaNumAcc = function( idx ) {
            return function( elemA, elemB) {
                var a = accessorFcts[ idx ]( elemA, idx );
                var b = accessorFcts[idx](elemB, idx );

                return a == b ? 0 : ( a < b ? -1 : 1);
            }
        }

        var getSortFct = function( idx ) {
            if ( theSortFcts == undefined ) {
                return undefined;
            }

            return theSortFcts[idx];
        }

        var buildHtml = function() {
            var ret = htmlTag( "table" ).
                        clazz( tabbyClass ).
                        build();

            ret += "<thead><tr>\n";
            for ( var i = 0; i < headerNames.length; i++ ) {
                ret += "<th>" + headerNames[i] + "</th>";
            }

            ret += "</tr></thead>\n<tbody>\n";

            for ( var r = 0; r < model.length; r++ ) {

                ret += "<tr id=\"tabby-idx-"+ r +"\">";// htmlTag( "tr" ).id( "tabby-" + r ).build();
                for ( var c = 0; c < getColCount(); c++ ) {
                    // ids needed for raty
                    ret += "<td id=\"tabby-cell-" + r + "-" + c + "\">" + that.getCellView( model[r], c ) + "</td>";
                }
                ret += "</tr>";
            }


            ret += "</tbody></table>"

            return ret;
        }

        that.headers = function( names ) {
            headerNames = names;
            return that;
        }

        that.accessors = function( fcts ) {
            accessorFcts = fcts;
            return that;
        }

        that.getCellView = function( row, idx ) {
            if ( accessorFcts == undefined ) {
                return row[idx];
            }

            if ( accessorFcts[idx] == undefined ) {
                return "";
            }

            return accessorFcts[idx]( row, idx );
        }

        that.zebra = function() {
            goZebra = true;
            return that;
        }


        var getColCount = function() {
            if ( accessorFcts == undefined ) {
                if (headerNames == undefined) {
                    return model[0].length;
                }
                return headerNames.length;
            }

            return accessorFcts.length;
        }

        return that;
     };

     main.alphaNum = function( acc ) {
            return function( elemA, elemB) {
                var a = acc( elemA );
                var b = acc( elemB );

                return a == b ? 0 : ( a < b ? -1 : 1);
            }
        }


    return main;
}();