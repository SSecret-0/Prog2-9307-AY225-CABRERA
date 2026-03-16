// Programmer: Theodore Kelly Cabrera | Student ID: 25-2422-239

let records = [];

// Load from localStorage if available
if (localStorage.getItem("studentRecords")) {
    records = JSON.parse(localStorage.getItem("studentRecords"));
} else {
    // Initial CSV data
    const csvData = `
StudentID,first_name,last_name,LAB WORK 1,LAB WORK 2,LAB WORK 3,PRELIM EXAM,ATTENDANCE GRADE
073900438,Osbourne,Wakenshaw,69,5,52,12,78
114924014,Albie,Gierardi,58,92,16,57,97
111901632,Eleen,Pentony,43,81,34,36,16
084000084,Arie,Okenden,31,5,14,39,99
272471551,Alica,Muckley,49,66,97,3,95
104900721,Jo,Burleton,98,94,33,13,29
111924392,Cam,Akram,44,84,17,16,24
292970744,Celine,Brosoli,3,15,71,83,45
107004352,Alan,Belfit,31,51,36,70,48
071108313,Jeanette,Gilvear,4,78,15,69,69
`.trim();

    const lines = csvData.split("\n");
    lines.slice(1).forEach(line => {
        const [id, first, last, lab1, lab2, lab3, prelim, attendance] = line.split(",");
        records.push({
            id,
            name: first + " " + last,
            lab1,
            lab2,
            lab3,
            prelim,
            attendance
        });
    });

    localStorage.setItem("studentRecords", JSON.stringify(records));
}

// Render table
function render() {
    const tbody = document.querySelector("#recordTable tbody");
    tbody.innerHTML = "";

    records.forEach((rec, index) => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${rec.id}</td>
            <td>${rec.name}</td>
            <td>${rec.lab1}</td>
            <td>${rec.lab2}</td>
            <td>${rec.lab3}</td>
            <td>${rec.prelim}</td>
            <td>${rec.attendance}</td>
            <td><button onclick="deleteRecord(${index})">Delete</button></td>
        `;

        tbody.appendChild(tr);
    });
}

// Add new record
function addRecord() {
    const id = document.getElementById("id").value.trim();
    const first = document.getElementById("first").value.trim();
    const last = document.getElementById("last").value.trim();
    const lab1 = document.getElementById("lab1").value.trim();
    const lab2 = document.getElementById("lab2").value.trim();
    const lab3 = document.getElementById("lab3").value.trim();
    const prelim = document.getElementById("prelim").value.trim();
    const attendance = document.getElementById("attendance").value.trim();

    if (!id || !first || !last) {
        alert("ID, First Name, and Last Name are required!");
        return;
    }

    records.push({
        id,
        name: first + " " + last,
        lab1,
        lab2,
        lab3,
        prelim,
        attendance
    });

    localStorage.setItem("studentRecords", JSON.stringify(records));
    render();

    // Clear inputs
    document.getElementById("id").value = "";
    document.getElementById("first").value = "";
    document.getElementById("last").value = "";
    document.getElementById("lab1").value = "";
    document.getElementById("lab2").value = "";
    document.getElementById("lab3").value = "";
    document.getElementById("prelim").value = "";
    document.getElementById("attendance").value = "";
}

// Delete a record
function deleteRecord(index) {
    if (confirm("Are you sure you want to delete this record?")) {
        records.splice(index, 1);
        localStorage.setItem("studentRecords", JSON.stringify(records));
        render();
    }
}

// Initial render
render();
