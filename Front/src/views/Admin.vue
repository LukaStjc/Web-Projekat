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
            <router-link class="btn btn-primary" :to="{name: 'PregledSvihKorisnika'}">Pregled svih korisnika</router-link>
            <a href="#" class="btn btn-primary">Kreiraj korisnika</a>
            <a href="#" class="btn btn-primary">Kreiraj restoran</a>

        </div>

        <div class="row mt-2 justify-content-center">
            <div class="col-2" v-for="restoran in restorani" :key="restoran.id">
                <div class="card" style="width: 10rem;">
                    <div class="card-body">

                        <h5 class="card-title"> {{ restoran.naziv }} </h5>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ restoran.tip }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ restoran.adresa }} </h6>

                        <router-link :to="{ name: 'Restoran', params: { id: restoran.id } }"><button
                                class="btn btn-primary" style="margin: 2%">Vidi restoran</button></router-link>
                        <button class="btn btn-primary" style="margin: 2%">Obriši restoran</button>


                    </div>
                </div>
            </div>
        </div>

    </div>

</template>

<script>

import axios from "axios";
import Restorani from "../components/Restorani.vue";


export default {
    name: "Admin",
    components: {
        Restorani
    },

    data: function () {
        return {
            restorani: [],
            korisnik: {}
        };
    },
    mounted: function () {
        axios
            .get("http://localhost:8081/api/restorani", { withCredentials: true })
            .then(res => {
                this.restorani = res.data;
                console.log(this.restorani);
            })
            .catch(error => {
                console.log(error);
            });
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

};

</script>

<style>
.navbar {
    width: auto;
    height: auto;
    background-color: #8cb1dc;
}

.image {
    width: 100px;
    height: 70px;
    margin: auto;
    padding-left: 10%;
}

.background {
    background: lightblue backgrou;
    background-size: cover;
    background-position: center;
    width: 100%;
    height: 100vh;
}

.buttonStyle {
    padding-right: 1%;
}

#restorani {
    padding: 3%;

}

.card {
    margin: auto 5px;
    padding-top: 5%;
}
</style>