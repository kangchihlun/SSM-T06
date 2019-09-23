/**
    構造函數 prm
*/

let prm = new Promise((resolve,reject)=>{
    //reject()  // 執行 .catch() 方法
    setTimeout(()=>{
        resolve()  // 執行 .then() 方法
    },3000) // 3秒後再執行
    console.log("Do my things")
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