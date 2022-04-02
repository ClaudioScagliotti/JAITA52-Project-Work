function search_word() {
    let input = document.getElementById('searchbar').value
    input=input.toLowerCase();
    let x = document.querySelectorAll(".ric");
      
    for (i = 0; i < x.length; i++) {
        if (x[i].innerText.toLowerCase().includes(input)) {
          x[i].removeAttribute("hidden");
        } else {
            x[i].setAttribute("hidden", true);
        }
      }
}

window.addEventListener('DOMContentLoaded', (event) => {
	document.getElementById("cerca").addEventListener("click", search_word);
});