/* $Id: corefunctionsOverlayBoxes.js 2764 2014-08-06 14:11:04Z nixonm $ */
;(function($j) {

    $j(document).ready(function() {
        
        $j('a.thickbox').fancybox();
    	
		$j('a.overlayBox,a.cleverbox').bind('click',function() {
			
            //Get rid of any existing overlay content holding divs
			var $joverlayHolderDiv = $j('div#overlayHolder');
			if ($joverlayHolderDiv.length > 0)
			{
				$joverlayHolderDiv.remove();
			}
			
            //Get the page from which content will be displayed
            var $ja = $j(this);
            var $joverlaypageurl = $ja.attr('href');

            $j.get($joverlaypageurl, function(data){

                //Set up the div structure and styling to hold the content
    		    $joverlayHolderDiv = $j('<div id="overlayHolder"></div>').appendTo('div#content').hide();
                var $joverlayContentDiv = $j('<div id="overlayContent"></div>').appendTo('div#overlayHolder');
                
                //Get the title and main content from the page to display
                var $joverlayPage = $j(data);
                var $joverlayTitle = $joverlayPage.find('div#content h1').first();
                var $joverlayMainContentDiv = $joverlayPage.find('#mainContent');
                
                //Change the id of the main content (to prevent clashes with existing mainContent div)
                if ($joverlayMainContentDiv.length >0)
                {
                    $joverlayMainContentDiv.attr('id','overlayMainContent');
					$joverlayMainContentDiv.prepend($joverlayTitle);
                }
                
                //Append the content to the overlay content div and display using fancybox.
                
        	    $joverlayContentDiv.append($joverlayMainContentDiv);
                                
                $j.fancybox({
                    'autoDimensions': false,
                    'autoScale': true,
                    'height': '800',
                    'showCloseButton': true,
                    'centerOnScroll': true,
                    'href': '#overlayContent',
                    'onComplete': function()
                    {
                        var $jfancyboxContent = $j('div#fancybox-content');
                        var fancyboxContentHeight = $jfancyboxContent.css('height');
                        $j('div#fancybox-content > div').first().css('height', fancyboxContentHeight);
                        var fancyboxContentWidth = $jfancyboxContent.css('width');
                        $j('div#fancybox-content > div').first().css('width', fancyboxContentWidth);

                    }
                });
                
            });

            return false;
		});
	});

})(jQuery);