import { convertXML } from "simple-xml-to-json";

class UrlService{
    base_url;
    constructor(base_url){
        this.base_url = base_url;
    }

    getAllUrl(entityName, sorts=[], filters=[], page=1, pageCount=5){
        let url = `${this.base_url}/${entityName}?page=${page}&pagesCount=${pageCount}`;
        return url;
    }

    getFindBySubstringUrl(entityName, substring) {
        let url = `${this.base_url}/${entityName}/search/${substring}`;
        return url;
    }

    checkForErrorMessage(jsonData) {
        return null;
        /*return {
            "code": 404,
            "message": "Not found",
            "time": "2023-09-12T00:00:00Z"

        };*/
    }

    async fetchXmlAsJson(url) {
        try {
            // Выполняем GET-запрос
            const response = await fetch(url);

            // Проверяем, успешен ли запрос
            if (!response.ok) {
                throw new Error(`Ошибка: ${response.status}`);
            }

            // Получаем текст ответа (XML)
            const xmlText = await response.text();
            console.log(xmlText);

            // Преобразуем XML в JSON
            const jsonData = convertXML(xmlText);
            //console.log(jsonData.dragonList.children[0].dragon);
            //const jO = JSON.stringify(jsonData.dragonList.children[0].dragon.children[0].id.content);

            // To-Do: Проверять, что прешедший ответ не содержит ошибки: <error>...</error>
            const error = this.checkForErrorMessage(jsonData)
            if(error != null) {
                alert(`Код: ${error.code}\nОшибка: ${error.message}`);
            }
            const jO = JSON.stringify(jsonData);
            console.log(jO);

            // Возвращаем JSON-данные
            return jsonData;
        } catch (error) {
            alert(`Ошибка при получении или обработке данных: ${error}`);
            console.error('Ошибка при получении или обработке данных:', error);
        }
    }
}

export {UrlService};