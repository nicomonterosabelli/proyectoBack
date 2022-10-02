window.addEventListener('load', function () {

    (function(){


      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(odontologo of data){

            var table = document.getElementById("odontologoTable");
            var peliculaRow =table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            peliculaRow.id = tr_id;


            peliculaRow.innerHTML =
                    '<td class=\"td_id\">' + odontologo.id + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>'+
                    '<td class=\"td_matricula\">' + odontologo.numeroDeMatricula + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologosList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })