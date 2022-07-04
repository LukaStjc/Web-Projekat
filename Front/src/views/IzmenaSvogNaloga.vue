<template>
    <div style="background-color:lightblue backgrou" class="background">
        <nav class="navbar" style="background-color: #8cb1dc;">
            <a>
                <img src="https://cdn.freebiesupply.com/logos/large/2x/sirius-1-logo-svg-vector.svg" class="image"
                    alt="">
            </a>

        </nav>

        <form>
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Korisničko ime</label>
                <input class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="this.korisnik.korisnickoIme">
            </div>

            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Lozinka</label>
                <input type="password" class="form-control" id="exampleInputPassword1" v-model="this.korisnik.lozinka">
            </div>

            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Ime</label>
                <input class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="this.korisnik.ime">
            </div>

            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Prezime</label>
                <input class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="this.korisnik.prezime">
            </div>

            <!-- TODO resiti za pol -->
           <label for="">Pol:</label> <br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="Muški"
                    v-model="this.korisnik.tipPola" />
                <label class="form-check-label" for="inlineRadio1">Muški</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="Ženski"
                    v-model="this.korisnik.tipPola" />
                <label class="form-check-label" for="inlineRadio2">Ženski</label>
            </div>

            <!-- TODO resiti za datum -->
            <br>
            <label>Datum rodjenja:</label> <br>
            <input v-model="this.korisnik.datumRodjenja" type="date" placeholder="dd-mm-yyyy" min="1997-01-01"
                max="2030-12-31" />

            <button type="submit" class="btn btn-primary" v-on:click="izmeni()">Izmeni</button>
        </form>


    </div>
</template>

<script>
import axios from "axios";

export default {
     name: "IzmenaSvogNaloga",
    data() {
        return {
            korisnik: {
                korisnickoIme: "",
                lozinka: "",
                ime: "",
                prezime: "",
                tipPola: "",
                datumRodjenja: "",
                tipUloge: ""
            }
        }
    },

    methods: {
        izmeni: function () {

            fetch("http://localhost:8081/api/korisnik/izmena", {
                method: "PUT",
                credentials: 'include',
                headers: {
                    Accept: "application/json",
                    "Content-type": "application/json",
                },

                body: JSON.stringify(this.korisnik)
            })

                .then((response) => response.json())
                .then((data) => {

                    console.log(data)
                })

                .catch((err) => {
                    console.log("Error : " + err);
                    alert(err);
                });

        }
    },

     mounted() {
       fetch('http://localhost:8081/api/ulogovani_korisnik', { credentials: 'include' })
        .then(res => res.json())
        .then(data => {
            this.korisnik = data
            console.log(data)
        })
        .catch(error => {
                console.log(error);
            });
    },
}
</script>