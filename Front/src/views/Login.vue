<template>
    <div style="background-color:lightblue backgrou" class="background">
        <NavBarNoButtons />
        <form style="width:500px" class="position-absolute top-50 start-50 translate-middle">
            <div class="mb-3">
                <label for="inputEmail14" class="form-label">Korisničko ime</label>
                <input v-model="korisnik.korisnickoIme" class="form-control" />
            </div>
            <div class="mb-3">
                <label for="inputPassword4" class="form-label">Lozinka</label>
                <input v-model="korisnik.lozinka" type="password" class="form-control" />
            </div>
            <button type="submit" v-on:click="combination()" class="btn btn-primary">Prijavi se</button>
        </form>

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
            uloga: ""
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
                    console.log(res);
                })
                .catch(error => {
                    console.log(error.response);
                    alert("Neuspesno");
                });
        },


        getUloga: function () {
            axios
                .get("http://localhost:8081/api/korisnik/uloga", { withCredentials: true })
                .then((res) => {
                    this.uloga = res.data
                    console.log(this.uloga)
                    if (this.uloga == "admin") {
                        this.$router.push("/admin");
                    } else if (this.uloga == "dostavljac") {
                        //this.$router.push("/dostavljac");
                    } else if (this.uloga == "menadzer") {
                        console.log(this.uloga)
                        this.$router.push("/menadzer");
                    } else {
                        //this.$router.push("/kupac");
                    }
                })
                .catch((err) => {
                    console.log(err)
                });
        },


        combination: function () {
            this.prijaviSe()
            this.getUloga()
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