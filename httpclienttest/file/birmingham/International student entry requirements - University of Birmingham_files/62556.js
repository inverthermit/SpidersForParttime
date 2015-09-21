var flxpxlObj = (function() {
	var obj = {};

	obj.version = '110';

	obj.execute = function() {

		var bodyHTML = '';
		var bodyText = '';
		var bodyNormalized = '';
		var currentQueryTemp = '';
		var currentFunction = function(){};

		// Page group: Virtual Open Day
		var conditions_691533 = {};
		setTimeout(function() {
		function pageGroup_691533() {
			obj.placeAppNexusSegmentScript('seg?add=1075623&t=1', null, null, null, 'None', '');
			obj.placeAppNexusScript('px?id=68619&t=1', '68619');
			obj.placeAppNexusScript('px?id=55045&t=1', '55045');
			obj.placeAppNexusScript('px?id=55044&t=1', '55044');
			obj.placeAppNexusScript('px?id=54752&t=1', '54752');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691533[queryId]=true);if(checkConditions(conditions_691533)){pageGroup_691533();}});};
		if(
			(window.location.href.indexOf('Virtual-Open-Day') != -1)
		) {
			if(checkConditions(conditions_691533)){pageGroup_691533();}
		}
		}, 1);

		// Page group: Test Site
		var conditions_691534 = {};
		setTimeout(function() {
		function pageGroup_691534() {
			obj.placeAppNexusSegmentScript('seg?add=1756188&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691534[queryId]=true);if(checkConditions(conditions_691534)){pageGroup_691534();}});};
		if(
			(window.location.href.indexOf('http://test.birminghamdev1.bham.ac.uk') != -1) ||
			(window.location.href == 'http://test.birminghamdev1.bham.ac.uk/account/login.aspx?ReturnURL=http%3a%2f%2ftest.birminghamdev1.bham.ac.uk%2findex.aspx')
		) {
			if(checkConditions(conditions_691534)){pageGroup_691534();}
		}
		}, 1);

		// Page group: Undergraduate
		var conditions_691535 = {};
		setTimeout(function() {
		function pageGroup_691535() {
			obj.placeAppNexusSegmentScript('seg?add=1831158,1831159,1831070&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691535[queryId]=true);if(checkConditions(conditions_691535)){pageGroup_691535();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/index.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/') != -1) ||
			(window.location.href.indexOf('/undergraduate') != -1)
		) {
			if(checkConditions(conditions_691535)){pageGroup_691535();}
		}
		}, 1);

		// Page group: International Clearing
		var conditions_691536 = {};
		setTimeout(function() {
		function pageGroup_691536() {
			obj.placeAppNexusSegmentScript('seg?add=1855863&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691536[queryId]=true);if(checkConditions(conditions_691536)){pageGroup_691536();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International') != -1)
		) {
			if(checkConditions(conditions_691536)){pageGroup_691536();}
		}
		}, 1);

		// Page group: International Clearing/ Adjustment 
		var conditions_691537 = {};
		setTimeout(function() {
		function pageGroup_691537() {
			obj.placeAppNexusSegmentScript('seg?add=1910302,1831070&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691537[queryId]=true);if(checkConditions(conditions_691537)){pageGroup_691537();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/clearing-adjustment/international') != -1) ||
			(window.location.href.indexOf('/undergraduate/clearing-adjustment/international') != -1)
		) {
			if(checkConditions(conditions_691537)){pageGroup_691537();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/visit/ug-opendays.aspx
		var conditions_691538 = {};
		setTimeout(function() {
		function pageGroup_691538() {
			obj.placeAppNexusSegmentScript('seg?add=2048421&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691538[queryId]=true);if(checkConditions(conditions_691538)){pageGroup_691538();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/visit/ug-opendays.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/visit/ug-opendays.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/visit/ug-opendays') != -1)
		) {
			if(checkConditions(conditions_691538)){pageGroup_691538();}
		}
		}, 1);

		// Page group: Open Day sign
		var conditions_691539 = {};
		setTimeout(function() {
		function pageGroup_691539() {
			obj.placeAppNexusSegmentScript('seg?add=1691976&t=1', null, null, null, 'None', '');
			obj.placeAppNexusScript('px?id=325573&t=1', '325573');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691539[queryId]=true);if(checkConditions(conditions_691539)){pageGroup_691539();}});};
		if(
			(window.location.href == 'http://www.birmingham.ac.uk/undergraduate/visit/ug-opendays.aspx')
		) {
			if(checkConditions(conditions_691539)){pageGroup_691539();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/schools/gees/courses/undergraduate/index.aspx
		var conditions_691540 = {};
		setTimeout(function() {
		function pageGroup_691540() {
			obj.placeAppNexusSegmentScript('seg?add=2150464&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691540[queryId]=true);if(checkConditions(conditions_691540)){pageGroup_691540();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/gees/courses/undergraduate/index.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/gees/courses/undergraduate/') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/gees/courses/undergraduate/') != -1)
		) {
			if(checkConditions(conditions_691540)){pageGroup_691540();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/geology.aspx
		var conditions_691541 = {};
		setTimeout(function() {
		function pageGroup_691541() {
			obj.placeAppNexusSegmentScript('seg?add=2150477&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691541[queryId]=true);if(checkConditions(conditions_691541)){pageGroup_691541();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/geology.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/geology') != -1)
		) {
			if(checkConditions(conditions_691541)){pageGroup_691541();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-geology
		var conditions_691542 = {};
		setTimeout(function() {
		function pageGroup_691542() {
			obj.placeAppNexusSegmentScript('seg?add=2150547&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691542[queryId]=true);if(checkConditions(conditions_691542)){pageGroup_691542();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-geology') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/environmental-geology') != -1)
		) {
			if(checkConditions(conditions_691542)){pageGroup_691542();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-geography
		var conditions_691543 = {};
		setTimeout(function() {
		function pageGroup_691543() {
			obj.placeAppNexusSegmentScript('seg?add=2150558&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691543[queryId]=true);if(checkConditions(conditions_691543)){pageGroup_691543();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-geography') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/geology-geography') != -1)
		) {
			if(checkConditions(conditions_691543)){pageGroup_691543();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/palaeobiology-environment
		var conditions_691544 = {};
		setTimeout(function() {
		function pageGroup_691544() {
			obj.placeAppNexusSegmentScript('seg?add=2150579&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691544[queryId]=true);if(checkConditions(conditions_691544)){pageGroup_691544();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/palaeobiology-environment') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/palaeobiology-environment') != -1)
		) {
			if(checkConditions(conditions_691544)){pageGroup_691544();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-science.
		var conditions_691545 = {};
		setTimeout(function() {
		function pageGroup_691545() {
			obj.placeAppNexusSegmentScript('seg?add=2150590&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691545[queryId]=true);if(checkConditions(conditions_691545)){pageGroup_691545();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/environmental-science') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/environmental-science') != -1)
		) {
			if(checkConditions(conditions_691545)){pageGroup_691545();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/geography
		var conditions_691546 = {};
		setTimeout(function() {
		function pageGroup_691546() {
			obj.placeAppNexusSegmentScript('seg?add=2150604&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691546[queryId]=true);if(checkConditions(conditions_691546)){pageGroup_691546();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/geography') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/geography') != -1)
		) {
			if(checkConditions(conditions_691546)){pageGroup_691546();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-geography
		var conditions_691547 = {};
		setTimeout(function() {
		function pageGroup_691547() {
			obj.placeAppNexusSegmentScript('seg?add=2150622&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691547[queryId]=true);if(checkConditions(conditions_691547)){pageGroup_691547();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/geology-geography') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/geology-geography') != -1)
		) {
			if(checkConditions(conditions_691547)){pageGroup_691547();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-urban-joint
		var conditions_691548 = {};
		setTimeout(function() {
		function pageGroup_691548() {
			obj.placeAppNexusSegmentScript('seg?add=2150626&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691548[queryId]=true);if(checkConditions(conditions_691548)){pageGroup_691548();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/geography-urban-joint') != -1)
		) {
			if(checkConditions(conditions_691548)){pageGroup_691548();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/planning-economics
		var conditions_691549 = {};
		setTimeout(function() {
		function pageGroup_691549() {
			obj.placeAppNexusSegmentScript('seg?add=2150630&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691549[queryId]=true);if(checkConditions(conditions_691549)){pageGroup_691549();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/planning-economics') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/planning-economics') != -1)
		) {
			if(checkConditions(conditions_691549)){pageGroup_691549();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/spatial-planning-business
		var conditions_691550 = {};
		setTimeout(function() {
		function pageGroup_691550() {
			obj.placeAppNexusSegmentScript('seg?add=2150638&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691550[queryId]=true);if(checkConditions(conditions_691550)){pageGroup_691550();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/spatial-planning-business') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/spatial-planning-business') != -1)
		) {
			if(checkConditions(conditions_691550)){pageGroup_691550();}
		}
		}, 1);

		// Page group: http://www.birmingham.ac.uk/undergraduate/courses/gees/planning-social-policy
		var conditions_691551 = {};
		setTimeout(function() {
		function pageGroup_691551() {
			obj.placeAppNexusSegmentScript('seg?add=2150653&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691551[queryId]=true);if(checkConditions(conditions_691551)){pageGroup_691551();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees/planning-social-policy') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/gees/planning-social-policy') != -1)
		) {
			if(checkConditions(conditions_691551)){pageGroup_691551();}
		}
		}, 1);

		// Page group: Birmingham LES GEES RT
		var conditions_691552 = {};
		setTimeout(function() {
		function pageGroup_691552() {
			obj.placeAppNexusSegmentScript('seg?add=2218948&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691552[queryId]=true);if(checkConditions(conditions_691552)){pageGroup_691552();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/gees') != -1) ||
			(window.location.href.indexOf('undergraduate/courses/gees') != -1)
		) {
			if(checkConditions(conditions_691552)){pageGroup_691552();}
		}
		}, 1);

		// Page group: Birmingham MPharm Retargeting
		var conditions_691553 = {};
		setTimeout(function() {
		function pageGroup_691553() {
			obj.placeAppNexusSegmentScript('seg?add=2309963&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691553[queryId]=true);if(checkConditions(conditions_691553)){pageGroup_691553();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/med/pharmacy-4-year.aspx') != -1) ||
			(window.location.href.indexOf('pharmacy-4-year') != -1) ||
			(window.location.href.indexOf('pharmacy') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/pharmacy/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/university/colleges/mds/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/university/colleges/mds') != -1)
		) {
			if(checkConditions(conditions_691553)){pageGroup_691553();}
		}
		}, 1);

		// Page group: International Register Your Interest
		var conditions_691554 = {};
		setTimeout(function() {
		function pageGroup_691554() {
			obj.placeAppNexusScript('px?id=482685&t=1', '482685');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691554[queryId]=true);if(checkConditions(conditions_691554)){pageGroup_691554();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/international/students/register-your-interest.aspx') != -1) ||
			(window.location.href.indexOf('/international/students/register-your-interest') != -1)
		) {
			if(checkConditions(conditions_691554)){pageGroup_691554();}
		}
		}, 1);

		// Page group: Birmingham International Recruitment Retargeting
		var conditions_691555 = {};
		setTimeout(function() {
		function pageGroup_691555() {
			obj.placeAppNexusSegmentScript('seg?add=2386233&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691555[queryId]=true);if(checkConditions(conditions_691555)){pageGroup_691555();}});};
		if(
			(window.location.href.indexOf('http://virtualtour.bham.ac.uk/campus/international/') != -1) ||
			(window.location.href.indexOf('virtualtour.bham.ac.uk/campus/international') != -1) ||
			(window.location.href.indexOf('International') != -1)
		) {
			if(checkConditions(conditions_691555)){pageGroup_691555();}
		}
		}, 1);

		// Page group: Course Area: Psychology
		var conditions_691556 = {};
		setTimeout(function() {
		function pageGroup_691556() {
			obj.placeAppNexusSegmentScript('seg?add=2493801&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691556[queryId]=true);if(checkConditions(conditions_691556)){pageGroup_691556();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/psychology/study-here/postgraduate/index.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/psychology/study-here/postgraduate/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691556)){pageGroup_691556();}
		}
		}, 1);

		// Page group: Course Area: Sport, Exercise and Rehabilitation Sciences 
		var conditions_691557 = {};
		setTimeout(function() {
		function pageGroup_691557() {
			obj.placeAppNexusSegmentScript('seg?add=2493808&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691557[queryId]=true);if(checkConditions(conditions_691557)){pageGroup_691557();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/sport-exercise/courses/postgraduate/masters.aspx ') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/sport-exercise/courses/postgraduate/masters.aspx ') != -1)
		) {
			if(checkConditions(conditions_691557)){pageGroup_691557();}
		}
		}, 1);

		// Page group: Course Area: Biosciences
		var conditions_691558 = {};
		setTimeout(function() {
		function pageGroup_691558() {
			obj.placeAppNexusSegmentScript('seg?add=2493815&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691558[queryId]=true);if(checkConditions(conditions_691558)){pageGroup_691558();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/biosciences/courses/postgraduate/index.aspx ') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/biosciences/courses/postgraduate/index.aspx ') != -1)
		) {
			if(checkConditions(conditions_691558)){pageGroup_691558();}
		}
		}, 1);

		// Page group: Course Area: Geography, Earth and Environmental Sciences 
		var conditions_691559 = {};
		setTimeout(function() {
		function pageGroup_691559() {
			obj.placeAppNexusSegmentScript('seg?add=2493817&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691559[queryId]=true);if(checkConditions(conditions_691559)){pageGroup_691559();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/gees/courses/postgraduate/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/gees/courses/postgraduate/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691559)){pageGroup_691559();}
		}
		}, 1);

		// Page group: MA Psychology
		var conditions_691560 = {};
		setTimeout(function() {
		function pageGroup_691560() {
			obj.placeAppNexusSegmentScript('seg?add=2493945&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691560[queryId]=true);if(checkConditions(conditions_691560)){pageGroup_691560();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/psych/psychology-ma.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/psych/psychology-ma.aspx') != -1)
		) {
			if(checkConditions(conditions_691560)){pageGroup_691560();}
		}
		}, 1);

		// Page group: MSc Psychology
		var conditions_691561 = {};
		setTimeout(function() {
		function pageGroup_691561() {
			obj.placeAppNexusSegmentScript('seg?add=2493966&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691561[queryId]=true);if(checkConditions(conditions_691561)){pageGroup_691561();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/psych/psychology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/psych/psychology.aspx') != -1)
		) {
			if(checkConditions(conditions_691561)){pageGroup_691561();}
		}
		}, 1);

		// Page group: MSc Cognitive Behaviour Therapy
		var conditions_691562 = {};
		setTimeout(function() {
		function pageGroup_691562() {
			obj.placeAppNexusSegmentScript('seg?add=2493968&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691562[queryId]=true);if(checkConditions(conditions_691562)){pageGroup_691562();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/cpd/psych/cognitive-behaviour-therapy-msc.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/cpd/psych/cognitive-behaviour-therapy-msc.aspx') != -1)
		) {
			if(checkConditions(conditions_691562)){pageGroup_691562();}
		}
		}, 1);

		// Page group: MSc Brain Imaging and Cognitive Neuroscience
		var conditions_691563 = {};
		setTimeout(function() {
		function pageGroup_691563() {
			obj.placeAppNexusSegmentScript('seg?add=2493973&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691563[queryId]=true);if(checkConditions(conditions_691563)){pageGroup_691563();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/psych/brain-imaging-cognitive-neuro.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/psych/brain-imaging-cognitive-neuro.aspx') != -1)
		) {
			if(checkConditions(conditions_691563)){pageGroup_691563();}
		}
		}, 1);

		// Page group: MSc Computational Neuroscience and Cognitive Robotics
		var conditions_691564 = {};
		setTimeout(function() {
		function pageGroup_691564() {
			obj.placeAppNexusSegmentScript('seg?add=2493978&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691564[queryId]=true);if(checkConditions(conditions_691564)){pageGroup_691564();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/psych/computation-neuro-cognitive-robotics.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/psych/computation-neuro-cognitive-robotics.aspx') != -1)
		) {
			if(checkConditions(conditions_691564)){pageGroup_691564();}
		}
		}, 1);

		// Page group: MSc Sport Policy, Business and Management
		var conditions_691565 = {};
		setTimeout(function() {
		function pageGroup_691565() {
			obj.placeAppNexusSegmentScript('seg?add=2493980&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691565[queryId]=true);if(checkConditions(conditions_691565)){pageGroup_691565();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/sport-policy-business-management.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/sport-policy-business-management.aspx') != -1)
		) {
			if(checkConditions(conditions_691565)){pageGroup_691565();}
		}
		}, 1);

		// Page group: MSc Exercise and Sport Sciences
		var conditions_691566 = {};
		setTimeout(function() {
		function pageGroup_691566() {
			obj.placeAppNexusSegmentScript('seg?add=2493982&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691566[queryId]=true);if(checkConditions(conditions_691566)){pageGroup_691566();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/students/courses/postgraduate/taught/sport-exercise/exercise-sport-sciences.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/students/courses/postgraduate/taught/sport-exercise/exercise-sport-sciences.aspx') != -1)
		) {
			if(checkConditions(conditions_691566)){pageGroup_691566();}
		}
		}, 1);

		// Page group: MSc Physical Education and Sport Pedagogy
		var conditions_691567 = {};
		setTimeout(function() {
		function pageGroup_691567() {
			obj.placeAppNexusSegmentScript('seg?add=2493986&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691567[queryId]=true);if(checkConditions(conditions_691567)){pageGroup_691567();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/sport-physical-education-sport-pedagogy.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/sport-physical-education-sport-pedagogy.aspx') != -1)
		) {
			if(checkConditions(conditions_691567)){pageGroup_691567();}
		}
		}, 1);

		// Page group: MSc Exercise and Sports Medicine (Football) MSc/Postgraduate Diploma
		var conditions_691568 = {};
		setTimeout(function() {
		function pageGroup_691568() {
			obj.placeAppNexusSegmentScript('seg?add=2493987&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691568[queryId]=true);if(checkConditions(conditions_691568)){pageGroup_691568();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/exercise-sports-medicine-football.aspx#CourseOverviewTab') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/exercise-sports-medicine-football.aspx#CourseOverviewTab') != -1)
		) {
			if(checkConditions(conditions_691568)){pageGroup_691568();}
		}
		}, 1);

		// Page group: MSc/Postgraduate Diploma Advanced Manipulative Physiotherapy
		var conditions_691569 = {};
		setTimeout(function() {
		function pageGroup_691569() {
			obj.placeAppNexusSegmentScript('seg?add=2493988&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691569[queryId]=true);if(checkConditions(conditions_691569)){pageGroup_691569();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/advanced-manipulative-physio.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/advanced-manipulative-physio.aspx') != -1)
		) {
			if(checkConditions(conditions_691569)){pageGroup_691569();}
		}
		}, 1);

		// Page group: MSc/Postgraduate Diploma/Postgraduate Certificate Advancing Practice
		var conditions_691570 = {};
		setTimeout(function() {
		function pageGroup_691570() {
			obj.placeAppNexusSegmentScript('seg?add=2493990&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691570[queryId]=true);if(checkConditions(conditions_691570)){pageGroup_691570();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/advancing-practice.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/advancing-practice.aspx') != -1)
		) {
			if(checkConditions(conditions_691570)){pageGroup_691570();}
		}
		}, 1);

		// Page group: MSc Health Studies
		var conditions_691571 = {};
		setTimeout(function() {
		function pageGroup_691571() {
			obj.placeAppNexusSegmentScript('seg?add=2494258&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691571[queryId]=true);if(checkConditions(conditions_691571)){pageGroup_691571();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/health-studies.aspx') != -1)
		) {
			if(checkConditions(conditions_691571)){pageGroup_691571();}
		}
		}, 1);

		// Page group: MSc Physiotherapy (pre-registration)
		var conditions_691572 = {};
		setTimeout(function() {
		function pageGroup_691572() {
			obj.placeAppNexusSegmentScript('seg?add=2494267&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691572[queryId]=true);if(checkConditions(conditions_691572)){pageGroup_691572();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/physio-pre-reg.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/physio-pre-reg.aspx') != -1)
		) {
			if(checkConditions(conditions_691572)){pageGroup_691572();}
		}
		}, 1);

		// Page group: MSc/Postgraduate Diploma Sport Coaching
		var conditions_691573 = {};
		setTimeout(function() {
		function pageGroup_691573() {
			obj.placeAppNexusSegmentScript('seg?add=2494272&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691573[queryId]=true);if(checkConditions(conditions_691573)){pageGroup_691573();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/sport-coaching.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/sport-coaching.aspx') != -1)
		) {
			if(checkConditions(conditions_691573)){pageGroup_691573();}
		}
		}, 1);

		// Page group: MSc Toxicology
		var conditions_691574 = {};
		setTimeout(function() {
		function pageGroup_691574() {
			obj.placeAppNexusSegmentScript('seg?add=2494279&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691574[queryId]=true);if(checkConditions(conditions_691574)){pageGroup_691574();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/biosciences/toxicology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/biosciences/toxicology.aspx') != -1)
		) {
			if(checkConditions(conditions_691574)){pageGroup_691574();}
		}
		}, 1);

		// Page group: MSc Molecular Biotechnology
		var conditions_691575 = {};
		setTimeout(function() {
		function pageGroup_691575() {
			obj.placeAppNexusSegmentScript('seg?add=2494293&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691575[queryId]=true);if(checkConditions(conditions_691575)){pageGroup_691575();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/biosciences/molecular-biotechnology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/biosciences/molecular-biotechnology.aspx') != -1)
		) {
			if(checkConditions(conditions_691575)){pageGroup_691575();}
		}
		}, 1);

		// Page group: MSc Microbiology and Infection
		var conditions_691576 = {};
		setTimeout(function() {
		function pageGroup_691576() {
			obj.placeAppNexusSegmentScript('seg?add=2494305&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691576[queryId]=true);if(checkConditions(conditions_691576)){pageGroup_691576();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/biosciences/microbiology-infection.aspx  ') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/biosciences/microbiology-infection.aspx  ') != -1)
		) {
			if(checkConditions(conditions_691576)){pageGroup_691576();}
		}
		}, 1);

		// Page group: MSc River Environments and Their Management
		var conditions_691577 = {};
		setTimeout(function() {
		function pageGroup_691577() {
			obj.placeAppNexusSegmentScript('seg?add=2494442&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691577[queryId]=true);if(checkConditions(conditions_691577)){pageGroup_691577();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/river-environ-mgt.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/river-environ-mgt.aspx') != -1)
		) {
			if(checkConditions(conditions_691577)){pageGroup_691577();}
		}
		}, 1);

		// Page group: MSc Public and Environmental Health Science 
		var conditions_691578 = {};
		setTimeout(function() {
		function pageGroup_691578() {
			obj.placeAppNexusSegmentScript('seg?add=2494445&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691578[queryId]=true);if(checkConditions(conditions_691578)){pageGroup_691578();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/public-environ-health-science.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/public-environ-health-science.aspx') != -1)
		) {
			if(checkConditions(conditions_691578)){pageGroup_691578();}
		}
		}, 1);

		// Page group: MSc Occupational Health, Safety and the Environment
		var conditions_691579 = {};
		setTimeout(function() {
		function pageGroup_691579() {
			obj.placeAppNexusSegmentScript('seg?add=2494452&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691579[queryId]=true);if(checkConditions(conditions_691579)){pageGroup_691579();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/science-occupational-health-safety-environ.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/science-occupational-health-safety-environ.aspx') != -1)
		) {
			if(checkConditions(conditions_691579)){pageGroup_691579();}
		}
		}, 1);

		// Page group: MSc Applied and Petroleum Micropalaeontology
		var conditions_691580 = {};
		setTimeout(function() {
		function pageGroup_691580() {
			obj.placeAppNexusSegmentScript('seg?add=2494453&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691580[queryId]=true);if(checkConditions(conditions_691580)){pageGroup_691580();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/micropalaeontology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/micropalaeontology.aspx') != -1)
		) {
			if(checkConditions(conditions_691580)){pageGroup_691580();}
		}
		}, 1);

		// Page group: MSc Hydrogeology
		var conditions_691581 = {};
		setTimeout(function() {
		function pageGroup_691581() {
			obj.placeAppNexusSegmentScript('seg?add=2494457&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691581[queryId]=true);if(checkConditions(conditions_691581)){pageGroup_691581();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/hydrogeology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/hydrogeology.aspx') != -1)
		) {
			if(checkConditions(conditions_691581)){pageGroup_691581();}
		}
		}, 1);

		// Page group: MSc Environmental Health
		var conditions_691582 = {};
		setTimeout(function() {
		function pageGroup_691582() {
			obj.placeAppNexusSegmentScript('seg?add=2494459&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691582[queryId]=true);if(checkConditions(conditions_691582)){pageGroup_691582();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/environmental-health.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/environmental-health.aspx') != -1)
		) {
			if(checkConditions(conditions_691582)){pageGroup_691582();}
		}
		}, 1);

		// Page group: MSc Urban and Regional Planning
		var conditions_691583 = {};
		setTimeout(function() {
		function pageGroup_691583() {
			obj.placeAppNexusSegmentScript('seg?add=2494479&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691583[queryId]=true);if(checkConditions(conditions_691583)){pageGroup_691583();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/urban-regional-planning.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/urban-regional-planning.aspx') != -1)
		) {
			if(checkConditions(conditions_691583)){pageGroup_691583();}
		}
		}, 1);

		// Page group: MSc Applied Meteorology and Climatology
		var conditions_691584 = {};
		setTimeout(function() {
		function pageGroup_691584() {
			obj.placeAppNexusSegmentScript('seg?add=2494482&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691584[queryId]=true);if(checkConditions(conditions_691584)){pageGroup_691584();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/applied-met-climatology.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/applied-met-climatology.aspx') != -1)
		) {
			if(checkConditions(conditions_691584)){pageGroup_691584();}
		}
		}, 1);

		// Page group: MSc Nuclear Decommissioning and Waste Management
		var conditions_691585 = {};
		setTimeout(function() {
		function pageGroup_691585() {
			obj.placeAppNexusSegmentScript('seg?add=2494484&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691585[queryId]=true);if(checkConditions(conditions_691585)){pageGroup_691585();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/nuclear-decommissioning.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/nuclear-decommissioning.aspx') != -1)
		) {
			if(checkConditions(conditions_691585)){pageGroup_691585();}
		}
		}, 1);

		// Page group: MSc Air Pollution Management and Control
		var conditions_691586 = {};
		setTimeout(function() {
		function pageGroup_691586() {
			obj.placeAppNexusSegmentScript('seg?add=2494490&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691586[queryId]=true);if(checkConditions(conditions_691586)){pageGroup_691586();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/air-pollution-mgt-ctrl.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/air-pollution-mgt-ctrl.aspx') != -1)
		) {
			if(checkConditions(conditions_691586)){pageGroup_691586();}
		}
		}, 1);

		// Page group: MSc Research in Human Geography
		var conditions_691587 = {};
		setTimeout(function() {
		function pageGroup_691587() {
			obj.placeAppNexusSegmentScript('seg?add=2494494&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691587[queryId]=true);if(checkConditions(conditions_691587)){pageGroup_691587();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/postgraduate/courses/taught/gees/research-human-geography.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/gees/research-human-geography.aspx') != -1)
		) {
			if(checkConditions(conditions_691587)){pageGroup_691587();}
		}
		}, 1);

		// Page group: BFA Generic
		var conditions_691588 = {};
		setTimeout(function() {
		function pageGroup_691588() {
			obj.placeAppNexusSegmentScript('seg?add=2509183&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691588[queryId]=true);if(checkConditions(conditions_691588)){pageGroup_691588();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/foundation-academy/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/foundation-academy/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691588)){pageGroup_691588();}
		}
		}, 1);

		// Page group: BFA USA
		var conditions_691589 = {};
		setTimeout(function() {
		function pageGroup_691589() {
			obj.placeAppNexusSegmentScript('seg?add=2509199&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691589[queryId]=true);if(checkConditions(conditions_691589)){pageGroup_691589();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/north-american-students/bfa-usa.aspx?') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/north-american-students/bfa-usa.aspx?') != -1)
		) {
			if(checkConditions(conditions_691589)){pageGroup_691589();}
		}
		}, 1);

		// Page group: Birmingham International
		var conditions_691590 = {};
		setTimeout(function() {
		function pageGroup_691590() {
			obj.placeAppNexusSegmentScript('seg?add=2511806&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691590[queryId]=true);if(checkConditions(conditions_691590)){pageGroup_691590();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/international/index.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/international/index.aspx') != -1) ||
			(window.location.href.indexOf('.ac.uk/international/') != -1)
		) {
			if(checkConditions(conditions_691590)){pageGroup_691590();}
		}
		}, 1);

		// Page group: LES Sports Excercise
		var conditions_691591 = {};
		setTimeout(function() {
		function pageGroup_691591() {
			obj.placeAppNexusSegmentScript('seg?add=2512652&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691591[queryId]=true);if(checkConditions(conditions_691591)){pageGroup_691591();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/sport-exercise/courses/postgraduate/masters.aspx?') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/sport-exercise/courses/postgraduate/masters.aspx?') != -1)
		) {
			if(checkConditions(conditions_691591)){pageGroup_691591();}
		}
		}, 1);

		// Page group: LES Psychology
		var conditions_691592 = {};
		setTimeout(function() {
		function pageGroup_691592() {
			obj.placeAppNexusSegmentScript('seg?add=2512654&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691592[queryId]=true);if(checkConditions(conditions_691592)){pageGroup_691592();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/psychology/study-here/postgraduate/index.aspx?') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/psychology/study-here/postgraduate/index.aspx?') != -1)
		) {
			if(checkConditions(conditions_691592)){pageGroup_691592();}
		}
		}, 1);

		// Page group: LES Geography
		var conditions_691593 = {};
		setTimeout(function() {
		function pageGroup_691593() {
			obj.placeAppNexusSegmentScript('seg?add=2512657&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691593[queryId]=true);if(checkConditions(conditions_691593)){pageGroup_691593();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/gees/courses/postgraduate/index.aspx?') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/gees/courses/postgraduate/index.aspx?') != -1)
		) {
			if(checkConditions(conditions_691593)){pageGroup_691593();}
		}
		}, 1);

		// Page group: LES Bioscience
		var conditions_691594 = {};
		setTimeout(function() {
		function pageGroup_691594() {
			obj.placeAppNexusSegmentScript('seg?add=2512661&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691594[queryId]=true);if(checkConditions(conditions_691594)){pageGroup_691594();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/schools/biosciences/courses/postgraduate/index.aspx?') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/schools/biosciences/courses/postgraduate/index.aspx?') != -1)
		) {
			if(checkConditions(conditions_691594)){pageGroup_691594();}
		}
		}, 1);

		// Page group: Birmingham LES School
		var conditions_691595 = {};
		setTimeout(function() {
		function pageGroup_691595() {
			obj.placeAppNexusSegmentScript('seg?add=2518994&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691595[queryId]=true);if(checkConditions(conditions_691595)){pageGroup_691595();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/university/colleges/les/index.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/university/colleges/les/index.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/university/colleges/les/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691595)){pageGroup_691595();}
		}
		}, 1);

		// Page group: LES Open Day Interest
		var conditions_691596 = {};
		setTimeout(function() {
		function pageGroup_691596() {
			obj.placeAppNexusScript('px?id=499558&t=1', '499558');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691596[queryId]=true);if(checkConditions(conditions_691596)){pageGroup_691596();}});};
		if(
false
		) {
			if(checkConditions(conditions_691596)){pageGroup_691596();}
		}
		}, 1);

		// Page group: BISS RT China
		var conditions_691597 = {};
		setTimeout(function() {
		function pageGroup_691597() {
			obj.placeAppNexusSegmentScript('seg?add=2676052&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691597[queryId]=true);if(checkConditions(conditions_691597)){pageGroup_691597();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1)
		) {
			if(checkConditions(conditions_691597)){pageGroup_691597();}
		}
		}, 1);

		// Page group: BISS RT Singapore
		var conditions_691598 = {};
		setTimeout(function() {
		function pageGroup_691598() {
			obj.placeAppNexusSegmentScript('seg?add=2676053&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691598[queryId]=true);if(checkConditions(conditions_691598)){pageGroup_691598();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1)
		) {
			if(checkConditions(conditions_691598)){pageGroup_691598();}
		}
		}, 1);

		// Page group: BISS RT Australia
		var conditions_691599 = {};
		setTimeout(function() {
		function pageGroup_691599() {
			obj.placeAppNexusSegmentScript('seg?add=2676056&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691599[queryId]=true);if(checkConditions(conditions_691599)){pageGroup_691599();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1)
		) {
			if(checkConditions(conditions_691599)){pageGroup_691599();}
		}
		}, 1);

		// Page group: BISS RT Canada
		var conditions_691600 = {};
		setTimeout(function() {
		function pageGroup_691600() {
			obj.placeAppNexusSegmentScript('seg?add=2676058&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691600[queryId]=true);if(checkConditions(conditions_691600)){pageGroup_691600();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1)
		) {
			if(checkConditions(conditions_691600)){pageGroup_691600();}
		}
		}, 1);

		// Page group: BISS RT Europe
		var conditions_691601 = {};
		setTimeout(function() {
		function pageGroup_691601() {
			obj.placeAppNexusSegmentScript('seg?add=2676059&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691601[queryId]=true);if(checkConditions(conditions_691601)){pageGroup_691601();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1)
		) {
			if(checkConditions(conditions_691601)){pageGroup_691601();}
		}
		}, 1);

		// Page group: BISS RT Hong Kong
		var conditions_691602 = {};
		setTimeout(function() {
		function pageGroup_691602() {
			obj.placeAppNexusSegmentScript('seg?add=2676060&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691602[queryId]=true);if(checkConditions(conditions_691602)){pageGroup_691602();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1)
		) {
			if(checkConditions(conditions_691602)){pageGroup_691602();}
		}
		}, 1);

		// Page group: BISS RT America
		var conditions_691603 = {};
		setTimeout(function() {
		function pageGroup_691603() {
			obj.placeAppNexusSegmentScript('seg?add=2676064&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691603[queryId]=true);if(checkConditions(conditions_691603)){pageGroup_691603();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1) ||
			(window.location.href.indexOf('htp://www.birmingham.ac.uk/biss?utm_source=facebook&utm_medium=rhs&utm_campaign=5018_UoB_BISS-FBRT') != -1)
		) {
			if(checkConditions(conditions_691603)){pageGroup_691603();}
		}
		}, 1);

		// Page group: bfa-interest-form
		var conditions_691604 = {};
		setTimeout(function() {
		function pageGroup_691604() {
			obj.placeAppNexusScript('px?id=523261&t=1', '523261');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691604[queryId]=true);if(checkConditions(conditions_691604)){pageGroup_691604();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/foundation-academy/bfa-interest-form.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/foundation-academy/bfa-interest-form.aspx') != -1)
		) {
			if(checkConditions(conditions_691604)){pageGroup_691604();}
		}
		}, 1);

		// Page group: BFA-download-prospectus
		var conditions_691605 = {};
		setTimeout(function() {
		function pageGroup_691605() {
			obj.placeAppNexusScript('px?id=523579&t=1', '523579');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691605[queryId]=true);if(checkConditions(conditions_691605)){pageGroup_691605();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/foundation-academy/BFA-download-prospectus.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/foundation-academy/BFA-download-prospectus.aspx') != -1)
		) {
			if(checkConditions(conditions_691605)){pageGroup_691605();}
		}
		}, 1);

		// Page group: BFA-how-to-apply
		var conditions_691606 = {};
		setTimeout(function() {
		function pageGroup_691606() {
			obj.placeAppNexusScript('px?id=523583&t=1', '523583');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691606[queryId]=true);if(checkConditions(conditions_691606)){pageGroup_691606();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/foundation-academy/how-to-apply/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/foundation-academy/how-to-apply/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691606)){pageGroup_691606();}
		}
		}, 1);

		// Page group: BFA-entry-requirements
		var conditions_691607 = {};
		setTimeout(function() {
		function pageGroup_691607() {
			obj.placeAppNexusScript('px?id=523585&t=1', '523585');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691607[queryId]=true);if(checkConditions(conditions_691607)){pageGroup_691607();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/foundation-academy/entry-requirements/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/foundation-academy/entry-requirements/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691607)){pageGroup_691607();}
		}
		}, 1);

		// Page group: BISS - Application page
		var conditions_691608 = {};
		setTimeout(function() {
		function pageGroup_691608() {
			obj.placeAppNexusSegmentScript('seg?add=2804689&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691608[queryId]=true);if(checkConditions(conditions_691608)){pageGroup_691608();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/application-form-biss.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/application-form-biss.aspx') != -1)
		) {
			if(checkConditions(conditions_691608)){pageGroup_691608();}
		}
		}, 1);

		// Page group: BISS - HomePage
		var conditions_691609 = {};
		setTimeout(function() {
		function pageGroup_691609() {
			obj.placeAppNexusSegmentScript('seg?add=2804691&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691609[queryId]=true);if(checkConditions(conditions_691609)){pageGroup_691609();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691609)){pageGroup_691609();}
		}
		}, 1);

		// Page group: BISS Programmes RT
		var conditions_691610 = {};
		setTimeout(function() {
		function pageGroup_691610() {
			obj.placeAppNexusSegmentScript('seg?add=2804692&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691610[queryId]=true);if(checkConditions(conditions_691610)){pageGroup_691610();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/cultural-heritage-programme.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/cultural-heritage-programme.aspx') != -1)
		) {
			if(checkConditions(conditions_691610)){pageGroup_691610();}
		}
		}, 1);

		// Page group: BISS Entry requirements
		var conditions_691611 = {};
		setTimeout(function() {
		function pageGroup_691611() {
			obj.placeAppNexusSegmentScript('seg?add=2804694&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691611[queryId]=true);if(checkConditions(conditions_691611)){pageGroup_691611();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/entry-requirements.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/entry-requirements.aspx') != -1)
		) {
			if(checkConditions(conditions_691611)){pageGroup_691611();}
		}
		}, 1);

		// Page group: BISS fees
		var conditions_691612 = {};
		setTimeout(function() {
		function pageGroup_691612() {
			obj.placeAppNexusSegmentScript('seg?add=2804695&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691612[queryId]=true);if(checkConditions(conditions_691612)){pageGroup_691612();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/fees.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/fees.aspx') != -1)
		) {
			if(checkConditions(conditions_691612)){pageGroup_691612();}
		}
		}, 1);

		// Page group: BISS Visa advice
		var conditions_691613 = {};
		setTimeout(function() {
		function pageGroup_691613() {
			obj.placeAppNexusSegmentScript('seg?add=2804696&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691613[queryId]=true);if(checkConditions(conditions_691613)){pageGroup_691613();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/visa-information-biss.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/visa-information-biss.aspx') != -1)
		) {
			if(checkConditions(conditions_691613)){pageGroup_691613();}
		}
		}, 1);

		// Page group: BISS Key dates
		var conditions_691614 = {};
		setTimeout(function() {
		function pageGroup_691614() {
			obj.placeAppNexusSegmentScript('seg?add=2804698&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691614[queryId]=true);if(checkConditions(conditions_691614)){pageGroup_691614();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/key-dates.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/key-dates.aspx') != -1)
		) {
			if(checkConditions(conditions_691614)){pageGroup_691614();}
		}
		}, 1);

		// Page group: BISS Contacts
		var conditions_691615 = {};
		setTimeout(function() {
		function pageGroup_691615() {
			obj.placeAppNexusSegmentScript('seg?add=2804699&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691615[queryId]=true);if(checkConditions(conditions_691615)){pageGroup_691615();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/birmingham-international-summer-school/Contact-us.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/birmingham-international-summer-school/Contact-us.aspx') != -1)
		) {
			if(checkConditions(conditions_691615)){pageGroup_691615();}
		}
		}, 1);

		// Page group: Intl PG Pathways
		var conditions_691616 = {};
		setTimeout(function() {
		function pageGroup_691616() {
			obj.placeAppNexusSegmentScript('seg?add=2878182&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691616[queryId]=true);if(checkConditions(conditions_691616)){pageGroup_691616();}});};
		if(
			(window.location.href.indexOf('http://birmingham.www.campaignpage.co.uk/international-pg-pathways/') != -1) ||
			(window.location.href.indexOf('birmingham.www.campaignpage.co.uk/international-pg-pathways/') != -1)
		) {
			if(checkConditions(conditions_691616)){pageGroup_691616();}
		}
		}, 1);

		// Page group: International pre-Masters
		var conditions_691617 = {};
		setTimeout(function() {
		function pageGroup_691617() {
			obj.placeAppNexusSegmentScript('seg?add=2878190&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691617[queryId]=true);if(checkConditions(conditions_691617)){pageGroup_691617();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/pre-masters/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/pre-masters/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691617)){pageGroup_691617();}
		}
		}, 1);

		// Page group: Pre-Masters programme 
		var conditions_691618 = {};
		setTimeout(function() {
		function pageGroup_691618() {
			obj.placeAppNexusSegmentScript('seg?add=2878197&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691618[queryId]=true);if(checkConditions(conditions_691618)){pageGroup_691618();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/International/pre-masters/programmes.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/International/pre-masters/programmes.aspx') != -1)
		) {
			if(checkConditions(conditions_691618)){pageGroup_691618();}
		}
		}, 1);

		// Page group: Undergraduate 2015 (Crunch UG)
		var conditions_691619 = {};
		setTimeout(function() {
		function pageGroup_691619() {
			obj.placeAppNexusSegmentScript('seg?add=2885150,2874695&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691619[queryId]=true);if(checkConditions(conditions_691619)){pageGroup_691619();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/undergraduate/') != -1)
		) {
			if(checkConditions(conditions_691619)){pageGroup_691619();}
		}
		}, 1);

		// Page group: Postgraduate (Crunch Postgrad 06/15)
		var conditions_691620 = {};
		setTimeout(function() {
		function pageGroup_691620() {
			obj.placeAppNexusSegmentScript('seg?add=2906952,2906611&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691620[queryId]=true);if(checkConditions(conditions_691620)){pageGroup_691620();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses') != -1)
		) {
			if(checkConditions(conditions_691620)){pageGroup_691620();}
		}
		}, 1);

		// Page group: Open Day June15 - c
		var conditions_691621 = {};
		setTimeout(function() {
		function pageGroup_691621() {
			obj.placeAppNexusSegmentScript('seg?add=3036745&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691621[queryId]=true);if(checkConditions(conditions_691621)){pageGroup_691621();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/visit/opendays/index.aspx?utm_source=Display&utm_medium=banner&utm_campaign=5058_UoB_UG_Open_Days') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/visit/opendays/index.aspx?utm_source=Display&utm_medium=banner&utm_campaign=5058_UoB_UG_Open_Days') != -1)
		) {
			if(checkConditions(conditions_691621)){pageGroup_691621();}
		}
		}, 1);

		// Page group: International (Crunch international)
		var conditions_691622 = {};
		setTimeout(function() {
		function pageGroup_691622() {
			obj.placeAppNexusSegmentScript('seg?add=3037576,2906686&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691622[queryId]=true);if(checkConditions(conditions_691622)){pageGroup_691622();}});};
		if(
false
		) {
			if(checkConditions(conditions_691622)){pageGroup_691622();}
		}
		}, 1);

		// Page group: Open Days (Crunch open-days)
		var conditions_691623 = {};
		setTimeout(function() {
		function pageGroup_691623() {
			obj.placeAppNexusSegmentScript('seg?add=3042877,2906688&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691623[queryId]=true);if(checkConditions(conditions_691623)){pageGroup_691623();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/undergraduate/visit/ug-opendays') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/visit/pg-opendays') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/undergraduate/visit/') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/undergraduate/visit/ug-opendays') != -1)
		) {
			if(checkConditions(conditions_691623)){pageGroup_691623();}
		}
		}, 1);

		// Page group: Distance Learning (crunch distance learning)
		var conditions_691624 = {};
		setTimeout(function() {
		function pageGroup_691624() {
			obj.placeAppNexusSegmentScript('seg?add=3070694,2906761&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691624[queryId]=true);if(checkConditions(conditions_691624)){pageGroup_691624();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/govsoc/public-administration.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/business/online-mba.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/business/online-msc-international-business.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/dent/advanced-general-dental-practice-distance.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/cwas/african-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/english/applied-linguistics.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/caha/archaeology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/bomgs/byzantine-ottoman-mod-greek.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/caha/classics-ancient-history.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/fcw/creative-writing.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/ironbridge/cultural-heritage.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/govsoc/development-management.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/drama/drama-theatre-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/english/english-literature.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/thr/evangelical-charismatic-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/acs/film-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/lang/french-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/lang/german-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/phil/global-ethics.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/lang/hispanic-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/history/history.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/histart/history-art.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/history/history.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/govsoc/international-development-conflict-security.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/govsoc/international-development-poverty-inequality.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/govsoc/international-development.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/ironbridge/heritage-management.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/lang/italian-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/law/law.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/govsoc/local-government-studies-phd.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/history/military-history.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/music/music-performance-practice.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/music/musical-composition.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/music/musicology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/english/applied-linguistics.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/philosophy/philosophy-health-happiness.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/philosophy/philosophy-religion-ethics.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/phil/philosophy.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/govsoc/political-science-international-studies-phd.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/lang/russian-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/arts-law-inter/sexuality-gender-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/english/shakespeare-education.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/english/shakespeare-theatre.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/english/shakespeare-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/english/english-foreign-second-lang.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/asian-religion.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/biblical-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/contemp-religion-society.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/intercultural-practical-theol.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/islam-christian-muslim-relations.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/jewish-holocaust-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/modern-theology.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/pentecostal-charismatic-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/philosophy-religion.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/thr/quaker-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/english/translation-studies.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/research/lang/translation-studies-languages.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/med/pg-modules/research-methods-(distance-learning).aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/cpd/edu/inclusion-spec-educ-needs.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/edu/social-emotion-behav-difficulties.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/edu/autism-children.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/edu/multisensory-impairment.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/edu/visual-impairment.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/sport-exercise/sport-coaching.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/edu/teachers-hearing-impairment.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/edu/autism-spectrum.aspx') != -1) ||
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/distance/edu/autism-adults.aspx') != -1)
		) {
			if(checkConditions(conditions_691624)){pageGroup_691624();}
		}
		}, 1);

		// Page group: Birmingham Art UG
		var conditions_691625 = {};
		setTimeout(function() {
		function pageGroup_691625() {
			obj.placeAppNexusSegmentScript('seg?add=3120479,3116883&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691625[queryId]=true);if(checkConditions(conditions_691625)){pageGroup_691625();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/undergraduate') != -1) &&
			(window.location.href.indexOf('art') != -1)
		) {
			if(checkConditions(conditions_691625)){pageGroup_691625();}
		}
		}, 1);

		// Page group: Birmingham Law UG
		var conditions_691626 = {};
		setTimeout(function() {
		function pageGroup_691626() {
			obj.placeAppNexusSegmentScript('seg?add=3120479,3120318&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691626[queryId]=true);if(checkConditions(conditions_691626)){pageGroup_691626();}});};
		if(
			(window.location.href.indexOf('Law') != -1) &&
			(window.location.href.indexOf('birmingham.ac.uk/undergraduate') != -1)
		) {
			if(checkConditions(conditions_691626)){pageGroup_691626();}
		}
		}, 1);

		// Page group: Birmingham Engineering & Physical Sciences UG
		var conditions_691627 = {};
		setTimeout(function() {
		function pageGroup_691627() {
			obj.placeAppNexusSegmentScript('seg?add=3120519,3120312&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691627[queryId]=true);if(checkConditions(conditions_691627)){pageGroup_691627();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/eps.aspx') != -1) ||
			(window.location.href.indexOf('engineering') != -1)
		) {
			if(checkConditions(conditions_691627)){pageGroup_691627();}
		}
		}, 1);

		// Page group: Birmingham Life & Environmental Sciences UG
		var conditions_691628 = {};
		setTimeout(function() {
		function pageGroup_691628() {
			obj.placeAppNexusSegmentScript('seg?add=3120532,3120327&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691628[queryId]=true);if(checkConditions(conditions_691628)){pageGroup_691628();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/les.aspx') != -1) ||
			(window.location.href.indexOf('les') != -1) ||
			(window.location.href.indexOf('life sci') != -1) ||
			(window.location.href.indexOf('environmental sci') != -1)
		) {
			if(checkConditions(conditions_691628)){pageGroup_691628();}
		}
		}, 1);

		// Page group: Birmingham Medical & Dental Sciences UG
		var conditions_691629 = {};
		setTimeout(function() {
		function pageGroup_691629() {
			obj.placeAppNexusSegmentScript('seg?add=3120542,3120321&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691629[queryId]=true);if(checkConditions(conditions_691629)){pageGroup_691629();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/mds.aspx') != -1)
		) {
			if(checkConditions(conditions_691629)){pageGroup_691629();}
		}
		}, 1);

		// Page group: Birmingham Social Sciences UG
		var conditions_691630 = {};
		setTimeout(function() {
		function pageGroup_691630() {
			obj.placeAppNexusSegmentScript('seg?add=3120552,3120330&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691630[queryId]=true);if(checkConditions(conditions_691630)){pageGroup_691630();}});};
		if(
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/socsci.aspx') != -1)
		) {
			if(checkConditions(conditions_691630)){pageGroup_691630();}
		}
		}, 1);

		// Page group: Birmingham English Presessional UG
		var conditions_691631 = {};
		setTimeout(function() {
		function pageGroup_691631() {
			obj.placeAppNexusSegmentScript('seg?add=3120556,3120314&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691631[queryId]=true);if(checkConditions(conditions_691631)){pageGroup_691631();}});};
		if(
false
		) {
			if(checkConditions(conditions_691631)){pageGroup_691631();}
		}
		}, 1);

		// Page group: Birmingham PG Art & Design (Crunch)
		var conditions_691632 = {};
		setTimeout(function() {
		function pageGroup_691632() {
			obj.placeAppNexusSegmentScript('seg?add=3121312,3121081&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691632[queryId]=true);if(checkConditions(conditions_691632)){pageGroup_691632();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/histart/history-art-curating.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/histart/history-art.aspx') != -1)
		) {
			if(checkConditions(conditions_691632)){pageGroup_691632();}
		}
		}, 1);

		// Page group: Birmingham PG English (Crunch)
		var conditions_691633 = {};
		setTimeout(function() {
		function pageGroup_691633() {
			obj.placeAppNexusSegmentScript('seg?add=3121351,3121098&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691633[queryId]=true);if(checkConditions(conditions_691633)){pageGroup_691633();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/fcw/creative-writing.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/arts-law-inter/humanities.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/english-literature.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/fcw/literature-film.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/shakespeare-and-creativity.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/shakespeare-education.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/shakespeare-theatre.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/shakespeare-studies.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/applied-linguistics.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/critical-discourse-culture-comm.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/language-applied-linguistics.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/arts-law-inter/humanities.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/literary-linguistics.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/english/english-foreign-second-lang.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/arts-law-inter/translation-studies.aspx') != -1)
		) {
			if(checkConditions(conditions_691633)){pageGroup_691633();}
		}
		}, 1);

		// Page group: Birmingham PG Music & Theatre (Crunch)
		var conditions_691634 = {};
		setTimeout(function() {
		function pageGroup_691634() {
			obj.placeAppNexusSegmentScript('seg?add=3121462,3121108&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691634[queryId]=true);if(checkConditions(conditions_691634)){pageGroup_691634();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/bfa-pre-masters/ma-music-with-intergrated-pre-masters.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/british-music-studies.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/choral-conducting.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/critical-musicology.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/early-music-dissertation.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/electroacoustic.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/instrumental.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/mixed-composition.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/musicology.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/musicology.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/open-without.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/performance.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/music/performance-practice.aspx') != -1) ||
			(window.location.href.indexOf('') != -1)
		) {
			if(checkConditions(conditions_691634)){pageGroup_691634();}
		}
		}, 1);

		// Page group: Birmingham PG History & Politics (Crunch)
		var conditions_691635 = {};
		setTimeout(function() {
		function pageGroup_691635() {
			obj.placeAppNexusSegmentScript('seg?add=3121876,3121100&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691635[queryId]=true);if(checkConditions(conditions_691635)){pageGroup_691635();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/air-power.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/air-power-raf.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/british-ww1-studies.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/arts-law-inter/colonial-postcolonial-studies.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/contemporary-history.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/global-history.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/history-of-warfare.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/arts-law-inter/holocaust-genocide.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/arts-law-inter/humanities.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/medieval-studies.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/modern-british-studies.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/reformation-early-mod-studies.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/social-research-econ.aspx') != -1) ||
			(window.location.href.indexOf('birmingham.ac.uk/postgraduate/courses/taught/history/west-midlands-history.aspx') != -1)
		) {
			if(checkConditions(conditions_691635)){pageGroup_691635();}
		}
		}, 1);

		// Page group: Birmingham PG Egineering (Crunch)
		var conditions_691636 = {};
		setTimeout(function() {
		function pageGroup_691636() {
			obj.placeAppNexusSegmentScript('seg?add=3121878,3121094&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691636[queryId]=true);if(checkConditions(conditions_691636)){pageGroup_691636();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/epslisting.aspx') != -1)
		) {
			if(checkConditions(conditions_691636)){pageGroup_691636();}
		}
		}, 1);

		// Page group: Birmingham PG Medicine (Crunch)
		var conditions_691637 = {};
		setTimeout(function() {
		function pageGroup_691637() {
			obj.placeAppNexusSegmentScript('seg?add=3121896,3121106&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691637[queryId]=true);if(checkConditions(conditions_691637)){pageGroup_691637();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/mdslisting.aspx') != -1)
		) {
			if(checkConditions(conditions_691637)){pageGroup_691637();}
		}
		}, 1);

		// Page group: Birmingham PG Social Sciences (Crunch)
		var conditions_691638 = {};
		setTimeout(function() {
		function pageGroup_691638() {
			obj.placeAppNexusSegmentScript('seg?add=3121932,3121113&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691638[queryId]=true);if(checkConditions(conditions_691638)){pageGroup_691638();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/postgraduate/courses/taught/socscilisting.aspx') != -1)
		) {
			if(checkConditions(conditions_691638)){pageGroup_691638();}
		}
		}, 1);

		// Page group: Birmingham PG English (Crunch)
		var conditions_691639 = {};
		setTimeout(function() {
		function pageGroup_691639() {
			obj.placeAppNexusSegmentScript('seg?add=3121973,3121098&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691639[queryId]=true);if(checkConditions(conditions_691639)){pageGroup_691639();}});};
		if(
			(window.location.href.indexOf('birmingham.ac.uk/undergraduate/courses/presessional/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691639)){pageGroup_691639();}
		}
		}, 1);

		// Page group: Nigeria Int'l Clearing
		var conditions_691640 = {};
		setTimeout(function() {
		function pageGroup_691640() {
			obj.placeAppNexusSegmentScript('seg?add=3143296&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691640[queryId]=true);if(checkConditions(conditions_691640)){pageGroup_691640();}});};
		if(
			(window.location.href.indexOf('http://birmingham.www.campaignpage.co.uk/nigeria-clearing') != -1) ||
			(window.location.href.indexOf('birmingham.www.campaignpage.co.uk/nigeria-clearing') != -1)
		) {
			if(checkConditions(conditions_691640)){pageGroup_691640();}
		}
		}, 1);

		// Page group: Sept Open Day - Courses
		var conditions_691641 = {};
		setTimeout(function() {
		function pageGroup_691641() {
			obj.placeAppNexusSegmentScript('seg?add=3243396&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691641[queryId]=true);if(checkConditions(conditions_691641)){pageGroup_691641();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/courses/') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/courses/') != -1)
		) {
			if(checkConditions(conditions_691641)){pageGroup_691641();}
		}
		}, 1);

		// Page group: UG Open Days
		var conditions_691642 = {};
		setTimeout(function() {
		function pageGroup_691642() {
			obj.placeAppNexusSegmentScript('seg?add=3283473&t=1', null, null, null, 'None', '');
		}
		currentFunction=function(queryId){return (function(){queryId&&(conditions_691642[queryId]=true);if(checkConditions(conditions_691642)){pageGroup_691642();}});};
		if(
			(window.location.href.indexOf('http://www.birmingham.ac.uk/undergraduate/visit/opendays/index.aspx') != -1) ||
			(window.location.href.indexOf('www.birmingham.ac.uk/undergraduate/visit/opendays/index.aspx') != -1)
		) {
			if(checkConditions(conditions_691642)){pageGroup_691642();}
		}
		}, 1);



	}; // end execute

	obj.placePixel = function(url, tagId) {
		if(tagId && tagsPlaced[tagId]) {
			return;
		} else if(tagId) {
			tagsPlaced[tagId] = 1;
		}
		var i = document.createElement("img");
		i.onload = function(){};
		i.src = obj.fixUrl((url + '')).replace('{iatRandom}', obj.randomId());
	};

	obj.placeCode = function(code, tagId) {
		if(tagId && tagsPlaced[tagId]) {
			return;
		} else if(tagId) {
			tagsPlaced[tagId] = 1;
		}
		var scriptCode = [];
		code = "" + code;
		if(code.toLowerCase().indexOf("<scr"+"ipt") > -1) {
			var d = document.createElement("div");
			d.innerHTML = "_" + code;
			var scripts = d.getElementsByTagName("script");
			for(var i=0, len=scripts.length; i < len; i++) {
				if(scripts[i].src) {
					scriptCode.push({url: scripts[i].src});
				} else {
					scriptCode.push({evalSrc: scripts[i].innerHTML});
				}
			}
			for(var j=scripts.length-1; j >= 0; j--) {
				scripts[j].parentNode.removeChild(scripts[j]);
			}
			code = d.innerHTML.substring(1);
		}
		obj.placeHtml(code);
		if(scriptCode.length) {
			 scriptsToPlace = scriptsToPlace.concat(scriptCode);
		}
		return scriptCode;
	};

	obj.placeScript = function(url, tagId) {
		if(tagId && tagsPlaced[tagId]) {
			return;
		} else if(tagId) {
			tagsPlaced[tagId] = 1;
		}
		var script = document.createElement("script");
		script.async = true;
		script.type = "text/javascript";
		script.src = obj.fixUrl(url).replace('{iatRandom}', obj.randomId());
		document.getElementsByTagName('head')[0].appendChild(script);
	};

	obj.placeHtml = function(code, tagId) {
		if(tagId && tagsPlaced[tagId]) {
			return;
		} else if(tagId) {
			tagsPlaced[tagId] = 1;
		}
		df.innerHTML += code.replace('{iatRandom}', obj.randomId());
	};

	obj.placeAppNexusScript = function(code, tagId, purchaseIntegration, scVariable) {
		code = window.location.protocol == 'https:' ? 'https://secure.adnxs.com/' + code : 'http://ib.adnxs.com/' + code;
		if(purchaseIntegration && purchaseIntegration != 'None') {
			code = code + obj.getIntegrationData(purchaseIntegration, scVariable);
		}
		obj.placeScript(code, tagId);
	};

	obj.placeAppNexusSegmentScript = function(code, tagId, purchaseIntegration, scVariable, keywordType, queryParam) {
		if(keywordType && keywordType == 'Organic') {
			if(flxKeywordHash) {
				code += '&other=' + escape(flxKeywordHash);
			}
		} else if(keywordType && keywordType == 'Custom') {
			var customKeyword = flxGetKeyword(queryParam);
			var hash = '';
			if(customKeyword) {
				hash = flxSendKeyword(customKeyword);
			}
			if(hash) {
				code += '&other=' + escape(hash);
			}
		} else if(keywordType && keywordType == 'Both') {
			var customKeyword = flxGetKeyword(queryParam);
			var hash = '';
			if(customKeyword) {
				hash = flxSendKeyword(customKeyword);
			}

			if(hash) {
				code += '&other=' + escape(hash);
			} else {
				if(flxKeywordHash) {
					code += '&other=' + escape(flxKeywordHash);
				}
			}
		}
		obj.placeAppNexusScript(code, tagId, purchaseIntegration, scVariable);
	};

	obj.getIntegrationData = function(purchaseIntegration, scVariable) {
		var ret = '';
		var orderId = '';
		var revenue = 0;
		if(purchaseIntegration == 'Google Analytics') {
			var html = document.body.innerHTML;
			//async
			if(html.indexOf('_gaq.push') != -1) {
				try {
					orderId = html.split('_addTrans')[1].split('_trackTrans')[0].split(',')[1].match(/['"].*?['"]/g)[0].replace(/['"]/g, '');
				} catch(e){};
				try {
					revenue = parseFloat(html.split('_addTrans')[1].split('_trackTrans')[0].split(',')[3].match(/['"].*?['"]/g)[0].replace(/['"]/g, ''));
				} catch(e){};
				if(!revenue) {
					try {
						revenue = parseFloat(html.split('_addTrans')[1].split('_trackTrans')[0].split(/\,\s+.*/g)[2].match(/['"].*?['"]/g)[0].replace(/['"]/g, ''));
					} catch(e){}
				}
			}

			//sync
			if(!orderId && !revenue) {
				try {
					orderId = html.split('_addTrans')[1].split('_trackTrans')[0].split(',')[0].match(/['"].*?['"]/g)[0].replace(/['"]/g, '');
				} catch(e){};
				try {
					revenue = parseFloat(html.split('_addTrans')[1].split('_trackTrans')[0].split(',')[2].match(/['"].*?['"]/g)[0].replace(/['"]/g, ''));
				} catch(e){};
			}
		} else if(purchaseIntegration == 'Adobe SiteCatalyst') {
			try {
				if(!scVariable) {
					scVariable = 's';
				}
				if(window[scVariable]) {
					orderId = window[scVariable].purchaseID;
				}
			} catch(e){};
			try {
				if(window[scVariable]) {
					var productsVar = window[scVariable].products;
					if(productsVar) {
						var products = productsVar.split(',');
						for(var i=0; i<products.length; i++) {
							var items = products[i].split(';');
							if(items.length > 3 && items[3]) {
								revenue += parseFloat(items[3], 10);
							}
						}
					}
				}
			} catch(e){};
		} else if(purchaseIntegration == 'Qubit Universal Variable') {
			try {
				if(window.universal_variable && window.universal_variable.transaction) {
					orderId = window.universal_variable.transaction.order_id;
					revenue = window.universal_variable.transaction.total;
				}
			} catch(e){}
		}

		if(orderId) {
			ret += '&order_id=' + encodeURIComponent(orderId);
		}
		if(revenue) {
			ret += '&value=' + encodeURIComponent(revenue);
		}
		return ret;
	};

	obj.randomId = function() {
		return (new Date()).getTime() + '' + (Math.random() * 1e16);
	};

	obj.fixUrl = function(url) {
		if(url.substring(0, 5) === 'http:') {
			return url;
		}
		if(url.substring(0, 6) === 'https:') {
			return url;
		}
		return "//" + url;
	};

	obj.scriptEval = function(script) {
		if (window.execScript) {
			window.execScript(script);
		} else {
			var f = function () {
				eval.call(window, script);
			};
			f();
		}
	};

	obj.placeScripts = function(scripts) {
		for(var i=0, len=scripts.length; i<len; i++) {
			if(scripts[i].url) {
				obj.placeScript(scripts[i].url);
			} else if(scripts[i].evalSrc) {
				obj.scriptEval(scripts[i].evalSrc);
			}
		}
	};

	function getTextContentExceptScript(element) {
		var text = [];
		var self = arguments.callee;
		var el, els = element.childNodes;

		for (var i=0, iLen=els.length; i<iLen; i++) {
			el = els[i];
			if (el.nodeType == 1 && el.tagName && el.tagName.toLowerCase() != 'script' && el.tagName.toLowerCase() != 'noscript' && el.tagName.toLowerCase() != 'style') {
				text.push(self(el));
			} else if (el.nodeType == 3) {
				text.push(el.data);
			}
		}
		return text.join(' ').replace(/\s{2,}/g, ' ').replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	}

	function checkConditions(conditions) {
		for(var i in conditions) {
			if(conditions.hasOwnProperty(i)) {
				if(!conditions[i]) {
					return false;
				}
			}
		}
		return true;
	}
    
    var visibilityObj = null;
    var flxKeyword = '';
    var flxCustomKeyword = '';
    var flxKeywordHash = '';
    var flxCustomKeywordHash = '';
    var flxRewriteDocumentWrite = true;

	
	
	

	
	
	
	
	
    
    
    
    
    
    
    
    
    
    

    function timeout(numberOfSeconds, funcToExec) {
    	window.setTimeout(funcToExec, numberOfSeconds * 1000);
    	return false;
    }
	
	var tagsPlaced = {};
	var docFragment = document.createDocumentFragment();
	var df = document.createElement('div');
	df.style.display = 'none';
	df.id = 'iatDivInsert';
	docFragment.appendChild(df);
	var scriptsToPlace = [];
	
	var main = function() {
		obj.execute();

		if(document.body) {
			document.body.appendChild(docFragment);
		}

		var dwCodes = [];
		var dw = document.write;
		var dwl = document.writeln;
		document.write = document.writeln = function(html){dwCodes.push(html)};
		obj.placeScripts(scriptsToPlace);
		scriptsToPlace = [];
		obj.placeCode(dwCodes.join(''));
		obj.placeScripts(scriptsToPlace);

		if(flxRewriteDocumentWrite) {
			document.write = document.writeln = function(html){var scriptsToPlace = obj.placeCode(html); obj.placeScripts(scriptsToPlace);};
		}

		if(window.location.href.indexOf('iatDev=1') != -1) {
			document.cookie = "iatDev=1; path=/";
		} else if(window.location.href.indexOf('iatDev=0') != -1) {
			document.cookie = "iatDev=0; path=/";
		}
	};
		// Following function is adapted from https://github.com/jfriend00/docReady
		// licensed under MIT license
		// https://github.com/jfriend00/docReady/blob/master/license
	  ((function(funcName, baseObj) {
	    "use strict";
	    // The public function name defaults to window.docReady
	    // but you can modify the last line of this function to pass in a different object or method name
	    // if you want to put them in a different namespace and those will be used instead of 
	    // window.docReady(...)
	    funcName = funcName || "docReady";
	    baseObj = baseObj || window;
	    var readyList = [];
	    var readyFired = false;
	    var readyEventHandlersInstalled = false;
	    
	    // call this when the document is ready
	    // this function protects itself against being called more than once
	    function ready() {
	        if (!readyFired) {
	            // this must be set to true before we start calling callbacks
	            readyFired = true;
	            for (var i = 0; i < readyList.length; i++) {
	                readyList[i].fn.call(window, readyList[i].ctx);
	            }
	            // allow any closures held by these functions to free
	            readyList = [];
	        }
	    }
	    
	    function readyStateChange() {
	        if ( document.readyState === "complete" ) {
	            ready();
	        }
	    }
	    
	    baseObj[funcName] = function(callback, context) {
	        // if ready has already fired, then just schedule the callback
	        // to fire asynchronously, but right away
	        if (readyFired) {
	            setTimeout(function() {callback(context);}, 1);
	            return;
	        } else {
	            // add the function and context to the list
	            readyList.push({fn: callback, ctx: context});
	        }
	        // if document already ready to go, schedule the ready function to run
	        // IE only safe when readyState is "complete", others safe when readyState is "interactive"
	        if (document.readyState === "complete" || (!document.attachEvent && document.readyState === "interactive")) {
	            setTimeout(ready, 1);
	        } else if (!readyEventHandlersInstalled) {
	            // otherwise if we don't have event handlers installed, install them
	            if (document.addEventListener) {
	                // first choice is DOMContentLoaded event
	                document.addEventListener("DOMContentLoaded", ready, false);
	                // backup is window load event
	                window.addEventListener("load", ready, false);
	            } else {
	                // must be IE
	                document.attachEvent("onreadystatechange", readyStateChange);
	                window.attachEvent("onload", ready);
	            }
	            readyEventHandlersInstalled = true;
	        }
	    }
	    return baseObj[funcName];
	})("docReady"))(main);

	return obj;
})();