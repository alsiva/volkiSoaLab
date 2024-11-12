class RequestAllDto {
    entityName;
    sort = [];
    filters = [];
    page = 1;
    pagesCount = 5;
    constructor(entityName, sort, filters, page, pagesCount){
        this.entityName = entityName;
        this.sort = sort;
        this.filters = filters;
        this.page = page;
        this.pagesCount = pagesCount;
    }
}



export {RequestAllDto};