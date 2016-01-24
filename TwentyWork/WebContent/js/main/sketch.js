var sketchFlag = false;
var color ='black';
var previousColor= 'black';
var size= 5 ;

var websokcet = new WebSocket('ws://twentywork.cloudapp.net:8080/TwentyWork/com/iii/twentywork/model/service/sketch/SetchServerEndpoint')
websokcet.onmessage = function processMessage(sketchMessage) {
	var json = JSON.parse(sketchMessage.data);
	sketch(json.x, json.y, json.size, json.color);
}
function initializeCanvas() {
	var sketchCanvas = document.getElementById('sketchCanvas');
	var context =sketchCanvas.getContext('2d');
//	sketchCanvas.style.border = "solid";
	context.canvas.addEventListener('mousemove', function(event){
		var positionX = event.clientX - context.canvas.offsetLeft;
		var positionY = event.clientY - context.canvas.offsetTop;
		if(sketchFlag == true){
			sketch(positionX, positionY, size, color);
			websokcet.send(JSON.stringify({'x' : positionX, 'y' : positionY, 'size' : size, 'color' : color}));
		}
	});
	context.canvas.addEventListener('mousedown',function(event){sketchFlag = true;});
	context.canvas.addEventListener('mouseup',function(event){sketchFlag = false;});
	context.canvas.addEventListener('mouseleave',function(event){sketchFlag = false;});
}
function chooseColor(newColor) {
	previousColor = color;
	color =newColor;
}
function toggleState(event) {
	if (event.value=='Erase'){
		event.value='Sketch';
		previousColor = color;
		color = '#e3e0e0';
		
		size = 50;
		document.getElementById('colorChooser').style.visibility="hidden";
		document.getElementById('sketchCanvas').style.cursor = 'text';
	}else{
		event.value='Erase';
		color = previousColor;
		size = 5;
		document.getElementById('colorChooser').style.visibility="visible";
		document.getElementById('sketchCanvas').style.cursor ='auto';
	}
}

function sketch(x, y, size, color) {
	var context = document.getElementById('sketchCanvas').getContext('2d');
	context.beginPath();
	context.fillStyle = color;
	context.fillRect(x, y, size, size);
	context.closePath();

}


