window.addEventListener('load', function () {


   const formulario = document.querySelector('#update_turno_form');



   formulario.addEventListener('submit', function (event) {

       let turnoId = document.querySelector('#turno_id').value;



       const formData = {

                  id: document.querySelector('#turno_id').value,

                  odontologo: {id:parseInt(document.querySelector('#actualizarOdontologo').value)},

                  paciente: {id:parseInt(document.querySelector('#actualizarPaciente').value)},

                  fechaYHora: document.querySelector('#fecha').value
                  };


       const url = '/turnos';

       const settings = {

           method: 'PUT',

           headers: {

               'Content-Type': 'application/json',


           },

           body: JSON.stringify(formData)

       }

         fetch(url,settings)

         .then(response => response.json())



   })

})


   function findBy(id) {

         const url = '/turnos'+"/"+id;

         const settings = {

             method: 'GET'

         }

         fetch(url,settings)

         .then(response => response.json())

         .then(data => {

             let turno = data;

             document.querySelector('#turno_id').value = turno.id;

             document.querySelector('#fecha').value = turno.fechaYHora;

             document.querySelector('#div_turno_updating').style.display = "block";

         }).catch(error => {

             alert("Error: " + error);

         })

     }