'use strict'

window.addEventListener('DOMContentLoaded', (event) => {
    document.querySelector('#navbarSideCollapse')
        .addEventListener('click', (e)=>{
            document.querySelector('.offcanvas-collapse').classList.toggle('open')
        })
});