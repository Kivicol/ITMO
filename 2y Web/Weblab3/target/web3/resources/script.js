(function (window, document) {

    window.onload = init;
    window.redrawCanvas = redrawCanvas;

    function drawCanvas1() {
        const canvas = document.getElementById('canvas1');
        canvas.width = 300;
        canvas.height = 300;
        if (canvas.getContext) {
            const ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);

            // Прямоугольник от (-R, 0) до (0, R)
            ctx.beginPath();
            ctx.rect(150 - 75, 150 - 150, 75, 150);
            ctx.fillStyle = 'rgb(42, 42, 42)';
            ctx.fill();
        }
    }

    function drawCanvas2() {
        const canvas = document.getElementById('canvas2');
        canvas.width = 300;
        canvas.height = 300;
        if (canvas.getContext) {
            const ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);

            // Треугольник от (0, 0) до (R, 0) и (0, -R/2)
            ctx.beginPath();
            ctx.moveTo(150, 150 - 75); // Вершина (0, R/2)
            ctx.lineTo(150 + 150, 150); // Вершина (R, 0)
            ctx.lineTo(150, 150); // Вершина (0, 0)
            ctx.closePath();
            ctx.fillStyle = 'rgb(42, 42, 42)';
            ctx.fill();
        }
    }

    function drawCanvas3() {
        const canvas = document.getElementById('canvas3');
        canvas.width = 300;
        canvas.height = 300;
        if (canvas.getContext) {
            const ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);

            // Четверть круга в левом нижнем углу
            ctx.beginPath();
            ctx.arc(150, 150, 75, 0.5 * Math.PI, Math.PI); // Радиус R/2
            ctx.lineTo(150, 150);
            ctx.closePath();
            ctx.fillStyle = 'rgb(42, 42, 42)';
            ctx.fill();
        }
    }

    function redrawCanvas() {
        clearDots();
        drawCanvas1();
        drawCanvas2();
        drawCanvas3();

        let r = Number(document.querySelector("input[id$='decimal']").value);
        drawDotsFromBeanTableData(r);
    }

    function init() {
        redrawCanvas();
        document.querySelector(".axis-box").addEventListener('click', handleAxisBoxClick);
    }

    function handleAxisBoxClick(event) {
        const axisBox = document.querySelector(".axis-box");
        const rect = axisBox.getBoundingClientRect();
        let r = Number(document.querySelector("input[id$='decimal']").value);

        if (!Number.isInteger(r)) {
            alert("Невозможно отправить точку: R должно быть натуральным числом!");
            return;
        }

        let x = event.clientX - rect.left - 150;
        let y = -(event.clientY - rect.top - 150); // Инвертируем Y, т.к. ось Y направлена вверх

        x = (x / 100) * r;
        y = (y / 100) * r;

        sendRequest(x, y, r, event.clientX, event.clientY);
    }


    function drawDot(clientX, clientY, hit) {
        const dot = document.createElement('div');
        dot.className = 'dot';
        dot.style.left = clientX + `px`;
        dot.style.top = clientY + `px`;
        if (hit) dot.classList.add("hit");
        document.body.appendChild(dot);
    }

    function sendRequest(x, y, r, clientX, clientY) {
        document.querySelector("input[id$=\":xInput\"]").value = x;
        document.querySelector("input[id$=':yInput_input']").value = y;
        document.querySelector("input[id$=\":submit-btn\"]").click();
    }

    function showResponse(response, clientX, clientY) {
        let tbody = document.querySelector("tbody") // table

        const tr = document.createElement("tr")
        tr.setAttribute("class", "move-in")

        const th1 = document.createElement("th")
        th1.setAttribute("class", "row")
        th1.appendChild(document.createTextNode(Number(response.x).toFixed(4)))

        const th2 = document.createElement("th")
        th2.setAttribute("class", "row")
        th2.appendChild(document.createTextNode(Number(response.y).toFixed(4)))

        const th3 = document.createElement("th")
        th3.setAttribute("class", "row")
        th3.appendChild(document.createTextNode(Number(response.r).toFixed(0)))


        const th4 = document.createElement("th")
        th4.setAttribute("class", "row")
        if (response.isHit) th4.setAttribute("class", "row text-gradient");
        th4.appendChild(document.createTextNode(response.isHit))

        tr.appendChild(th1)
        tr.appendChild(th2)
        tr.appendChild(th3)
        tr.appendChild(th4)

        tbody.insertBefore(tr, tbody.lastElementChild)
        drawDot(clientX, clientY, response.isHit)
    }

    function drawDotsFromBeanTableData(r) {
        const tbody = document.querySelector("table#results-table > tbody")
        let output = []
        tbody.childNodes.forEach(function (tr) {
            let data = []
            tr.childNodes.forEach(function (th) {
                if (th.textContent.trim() !== "") data.push(th.textContent.trim());
            })
            if (data.length !== 0) output.push(data);
        });
        console.log(output)

        const rect = document.querySelector(".axis-box").getBoundingClientRect();
        for (var i = 0; i < output.length; i++) {
            let x = Number(output[i][0]);
            let y = Number(output[i][1]);
            let isHit = String(output[i][3]) === 'true' ? true : false;

            x /= r
            y /= r
            x *= 100
            y *= 100

            y = 150 - y
            x += 150

            x += rect.left;
            y += rect.top;

            drawDot(x, y, isHit)

        }
    }

    function clearDots() {
        const dots = document.querySelectorAll(".dot");
        dots.forEach(function (dot) {
            dot.remove();
        });
    }

})(window, document);


