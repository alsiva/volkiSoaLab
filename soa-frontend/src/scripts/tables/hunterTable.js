import { createHunterFromJsonObject } from "../entities/hunter";

class HunterTable {
    columns = ['id', 'first_name', 'last_name', 'strength', 'team_id'];
    urlService;
    constructor(urlService){
        this.urlService = urlService;
    }

    async getAll(requestDto){
        let url = this.urlService.getAllUrl(requestDto.entityName, requestDto.sort, requestDto.filters, requestDto.page, requestDto.pagesCount);
        console.log(url);
        let data;
        data = await this.urlService.fetchXmlAsJson(url);
        console.log(`Data from Url Service ${data}`);
        let hunters = []
        if(Object.keys(data.hunterList).length == 0){
            return [{}];
        }
        for(const d of data.hunterList.children){
            hunters.push(createHunterFromJsonObject(d));
        }
        return hunters;
    }
}

export {HunterTable};