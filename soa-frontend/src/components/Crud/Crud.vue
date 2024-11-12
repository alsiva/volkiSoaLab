<template>
    <div class="table-container">
        <div class="table-container__header">
            <Search @search="findEntity"/>
            <label for="collections">Выберите коллекцию:</label>
            <select name="collections" id="collections" v-model="selected_collection">
                <option value="dragons">Dragons</option>
                <option value="teams">Teams</option>
                <option value="hunters">Hunters</option>
            </select>
        </div>
        <div class="table-container_body">
            <DataTable v-if="selected_collection === 'dragons'" :columns="entityColumns"  :data="entityData"/>
            <DataTable v-else-if="selected_collection === 'hunters'" :columns="entityColumns"  :data="entityData"/>
            <DataTable v-else-if="selected_collection === 'teams'" :columns="entityColumns"  :data="entityData"/>
        </div>
        <div class="table__footer">
            <PageInput @pageNumber="updatePageNumber"/>
            <SearchBtn  @fetchData="updateData" :table="entity" :requestDto="reqDto"/>
        </div>
    </div>
</template>

<script setup>
import DataTable from '@/components/Crud/DataTable.vue';
import Search from './Search.vue';
import SearchBtn from './SearchBtn.vue';
import PageInput from './PageInput.vue';

import {ref, watch, onMounted} from "vue";

import { DragonTable } from '@/scripts/tables/dragonTable';
import { urlService } from '@/main';
import { RequestAllDto } from '@/scripts/dto/dto';
import { HunterTable } from '@/scripts/tables/hunterTable';
import { TeamTable } from '@/scripts/tables/teamTable';
import { updateTableData } from '@/scripts/utils';

const selected_collection = ref('dragons');

const entityColumns = ref([]);
const entityData = ref([]);

const entity = ref(new DragonTable(urlService));
const reqDto = ref(new RequestAllDto("dragons", [], [], 1, 5));

const pageNumber = ref(0);

onMounted(() => {

})

watch(selected_collection, async (new_collection) => {
    reqDto.value.entityName = new_collection;
    if (new_collection === "dragons") {
        entity.value = new DragonTable(urlService);
    } else if(new_collection == "hunters"){
        entity.value = new HunterTable(urlService);

    } else if(new_collection == "teams"){
        entity.value = new TeamTable(urlService);
    }else {
        alert("Нет такой коллекции!");
    }
    let data = await entity.value.getAll(reqDto.value);
    console.log(`Данные полученные в watch: ${data}`);
    entityData.value = data;
    entityColumns.value = Object.keys(data[0]);
})


const findEntity = async (search) => {
    let data = await entity.value.findBySubstring(search);
    entityData.value = data;
    entityColumns.value = Object.keys(data[0]);
    console.log(JSON.stringify(data));
}

const updateData = (newData) => {
    //dragon_data.value = newData;
    console.log(`Данные полученные в Crud от SearchBtn: ${newData}`);
    entityData.value = newData;
    console.log(`New Columns: ${Object.keys(newData[0])}`)
    entityColumns.value = Object.keys(newData[0]);
}

const updatePageNumber = async (newPageNumber) => {
    reqDto.value.page = newPageNumber;
    let data = await entity.value.getAll(reqDto.value);
    console.log(`Данные полученные в watch: ${data}`);
    entityData.value = data;
    entityColumns.value = Object.keys(data[0]);

    //await updateTableData(entityData, entityColumns, entity, reqDto);
}

</script>

<style>
    .table-container{
        width: 100%;
        border: 1px solid black;
        overflow-y: auto;
    }
    .table-container__header{
        box-sizing: border-box;
        display: flex;
        gap: 10px;
        border-bottom: 1px solid black;
        justify-content: flex-end;
        padding: 10px 30px;
    }
    .table-container_body {
        max-height: 300px;
        overflow-y: auto;
    }
    .table__footer {
        border-top: 1px solid black;
        padding: 10px 10px;
        display: flex;
        justify-content: center;
        gap: 10px;
    }

</style>