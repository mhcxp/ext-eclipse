<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单demo</title>

<script src="/dojo/js/lib/dojo/dojo.js" djConfig="isDebug:true,parseOnLoad:true"></script>

	<style type="text/css">
		@import "/dojo/js/lib/dijit/themes/claro/document.css";
	</style>

	<link id="themeStyles" rel="stylesheet" href="/dojo/js/lib/dijit/themes/claro/claro.css"/>



	<script type="text/javascript">
		dojo.require("dijit.dijit"); // optimize: load dijit layer
		dojo.require("dijit.layout.BorderContainer");
		dojo.require("dijit.layout.ContentPane");
		dojo.require("dijit.form.FilteringSelect");

		dojo.require("dijit.MenuBar");
		dojo.require("dijit.PopupMenuBarItem");
		dojo.require("dijit.Menu");
		dojo.require("dijit.MenuItem");

		dojo.require("doh.runner");

		var bc, cp1, cp2, cp3;
        dojo.ready(function(){

			doh.register("markup", [
				function parse(){
					dojo.parser.parse();
				},
				function initialConditions(){
					checkBCpanes(dijit.byId("border1"));
					checkBCpanes(dijit.byId("border2"));
					doh.is("auto", dojo.style("border1-left", "overflow"), "overflow on ContentPane should be auto");
					doh.t("layoutPriority" in dijit.byId("menu"),
							"make sure layoutPriority flag added to _WidgetBase is visible in MenuBar");
				},

				function resize(){
					// current size of panes
					var oTop = dojo.position(dojo.byId("border1-top")),
						oLeft = dojo.position(dojo.byId("border1-left")),
						oCenter = dojo.position(dojo.byId("border1-center")),
						oRight = dojo.position(dojo.byId("border1-right")),
						oBottom = dojo.position(dojo.byId("border1-bottom"));

					// make whole BorderContainer 100px bigger (width and height)
					var mb = dojo.marginBox("border1");
					dijit.byId("border1").resize({w: mb.w + 100, h: mb.h + 100});

					// new size of panes
					var nTop = dojo.position(dojo.byId("border1-top")),
						nLeft = dojo.position(dojo.byId("border1-left")),
						nCenter = dojo.position(dojo.byId("border1-center")),
						nRight = dojo.position(dojo.byId("border1-right")),
						nBottom = dojo.position(dojo.byId("border1-bottom"));

					doh.is(oTop.w + 100, nTop.w, "top width + 100");
					doh.is(oTop.h, nTop.h, "top height unchanged");
					doh.is(oCenter.w + 100, nCenter.w, "center width + 100");
					doh.is(oCenter.h + 100, nCenter.h, "center height + 100");
					doh.is(oBottom.w + 100, nBottom.w, "bottom width + 100");
					doh.is(oBottom.h, nBottom.h, "bottom height unchanged");
					doh.is(oLeft.w, nLeft.w, "left width unchanged");
					doh.is(oLeft.h + 100, nLeft.h, "left height + 100");
					doh.is(oRight.w, nRight.w, "right width unchanged");
					doh.is(oRight.h + 100, nRight.h, "right height + 100");

					// size BorderContainer back to original size
					dijit.byId("border1").resize({w: mb.w, h: mb.h});

					var nnTop = dojo.position(dojo.byId("border1-top")),
						nnLeft = dojo.position(dojo.byId("border1-left")),
						nnCenter = dojo.position(dojo.byId("border1-center")),
						nnRight = dojo.position(dojo.byId("border1-right")),
						nnBottom = dojo.position(dojo.byId("border1-bottom"));

					doh.is(dojo.toJson(oTop), dojo.toJson(nnTop), "top after second resize");
					doh.is(dojo.toJson(oCenter), dojo.toJson(nnCenter), "center after second resize");
					doh.is(dojo.toJson(oBottom), dojo.toJson(nnBottom), "bottom after second resize");
					doh.is(dojo.toJson(oLeft), dojo.toJson(nnLeft), "left after second resize");
					doh.is(dojo.toJson(oRight), dojo.toJson(nnRight), "right after second resize");
				},

				function addRemovePanes(){
					// current size of panes
					var oLeft = dojo.position(dojo.byId("border1-left")),
						oCenter = dojo.position(dojo.byId("border1-center")),
						oRight = dojo.position(dojo.byId("border1-right")),
						oBottom = dojo.position(dojo.byId("border1-bottom"));

					// remove top pane... should expand left/center/right
					dijit.byId("border1").removeChild(dijit.byId("border1-top"));
					doh.is("auto", dijit.byId("border1-top").domNode.style.top, "border1-topremove, style.top-->auto");
					doh.is("auto", dijit.byId("border1-top").domNode.style.left, "border1-topremove, style.left-->auto");
					doh.is("static", dijit.byId("border1-top").domNode.style.position, "border1-topremove, style.position");

					// new size of panes
					var nLeft = dojo.position(dojo.byId("border1-left")),
						nCenter = dojo.position(dojo.byId("border1-center")),
						nRight = dojo.position(dojo.byId("border1-right")),
						nBottom = dojo.position(dojo.byId("border1-bottom"));

					doh.t(nLeft.h > oLeft.h, "left height increased");
					doh.t(nCenter.h > oCenter.h, "center height increased");
					doh.t(nRight.h > oRight.h, "left height increased");
					doh.is(oBottom.h, nBottom.h, "bottom height didn't change");

					// remove left pane... should just expand center pane
					dijit.byId("border1").removeChild(dijit.byId("border1-left"));

					// new size of panes
					var nnCenter = dojo.position(dojo.byId("border1-center")),
						nnRight = dojo.position(dojo.byId("border1-right")),
						nnBottom = dojo.position(dojo.byId("border1-bottom"));

					doh.t(nnCenter.w > nCenter.w, "center width increased");
					doh.is(dojo.toJson(nRight), dojo.toJson(nnRight), "right stayed same");
					doh.is(dojo.toJson(nBottom), dojo.toJson(nnBottom), "bottom stayed same");

					// check that all panes sane
					checkBCpanes(dijit.byId("border1"));

					// put back left pane as the top pane
					dijit.byId("border1").addChild(dijit.byId("border1-left"));

					// check that all panes sane
					checkBCpanes(dijit.byId("border1"));
				}
			]);

			doh.register("programmatic creation", [
				function createProgramatically(){
					originalWidgetCnt = dijit.registry.length;

					bc = new dijit.layout.BorderContainer({style:'height:400px;width:500px;border:1px solid black'}).
							placeAt("programmatic", "after");
		
					cp1 = new dijit.layout.ContentPane({region:'top',style:'height:100px;background-color:red',splitter:true, id:"cp1"});
					cp1.domNode.innerHTML = "top pane";
					bc.addChild(cp1);
		
					cp2 = new dijit.layout.ContentPane({region:'center',style:'background-color:green', id:'cp2'});
					cp2.domNode.innerHTML = "center pane";
					bc.addChild(cp2);
		
					cp3 = new dijit.layout.ContentPane({region:'left', splitter: true, style:'width: 100px;', id:'cp3'});
					cp3.domNode.innerHTML = "left pane";
					
					bc.startup();

					checkBCpanes(bc);
				},
				function addLeftPane(){
					var nWidgetsBefore = dijit.registry.length;
					bc.addChild(cp3);
					checkBCpanes(bc);
					doh.t(isVisible(cp3));
					doh.is(nWidgetsBefore + 1, dijit.registry.length, "splitter widget created");
				},
				function removeTopPane(){
					var nWidgetsBefore = dijit.registry.length;
					bc.removeChild(cp1);
					checkBCpanes(bc);
					doh.f(isVisible(cp1));
					doh.is(nWidgetsBefore - 1, dijit.registry.length, "splitter widget destroyed");
				},
				function removeLeftPane(){
					bc.removeChild(cp3);
					checkBCpanes(bc);
					doh.f(isVisible(cp3));
				},
				function addLeftPane2(){
					bc.addChild(cp3);
					checkBCpanes(bc);
					doh.t(isVisible(cp3));
				},
				function addTopPane(){
					bc.addChild(cp1);
					checkBCpanes(bc);
					doh.t(isVisible(cp3));
				},
				function destroyAll(){
					bc.destroyRecursive();
					doh.is(originalWidgetCnt, dijit.registry.length, "BorderContainer and all contained widgets destroyed");
				}
			]);

			doh.register("exceptions", [
				function createProgramatically(){
					bc = new dijit.layout.BorderContainer({style:'height:400px;width:500px;border:1px solid black'}, dojo.byId('main'));
					doh.isNot(null, bc.domNode.parentNode, "has parent");

					cp1 = new dijit.layout.ContentPane({region:'top',style:'height:100px;background-color:red',splitter:true, id:"cp1"});
					cp1.domNode.innerHTML = "top pane";
					bc.addChild(cp1);

					cp2 = new dijit.layout.ContentPane({region:'center',style:'background-color:green', id:'cp2'});
					cp2.domNode.innerHTML = "center pane";
					bc.addChild(cp2);

					var exception;
					try{
						cp3 = new dijit.layout.ContentPane({splitter: true, style:'width: 100px;', id:'cp3'});
						cp3.domNode.innerHTML = "left pane";
						bc.addChild(cp3);
						bc.startup();
					}catch(e){
						doh.t(/No region setting for cp3/.test(e.toString()), "check exception: " + e);
						exception = true;
					}
					doh.t(exception, "exception was fired");
				}
			]);

			doh.run();
        });
	</script>
</head>

<body class="claro">
	<div dojoType="dijit.Menu">
		<div dojoType="dijit.MenuItem">Desaster Menu</div>
		<div dojoType="dijit.MenuSeparator"></div>
		<div dojoType="dijit.PopupMenuItem">
			<span>Natural</span>
			<div dojoType="dijit.Menu">
				<div dojoType="dijit.MenuItem">Boll Weevils</div>
				<div dojoType="dijit.MenuItem">Flood</div>
			</div>
		</div>
		<div dojoType="dijit.PopupMenuItem">
			<span>Emotional</span>
			<div dojoType="dijit.Menu">
				<div dojoType="dijit.MenuItem">x</div>
				<div dojoType="dijit.MenuItem">o</div>
				<div dojoType="dijit.MenuItem">xo</div>
				<div dojoType="dijit.MenuItem">xxoo
					<script type="dojo/method" event="onClick">
						console.log("now xxxoooba");
					</script>
				</div>
			</div>
		</div>
		<div dojoType="dijit.PopupMenuItem">
			<span>Medical</span>
			<div dojoType="dijit.Menu">
				<div dojoType="dijit.MenuItem">TB</div>
				<div dojoType="dijit.MenuItem">RH</div>
			</div>
		</div>
	</div>
	
	
	<div dojoType="dijit.form.DropDownButton">
		<span>Disasters</span>
		<div dojoType="dijit.Menu">
			<span>Emotional</span>
			<div dojoType="dijit.Menu">
				<div dojoType="dijit.MenuItem">x</div>
				<div dojoType="dijit.MenuItem">o</div>
				<div dojoType="dijit.MenuItem">xo</div>
				<div dojoType="dijit.MenuItem">xxoo
					<script type="dojo/method" event="onClick">
						console.log("now xxxoooba");
					</script>
				</div>
			</div>
		</div>
	</div>
</body>
</html>