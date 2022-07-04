<template>
  <div class="card" style="width: 18rem;">
   
          <div class="card-body">
            <p>{{ artikal.naziv }}</p>
            <p>{{ artikal.cena }} dinara</p>
            <p>{{ artikal.opis }}</p>
            <p>{{ artikal.kolicina }}</p>
            <br />

          </div>

  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ArtikalComp",
    props: ["artikal"],

  
};
</script>

<style scoped>
img {
  width: 60px;
}
.card{
    height: 300px;
}
button{
    margin-top: 3%;
}
</style>