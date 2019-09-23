/**
    構造函數 prm
*/

let prm = new Promise((resolve,reject)=>{
    //reject()  // 執行 .catch() 方法
    console.log("Do my things")
    resolve()  // 執行 .then() 方法
})
console.log(prm)

prm
.then(()=>{
    console.log("成功")
})
.then(()=>{
    console.log("無限調用 then 方法")
})
.catch(()=>{
    console.log("出現了重大問題")
})