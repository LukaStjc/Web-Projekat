<template>
  <div class="card" style="width: 18rem;">
   
          <div class="card-body">
            <p>{{ restoran.naziv }}</p>
            <p>{{ restoran.lokacija }}</p>
            <br />

          </div>

  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "RestoranComp",
    props: ["restoran"],

  
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