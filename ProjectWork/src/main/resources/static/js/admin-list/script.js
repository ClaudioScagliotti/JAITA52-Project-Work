"use strict";

const GET_VEHICLE_BY_ID_URL="/api/veicolo/";
const DELETE_VEHICLE_BY_ID_URL="/api/veicolo/";

let veicolo_form = {
	"id":null,
	"categoria":null,
	"alimentazione":null,
	"marca":null,
	"modello":null,
	"colore":null,
	"descrizione":null,
	"indirizzo":null,
	"x":null,
	"y":null,
}

let veicolo_form_checkbox = {
	"disponibile":null,
	"visibile":null,
}

let immagine = null;

let token = null;

let btn_delete = null;

let editModal;
let deleteModal;

function delete_button_click(event){
	let btn = event.currentTarget;
	let id = btn.dataset.vId;

	btn_delete.setAttribute("data_vehicle_id",id);
}

function confirmed_delete(event){
	let id = event.currentTarget.getAttribute("data_vehicle_id");
	
	fetch(DELETE_VEHICLE_BY_ID_URL+id+"?token="+token,{method: 'DELETE'})
	.then(res=>{
		if(res.ok)
		{
			let row_deleted = document.getElementById("row_"+id);
			if(row_deleted){
				row_deleted.remove();
			}
		}else if(res.status==422){
			showAlert("CANNOT DELETE TO PRESERVE DATA INTEGRITY");
		}
		else{
			showAlert("FAILED TO DELETE - [#"+res.status+"]");
		}
		deleteModal.hide();
	}).catch();
}

function edit_button_click(event){
	
	let btn = event.currentTarget;
	let id = btn.dataset.vId;
	fetch(GET_VEHICLE_BY_ID_URL+id+"?token="+token)
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
	
	let del_btns = document.querySelectorAll(".delete-vehicle");
	del_btns.forEach(btn=>btn.addEventListener('click',delete_button_click));	
	
	token = document.getElementById("rest_token").value;
	
	btn_delete = document.getElementById("btn_confirmed_delete");
	btn_delete.addEventListener('click',confirmed_delete);
	init_form_element_ref();

	editModal = new bootstrap.Modal(document.getElementById('modVeic'));
	deleteModal = new bootstrap.Modal(document.getElementById('elimVeic'));  
})