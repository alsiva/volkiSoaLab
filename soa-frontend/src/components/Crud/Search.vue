<template>
    <div class="search">
        <input type="text" placeholder="Введите имя дракона" @input="search">
    </div>
</template>

<script setup>
const emit = defineEmits(
    ['search']
);
const search = (e) => {
    emit('search', e.target.value);
}

</script>

<style scoped>
.search {
    display: flex;
    width: fit-content;
}

</style>