<template>
    <div style="background-color:lightblue backgrou" class="background">
        <nav class="navbar" style="background-color: #8cb1dc;">
            <a>
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


        <div class="row mt-2 justify-content-center">
            <div class="col-2" v-for="porudzbina in porudzbine" :key="porudzbina.id">
                <div class="card" style="width: 10rem;">
                    <div class="card-body">

                        <h5 class="card-title"> {{ porudzbina.id }} </h5>
                        <!-- TODO ispis naziva restorana -->
                        <h6 class="card-subtitle mb-2 text-muted"> Naziv restorana: {{ porudzbina.restoran }} </h6>
                        <artikal-comp v-for="artikal in porudzbina.poruceniArtikli" :key="artikal.id" :artikal="artikal">
                    </artikal-comp>
                        <button class="btn btn-primary">Vidi restoran</button>


                    </div>
                </div>
            </div>
        </div>




    </div>

</template>



<script>
import axios from "axios";
import RestoranComp from "../components/RestoranComp.vue";
import ArtikalComp from "../components/ArtikalComp.vue";

export default {
    name: 'Dostavljac',
    data: function () {
        return {
            porudzbine: [],
        };
    },

    components: {
        RestoranComp, ArtikalComp
    },

    mounted: function () {
        axios
            .get("http://localhost:8081/api/dostavljac/porudzbine", { withCredentials: true })
            .then(res => {
                this.porudzbine = res.data;
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
        }
    }

}
</script>



<style>
</style>