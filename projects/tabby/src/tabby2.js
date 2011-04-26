var tabby = function() {

    var main = {};

    main.tabby = function( arr ) {

        var that = {};

        var model = arr;
        var columns = [];
//        var tabbyClass = 'tabby';
        var goZebra = false;
//        var theSortFcts;
//        var filterFct;


        that.column = function( colSpec ) {

            columns.push( colSpec );
            return that;
        }

        that.build = function( selector ) {

            $( selector ).replaceWith( buildHtml() );

            addHooks();
            hoverize();
//            //applyFilter();
            mkSortable();
//            that.filter( function(x){ return true; });
            zebraize();

            return that;
        }

        var buildHtml = function() {
            var ret = "<table class=\"tabby\">";

            // header
            ret += "<thead><tr>\n";
            for ( var i = 0; i < columns.length; i++ ) {
                ret += "<th>" + (columns[i].header == undefined ? ("column-" + i) : columns[i].header ) + "</th>";
            }
            ret += "</tr></thead>\n<tbody>\n";

            // body
            for ( var r = 0; r < model.length; r++ ) {

                ret += "<tr id=\"tabby-row-"+ r +"\">";
                for ( var c = 0; c < columns.length; c++ ) {
                    // ids needed for raty
                    ret += "<td id=\"tabby-cell-" + r + "-" + c + "\">" + that.getCellView( model[r], c ) + "</td>";
                }
                ret += "</tr>";
            }


            ret += "</tbody></table>"

            return ret;
        }

        that.getCellView = function( row, idx ) {
            var col = columns[idx];
            if ( col.accessor == undefined ) {
                return "";
            }
            return col.accessor(row);
        }

        var addHooks = function() {
            for ( var i = 0; i < columns.length; i++ ) {
                if ( columns[i].hook != undefined ) {
                    for ( var r = 0; r < model.length; r++ ) {
                        (function() {
                            var jq = ".tabby tbody tr:nth-child("+ (r+1)  +") td:nth-child(" + (i+1) +")";
                            columns[i].hook( jq , model[r] );
                        })();
                    }
                }
            }
        }

        that.zebra = function() {
            goZebra = true;
            return that;
        }

        var zebraize = function() {
            if ( goZebra ) {
                $( ".tabby tbody tr" ).removeClass( "odd" );
                $( ".tabby tbody tr.visible:odd" ).addClass( "odd" );
            }
        }

        var mkSortable = function() {
            for ( var i = 0; i < columns.length; i++ ) {
                if ( columns[i].sort != undefined ) {
                    (function() {
                        var idx = i;
                        $( ".tabby thead th:nth-child("+ (idx + 1) +")" ).
                            click( function() {
                                var desc = $(this).hasClass( "desc" ) ? -1 : 1;
                                sort( columns[idx].sort, desc );
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

        var sort = function( fct, factor ) {
            var tableRows = $( ".tabby tbody > tr " ).get();

            if ( fct == undefined ) {
                return;
            }

            tableRows.sort( function(a,b){
                return factor * fct( that.domToElem( a), that.domToElem(b));
            });

            for ( var r = 0; r < tableRows.length; r++ ) {
                $(".tabby tbody").append(tableRows[r]);
            }
        }

        // reset all but the current header to default sortable
        var sortableinit = function( idx ) {
            for ( var i = 0; i < columns.length; i++ ) {
                if ( columns[i].sort != undefined && i != idx ) {
                    $( ".tabby thead th:nth-child("+ (i+ 1) +")" ).removeClass("asc").removeClass("desc").addClass("sortable");
                }
            }
        }

        that.domToElem = function( dom ) {
            var idx = parseInt( $(dom).attr("id").substring( "tabby-idx-".length ));

            if ( typeof(idx) != "number" ) {
                alert( "naN");
            }

            return model[idx];
        }

        var hoverize = function() {
            $( ".tabby tbody tr" ).hover( function() {
                $(this).toggleClass( "hovered" );
            });
        }














        return that;

    }

    main.alphaNum = function( acc ) {
           return function( elemA, elemB) {
               var a = acc( elemA );
               var b = acc( elemB );
                return a == b ? 0 : ( a < b ? -1 : 1);
           }
    }



    return main;

}();