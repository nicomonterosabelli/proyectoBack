window.addEventListener('load', function () {

   const formulario = document.querySelector('#add_new_turno');
   const odo = document.querySelector("#agregarOdontologo");
   const pac = document.querySelector("#agregarPaciente");


   const url1 = '/pacientes';
   const settings1 = {
       method: 'GET'
   }

   fetch(url1,settings1)
         .then(response => response.json())
         .then(data => {

            for(paciente of data){
               pac.innerHTML += '<option value="'+ paciente.id + '">'+ paciente.nombre + ' ' + paciente.apellido + '</option>'
               }
   })

   const url2 = '/odontologos';
      const settings2 = {
          method: 'GET'
      }

      fetch(url2,settings2)
            .then(response => response.json())
            .then(data => {

               for(odontologo of data){
                  odo.innerHTML += '<option value="'+  odontologo.id + '">'+ odontologo.nombre + ' ' + odontologo.apellido + '</option>'
                  }

   })


   formulario.addEventListener('submit', function (event) {


       const formData = {

           odontologo: {id:parseInt(document.querySelector('#agregarOdontologo').value)},

           paciente: {id:parseInt(document.querySelector('#agregarPaciente').value)},

           fechaYHora: document.querySelector('#fecha').value,

       };
       const url = "/turnos"

       const settings = {

           method: 'POST',

           headers: {

               'Content-Type': 'application/json'

           },

           body: JSON.stringify(formData)

       }



       fetch(url, settings)

           .then(response => response.json())

           .then(data => {


                let successAlert = '<div class="alert alert-success alert-dismissible">' +

                    '<button type="button" class="close" ' +

                    'data-dismiss="alert">&times;</button>' +

                    '<strong></strong> Turno agregado </div>'



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

          document.querySelector('#agregarOdontologo').value = "";

          document.querySelector('#agregarPaciente').value = "";

          document.querySelector('#fecha').value = "";
      }