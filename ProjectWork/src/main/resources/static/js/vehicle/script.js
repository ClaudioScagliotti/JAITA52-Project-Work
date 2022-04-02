"use strict";

let login_modal = null;
const base_login_action = "/login";

function updateMapPosition(){
	let x = document.getElementById("input_x_cord").value;
	let y = document.getElementById("input_y_cord").value;

	let position = [x,y];

	let map = L.map('map').setView(position, 13);
	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		maxZoom: 18,
		id: 'mapbox/streets-v11',
		tileSize: 512,
		zoomOffset: -1,
		accessToken: 'pk.eyJ1IjoiYXJpYW5uYWFsZXNzIiwiYSI6ImNsMWV6YWpybjBlZjYzY21xaGF3bmlwNDQifQ.DfisejmxdOSOJQNYqzvT9g'
	}).addTo(map);


	L.marker(position).addTo(map)
    .bindPopup('Vehicle<br>position');
}

window.addEventListener('DOMContentLoaded', (event) => {

	updateMapPosition();
	
	let btn_prenota = document.getElementById("btn_prenota");
	if(btn_prenota){
		login_modal = new bootstrap.Modal(document.getElementById("loginForm"));
		btn_prenota.addEventListener('click',()=>{
			let lm_form = document.getElementById("login_modal_form");
			let url = window.location.href.substring(window.location.origin.length)+"#prenotazione";
			lm_form.action=base_login_action+"?redirect="+encodeURIComponent(url);
			login_modal.show();
		});
		
		login_modal.addEventListener('hide.bs.modal', function () {
			alert("modal closed");
			document.getElementById("login_modal_form").action=base_login_action;
		});
	}
	
	
	//let dateTimes = document.querySelectorAll("input[type=datetime-local]");
	//dateTimes.forEach(date=>date.step=900);
});