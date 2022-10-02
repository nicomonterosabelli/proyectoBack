window.addEventListener('load', function () {

    (function(){


      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(turno of data){

            var table = document.getElementById("turnoTable");
            var peliculaRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            peliculaRow.id = tr_id;


            peliculaRow.innerHTML =
                    '<td class=\"td_id\">' + turno.id + '</td>' +
                    '<td class=\"td_paciente\">' + turno.paciente.nombre.toUpperCase() + ' ' + turno.paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_odontologo\">' + turno.odontologo.nombre.toUpperCase() + ' ' + turno.odontologo.apellido.toUpperCase() + '</td>'+
                    '<td class=\"td_fechaYHora\">' + turno.fechaYHora + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnosList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })