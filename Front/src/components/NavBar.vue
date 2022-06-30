<template>
  <div>
    <nav class="navbar" style="background-color: #8cb1dc;">
        <a>
             <img src="https://cdn.freebiesupply.com/logos/large/2x/sirius-1-logo-svg-vector.svg" class="image" alt="">
        </a>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
             <a class="btn btn-light" href="/login" role="button">Login</a>
             <a class="btn btn-light" href="/registracija" role="button">Registracija</a>
        </div>
        
    </nav>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  components: {
    
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


</style>