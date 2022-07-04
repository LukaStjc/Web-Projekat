<template>
    <div style="background-color:lightblue backgrou" class="background">
        <nav class="navbar" style="background-color: #8cb1dc;">
            <a  v-on:click="vratiSe()">
                <img src="https://cdn.freebiesupply.com/logos/large/2x/sirius-1-logo-svg-vector.svg" class="image"
                    alt="">
            </a>

        </nav>

        <table class="table-primary tableColor" style="padding-bottom: 1%">
            <tbody>
                <tr class="table-primary">
                    <th scope="row">Korisničko ime</th>
                    <td class="table-primary">{{ korisnik.korisnickoIme }}</td>
                </tr>

                <tr class="table-primary">
                    <th scope="row">Ime</th>
                    <td class="table-primary">{{ korisnik.ime }}</td>
                </tr>

                <tr class="table-primary">
                    <th scope="row">Prezime</th>
                    <td class="table-primary">{{ korisnik.prezime }}</td>
                </tr>

                <tr class="table-primary">
                    <th scope="row">Tip pola</th>
                    <td class="table-primary">{{ korisnik.tipPola  }}</td>
                </tr>

                <tr class="table-primary">
                    <th scope="row">Datum rodjenja</th>
                    <td class="table-primary">{{ korisnik.datumRodjenja }}</td>
                </tr>

            </tbody>

        </table>

        <li class="breadcrumb-item " style="background-color:lightblue"><router-link :to="'/izmeni_nalog'">Izmeni</router-link></li>
    </div>
</template>

<script>

import axios from "axios";

export default {
    name: "PrikazSvogNaloga",
    data() {
        return {
            korisnik: {
                korisnickoIme: "",
                lozinka: "",
                ime: "",
                prezime: "",
                tipPola: "",
                datumRodjenja: "",
                tipUloge: "",
                restoran: {}
            }
        }
    },

    props: ['id'],

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

    methods: {
        vratiSe: function () {
            fetch("http://localhost:8081/api/korisnik/uloga", { credentials: 'include' })
                //.then(response => response.json())
                .then(data => {
                    console.log(data)
                    if (data == 'admin') {
                        this.$router.push("/admin");
                    } else if (data == 'dostavljac') {
                        this.$router.push("/dostavljac");
                    } else if (data == 'menadzer') {
                        this.$router.push("/menadzer");
                    } else {
                        this.$router.push("/kupac");
                    }
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }

//TODO resiti da salje link {korisnickoIme}
  /*  mounted() {
        fetch('http://localhost:8081/api/korisnik/' + this.$route.params.id  , { credentials: 'include' })
            .then(res => res.json())
            .then(data => {
                this.korisnik = data
                console.log(this.korisnik)
            })
            .catch(error => {
                console.log(error);
            });
    }*/
}
</script>