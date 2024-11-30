<template>
    <div id="app">

        <div style="width: 100%; height: 100%; position: relative">

            <navigator :class="{'nav-open': navOpen, 'nav-close': !navOpen}"/>

            <router-view style="width: 100%; height: 100%; z-index: 0; position: absolute"/>

        </div>

<!--        <router-view/>-->

    </div>
</template>

<script>

import navigator from "@/nav/index.vue";
import {computed} from "vue";
import store from "@/store";

export default {
    name: 'App',
    components: {navigator},
    setup() {
        const navOpen = computed(() => store.state.navOpen);
        return {
            navOpen
        }
    }
}

</script>

<style>
@import "asset/fonts/index.css";
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
::-webkit-scrollbar {
    width: 5px;
    height: 5px;
}
::-webkit-scrollbar-thumb {
    background-color: #0fb2ff;
    opacity: 0.7;
    border-radius: 5px;
}

.nav-open{
    z-index: 10;
    position: fixed;
    top: 0;
    transition: top 0.5s;
}

.nav-close{
    z-index: 10;
    position: fixed;
    top: -80px;
    transition: top 0.5s;
}
</style>
