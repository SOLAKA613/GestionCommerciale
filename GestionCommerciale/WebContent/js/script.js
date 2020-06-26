$(document).ready(function(){
	
	$('#prdform').submit(function(){
		
		if($('#prd_libelle').val()==""){
			$('#error_libelle').text("Libelle non renseigne");
			return false;
		}
		
		if($('#prd_pa').val()==""){
			$('#error_pa').text("Prix Achat non renseigne");
			return false;
		}
		
		if($('#prd_pv').val()==""){
			$('#error_pv').text("Prix Vente non renseigne");
			return false;
		}
		
		return true;	
	});
	
	
});



function Confirme()
{
	$('#deleteModal').modal();
	$('#deleteButton').html('<a class="btn btn-danger" onclick="appelsupprimer()">Confirmer</a>');	
}

function appelsupprimer()
{
	$('#deleteModal').hide();	
}