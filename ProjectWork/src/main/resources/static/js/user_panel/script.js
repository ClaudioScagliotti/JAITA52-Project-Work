"use strict";

const GET_VEHICLE_BY_ID_URL="/api/veicolo/";
const DELETE_BY_ID_URL="/prenotazione/termina";

//let token = null;

let btn_delete = null;

let editModal;
let deleteModal;

function delete_button_click(event){
	let btn = event.currentTarget;
	let id = btn.dataset.pid;
	btn_delete.setAttribute("data_pren_id",id);
}

function confirmed_delete(event){
	let id = event.currentTarget.getAttribute("data_pren_id");
	fetch(DELETE_BY_ID_URL+"?id="+encodeURIComponent(id),{method:"POST"})
		.then(res=>{
			if(res.ok)
				window.location.href = "/prenotazione";
			else{
				//TODO alert
			}
		})
		.catch(e=>{
				//TODO alert
		});
}
/*
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
*/

window.addEventListener('DOMContentLoaded',event=>{
	//let edit_btns = document.querySelectorAll(".btn_edit");
	//edit_btns.forEach(btn=>btn.addEventListener('click',edit_button_click));	
	
	let del_btns = document.querySelectorAll(".btn_delete");
	del_btns.forEach(btn=>btn.addEventListener('click',delete_button_click));	
	
	//token = document.getElementById("rest_token").value;
	
	btn_delete = document.getElementById("btn_confirmed_delete");
	btn_delete.addEventListener('click',confirmed_delete);

	editModal = new bootstrap.Modal(document.getElementById('modPren'));
	deleteModal = new bootstrap.Modal(document.getElementById('elimPren'));  
})