const fileInput = document.getElementById("file-input");
const inputBox = document.getElementById("input-box");

function leseDatei() {
    console.log("test");
    let file = fileInput.files[0];
    let reader = new FileReader();
    console.log("test");
    let fileContentArray = file.result.split(/\r\n|\n/);
    for(let line = 0; line < fileContentArray.length - 1; line++) {
        console.log(line + " --> " + fileContentArray[line]);
    }
    console.log("test");
}

let vorratKategorie = "Vorrat:";
let gemueseObstKategorie ="Gemüse / Obst:";
let gekuehltKategorie = "Gekühlt:";
let tiefkuelKategorie = "Tiefkühl:";

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
