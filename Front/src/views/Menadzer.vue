<template>
    <div style="background-color:lightblue backgrou" class="background">
        <nav class="navbar" style="background-color: #8cb1dc;">
            <a>
                <img src="https://cdn.freebiesupply.com/logos/large/2x/sirius-1-logo-svg-vector.svg" class="image"
                    alt="">
            </a>

            <ol class="breadcrumb justify-content-md-end">

                <li class="breadcrumb-item "><button v-on:click="prikazNaloga()">Moj nalog</button></li>
                <li class="breadcrumb-item ">
                    <router-link :to="{ name: 'PrikazSvogNaloga', params: { id: korisnickoIme } }"><button>Molim te
                            radi</button></router-link>
                </li>
                <li class="breadcrumb-item "><a href="#">Moj restoran</a></li>
                <li class="breadcrumb-item "><button v-on:click="odjaviSe()">Odjavi se</button></li>

            </ol>

        </nav>

        <h1 class="tableColor" style="padding-top: 1%;">
            Moj Restoran
        </h1>


        <table class="table-primary tableColor" style="padding-bottom: 1%">
            <tbody>
                <tr class="table-primary">
                    <th scope="row">Naziv</th>
                    <td class="table-primary">{{ mojRestoran.naziv }}</td>
                </tr>

                <tr class="table-primary">
                    <th scope="row">Tip</th>
                    <td class="table-primary">{{ mojRestoran.tip }}</td>
                </tr>

                <tr class="table-primary">
                    <th scope="row">Adresa</th>
                    <td class="table-primary">{{ mojRestoran.adresa }}</td>
                </tr>

                <tr class="table-primary">
                    <th scope="row">Status</th>
                    <td class="table-primary">{{ mojRestoran.radi ? "Da" : "Ne" }}</td>
                </tr>
            </tbody>

        </table>

        <h4 class="tableColor" style="padding-top: 1%;">
            Jelovnik
        </h4>

        <div class="row mt-2 justify-content-left">
            <div class="col-2" v-for="artikal in mojRestoran.jelovnik" :key="artikal.id">
                <div class="card" style="width: 10rem;">
                    <div class="card-body">

                        <h5 class="card-title"> {{ artikal.naziv }} </h5>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ artikal.tip }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ artikal.cena }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ artikal.kolicina }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ artikal.opis }} </h6>

                        <button class="btn btn-primary" style="margin: 5%;">Izmeni</button>

                        <button class="btn btn-primary" style="margin: 5%;"
                            @click="obrisiArtikal(artikal.id)">Obrisi</button>


                    </div>
                </div>
            </div>
        </div>
        <h4 class="tableColor" style="padding-top: 1%;">
            Porudzbine
        </h4>

        <div class="row mt-2">
            <div class="col-2" v-for="porudzbina in porudzbine" :key="porudzbina.id">
                <div class="card" style="width: 10rem;">
                    <div class="card-body">

                        <h5 class="card-title"> {{ porudzbina.id }} </h5>
                        <artikal-comp v-for="artikal in porudzbina.poruceniArtikli" :key="artikal.id"
                            :artikal="artikal">
                        </artikal-comp>

                         <button class="btn btn-primary">Vidi porudzbinu</button>


                    </div>
                </div>
            </div>
        </div>






    </div>
</template>

<script>
import axios from "axios";
import ArtikalComp from "../components/ArtikalComp.vue";
import PrikazSvogNaloga from "./PrikazSvogNaloga.vue";


export default {
    name: 'Menadzer',

    data: function () {
        return {
            mojRestoran: {},
            artikal: {
                id: "",
                naziv: "",
                cena: "",
                tip: "",
                kolicina: "",
                opis: ""
            },
            porudzbine: []
        };
    },

    props: ['korisnickoIme'],

    components: {
        ArtikalComp
    },

    premounted: function () {
        axios
            .get("http://localhost:8081/api/ulogovani_korisnik", {
                withCredentials: true
            })

            .then(res => {
                this.korisnickoIme = res.data
                console.log(this.korisnickoIme)
            })

            .catch(error => {
                console.log(error.response);
                alert("Neuspesno");
            });
    },

    mounted: function () {
        fetch("http://localhost:8081/api/moj_restoran", { credentials: 'include' })
            .then(response => response.json())
            .then(data => {
                this.mojRestoran = data
                console.log(this.mojRestoran)
            })
            .catch(error => {
                console.log(error);
            });

        fetch("http://localhost:8081/api/menadzer/porudzbine", { credentials: 'include' })
            .then(response => response.json())
            .then(data => {
                this.porudzbine = data
                console.log(this.mojRestoran)
            })
            .catch(error => {
                console.log(error);
            });
    },

    /* mounted: function () {
         fetch("http://localhost:8081/api/menadzer/porudzbine", { credentials: 'include' })
             .then(response => response.json())
             .then(data => {
                 this.porudzbine = data
                 console.log(this.mojRestoran)
             })
             .catch(error => {
                 console.log(error);
             });
     },*/

    methods: {
        obrisiArtikal: function () {
            fetch("http://localhost:8081/api/moj_restoran/ukloni_artikal", {
                method: "DELETE",
                credentials: 'include',
                headers: {
                    Accept: "application/json",
                    "Content-type": "application/json",
                },
                body: JSON.stringify(this.artikal.id)
            })

                .then((data) => {

                    console.log(data)
                })

                .catch((err) => {
                    console.log("Error : " + err);
                    alert(err);
                });
        },

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
        }


    }
}
</script>

<style>
.tableColor {
    background-color: powderblue;
    width: auto;
}
</style>