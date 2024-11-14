<template>
  <div>
    <!-- Кнопка для открытия модального окна -->
    <button class="filter-button" @click="openModal">Сортировка</button>

    <!-- Модальное окно -->
    <div v-if="isModalOpen" class="modal-overlay" @click="closeModal">
      <div class="modal-content" @click.stop>
        <h2>Настройка сортировки</h2>
        
        <!-- Таблица с фильтрами -->
        <form @submit.prevent="applyFilters">
          <table>
            <tbody>
                <tr v-for="f in fieldsToSort">
                  <td>
                    {{ f }}
                  </td>
                  <td>
                    <input type="radio" name="f" value="true">По возрастанию
                  </td>
                  <td>
                    <input type="radio" name="f" value="false">По убыванию
                  </td>
                </tr>
            </tbody>
          </table>
          
          <!-- Кнопки для применения и сброса фильтров -->
          <div class="buttons">
            <button type="submit">Применить</button>
            <button type="button" @click="resetFilters">Сбросить</button>
          </div>
        </form>

        <!-- Кнопка закрытия модального окна -->
        <button class="close-button" @click="closeModal">Закрыть</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';


const props = defineProps({
    fieldsToSort: Array
})

// Состояние для модального окна
const isModalOpen = ref(false);

// Состояние для фильтров
const filters = reactive({
  name: '',
  age: '',
  email: ''
});

// Открытие модального окна
const openModal = () => {
  isModalOpen.value = true;
};

// Закрытие модального окна
const closeModal = () => {
  isModalOpen.value = false;
};

// Применение фильтров (в вашем случае можно отправить на сервер)
const applyFilters = () => {
  console.log('Фильтры применены:', filters);
  closeModal();
};

// Сброс фильтров
const resetFilters = () => {
  filters.name = '';
  filters.age = '';
  filters.email = '';
};
</script>

<style scoped>
/* Стили для кнопки */
.filter-button {
  padding: 10px 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

/* Стили для модального окна */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  /*
  width: 400px;
  max-width: 90%;*/
}

.buttons {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}

.close-button {
  margin-top: 20px;
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
}
</style>
