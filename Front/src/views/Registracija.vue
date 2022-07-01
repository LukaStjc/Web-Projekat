<template>
    <div style="background-color:lightblue backgrou" class="background">
        <NavBarNoButtons />
        <form style="width:500px" class="position-absolute top-50 start-50 translate-middle">
            <div class="mb-3">
                <label for="inputEmail14" class="form-label">Korisničko ime</label>
                <input v-model="korisnik.korisnickoIme" class="form-control" />
            </div>

            <div>
                <label for="inputEmail4" class="form-label">Ime</label>
                <input v-model="korisnik.ime" class="form-control" />
            </div>

            <div>
                <label for="inputEmail4" class="form-label">Prezime</label>
                <input v-model="korisnik.prezime" class="form-control" />
            </div>

            <label for="">Pol:</label> <br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="Muški"
                    v-model="korisnik.tipPola" />
                <label class="form-check-label" for="inlineRadio1">Muški</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="Ženski"
                    v-model="korisnik.tipPola" />
                <label class="form-check-label" for="inlineRadio2">Ženski</label>
            </div>

            <br>
            <label>Datum rodjenja:</label> <br>
            <input v-model="korisnik.datumRodjenja" type="date" placeholder="dd-mm-yyyy" min="1997-01-01"
                max="2030-12-31" />

            <div class="mb-3">
                <label for="inputPassword4" class="form-label">Lozinka</label>
                <input v-model="korisnik.lozinka" type="password" class="form-control" />
            </div>


            <button type="submit" v-on:click="registracija()" class="btn btn-primary">Registruj se</button>
        </form>

    </div>
</template>

<script>
// @ is an alias to /src
import NavBarNoButtons from "@/components/NavbarNoButtons.vue";
import axios from "axios";

export default {
    name: "Registracija",
    data: function () {
        return {
            korisnik: {
                korisnickoIme: "",
                lozinka: "",
                ime: "",
                prezime: "",
                tipPola: "",
                datumRodjenja: ""

            },
        };
    },

    components: {
        NavBarNoButtons,
    },

    methods: {

        registracija: function () {
            axios
                .post("http://localhost:8081/api/korisnik/registracija", this.korisnik, {
                    withCredentials: true
                })

                .then(res => {
                    console.log(res);

                    this.$router.push("/login");

                })

                .catch(error => {
                    console.log(error.response);
                    alert("Neuspesno");
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