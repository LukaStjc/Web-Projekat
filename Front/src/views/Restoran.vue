<template>
    <div style="background-color:lightblue backgrou" class="background">
        <nav class="navbar" style="background-color: #8cb1dc;">
            <a>
                <img src="https://cdn.freebiesupply.com/logos/large/2x/sirius-1-logo-svg-vector.svg" class="image"
                    alt="">
            </a>

        </nav>

        <section style="background-color:lightblue">
            <h2>{{ restoran.naziv }}</h2>
            <h5>Tip restorana: {{ restoran.tip }}</h5>
            <h5>Lokacija: {{ restoran.lokacija }}</h5>
            <h5>Status: {{ restoran.radi ? "Radi" : "Ne radi" }}</h5>
            <h5>Prosek ocena: {{ restoran.ocena }}</h5>
            <h5>Komentari: {{ restoran.komentari }}</h5>
        </section>
        <section id="jelovnik">
            <h2 style="background-color:lightblue">Jelovnik</h2>

            <div class="container-fluid">
                <div class="row">

                    <artikal-comp v-for="artikal in restoran.jelovnik" :key="artikal.id" :artikal="artikal">
                    </artikal-comp>

                </div>
            </div>
        </section>
    </div>
</template>

<script>
import axios from 'axios'
import ArtikalComp from "../components/ArtikalComp.vue";


export default {
    name: 'Restoran',

    data: function () {
        return {
            restoran: {}
        };
    },

    components: {
        ArtikalComp
    },

    mounted: function () {
        axios
            .get("http://localhost:8081/api/restoran/" + this.$route.params.id, { withCredentials: true })
            .then(res => {
                this.restoran = res.data;
                console.log(this.restoran);
            })
            .catch(error => {
                console.log(error);
            });

    }


}
</script>


<style>

.background {
    background: lightblue backgrou;
    background-size: cover;
    background-position: center;
    width: 100%;
    height: 100vh;
}

</style>