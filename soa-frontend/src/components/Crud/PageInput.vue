<template>
    <input type="number" class="table__page-input" id="page-input" @input="input">
</template>

<script setup>

const emit = defineEmits(
    ['pageNumber']
);

const input = (e) => {
    if(e.target.value != ""){
        emit('pageNumber', parseInt(e.target.value));
    }
}

</script>

<style scoped>
.table__page-input{
    position: relative;
    margin: 0 auto;
    width: 50px;
}

</style>