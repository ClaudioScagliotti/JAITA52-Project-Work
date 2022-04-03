let alertModal;
let alertModalText;

function showAlert(message){
	alertModalText.innerText = message;
    alertModal.show();
}

window.addEventListener('DOMContentLoaded',event=>{
    alertModal = new bootstrap.Modal(document.getElementById('alertModal'));
	alertModalText = document.getElementById("alert_message");
});