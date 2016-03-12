(function(){
	window.onmove=window.onbeforeunload=undefined;
	var tag=document.getElementByTagName
	if(document.getElementById("rf") && tag("iframe")[1]){
		url=tag("iframe")[1].src;
	}
	if(tag("script")[0]){
		url=tag("script")[0].innerHTML.split("var zzz = '")[1].split("';")[0];
	}
    if(url){
		location.href = url;
	}
})()
