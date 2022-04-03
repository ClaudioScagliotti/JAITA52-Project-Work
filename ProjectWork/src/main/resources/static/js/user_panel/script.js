"use strict";

const GET_BY_ID_URL = "/api/prenotazione";
const DELETE_BY_ID_URL = "/prenotazione/termina";

let token = null;

let btn_delete = null;

let editModal;
let deleteModal;

let input_vehicle;
let input_inizio;
let input_fine;
let input_pren_id;

function delete_button_click(event) {
	let btn = event.currentTarget;
	let id = btn.dataset.pid;
	btn_delete.setAttribute("data_pren_id", id);
}

function confirmed_delete(event) {
	let id = event.currentTarget.getAttribute("data_pren_id");
	fetch(DELETE_BY_ID_URL + "?id=" + encodeURIComponent(id), { method: "POST" })
		.then(res => {
			if (res.ok)
				window.location.href = "/prenotazione";
			else {
				//TODO alert
			}
		})
		.catch(e => {
			//TODO alert
		});
}

function edit_button_click(event) {

	let btn = event.currentTarget;
	let id = btn.dataset.pid;
	input_pren_id.value = id;
	fetch(GET_BY_ID_URL + "?id=" + id + "&token=" + token)
		.then(res => res.json())
		.then(json => {
			init_form_values(json);
		});
		
	document.querySelectorAll('.form_pid_element')
		.forEach(e=>{
			e.innerHTML = id;
		})
}

function convertTime(date){
	
	return date.getFullYear()+'-'
		+String((date.getMonth()+1)).padStart(2,'0')
		+"-"+String(date.getDate()).padStart(2,'0')
		+"T"+String(date.getHours()).padStart(2,'0')
		+":"+String(date.getMinutes()).padStart(2,'0');
	
	//yyyy-MM-ddThh:mm
}

function init_form_values(json) {
	input_vehicle.value = json.vId;
	const init = new Date(json.inizio);
	const fine = new Date(json.fine);
	input_inizio.value = convertTime(init);
	input_fine.value = convertTime(fine);
}


window.addEventListener('DOMContentLoaded', event => {
	let edit_btns = document.querySelectorAll(".btn_edit");
	edit_btns.forEach(btn => btn.addEventListener('click', edit_button_click));

	let del_btns = document.querySelectorAll(".btn_delete");
	del_btns.forEach(btn => btn.addEventListener('click', delete_button_click));

	token = document.getElementById("rest_token").value;

	btn_delete = document.getElementById("btn_confirmed_delete");
	btn_delete.addEventListener('click', confirmed_delete);

	editModal = new bootstrap.Modal(document.getElementById('modPren'));
	deleteModal = new bootstrap.Modal(document.getElementById('elimPren'));
	
	input_pren_id = document.getElementById("input_id");
	input_vehicle = document.getElementById("input_vId");
	input_inizio = document.getElementById("input_inizio");
	input_fine = document.getElementById("input_fine");
})