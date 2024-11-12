import { RequestAllDto } from "../dto/dto";
import { createDragonFromJsonObject } from "../entities/dragon";


class DragonTable {
    columns = ['id', 'age', 'color', 'coordinate_x', 'coordinate_y', 'creation_date', 'eyes_count', 'name', 'speaking', 'wingspan'];
    urlService;
    constructor(urlService){
        this.urlService = urlService;
    }

    async getAll(requestDto){
        let url = this.urlService.getAllUrl(requestDto.entityName, requestDto.sort, requestDto.filters, requestDto.page, requestDto.pagesCount);
        console.log(url);
        let data;
        /*this.urlService.fetchXmlAsJson(url)
        .then(jsonData => data = jsonData);
        console.log(data);*/
        data = await this.urlService.fetchXmlAsJson(url);
        console.log(`Data from Url Service ${data}`);
        let dragons = []
        for(const d of data.dragonList.children){
            dragons.push(createDragonFromJsonObject(d));
        }
        return dragons;
    }
    async findBySubstring(substring) {
        let url = this.urlService.getFindBySubstringUrl("dragons", substring);
        console.log(url);
        let data;
        /*this.urlService.fetchXmlAsJson(url)
        .then(jsonData => data = jsonData);
        console.log(data);*/
        data = await this.urlService.fetchXmlAsJson(url);
        console.log(`Data from Url Service ${data}`);
        let dragons = []
        for(const d of data.dragonList.children){
            dragons.push(createDragonFromJsonObject(d));
        }
        return dragons;
    }
}

export {DragonTable};