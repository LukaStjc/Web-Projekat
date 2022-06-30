<template>
    <div style="background-color:lightblue backgrou" class="background">
        <NavBarNoButtons />
        <form style="width:500px" class="position-absolute top-50 start-50 translate-middle">
            <div class="mb-3">
                <label for="korisnickoIme" class="form-label">Korisničko ime</label>
                <input type="korisnickoIme" class="form-control" id="KorisnickoIme" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Šifra</label>
                <input type="sifra" class="form-control" id="exampleInputPassword1">
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

    methods: {

        prijaviSe: function () {
            axios
                .post("http://localhost:8080/api/korisnik/login", this.korisnik, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    alert("Uspesno");
                })
                .catch(error => {
                    console.log(error.response);
                    alert("Neuspesno");
                });
        },
        components: {
            NavBarNoButtons,
        },


        getRole: function () {
            axios
                .get("http://localhost:8080/api/korisnici/role", { withCredentials: true })
                .then((res) => {
                    this.uloga = res.data
                    console.log(this.uloga)
                    if (this.uloga == "Admin") {
                        this.$router.push("/admin");
                    } else if (this.uloga == "Dostavljac") {
                        //this.$router.push("/dostavljac");
                    } else if (this.uloga == "Menadzer") {
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
            this.getRole()
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