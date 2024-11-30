<template>
    <div ref="pinComponent" class="pin-content">
        <fade-box class="box-set">

        </fade-box>
    </div>
</template>

<script>

import fadeBox from "@/page/home/component/fadeBox/index.vue";

export default {
    name: "leftPin",
    components:{fadeBox},
    data(){
        return{
            offsetT : 0
        }
    },
    methods:{
        calcOffset(){
            return this.$refs.pinComponent.getBoundingClientRect().top + window.scrollY
                - parseInt(this.$refs.pinComponent.style.marginTop.match(/\d+/g));
        },
        handleScroll() {
            const componentElement = this.$refs.pinComponent;
            this.updateDistanceFromTop(componentElement);
        },
        updateDistanceFromTop(element) {
            const rect = element.getBoundingClientRect();
            const distanceFromTop = rect.top;
            this.offsetT = this.calcOffset();
            element.style.marginTop = `${
                window.scrollY-this.offsetT+90 > 0 ? window.scrollY-this.offsetT+90 : 0
            }px`;
        }
    },
    mounted() {
        window.addEventListener("scroll", this.handleScroll);
    },
    unmounted() {
        window.removeEventListener("scroll", this.handleScroll);
    }
}
</script>

<style scoped>
.pin-content{
    width: 100%;
    height: 500px;
}
.box-set{
    background-color: rgba(255, 255, 255, 0.92);
    border-radius: 5px;
    width: 100%;
    height: 100%;
}
</style>
