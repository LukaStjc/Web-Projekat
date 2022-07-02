<template>
    div style="background-color:lightblue backgrou" class="background">
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
</template>

<script>

    import axios from "axios";

    export default {
         name: "PrikazSvogNaloga"
    }
</script>