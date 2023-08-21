
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
    }
    inputBox.value = "";
}

const listContainer = document.getElementById("list-container");
listContainer.addEventListener("click", function(e) {
    if(e.target.tagName === "LI") {
        e.target.classList.toggle("checked");
    }
}, false);


