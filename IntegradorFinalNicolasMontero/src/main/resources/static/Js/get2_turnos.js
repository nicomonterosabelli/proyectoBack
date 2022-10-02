window.addEventListener('load', function () {

   const odo = document.querySelector("#actualizarOdontologo");
   const pac = document.querySelector("#actualizarPaciente");

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



     const url = '/turnos';

     const settings = {

       method: 'GET'

     }

     fetch(url,settings)

     .then(response => response.json())

     .then(data => {


        for(turno of data){

           var table = document.getElementById("turnoTable");

           var turnoRow =table.insertRow();

           let tr_id = 'tr_' + turno.id;

           turnoRow.id = tr_id;



           let deleteButton = '<button' +

                                     ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +

                                     ' type="button" onclick="deleteBy('+turno.id+')"' +

                                     'class="btn btn-danger btn_delete">' +

                                     '&times' +

                                     '</button>';


           let updateButton = '<button' +

                                     ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +

                                     ' type="button" onclick="findBy('+turno.id+')"' +

                                     ' class="btn btn-info btn_id">' +

                                     turno.id +

                                     '</button>';


           turnoRow.innerHTML = '<td>' + updateButton + '</td>' +

                 '<td class=\"td_odontologo\">' + turno.odontologo.nombre + ' ' + turno.odontologo.apellido + '</td>' +

                 '<td class=\"td_paciente\">' + turno.paciente.nombre + ' ' + turno.paciente.apellido + '</td>' +

                 '<td class=\"td_fecha\">' + turno.fechaYHora + '</td>' +

                 '<td>' + deleteButton + '</td>';



       };



   })



   (function(){

     let pathname = window.location.pathname;

     if (pathname == "/turnosList.html") {

         document.querySelector(".nav .nav-item a:last").addClass("active");

     }

   })
   })