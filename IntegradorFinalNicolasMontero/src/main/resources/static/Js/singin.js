const apiUrl = 'http://localhost:8080/usuarios';



window.addEventListener('load', function(){

   const formulario =  this.document.forms[0];

   const inputNombre = this.document.querySelector('#inputNombre')

   const inputEmail = this.document.querySelector('#inputEmail');

   const inputPassword =  this.document.querySelector('#inputPassword');



   formulario.addEventListener('submit', function(event){

       event.preventDefault();

       const validacion = validacionNoVacio(inputEmail.value) && validacionNoVacio(inputPassword.value);

       if(validacion){

           const datosUsuario = normalizacionSingIn(inputNombre.value ,inputEmail.value, inputPassword.value);

           fetchApiSingIn(apiUrl, datosUsuario);

       }else{

           console.log("algún dato no es correcto");

       }

       formulario.reset();

   });

});



/* -------------------------------------------------------------------------- */

/*                      sección de funciones disponibles                      */

/* -------------------------------------------------------------------------- */



function validacionNoVacio(texto) {

   let resultado = true;



   if(texto === ""){

       resultado = false;

   }



   return resultado

}



function normalizacionSingIn(nombre ,email, password) {

   const usuario = {

       nombre: nombre,

       userName: email.trim(),

       mail: email.trim(),

       password: password.trim()

   }



   return usuario;

}



function fetchApiSingIn(url,payload) {



   const configuraciones = {

       method: 'POST',

       headers: {

           'Content-Type': 'application/json',

           'Access-Control-Allow-Origin': '*'

       },

       body: JSON.stringify(payload)

   }



   fetch(url, configuraciones)

   .then( respuesta => {

       return respuesta.json()

   })

   .then( data => {


           location.href = '/index.html'

       }

   ).catch( error => console.log(error))

}