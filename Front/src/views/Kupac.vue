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
                <li class="breadcrumb-item "><a href="#">Moj nalog</a></li>
                <li class="breadcrumb-item "><a href="#">Korpa</a></li>
                <li class="breadcrumb-item "><a href="/login">Odjavi se</a></li>

            </ol>

        </nav>

        <div class="row mt-2 justify-content-center">
            <div class="col-2" v-for="restoran in restorani" :key="restoran.id">
                <div class="card" style="width: 10rem;">
                    <div class="card-body">

                        <h5 class="card-title"> {{ restoran.naziv }} </h5>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ restoran.tip }} </h6>
                        <h6 class="card-subtitle mb-2 text-muted"> {{ restoran.adresa }} </h6>

                        <button class="btn btn-primary">Vidi restoran</button>

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
    name: "Kupac",
    components: {
        Restorani
    },

    /*async created() {
        const response = await axios.get('login', {
            headers: {
                Authorization: 'Bearer' + localStorage.getItem('token')
            }
        });
        console.log(response.data)
    },*/

    data: function () {
        return {
            restorani: [],
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