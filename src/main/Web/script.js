const fileInput = document.getElementById("file-input");
const inputBox = document.getElementById("input-box");
let tempString = "";
let textAusDatei = "";

let produktErfasst = false;

fileInput.addEventListener("change", function(e) {
    const file = fileInput.files[0];
    const reader = new FileReader();

    reader.onload = function(e) {
        textAusDatei = reader.result;
    }
    reader.readAsText(file, "ISO-8859-1");
});

function verarbeiteDatei() {
    let vorratKategorie = "Vorrat:";
    let gemueseObstKategorie ="Gemuese / Obst:";
    let gekuehltKategorie = "Gekuehlt:";
    let tiefkuehlKategorie = "Tiefkuehl:";

    for(let i1 = 0; i1 < textAusDatei.length; i1++) {
        tempString = tempString.trimStart();

        if(tempString === vorratKategorie || tempString === gemueseObstKategorie || tempString === gekuehltKategorie || tempString === tiefkuehlKategorie) {
            console.log(tempString);
            kategorieAusDatei(tempString);
            tempString = "";
            i1 += 2;
            continue;
        }

        if(textAusDatei[i1] === "*" && tempString != "") {
            taskAusDatei(tempString);
            tempString = "";
            continue;
        }

        tempString = tempString + textAusDatei[i1];

    }
}

inputBox.addEventListener("keypress", function(event) {
    if(event.key === "Enter") {
        addTask();
    }
});

function addTask() {
    if(inputBox.value === "") {
        alert("Keine Leeren Eingaben bitte dankeee");
    }
    else {
        let li = document.createElement("li");
        li.innerHTML = inputBox.value;
        listContainer.appendChild(li);
        let span = document.createElement("span");
        span.innerHTML = "\u00d7";
        li.appendChild(span);
    }
    inputBox.value = "";
    saveData();
}

function taskAusDatei(string) {
    let li = document.createElement("li");
    li.innerHTML = string;
    listContainer.appendChild(li);
    let span = document.createElement("span");
    span.innerHTML = "\u00d7";
    li.appendChild(span);
}

function kategorieAusDatei(string) {
    let li = document.createElement("li");
    li.innerHTML = string;
    li.setAttribute("class", "kategorie");
    listContainer.appendChild(li);
    let span = document.createElement("span");
    span.innerHTML = "\u00d7";
    li.appendChild(span);
}

const listContainer = document.getElementById("list-container");
listContainer.addEventListener("click", function(e) {
    if(e.target.tagName === "LI") {
        e.target.classList.toggle("checked");
        saveData();
    }
    else if(e.target.tagName === "SPAN") {
        e.target.parentElement.remove();
        saveData();
    }
}, false);


function saveData() {
    localStorage.setItem("data", listContainer.innerHTML);
}

function showListFromLocalStorage() {
    listContainer.innerHTML = localStorage.getItem("data");
}

showListFromLocalStorage();
