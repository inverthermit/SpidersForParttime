/* ($Id: corefunctionsRevised.js 3112 2015-04-01 08:14:20Z winterjp $) */

;(function($j, global) {
    
    global.uob  = global.uob || {};
    
	var uob  = global.uob || {};
	uob.request = uob.request || {};

	uob.request.getQueryStringValue = function(name) {
        name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
        var regexS = "[\\?&]" + name + "=([^&#]*)";
        var regex = new RegExp(regexS);
        var results = regex.exec(window.location.search);
        if(results == null)
            return null;
        else
            return decodeURIComponent(results[1].replace(/\+/g, " "));
    }
	
    stopAnim = false;
	sliderAnim = false;
	sliderWidth = 710;
	timer = 0;
	expandedImage = false;
	overlayHeight =0;

	/* Thanks to http://www.hardcode.nl/ for providing the following script */
	function drawSocialLinks(oContainers){
			var l,i,socialList = [], socialHtm='';
			var t = $j('h1').eq(1).text();   /*  adjust this to select the title of your article */
			var u = window.location.href;  /*  this selects the link to your article */
			var iconDirectory = '/Images/website/icons/'; /* this is the director containing your icons */
	 
			var socialMedia = [
				{linkText: 'Del-icio-us', icon:'delicious32.png',href:'http://del.icio.us/post?url='+u+'&title='+t},
				{linkText: 'Stumbleupon', icon:'stumbleupon32.png',href:'http://www.stumbleupon.com/submit?url='+u},
				{linkText: 'Facebook', icon:'FB-f-Logo--blue-50.png',href:'http://www.facebook.com/share.php?u='+u}, 
				{linkText: 'Digg', icon:'digg32.png',href:'http://digg.com/submit?phase=2&url='+u+'&title='+t},
				{linkText: 'Twitter', icon:'twitter-bird-white-on-blue.png',href:'http://twitter.com/home?status='+u}, 
				{linkText: 'Google', icon:'google32.png',href:'http://www.google.com/bookmarks/mark?op=edit&bkmk='+u}
			];
	 
			l = socialMedia.length;
			for (i=0; i<l;i++){
				socialList.push('<li><a href="'+socialMedia[i].href+'" title="'+socialMedia[i].linkText+'"><img width="21" height="21" src="'+iconDirectory+socialMedia[i].icon+'" alt="'+socialMedia[i].linkText+'" /></a></li>');
			}
			socialHtm = '<ul><li class="sys_first">Bookmark this</li>'+socialList.join("\n")+'</ul>';
			oContainers.append('<div class="socialMediaContainer">'+socialHtm+'</div>'); 
			}


	function setAccordionSummaryText() {

		$jcontentCupboards = $j('div.sys_accordion.contentCupboard');
		
		if ($jcontentCupboards.length>0)
		{
			//This page has content cupboards so the top one will have the Open all button
			var topContentCupboard = $jcontentCupboards[0];
			if ($j('div.sys_accordion.contentCupboard>div:hidden').length>0)
			{
				$j(topContentCupboard).find('h2 span').html('Open all sections');
			}
			else {
				$j(topContentCupboard).find('h2 span').html('Close all sections');
			}
		}
		else{
			$j('div.sys_accordion').each(function() {

				//This page uses accordions with content grouped in them so each accordion can have an Open all button
				var $jaccordion = $j(this);

				if ($jaccordion.find('div:hidden').length > 0) {
					$jaccordion.find('h2.sys_summary span').html('Open all sections');
				} else {
					$jaccordion.find('h2.sys_summary span').html('Close all sections');
				};
			})
		}
	}


	$j(document).ready(function(){

		//Cookies pop-up
        $j.cookieCuttr();
        
        // Draw Social Bookmarks //
		drawSocialLinks($j('#bookmarks'));
        
        //check for WIley course - FAO MS temp ugly remove button by URL - to be replaced by additional field in course strcutured content in time 2015-03-31 
        checkWiley();
			
		//Contensis advert fade-away
			$j('.sys_fadeAway a').animate({ opacity: 1.0 }, 3000).fadeOut('slow', function() {
				$j(this).remove()
			});
		
		//Set up the search input box
		$j('#search .sys_textbox').focus(function(){
			if(this.value == 'Search...')
				this.value = '';
		});
		$j('#search .sys_textbox').blur(function(){
			if(this.value == '')
				this.value = 'Search...';
		});
		
		
		$jfeaturesDiv = $j('#features');
		
        // Global tracking of downloaded documents
                
        $("a[href$='.pdf']").click(function() {
            _gaq.push(["_trackEvent", "PDF Downloads", "Download", $(this).attr('href')]);
        });
        
        $("a[href$='.doc'], a[href$='.docx']").click(function() {
            _gaq.push(["_trackEvent", "Word Downloads", "Download", $(this).attr('href')]);
        });
		
		$("a[href$='.xls'], a[href$='.xlsx']").click(function() {
            _gaq.push(["_trackEvent", "Excel Downloads", "Download", $(this).attr('href')]);
        });
		
		$("a[href$='.swf']").click(function() {
            _gaq.push(["_trackEvent", "Shockwave Flash Downloads", "Download", $(this).attr('href')]);
        });
		
		$("a[href$='.flv']").click(function() {
            _gaq.push(["_trackEvent", "Flash Video Downloads", "Download", $(this).attr('href')]);
        });
		
		$("a[href$='.ppt'], a[href$='.pptx']").click(function() {
            _gaq.push(["_trackEvent", "PowerPoint Downloads", "Download", $(this).attr('href')]);
        });
		
		$("a[href$='.wav'], a[href$='.mp3']").click(function() {
            _gaq.push(["_trackEvent", "Audio Downloads", "Download", $(this).attr('href')]);
        });
		
		$("a[href$='.mov'],  a[href$='.wmv'], a[href$='.mp4'], a[href$='.avi']").click(function() {
            _gaq.push(["_trackEvent", "Video Downloads", "Download", $(this).attr('href')]);
        });
			
		/***************************************/
		// Top-level navigation
		/***************************************/
		$j("div#dropDownNavigation>ul>li>a").click(function(){
			var addClassToThisNavigation = !$j(this).parent().hasClass("sys_selectedHeader");
			clearGlobalNavigationDropdowns();
			if (addClassToThisNavigation){
				!$j(this).parent().addClass("sys_selectedHeader");
			}
			
		});
		
		$j("div#globalNavigation").mouseleave(function(){
			clearGlobalNavigationDropdowns();
		});
		
		var clearGlobalNavigationDropdowns = function ()
		{
			$j("div#dropDownNavigation>ul>li").removeClass("sys_selectedHeader");
		};
		
		
			//Slider tabs
			$jsliderWrapper = $j('div#sliderWrapper');

			$j('div.sys_sliderContent').mouseleave(function() {
				$jelem = $j(this).parent();

				$jelem.stop().css({ 'z-index': 500 });
				sliderAnim = true;
				$jsliderWrapper.animate({ 'width': 45 });
				$jelem.animate({ 'right': 0 - sliderWidth }, function() {
					$jelem.css({ 'z-index': $jelem.data('zindex') });
				sliderAnim = false;
			});
			//$jsliderWrapper.animate({ 'width': 45 });
		});

		$j('a.sys_sliderButton').click(function() {
			if (sliderAnim == false) {
				$jelem = $j(this).parent();
				$jelem.data('zindex', $jelem.css('z-index'));
				$jelem.css({ 'z-index': 500 });
				sliderAnim = true;
				$jsliderWrapper.animate({ 'width': sliderWidth + 90 });
				$jelem.animate({ 'right': 0 }, function() {
					sliderAnim = false;
				});
				//$jsliderWrapper.animate({ 'width': 45 });
			}
		 });
	 
		
		
	//for accordions
		$j('div.sys_accordion h2,div.sys_accordion h3.cupboard').each(function() {
			
			var $jh2 = $j(this);

			if ($jh2.hasClass('sys_summary')) {
				$jh2.append('<span>Open all sections</span>').bind('click',function() {

					if ($j(this).parent().parent().find('div.sys_squeezeBox:hidden').length > 0) {
						$j(this).parent().parent().find('div.sys_squeezeBox:hidden').slideDown('fast');
						$j(this).find('span').html('Close all sections');
						$j(this).parent().parent().find('h2:not(.sys_summary)').addClass('sys_collapse').removeClass('sys_expand');
					}
					else {
						$j(this).parent().parent().find('div.sys_squeezeBox').slideUp('fast');
						$j(this).find('span').html('Open all sections');
						$j(this).parent().parent().find('h2:not(.sys_summary)').removeClass('sys_collapse').addClass('sys_expand');
					};

				});
			} 
			else if (!$jh2.hasClass('sys_normal')) {
				//Only add sliders to headers that aren't marked as normal.
                
                //If the header isn't initialised as open then close it.
                if (!$jh2.hasClass('sys_collapse'))
                {
				    $jh2.next('div').hide();
                    $jh2.addClass('sys_expand');
                }

                //Bind click function to the header:
                $jh2.bind('click',function() {
					if ($j(this).hasClass('sys_expand')) {
						$j(this).addClass('sys_collapse').removeClass('sys_expand').next('div').slideDown('fast', setAccordionSummaryText);
					} else {
						$j(this).removeClass('sys_collapse').addClass('sys_expand').next('div').slideUp('fast', setAccordionSummaryText);
					};
				});
			};
			
		});
		
		//Add open all button to first content cupboard in a page:
		var $jcontentCupboards = $j('div.sys_accordion.contentCupboard');
		if ($jcontentCupboards.length>1)
		{
			var topCupboard = $jcontentCupboards[0];
			$j(topCupboard).find('h2').addClass('sys_openAllButton').append('<span>Open all sections</span>');
			
			$j(topCupboard).find('h2 span').bind('click',function() {

					if ($j('div.sys_accordion.contentCupboard>div:hidden').length > 0) {
						$j('div.sys_accordion.contentCupboard>div:hidden').slideDown('fast', setAccordionSummaryText);
						$j('div.sys_accordion.contentCupboard h2').addClass('sys_collapse').removeClass('sys_expand');
					}
					else {
						$j('div.sys_accordion.contentCupboard>div').slideUp('fast', setAccordionSummaryText);
						$j('div.sys_accordion.contentCupboard h2').removeClass('sys_collapse').addClass('sys_expand');
					};
					return false;
				});
		}
		        
        //jackie's small content cupboards
        $j('div.contentCupboardsmall h2').each(function() {
            var $jh2 = $(this);

            if (!$jh2.hasClass('sys_collapse'))
            {
                $jh2.next('div').hide();
                $jh2.addClass('sys_expand');
            }

            //Bind click function to the header:
            $jh2.bind('click',function() {
                if ($j(this).hasClass('sys_expand')) {
                    $j(this).addClass('sys_collapse').removeClass('sys_expand').next('div').slideDown('fast');
                } else {
                    $j(this).removeClass('sys_collapse').addClass('sys_expand').next('div').slideUp('fast');
                };
            });
        });
		
		//Converts media galleries to enable clicking on pictures.
		$j('div.sys_mediagallery-control div.sys_itemslist').each(function() {
			
			$jitemslist = $j(this);
			//Go through each item in the list
			$jitemslist.find('div.sys_subitem div').each(function() {
			
				$jitem = $j(this);
				//Get the anchor from inside the item
				$janchor = $jitem.find('h3 a');

				//Get the name of the picture and remove it from the anchor
				nameOfPicture = $janchor.html();
				$janchor.html('');

				//Wrap the anchor around the div for the item
				$jitem.wrap($janchor);

				//Find the original anchor and replace it with a span, putting in the name of the picture
				$jitem.find('h3 a').replaceWith(function(){
					return $j("<span>" + nameOfPicture+ "</span>");
				});
			});
		});

        var openSection = uob.request.getQueryStringValue('OpenSection');
        if (openSection !=null)
        {
            $j.scrollTo('#' + openSection, 1000);
        }

	});

	/* used to get rid of _blank so your code validates w3c stylee */
	function setExternalLinks() {
	  var el_list = document.getElementsByTagName('A');
	  for (i=0; i<el_list.length; i++) {
		if (el_list[i].getAttribute('rel') == 'external') {
		  el_list[i].setAttribute('target', '_blank');
		}
	  }
	}

	/* Check's users version of Flash */
	function checkFlash (version) {
		return DetectFlashVer(version, 0, 0);
	}
    
    function checkWiley () {
        var url = window.location.href;
        
        var wilies = ['courses/distance/govsoc/public-administration.aspx', 'courses/taught/business/online-msc-international-business.aspx', 'courses/taught/business/online-mba.aspx'];
        $j.each(wilies, function(key, cell){
            if (url.indexOf(cell) !== -1)
                $j('p.sys_registeryourinterest').hide();
        });
    };
    



})(jQuery, window);