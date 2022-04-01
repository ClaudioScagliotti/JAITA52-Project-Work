'use strict'

function fetchCategories(){
	
	const GET_CATEGORIES_URL = "";
	const LI_TEMPLATE = 
	`<li><a class="dropdown-item" href="/veicol">`+
	`<i class="fa fa-car-side me-1"></i> {{cat}}</a></li>`;
	
	fetch(GET_CATEGORIES_URL).then(res=>{
			if(res.ok)
				return res.json();
			throw "failed to fetch categories";
		}).then(json=>{
			let dropdown = document.getElementById('navbar-dropdown-categories');
			let a = [];
			a.forEach(e=>{
				document.createElement("")
			});
			json.forEach(e=>{
				
			});
		}).catch(error=>console.log(error));
	
}

window.addEventListener('DOMContentLoaded', (event) => {
    document.querySelector('#navbarSideCollapse')
        .addEventListener('click', (e)=>{
            document.querySelector('.offcanvas-collapse').classList.toggle('open')
        })

	//fetchCategories();
});