<template>
    <div style="background-color:lightblue backgrou" class="background">
        <nav class="navbar" style="background-color: #8cb1dc;">
            <a v-on:click="vratiSe()">
                <img src="https://cdn.freebiesupply.com/logos/large/2x/sirius-1-logo-svg-vector.svg" class="image"
                    alt="">
            </a>

            <div class="row g-3 align-items-center">
                <div class="col-auto">
                    <input type="password" id="inputPassword6" class="form-control"
                        aria-describedby="passwordHelpInline" placeholder="Pretraga">
                </div>

            </div>

            <ol class="breadcrumb justify-content-md-end">
                <li class="breadcrumb-item "><button v-on:click="prikazNaloga()">Moj nalog</button></li>
                <li class="breadcrumb-item "><button v-on:click="odjaviSe()">Odjavi se</button></li>

            </ol>

        </nav>

        <div class="btn-group-vertical justify-content-md-end">
            <a href="#" class="btn btn-primary">Kreiraj korisnika</a>
            <a href="#" class="btn btn-primary">Pregled svih korisnika</a>
            <a href="#" class="btn btn-primary">Kreiraj restoran</a>

        </div>

         <div class="row mt-2 justify-content-center">
            <div class="col-2" v-for="korisnik in korisnici" :key="korisnik.korisnickoIme">
                <div class="card" style="width: 10rem;">
                    <div class="card-body">

                        <h5 class="card-title"> {{ korisnik.korisnickoIme }} </h5>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ korisnik.ime }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ korisnik.prezime }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ korisnik.tipPola }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ korisnik.datumRodjenja }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ korisnik.tipUloge }} </h6>



                        <button class="btn btn-primary" v-on:click="prikazNaloga()">Vidi korisnika</button>

                    </div>
                </div>
            </div>
        </div>


    </div>

</template>

<script>
import axios from 'axios'
import KorisnikComp from "../components/KorisnikComp.vue";

export default {
    name: 'PregledSvihKorisnika',
    data: function() {
        korisnici: []
    },
    mounted: function() {
        axios
            .get("http://localhost:8081/api/korisnici", { withCredentials: true })
            .then(res => {
                this.korisnici = res.data;
                console.log(this.restorani);
            })
            .catch(error => {
                console.log(error);
            });
    },
    components: {
        KorisnikComp
    },
    methods: {
        prikazNaloga: function () {
            axios
                .get("http://localhost:8081/api/ulogovani_korisnik", {
                    withCredentials: true
                })

                .then(res => {
                    console.log(res.data);
                    this.$router.push('/moj_nalog');
                })

                .catch(error => {
                    console.log(error.response);
                    alert("Neuspesno");
                });
        },

        odjaviSe: function () {
            fetch("http://localhost:8081/api/korisnik/logout", {
                method: "POST",
                credentials: 'include',
                headers: {
                    Accept: "application/json",
                    "Content-type": "application/json",
                },
            })

                .then((data) => {

                    this.$router.push("/login");
                })

                .catch((err) => {
                    console.log("Error : " + err);
                    alert(err);
                });
        },

        vratiSe: function () {
            axios
                .get("http://localhost:8081/api/ulogovani_korisnik", {
                    withCredentials: true
                })

                .then(res => {
                    this.korisnik = res.data
                    console.log(this.korisnik.tipUloge)
                    if (this.korisnik.tipUloge == 'ADMIN') {
                        this.$router.push("/admin");
                    } else if (this.korisnik.tipUloge == 'DOSTAVLJAC') {
                        this.$router.push("/dostavljac");
                    } else if (this.korisnik.tipUloge == 'MENADZER') {
                        this.$router.push("/menadzer");
                    } else {
                        this.$router.push("/kupac");
                    }
                })

                .catch(error => {
                    console.log(error.response);
                    alert("Neuspesno");
                });
        }
    }
}
</script>


<style>

</style>
