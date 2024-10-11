function validatePoint() {
    let xElement = document.querySelector('input[name="x"]:checked');
    let y = parseFloat(document.getElementById("y").value);
    let r = parseFloat(document.getElementById("r").value);
    let message = document.getElementById("message");


    if (!xElement) {
        message.textContent = "Пожалуйста, выберите координату X.";
        return;
    }

    let x = parseFloat(xElement.value);

    console.log("Приняты данные:", x, y, r);

    if (isNaN(x) || isNaN(y) || isNaN(r)) {
        message.textContent = "Пожалуйста, введите корректные значения!";
        return;
    }

    if (r < 1 || r > 4 || y < -5 || y > 5) {
        message.textContent = "Координаты Y должны быть в пределах [-5, 5], \nа R в пределах [1, 4].";
        return;
    } else {
        message.textContent = "";
    }


    fetch(`http://localhost:8081/fcgi-bin/server.jar?x=${x}&y=${y}&r=${r}`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(result => {
            const resultBody = document.getElementById('resultBody');
            const newRow = document.createElement('tr');

            newRow.innerHTML = ` 
            <td>${x}</td>
            <td>${y}</td>
            <td>${r}</td>
            <td>${result.result !== undefined ? (result.result ? 'true' : 'false') : 'undefined'}</td>
            <td>${result.currentTime !== undefined ? result.currentTime : 'undefined'}</td>
            <td>${result.executionTime !== undefined ? result.executionTime : 'undefined'}</td>
        `;

            resultBody.appendChild(newRow);
        })
        .catch(error => console.error('Error:', error));
}
function appendData(item, x ,y ,r){
    let body = document.querySelector("table tbody");
    let thead = document.querySelector("table thead");
    let RequestStatus = document.querySelector("status")
    RequestStatus.innerHTML = '';

    const row = document.createElement("tr");

    const Xcell = document.createElement("td");
    Xcell.textContent = x;
    row.appendChild(Xcell);

    const Ycell = document.createElement("td");
    Ycell.textContent = y;
    row.appendChild(Ycell);

    const Rcell = document.createElement("td");
    Rcell.textContent = r;
    row.appendChild(Rcell);

    const status = document.createElement("td");

    item.status === true ? status.textContent = "Попала" : status.textContent = "Не попала";
    row.appendChild(status);

    const currentTime = document.createElement("td");
    currentTime.textContent = new Date().toLocaleDateString() + ":" + new Date().toLocaleTimeString();
    row.appendChild(currentTime);

    const executionTime = document.createElement("td");
    executionTime.textContent = item.time;
    row.appendChild(executionTime);

    body.prepend(row);
    thead.classList.add('visible');

    let statusText = document.createElement("h2");
    if(item.status){
        statusText.textContent = "Попадание";
        RequestStatus.style.color = "green";
    }
    else{
        statusText.textContent = "Промах"
        RequestStatus.style.color = "red";
    }
    RequestStatus.classList.add('visible');
    RequestStatus.appendChild(statusText);

}

document.getElementById("info-block").addEventListener("submit", function (event) {
    event.preventDefault();
    validatePoint();
});


document.querySelectorAll("input[name='x']").forEach(checkbox => {
    checkbox.addEventListener('change', function () {
        document.querySelectorAll("input[name='x']").forEach(cb => {
            if (cb !== this) cb.checked = false;
        });
    });
});


function resetForm() {
    document.getElementById("status").textContent = "";
    document.getElementById("table-text").innerHTML = `
        <tr>
            <th>Координата X</th>
            <th>Координата Y</th>
            <th>Радиус R</th>
            <th>Факт попадания в область</th>
            <th>Текущее время</th>
            <th>Время выполнения скрипта (ms)</th>
        </tr>`;
}


