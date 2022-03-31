<td scope="row">{{id}}</td>
<td scope="row">{{categoria}}</td>
<td scope="row">{{marca}}</td>
<td scope="row">{{modello}}</td>
<td scope="row">{{indirizzo}}</td>
<td scope="row">{{disponibile}}</td>

<td>
	<button type="button" class="btn btn-warning ms-2 edit-vehicle"
		data-v-id="{{id}}"
		data-bs-toggle="modal" data-bs-target="#modVeic">
		<i class="fa fa-pen-to-square me-1"></i> Modifica
	</button>
	<button type="button" class="btn btn-danger ms-2 delete-vehicle"
		data-v-id="{{id}}"
		data-bs-toggle="modal" data-bs-target="#elimVeic">
		<i class="fa fa-trash-can me-1"></i> Elimina
	</button>
</td>