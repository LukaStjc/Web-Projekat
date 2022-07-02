<template>
    <div style="background-color:lightblue backgrou" class="background">
        <NavBarNoButtons />

        <div class="mb-3">
            <label for="inputEmail14" class="form-label">Korisničko ime</label>
            <input v-model="korisnik.korisnickoIme" class="form-control" />
        </div>

        <div class="mb-3">
            <label for="inputPassword4" class="form-label">Lozinka</label>
            <input v-model="korisnik.lozinka" type="password" class="form-control" />
        </div>

        <button v-on:click="prijaviSe()" class="btn btn-primary">Prijavi se</button>


    </div>
</template>

<script>
// @ is an alias to /src
import NavBarNoButtons from "@/components/NavbarNoButtons.vue";
import axios from "axios";

export default {
    name: "Login",
    data: function () {
        return {
            korisnik: {
                korisnickoIme: "",
                lozinka: ""
            },
            odgovor: {
                korisnickoIme: "",
                lozinka: "",
                uloga: ""
            }
        };
    },

    components: {
        NavBarNoButtons,
    },

    methods: {

        /* async prijaviSe() {
 
             const response = await axios
                 .post("http://localhost:8081/api/korisnik/login", this.korisnik);
 
             console.log(response)
             if (response.data.uloga == 'admin') {
                 this.$router.push("/admin");
             } else if (response.data.uloga == 'dostavljac') {
                 this.$router.push("/dostavljac");
             } else if (response.data.uloga == 'menadzer') {
                 this.$router.push("/menadzer");
             } else {
                 this.$router.push("/kupac");
             }
 
             //localStorage.setItem('token',response.data.token);
         }*/

        prijaviSe: function () {

            fetch("http://localhost:8081/api/korisnik/login", {
                method: "POST",
                credentials: 'include',
                headers: {
                    Accept: "application/json",
                    "Content-type": "application/json",
                },

                body: JSON.stringify(this.korisnik)
            })

            .then((response) => response.json())
            .then((data) => {
        
                if (data.uloga == 'admin') {
                 this.$router.push("/admin");
             } else if (data.uloga == 'dostavljac') {
                 this.$router.push("/dostavljac");
             } else if (data.uloga == 'menadzer') {
                 this.$router.push("/menadzer");
             } else {
                 this.$router.push("/kupac");
             }
            })

             .catch((err) => {
              console.log("Error : " + err);
              alert(err);
            });

        }




    }
};
</script>

<style>
.loginForm {
    width: auto;
}

.background {
    background: lightblue backgrou;
    background-size: cover;
    background-position: center;
    width: 100%;
    height: 100vh;
}
</style>