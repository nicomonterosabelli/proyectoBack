window.addEventListener('load', function () {

   const formulario = document.querySelector('#add_new_paciente');



   formulario.addEventListener('submit', function (event) {


       const formData = {

           apellido: document.querySelector('#apellido').value,

           nombre: document.querySelector('#nombre').value,

           dni: document.querySelector('#dni').value,

           fecha: document.querySelector('#fecha').value,

           domicilio: {
                calle: document.querySelector('#calle').value,

                numero: document.querySelector('#numero').value,

                localidad: document.querySelector('#localidad').value,

                provincia: document.querySelector('#provincia').value,
           },

           mail: document.querySelector('#mail').value

       };



       const url = '/pacientes';

       const settings = {

           method: 'POST',

           headers: {

               'Content-Type': 'application/json',


           },

           body: JSON.stringify(formData)

       }



       fetch(url, settings)

           .then(response => response.json())

           .then(data => {


                let successAlert = '<div class="alert alert-success alert-dismissible">' +

                    '<button type="button" class="close" ' +

                    'data-dismiss="alert">&times;</button>' +

                    '<strong></strong> Paciente agregado </div>'



                document.querySelector('#response').innerHTML = successAlert;

                document.querySelector('#response').style.display = "block";

                resetUploadForm();



           })

           .catch(error => {

                   let errorAlert = '<div class="alert alert-danger alert-dismissible">' +

                                    '<button type="button" class="close"' +

                                    'data-dismiss="alert">&times;</button>' +

                                    '<strong> Error intente nuevamente</strong> </div>'



                     document.querySelector('#response').innerHTML = errorAlert;

                     document.querySelector('#response').style.display = "block";

                    resetUploadForm();})

   });
   });
   function resetUploadForm(){

          document.querySelector('#numeroMatricula').value = "";

          document.querySelector('#nombre').value = "";

          document.querySelector('#apellido').value = "";



      }