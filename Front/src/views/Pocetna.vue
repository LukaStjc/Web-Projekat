<template>
    <div style="background-color:lightblue backgrou" class="background">
        <NavBar />
        <h1>
            <div class="fw-bold text text-center">
                Sirius - lako do uzivanja
            </div>
        </h1>

        <footer class="text-center" style="background-color: lightsteelblue;">
            Made in Serbia
        </footer>

    </div>
</template>

<script>

import NavBar from "@/components/NavBar.vue";

export default {
    name: "Pocetna",
    components: {
        NavBar,
    },
};
</script>

<style>
.background {
    background-image: url("https://a.storyblok.com/f/67418/1920x1280/166659692b/ella-olsson-opbjwbccaeo-unsplash.jpg");
    background-size: cover;
    background-position: center;
    width: 100%;
    height: 100vh;
}

.text {
    size: 100px;
    margin-top: 20%;
    margin-bottom: 20%;
    color: rgb(32, 54, 54);

}
</style>