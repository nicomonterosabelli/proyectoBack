window.addEventListener('load', function () {

    (function(){


      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(paciente of data){

            var table = document.getElementById("pacienteTable");
            var peliculaRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            peliculaRow.id = tr_id;


            peliculaRow.innerHTML =
                    '<td class=\"td_id\">' + paciente.id + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>'+
                    '<td class=\"td_dni\">' + paciente.dni + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/pacientesList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })