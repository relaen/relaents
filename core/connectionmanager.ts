export default class ConnectionManager{
    /**
     * 配置
     */
    static options:any;

    /**
     * 连接池
     */
    static pool:any;


    public static async getConnection():Promise<any>{
        switch(RelaenManager.product){
            case 'mysql':
            return this.getMysqlConnection();
            case 'oracle':
                return this.getOracleConnection();
            case 'mssql':
                return this.getMssqlConnection();
        }
    }

    /**
     * 关闭连接
     * @param conn 
     */
    public static async closeConnection(conn:any){

    }


    /**
     * 获取 mysql 连接
     */
    private static async getMysqlConnection():Promise<any>{
        if(this.pool){
            return new Promise((resolve,reject)=>{
                this.pool.getConnection((err,conn)=>{
                    if(err){
                        reject(err);
                    }
                    resolve(conn);
                });
            });
        }else{
            return  await require('mysql').createConnection(this.options);
        }
    }

    private static async getOracleConnection(){

    }

    private static async getMssqlConnection(){

    }
}
