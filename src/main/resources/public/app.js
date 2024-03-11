function loadGetMsg() {
    const msgVar = document.getElementById("msg").value.trim(); // Trim para eliminar espacios en blanco innecesarios
    if (!msgVar) {
        alert("Por favor ingrese un mensaje."); // Validación del mensaje
        return;
    }

    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        if (this.status === 200) {
            const logs = JSON.parse(this.responseText);
            PutLogs(logs);
        } else {
            console.error("Error al cargar los logs:", this.status); // Manejo de errores
        }
    };
    xhttp.onerror = function () {
        console.error("Error de red al cargar los logs."); // Manejo de errores de red
    };
    xhttp.open("GET", "/log?message=" + encodeURIComponent(msgVar)); // Codificar el mensaje para evitar problemas con caracteres especiales
    xhttp.send();
}

function PutLogs(logs) {
    const tableHtml = `
        <table>
            <tr>
                <th>Message</th>
                <th>Issued At</th>
            </tr>
            ${logs.map(log => `
                <tr>
                    <td>${log.message}</td>
                    <td>${new Date(log.issuedAt.$date).toLocaleString()}</td> <!-- Convertir la fecha a formato legible -->
                </tr>
            `).join('')}
        </table>`;
    document.getElementById("logsTable").innerHTML = tableHtml;
}
