function deleteBy(id)

{

         const url = '/odontologos/'+ id;

         const settings = {

             method: 'DELETE'

         }

         fetch(url,settings)

         .then(response => response.json())



         //borrar la fila de la película eliminada

         let row_id = "#tr_" + id;

         document.querySelector(row_id).remove();

}