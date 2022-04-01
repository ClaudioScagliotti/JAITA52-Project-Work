'use strict'

function fetchCategories() {

	const GET_CATEGORIES_URL = "/api/categories";
	const LI_TEMPLATE =
		`<li><a class="dropdown-item" href="/disponibilita?categoria={{id}}">` +
		`{{cat}}</a></li>`;

	fetch(GET_CATEGORIES_URL).then(res => {
		if (res.ok)
			return res.json();
		throw "failed to fetch categories";
	}).then(json => {
		let dropdown = document.getElementById('navbar-dropdown-categories');
		let a = [];
		json.forEach(e => {
			dropdown.innerHTML += LI_TEMPLATE
				.replaceAll("{{id}}", e.id)
				.replaceAll("{{cat}}", e.nome);
		});
	}).catch(error => console.log(error));

}

window.addEventListener('DOMContentLoaded', (event) => {
	document.querySelector('#navbarSideCollapse')
		.addEventListener('click', (e) => {
			document.querySelector('.offcanvas-collapse').classList.toggle('open')
		})

	fetchCategories();
});