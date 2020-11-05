class A{
    constructor(name){
        this.name = name;
    }
    getName(){
        return this.name;
    }
}

class B extends A{
    constructor(name){
        super(name);
        this.age = 40;
    }
}

let b = new B();
console.log(b instanceof B);