var numbers = [1,2,3]
var double_numbers =[]


// es5 作法，把每個元素取出來乘以2
for (let index = 0; index < numbers.length; index++) {
    const element = numbers[index];
    double_numbers.push(element*2)
}
console.log(double_numbers)


// es6 作法 ， map
var doubled = numbers.map((number)=>{
    return number*2
})
console.log(doubled)


var cars = [
    {model:"Buick",price:"Cheap"},
    {model:"BMW",price:"Expensive"}
]

var prices = cars.map(car=>{
    return car.price
})
console.log(prices)