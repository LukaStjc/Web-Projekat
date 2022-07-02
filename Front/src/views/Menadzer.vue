<template>
    <div style="background-color:lightblue backgrou" class="background">
        <nav class="navbar" style="background-color: #8cb1dc;">
            <a>
                <img src="https://cdn.freebiesupply.com/logos/large/2x/sirius-1-logo-svg-vector.svg" class="image"
                    alt="">
            </a>

            <ol class="breadcrumb justify-content-md-end">
                <li class="breadcrumb-item "><a href="#">Moj nalog</a></li>
                <li class="breadcrumb-item "><a href="#">Moj restoran</a></li>
                <li class="breadcrumb-item "><a href="/login">Odjavi se</a></li>

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
                        <button class="btn btn-primary" style="margin: 5%;">Obrisi</button>


                    </div>
                </div>
            </div>
        </div>




    </div>
</template>

<script>
import axios from "axios";


export default {
    name: 'Menadzer',

    data: function () {
        return {
            mojRestoran: {},
            artikal: {
                naziv: "",
                cena: "",
                tip: "",
                kolicina: "",
                opis: ""
            }
        };
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
    }
}
</script>

<style>
.tableColor {
    background-color: powderblue;
    width: 100%;
}
</style>