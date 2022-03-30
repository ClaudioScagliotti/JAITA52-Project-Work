"use strict"

let myChart = null;
let chartData = null;



function fetchData() {
	const URL = "/api/veicolo/chart";

	fetch(URL).then(
		res => {
			if (res.ok)
				return res.json();
		}).then(data => {
        createChart(data);
		}).catch(()=>{
        createChart({names:["no-data"],count:[0]});
    });
}

function createChart(data){
 
  chartData = {
		labels: data.names,
		datasets: [{
			label: 'Risparmio annuale CO2 in KG',
			backgroundColor: '#59C567',
			borderColor: 'rgb(255, 99, 132)',
			data: data.count,
		}]
	};

  const config = {
    type: 'bar',
    data: chartData,
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    },
  };
  
  myChart = new Chart(
    document.getElementById('myChart'),
    config
  );

}

window.addEventListener("DOMContentLoaded",()=>{

  fetchData();
})
