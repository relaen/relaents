export default {
    "error":{
        "0000": "Unknown error",
        "0001": "Relaen config is wrong",
        "0002": "Wrong sql:${0}",
        "0010": "Entity class '${0}' is not exist",
        "0011": "Alias '${0}' is not exist",
        "0020": "Entity class '${0}' has no id property",
        "0021": "Property name '${0}' is not nullable",
        "0022": "Entity class '${0}' has no property '${1}'",
        "0023": "Property '${1}' of entity class '${0}' is not a relative property",
        "0024": "The max-length of property '${1}' of entity class '${0}' is ${2}",
        "0025": "Entity has no id property or id value",
        
        "0030": "MappedBy colunm '${0}' is not exist in entity '${1}'",
        
        "0050": "Entity '${0}' id config is incorrect",
        "0051": "Entity '${0}' id config sequence is incorrect",
        "0052": "Entity '${0}' id can not be found by table",

        "0100": "Wrong rql: lack of keyword 'from'",
        "0101": "Wrong rql: nearby 'join on'",
        "0102": "Wrong rql:incorrect select fields:${0}",
        "013": "Wrong rql:lack of 'where condition' in delete sql",
        

        "0150": "Entity has no id value，can not be deleted",
        "0151": "Entity has no id value，can not be updated",
        "0152": "Entity has no id value，can not be loaded",
        "0153": "Entity has no id value，can not get relation property",
        
        "0200": "Failed to create connection,error message is:${0}",
        "0201": "Failed to open connection,error message is:${0}",
        "0202": "Failed to close connection,error message is:${0}",
        "0250": "Connection is not exist,create connection first",

        "0300": "Dialect '${0}' has no corresponding provider",

        "0402": "Optimistic lock version only supports number",
        "0403": "Optimistic lock version is null",
        "0404": "The 'in' conditional relation's param is an array",
        "0405": "The 'between' conditional relation's param is an array of length 2",
        "0406": "Can't update or delete full table without 'where' condition",
        "0407": "Oracle only supports SERIALIZABLE and READ COMMITTED isolation level",
        "0408": "Entity '${0}' version can not be found",
        "0409": "Entity '${0}' version only supports number"
    },

    "tip":{
        "executeSql":"[Relaen]",
        "params":"Parameters",
        "ok":"OK",
        "txbegin":"Transaction has begun.",
        "txcommit":"Transaction committed.",
        "txrollback":"Transaction rolled back.",
        error:'Error',
        message:'message'
    }
    
}