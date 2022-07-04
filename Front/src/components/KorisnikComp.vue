<template>
                <p>{{ korisnik.korisnickoIme }}</p>
                <p>{{ korisnik.ime }}</p>
                <p>{{ korisnik.prezime }}</p>
                <p>{{ korisnik.tipPola }}</p>
                <p>{{ korisnik.datumRodjenja }}</p>
                <p>{{ korisnik.tipUloge }}</p>
                <br />
</template>

<script>
import axios from "axios";
export default {
    name: "KorisnikComp",
    props: ["korisnik"],


};
</script>

<style scoped>
img {
    width: 60px;
}

.card {
    height: 300px;
}

button {
    margin-top: 3%;
}
</style>