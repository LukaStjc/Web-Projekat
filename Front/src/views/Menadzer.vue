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

        <h1>
            Moj Restoran
        </h1>

        <h5> {{mojRestoran.naziv}}</h5>

        <h5> {{mojRestoran.tip}}</h5>

        

    </div>
</template>

<script>
import axios from "axios";


export default {
    name: 'Menadzer',

    data: function () {
        return {
            mojRestoran: {},
        };
    },

    mounted: function () {
            fetch("http://localhost:8081/api/moj_restoran", { withCredentials: true })
            .then(response => response.json())
            .then(data => {
                this.mojRestoran = data
            })
            .catch(error => {
                console.log(error);
            });
    }
}
</script>