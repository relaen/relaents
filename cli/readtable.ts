class ReadTable{
    async getConn(){
        const db = require('mysql');
        let cfg = {
            "host":"localhost",
            "port":3306,
            "user":"root",
            "password":"field",
            "database":"tunnel",
            "connectionLimit":10
        }
        return await db.createConnection(cfg);
    }

    async read(){
        let conn = await this.getConn();
        let results:Array<any> = await new Promise((resolve,reject)=>{
            conn.query("desc t_agent",
            (error,results,fields)=>{
                if(error){
                    reject(error);
                }
                resolve(results);
            });
        });
        
        console.log(results);
    }

}

new ReadTable().read();