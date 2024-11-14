import { createTeamFromJsonObject } from "../entities/team";

class TeamTable {
    columns = ['id', 'first_name', 'last_name', 'strength', 'team_id'];
    urlService;
    constructor(urlService){
        this.urlService = urlService;
    }

    async getAll(requestDto){
        let url = this.urlService.getAllUrl(requestDto.entityName, requestDto.sort, requestDto.filters, requestDto.page, requestDto.pageCount);
        console.log(url);
        let data;
        data = await this.urlService.fetchXmlAsJson(url);
        console.log(`Data from Url Service ${JSON.stringify(data.teamList)}`);
        let teams = []
        if(Object.keys(data.teamList).length == 0){
            return [{}];
        }
        for(const d of data.teamList.children){
            teams.push(createTeamFromJsonObject(d));
        }
        return teams;
    }
}

export {TeamTable};