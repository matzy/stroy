/**
 * jQuery Raty - A Star Rating Plugin - http://wbotelhos.com
 * --------------------------------------------------------------------------
 *
 * jQuery Raty is a plugin that generates a customizable star rating automatically.
 *
 * Licensed under The MIT License
 * 
 * @version     1.0.1
 * @since       06.11.2010
 * @author      Washington Botelho dos Santos
 * @link        http://wbotelhos.com/raty
 * @twitter     http://twitter.com/wbotelhos
 * @license     http://www.opensource.org/licenses/mit-license.php MIT 
 * @package     jQuery Plugins
 * 
 * Thanks to:
 * --------------------------------------------------------------------------
 * @glbenz
 * @franciscosouza
 * @olleolleolle
 *
 * Default values:
 * --------------------------------------------------------------------------
 * cancelHint:   'cancel this rating!'                            // The hint information.
 * cancelOff:    'cancel-off.png'                                 // Name of the cancel image off.
 * cancelOn:     'cancel-on.png'                                  // Name of the cancel image on.
 * cancelPlace:  'left'                                           // Position of the cancel button.
 * hintList:     ['bad', 'poor', 'regular', 'good', 'gorgeous']   // A hint information for default 5 stars.
 * iconRange:    []                                               // A range of custom icons that you can set.
 * noRatedMsg:   'not rated yet'                                  // A hint for no rated elements when it's read-only.
 * number:       5                                                // Number of star.
 * path:         'img                                             // Path of images.
 * readOnly:     false                                            // read-only or not.
 * scoreName:    'score'                                          // The name of target score.
 * showCancel:   false                                            // Show a button to cancel the rating or not.   
 * showHalf:     false                                            // Active the half star.
 * starHalf:     'star-half.png'                                  // The image of the half star.
 * starOff:      'star-off.png'                                   // Name of the star image on.
 * starOn:       'star-on.png'                                    // Name of the star image on.
 * start:        0                                                // Start with a score value.
 * onClick:      null                                             // Default onClick function.
 *
 *
 * Usage with default values:
 * --------------------------------------------------------------------------
 *
 * $('#star').raty();
 *
 * <div id="star"></div>
 *
 *
 * $('.group').raty();
 *
 * <div class="group"></div>
 * <div class="group"></div>
 * <div class="group"></div>
 *
 *
 * Public functions:
 * --------------------------------------------------------------------------
 * $.fn.raty.start(3);                                             // Starting the rating with 3 stars later.
 * $.fn.raty.readOnly(true);                                       // Adjusts the rating for read-only later.
 * $.fn.raty.click(2);                                             // Click in the second star later.
 *
 * You can pass a ID or a class to be the target of the action
 *
 * $.fn.raty.start(5, '#target');                                  // Starting the rating with ID named "target".
 *
 */