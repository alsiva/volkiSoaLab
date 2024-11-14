import { j2xParser } from "simple-xml-to-json";


// Функция получает на вход объект и node - raw json в неподходящем формате
// Рекурсивно обходит этот json и заполняет поля obj
function rawJsonParser(node, obj) {
    let fieldName = Object.keys(node)[0];
    let child = node[fieldName].children;
    if(child == undefined) {
        obj[fieldName] = node[fieldName].content;
    }else{
        for(const ch of child){
            rawJsonParser(ch, obj);
        }
    }
}

async function updateTableData(entityData, entityColumns, entity, reqDto){
    let data = await entity.value.getAll(reqDto.value);
    entityData.value = data;
    entityColumns.value = Object.keys(data[0]);
}

function convertJsonToXml(jsonObject) {
    const parser = new j2xParser();
    const xmlDataWithHeader = parser.parse(jsonObject);
    console.log(`Parsed JSON Object: ${xmlDataWithHeader}`);
}

export {rawJsonParser, updateTableData, convertJsonToXml};