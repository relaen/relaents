export let config = {
    "mysql": {
        "dialect": "mysql",
        "host": "localhost",
        "port": 3306,
        "username": "root",
        "password": "root",
        "database": "relaen",
        "entities": [
            "/test/entity/**/*.ts"
        ],
        "cache": true,
        "debug": true,
        "fileLog": false,
        "fullTableOperation": false,
        "pool": {
            "min": 1,
            "max": 10
        },
        "connectTimeout": 10000,
        "idleTimeout": 10000
    },
    "oracle": {
        "dialect": "oracle",
        "host": "localhost",
        "port": 1521,
        "username": "c##frank2",
        "password": "root",
        "database": "xe",
        "entities": [
            "/test/entity/**/*.ts"
        ],
        "cache": true,
        "debug": true,
        "fileLog": false
    },
    "mssql": {
        "dialect": "mssql",
        "host": "localhost\\SQLEXPRESS",
        "port": 1433,
        "username": "sa",
        "password": "root",
        "database": "relaen",
        "entities": [
            "/test/entity/**/*.ts"
        ],
        "cache": true,
        "debug": true,
        "fileLog": false
    },
    "postgres": {
        "dialect": "postgres",
        "host": "localhost",
        "port": 5432,
        "username": "postgres",
        "password": "root",
        "database": "relaen",
        "entities": [
            "/test/entity/**/*.ts"
        ],
        "cache": true,
        "debug": true,
        "fileLog": false
    },
    "mariadb": {
        "dialect": "mariadb",
        "host": "localhost",
        "port": 3305,
        "username": "root",
        "password": "root",
        "database": "relaen",
        "entities": [
            "/test/entity/**/*.ts"
        ],
        "cache": true,
        "debug": true,
        "fileLog": false
    },
    "sqlite": {
        "dialect": "sqlite",
        "database": "C:\\Users\\Frank\\Desktop\\ORM\\demo.db",
        "entities": [
            "/test/entity/**/*.ts"
        ],
        "cache": true,
        "debug": true,
        "fileLog": false
    }
}