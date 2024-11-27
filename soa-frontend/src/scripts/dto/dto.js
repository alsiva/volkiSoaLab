class RequestAllDto {
    entityName;
    sort = "";
    filters = "";
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

class Filter {
    fieldName;
    eq;
    nq;
    gt;
    lt;
    ge;
    le;
    constructor(fieldName, eq="", nq="", gt="", lt="", ge="", le=""){
        this.fieldName = fieldName;
        this.eq = eq;
        this.nq = nq;
        this.gt = gt;
        this.lt = lt;
        this.ge = ge;
        this.le = le;
    }
}

function createFiltersFromArray(filtersArr){
    let filters = [];
    for(const f of filtersArr){
        filters.push(new Filter(f));
    }
    return filters;
}

function createFilterString(filter){
    let filterString = "";
    for(const k of Object.keys(filter)){
        if((filter[k] != "") && (k != "fieldName")){
            filterString += `filter=${filter.fieldName}-${k}-${filter[k]}&`;
        }
    }
    return  filterString.substring(0, filterString.length-1);
}



export {RequestAllDto, Filter, createFiltersFromArray, createFilterString};