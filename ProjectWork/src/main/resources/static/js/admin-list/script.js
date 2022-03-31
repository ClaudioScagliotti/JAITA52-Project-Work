"use strict";

const GET_VEHICLE_BY_ID_URL="/api/veicolo/";

let veicolo_form = {
	"id":null,
	"categoria":null,
	"alimentazione":null,
	"marca":null,
	"modello":null,
	"colore":null,
	"descrizione":null,
	"indirizzo":null,
	"coordinataX":null,
	"coordinataY":null,
}

let veicolo_form_checkbox = {
	"disponibile":null,
	"visibile":null,
}

let immagine = null;

function delete_button_click(event){
	let btn_delete = document.getElementById("btn_confirmed_delete");
	btn_delete.setAttribute("data_vehicle_id",event.currentTarget);
}

function edit_button_click(event){


	let btn = event.currentTarget;
	let id = btn.dataset.vId;
	fetch(GET_VEHICLE_BY_ID_URL+id)
		.then(res=>res.json())
		.then(json=>{
			init_form_values(json);
		});
	}
	
	function init_form_values(json){
		
		let entries = Object.keys(veicolo_form);
		entries.forEach(key=>{
			veicolo_form[key].value = json[key];
		});
		entries = Object.keys(veicolo_form_checkbox);
		entries.forEach(key=>{
			veicolo_form_checkbox[key].checked = json[key];
		});
		immagine.setAttribute('src',json.immagine);
}

function init_form_element_ref(){
	let entries = Object.keys(veicolo_form);
	entries.forEach(key=>{
		veicolo_form[key] = document.getElementById('input_'+key);
	});
	entries = Object.keys(veicolo_form_checkbox);
	entries.forEach(key=>{
		veicolo_form_checkbox[key] = document.getElementById('input_'+key);
	});
	immagine = document.getElementById('v_img');
}

window.addEventListener('DOMContentLoaded',event=>{
	let edit_btns = document.querySelectorAll(".edit-vehicle");
	edit_btns.forEach(btn=>btn.addEventListener('click',edit_button_click));

	init_form_element_ref();
})