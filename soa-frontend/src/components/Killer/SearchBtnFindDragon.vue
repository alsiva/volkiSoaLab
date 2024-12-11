<template>
    <div class="search-btn">
        <button @click="fillDataContainer">Поиск</button>
    </div>
</template>

<script setup>
import { urlService } from '@/main';

const props = defineProps({
    minOrMax: String,
})

const emit = defineEmits(
    ['dragonData']
);

const fillDataContainer = async () => {
    const url = `/killer/dragon/find-by-cave-depth/${props.minOrMax}`;
    /*const data = await urlService.fetchXmlAsJson(url)
    if(Object.keys(data.dragonList).length == 0){
        return [{}];
    }
    console.log(`Data from Url Service ${data}`);
    let dragons = []
    for(const d of data.dragonList.children){
        dragons.push(createDragonFromJsonObject(d));
    }*/
   emit("dragonData", [{
    name: "Dragon",
    age: 213
   }])
}

</script>

<style scoped>
.search-btn{
    display: flex;
    justify-content: flex-end;
    padding: 10px;
    border-bottom: 1px solid black;
}

</style>