(function(window, document) {



    window.onload = init;


    function sendRequest(x, y, r, clientX, clientY) {
        // Create a new XMLHttpRequest object
        let xhr = new XMLHttpRequest();

        // Configure it: POST-request to the URL
        xhr.open('POST', 'http://localhost:24738/MVC-GeoValidator/controller', true); // Replace with your server URL

        // Set the request header
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        // Set up a function to handle the response
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Parse and log the response
                    const response = JSON.parse(xhr.responseText)
                    showResponse(response, clientX, clientY)
                } else {
                    console.error('Error:', xhr.status, xhr.statusText);
                }
            }
        };

        // Send the request with the data
        xhr.send("x="+x+"&y="+y+"&r="+r);
    }

    document.getElementById("info-block").addEventListener("submit", function (event) {
        event.preventDefault();
        sendRequestButton();
    });

    function sendRequestButton() {
        let xElement = document.querySelector('input[name="x"]:checked');
        if (!xElement) {
            message.textContent = "Please, choose X";
            return;
        }
        let x = parseFloat(xElement.value);
        let y = document.getElementById("y").value
        let r = document.getElementById("r").value
        const rect = document.querySelector(".canvas").getBoundingClientRect();
        let clientX = x * r * 200 + 300 + rect.left
        let clientY =  y * r * 200
        clientY = 400 - clientY
        clientY += rect.top
        sendRequest(x, y, r, clientX, clientY)
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

    function init(){
        drawDotsFromBeanTableData();
        document.querySelector(".canvas").addEventListener('click', handleAxisBoxClick);
    }

    function handleAxisBoxClick(event) {
        const axisBox = document.querySelector(".canvas");
        const rect = axisBox.getBoundingClientRect();
        // Calculate mouse position relative to the div
        let x = event.clientX - rect.left;
        let y = event.clientY - rect.top;
        let r = parseFloat(document.getElementById("r").value);

        if (r > 0) {
            x = (x - 300) / 200;
            y = -(y - 400) / 200;
            x *= r;
            y *= r;
            if (r !== null && r !== undefined) {
                sendRequest(x, y, r, event.clientX, event.clientY);
            } else {
                console.error('Error: r value is null or undefined');
            }
        } else {
            alert("Unable to determine point coordinates without radius");
            return;
        }

        // Scale the mouse coordinates to the range of the canvas
    }

    function drawDot(clientX, clientY, hit) {
        const dot = document.createElement('dot');
        dot.style.left = clientX+`px`; // Center the dot
        dot.style.top = clientY+`px` + window.scrollY;  // Center the dot
        if (hit) dot.classList.add("hit");
        document.body.appendChild(dot)
    }

    function drawDotsFromBeanTableData() {
        const tbody = document.querySelector("tbody")
        let output = []
        tbody.childNodes.forEach(function (tr) {
            let data = []
            tr.childNodes.forEach(function (th) {
                if (th.textContent.trim() !== "") data.push(th.textContent.trim());
            })
            if (data.length !== 0) output.push(data);
        });
        output = output.slice(1); // [0] - X, [1] - Y, [2] - R, [3] - isHit

        const rect = document.querySelector(".canvas").getBoundingClientRect();
        for (var i = 0; i < output.length; i++) {
            let x = Number(output[i][0]);
            let y = Number(output[i][1]);
            let r = Number(output[i][2]);
            let rlast = Number(output[output.length - 1][2]);
            let isHit = String(output[i][3]) === 'true';

            if (x >= -1.25*rlast && x <= 1.25*rlast && y >= -1.25*rlast && y <= 1.25*rlast) {
                x /= rlast
                y /= rlast
                x *= 200
                y *= 200

                y = 400 - y
                x += 300

                x += rect.left;
                y += rect.top;

                drawDot(x, y, isHit)
            }
        }
    }
})(window, document);


