/*
    萬事皆對象
*/

// class declaration
function Car(options){
    this.title = options.title
}
// declare member function to Car class
Car.prototype.drive = function(){
    return "ovo"
}

const car = new Car({title:"BMW"})
console.log(car.title)
console.log(car.drive())

// Toyota繼承Car  @ES5
function Toyota(options){
    Car.call(this,options); // 繼承屬性
    this.color = options.color
}
//繼承方法  @ES5
Toyota.prototype = Object.create(Car.prototype)
Toyota.prototype.constructor = Toyota

const toyota = new Toyota({color:"red",title:"Altis"})
console.log(toyota)
console.log(toyota.drive())



// ES6寫法 ，直接使用 class
class Car6{
    constructor(options){
        //{title}
        this.title = options.title
    }
    drive(){
        return "vronm"
    }
}

const car6 = new Car6({title:"BMW"})
console.log(car6.title)
console.log(car6.drive())

// 繼承
class Toyota6 extends Car6{
    constructor(options){
        super(options) // 繼承，一定要寫
        this.color = options.color
    }
}

const toyota6 = new Toyota6({color:"red",title:"Focus"})
console.log(toyota6)
console.log(toyota6.drive())