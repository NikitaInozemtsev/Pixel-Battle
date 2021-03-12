
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
        
        // frame.setAttribute("height",canvasHeight)
        // frame.setAttribute("width", canvasWidth)

        let a = ""
        for (let i = 0; i < canvasHeight*canvasWidth; i++) {
            a += "#FFFFFF ";
        }

        pixels = a.split(" ")
        pixels.pop();
        console.log(pixels)
        // let bl = lzw_encode(a);
        // postRequest(bl.join(" "))

        
        function initCanvas() {

            
            ctx.canvas.style.cursor = "crosshair";

            // canvasWidth = ctx.canvas.width,
            //     canvasHeight = ctx.canvas.height;
            ctx.fillStyle = "red";
            ctx.fillRect(0,0,5,5);
            ctx.fillRect(25,25,5,5);

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

        function saveCanvas() {
            console.clear();
            let pixelData = ctx.getImageData(canvasOffsetLeft, canvasOffsetTop, canvasWidth, canvasHeight)

            for (var x = 0; x < canvasWidth; x++) {
                for (var y = 0; y < canvasHeight; y++) {
                    var index = (canvasWidth * x + y) * 4
                    console.log("-----------------------------")
                    console.log("x = " + x + '\n' + "y = " + y)
                    // pixelData.data[index] = 190
                    // pixelData.data[index+1] = 0
                    // pixelData.data[index+2] = 210
                    // pixelData.data[index+3] = 255

                    console.log(pixelData.data[index])
                    console.log(pixelData.data[index + 1])
                    console.log(pixelData.data[index + 2])
                    console.log(pixelData.data[index + 3])
                    console.log("-----------------------------")
                }
            }

            ctx.putImageData(pixelData, canvasOffsetLeft, canvasOffsetTop)

        }

        function updateCanvas() {
            var x,y
            //get запрос на получение массива и его выгрузка на полотно
            for (let i = 0; i < pixels.length; i++) {
                ctx.fillStyle = pixels[i]
                x = i
                y = Math.floor(i/canvasHeight)
                console.log("x = " + (x-canvasWidth*y) + " " + "y = " + y)
                ctx.fillRect(i - canvasWidth*y ,y,1,1)
            }
            ctx.strokeRect(0, 0, canvasWidth, canvasHeight);
            console.log(pixels)
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
            ctx.fillStyle = "red"; //любой другой цвет change
            ctx.fillRect(mouseX, mouseY, 1, 1);
            console.log(pixels)
            pixels[mouseX + mouseY*canvasWidth] = currColor
            console.log(pixels)

        });
        //палитра