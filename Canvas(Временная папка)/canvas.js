
        
        
        let btn = document.getElementsByClassName("save")[0];
     
        
        let btnU = document.getElementsByClassName("update")[0];
       

        // let frame = document.getElementsByTagName("iframe")[0];
        // let frameDoc = frame.contentWindow.document;

        let ctx = document.getElementById('canvas').getContext('2d');
        var canvasWidth = ctx.canvas.width, canvasHeight = ctx.canvas.height
        var mouseX, mouseY
        var canvasOffsetLeft = ctx.canvasOffsetLeft, canvasOffsetTop = ctx.canvasOffsetTop
        var pixels = []

        let currColor = "#FF0000"

        // eslint-disable-next-line no-undef
        

       
        // frame.setAttribute("height",canvasHeight)
        // frame.setAttribute("width", canvasWidth)

        // let a = ""
        // for (let i = 0; i < canvasHeight*canvasWidth; i++) {
        //     a += "#FFFFFF ";
        // }

        // let bl = lzw_encode(a);
        // postRequest(bl.join(" "))

        
        function initCanvas() {

            
            ctx.canvas.style.cursor = "crosshair";

            // canvasWidth = ctx.canvas.width,
            //     canvasHeight = ctx.canvas.height;
            // ctx.fillStyle = "red";
            // ctx.fillRect(0,0,5,5);
            // ctx.fillRect(25,25,5,5);

            //imageData = ctx.createImageData(canvasWidth, canvasHeight);

            // ctx.fillRect(0,0,1,1);
            ctx.strokeRect(0, 0, canvasWidth, canvasHeight);

           // canvasOffsetLeft = ctx.canvas.offsetLeft;
          //  canvasOffsetTop = ctx.canvas.offsetTop;

            //ctx.fillRect(10,10,50,50);
        }

        // eslint-disable-next-line no-unused-vars
        window.addEventListener('load', function (e) {
            initCanvas();
        })

        function saveCanvas(arr) {     
            
            postRequest(arr.join(" "));

        }

        function updateCanvas() {
            var x,y
            //get запрос на получение массива и его выгрузка на полотно
            str = getRequest();
            pixels = str.split(" ");

            for (let i = 0; i < pixels.length; i++) {
                ctx.fillStyle = pixels[i];
                x = i;
                y = Math.floor(i/canvasHeight);
                console.log("x = " + (x-canvasWidth*y) + " " + "y = " + y);
                ctx.fillRect(i - canvasWidth*y ,y,1,1);
            }
            ctx.strokeRect(0, 0, canvasWidth, canvasHeight);
            console.log(pixels);
        }
 




        btn.addEventListener("click", function () {
            saveCanvas()
        });

        btnU.addEventListener("click", function () {
           updateCanvas()
        //    ctx.fillStyle = pixels[0]
          
        //     ctx.fillRect(0,0,1,1)
        //     ctx.fillRect(1,0,1,1)
        });

        ctx.canvas.addEventListener('mousemove', function (e) {
            mouseX = e.clientX - ctx.canvas.offsetLeft;
            mouseY = e.clientY - ctx.canvas.offsetTop;
            let status = document.getElementById('status');
            status.innerHTML = mouseX + " | " + mouseY;
        });

        ctx.canvas.addEventListener('click', function (e) {
            // занесение пикселя в массив 
            let mouseX = e.clientX - ctx.canvas.offsetLeft;
            let mouseY = e.clientY - ctx.canvas.offsetTop;
            ctx.fillStyle = currColor; //любой другой цвет change
            ctx.fillRect(mouseX, mouseY, 1, 1);
            console.log(pixels)
            pixels[mouseX + mouseY*canvasWidth] = currColor
            console.log(pixels)

        });


        async function getRequest() {
			let response = await fetch("/update");

			if (response.ok) { // если HTTP-статус в диапазоне 200-299
			  // получаем тело ответа (см. про этот метод ниже)
				var a = await response.text();
				if(a == "") {
					for(var i = 0; i < 160000; i++) {
						a += "#FFFFFF ";
					}
					a = a.substring(0, a.length-1);
					var bl = lzw_encode(a);

					postRequest(bl.join(" "));
                    return a;
				}
				else {
					var res = lzw_decode(a.split(" "));
					return res;
				}
			  
			} else {
			  alert("Ошибка HTTP: " + response.status);
			}
			
		}

        async function postRequest(post) {
			let response = await fetch('/update', {
			  method: 'POST',
			  headers: {
				'Content-Type': 'text/plain;charset=UTF-8'
			  },
			  body: (post)
			});
		}
		
		function lzw_encode(s) {
		var dict = {};
		var data = (s + "").split("");
		var out = [];
		var currChar;
		var phrase = data[0];
		var code = 256;
		for (var i = 1; i < data.length; i++) {
			currChar = data[i];
			if (dict[phrase + currChar] != null) {
				phrase += currChar;
			}
			else {
				out.push(phrase.length > 1 ? dict[phrase] : phrase.charCodeAt(0));
				dict[phrase + currChar] = code;
				code++;
				phrase = currChar;
			}
		}
		out.push(phrase.length > 1 ? dict[phrase] : phrase.charCodeAt(0));
			
		
		return out;
		}

		// Decompress an LZW-encoded string
		function lzw_decode(data) {
			var dict = {};
			var currChar = String.fromCharCode(data[0]);
			var oldPhrase = currChar;
			var out = [currChar];
			var code = 256;
			var phrase;
			for (var i = 1; i < data.length; i++) {

				var currCode = data[i];
				if (currCode < 256) {
					phrase = String.fromCharCode(data[i]);
				}
				else {
					phrase = dict[currCode] ? dict[currCode] : (oldPhrase + currChar);
				}
				out += phrase;
				currChar = phrase[0];
				dict[code] = oldPhrase + currChar;
				code++;
				oldPhrase = phrase;
			}
			return out;
			}
		
	

        


