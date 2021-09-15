var RelaenTip = {
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

    "0050": "Entity '${0}' id config is incorrect",
    "0051": "Entity '${0}' id config sequence is incorrect",

    "0100": "Wrong rql: lack of keyword 'from'",
    "0101": "Wrong rql: nearby 'join on'",
    "0102": "Entity has no id value，can not be deleted",
    "0103": "Entity has no id value，can not be updated",
    "0104": "Entity has no id value，can not be loaded",
    "0105": "Entity has no id value，can not get relation property",
    "0106": "Wrong rql:incorrect select fields:${0}",

    "0200": "Failed to create connection,error message is:${0}",
    "0201": "Failed to open connection,error message is:${0}",
    "0202": "Failed to close connection,error message is:${0}",
    "0250": "Connection is not exist,create connection first",

    "0300": "Dialect '${0}' has no corresponding provider",

    "0401": "Entity '${0}' id can not be found by table",
    "0402": "Optimistic lock version only supports number",
    "0403": "Optimistic lock version is null",
    "0404": "关系符in，参数为数组",
    "0405": "关系符between，参数为长度2的数组",
    "0406": "Entity has no where conditions",
    "0407": "Relaen 不支持${0}悲观锁",
    "0107": "Entity has no id value，can not be loaded",
    "0408": "Oracle only supports SERIALIZABLE and READ COMMITTED isolation level",
    "0409": "实体version版本值为空",
    "0410": "实体version版本值"
}
export { RelaenTip }