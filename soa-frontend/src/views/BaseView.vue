<template>
    <div class="content">
        <Killer />
        <Crud />
    </div>
</template>

<script setup>
import Crud from '@/components/Crud/Crud.vue';
import Killer from '@/components/Killer/Killer.vue';
</script>

<style>
.content {
    width: 70%;
    margin: 0 auto;
}
</style>