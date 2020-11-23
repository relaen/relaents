import { Transaction } from "./transaction/transaction";
import { ConnectionManager } from "./connectionmanager";

class Connection{
    conn:any;
    connected:boolean;
    constructor(conn){
        this.conn = conn;
    }

    public async connect(){
        if(this.connected){
            return;
        }
        this.connected = true;
    }

    public async close(){
        if(!this.connected){
            return;
        }
        ConnectionManager.closeConnection(this);
    }
    
    public createTransaction():Transaction{
        return new Transaction(this);
    }
}

export {Connection}