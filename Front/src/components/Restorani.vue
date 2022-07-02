<template>
<div class="card" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title"> {{restoran.naziv}} </h5>
    <h6 class="card-subtitle mb-2 text-muted"> {{restoran.tip}} </h6>
    <h6 class="card-subtitle mb-2 text-muted"> {{restoran.adresa}} </h6>

    <button class="btn btn-primary">Vidi restoran</button>
  </div>
</div>
</template>

<script>
export default {
    name: "Restorani",
    props: ["restoran"]
}
</script>

<style>
.card{
    margin: auto 5px;
}
</style>