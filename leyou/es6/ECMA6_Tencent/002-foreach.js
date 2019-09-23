//alert("1123");
var colors = ["red","blue","green"]

// ES5 遍歷
for (let index = 0; index < colors.length; index++) {
    const element = colors[index];
    console.log(element)
}

/// ES6 遍歷作法
colors.forEach( (cl)=>{
    console.log(cl)
})

// 遍歷數組中值並計算總和
var numbers = [1,2,3,4,5]
var sum = 0
numbers.forEach(number => {
    sum += number
});
console.log(sum)


// 使用外部函數塞入 foreach
sum = 0;
const adder = (number) =>{
    sum += number
    //console.log("..."+sum)
}
numbers.forEach(adder)
console.log(sum)


