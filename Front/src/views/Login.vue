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
        };
    },

    components: {
        NavBarNoButtons,
    },
    
    methods: {
        prijaviSe: function () {
            axios
                .post("http://localhost:8081/api/korisnik/login", this.korisnik, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res.data)
                    alert("Uspesno");
                    if (res.data == "admin") {
                        this.$router.push("/admin");
                    } else if (res.data == "dostavljac") {
                        this.$router.push("/dostavljac");
                    } else if (res.data == "menadzer") {
                        this.$router.push("/menadzer");
                    } else {
                        this.$router.push("/kupac");
                    }
                })
                .catch(error => {
                    console.log(error.response);
                    alert("Neuspesno");
                });
        },

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