window.addEventListener('load', function () {


   const formulario = document.querySelector('#update_paciente_form');



   formulario.addEventListener('submit', function (event) {

       let pacienteId = document.querySelector('#paciente_id').value;



       const formData = {

                  id: document.querySelector('#paciente_id').value,

                  apellido: document.querySelector('#apellido').value,

                  nombre: document.querySelector('#nombre').value,

                  dni: document.querySelector('#dni').value,

                  fecha: document.querySelector('#fecha').value,

                  domicilio: {
                       id: document.querySelector('#domicilio_id').value,

                       calle: document.querySelector('#calle').value,

                       numero: document.querySelector('#numero').value,

                       localidad: document.querySelector('#localidad').value,

                       provincia: document.querySelector('#provincia').value,
                  },

                  mail: document.querySelector('#mail').value
                  };


       const url = '/pacientes';

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

         const url = '/pacientes'+"/"+id;

         const settings = {

             method: 'GET'

         }

         fetch(url,settings)

         .then(response => response.json())

         .then(data => {

             let paciente = data;

             document.querySelector('#paciente_id').value = paciente.id;

             document.querySelector('#apellido').value = paciente.apellido;

             document.querySelector('#nombre').value = paciente.nombre;

             document.querySelector('#dni').value = paciente.dni;

             document.querySelector('#fecha').value = paciente.fecha;

             document.querySelector('#domicilio_id').value = paciente.domicilio.id;

             document.querySelector('#calle').value = paciente.domicilio.calle;

             document.querySelector('#numero').value = paciente.domicilio.numero;

             document.querySelector('#localidad').value = paciente.domicilio.localidad;

             document.querySelector('#provincia').value = paciente.domicilio.provincia;

             document.querySelector('#mail').value = paciente.mail;

             document.querySelector('#div_paciente_updating').style.display = "block";

         }).catch(error => {

             alert("Error: " + error);

         })

     }