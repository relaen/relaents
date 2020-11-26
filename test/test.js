
const{AsyncLocalStorage} = require("async_hooks");
const asyncLocalStorage = new AsyncLocalStorage();

async function foo(id){
    asyncLocalStorage.enterWith(id);
    console.log(asyncLocalStorage.getStore());
    await f1();
    await f2();
    
}

async function f1(){
    return new Promise((res,rej)=>{
        setTimeout(()=>{
            console.log('f1:',asyncLocalStorage.getStore());
            res();
        },300);
    })
}


async function f2(){
    return new Promise((res,rej)=>{
        setTimeout(()=>{
            console.log('f2:',asyncLocalStorage.getStore());
            res();
        },100);
    })
}


(async ()=>{
    foo(1000);
    foo(2000);
})();

