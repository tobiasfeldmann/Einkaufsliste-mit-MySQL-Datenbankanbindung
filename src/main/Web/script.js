
const inputBox = document.getElementById("input-box");

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
