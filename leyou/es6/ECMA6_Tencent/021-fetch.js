//console.log(window) 
// console.log(fetch)

// jsonplaceholder 測試常用網址，好用
// http://jsonplaceholder.typicode.com
let url = "http://jsonplaceholder.typicode.com/posts"
//url = "http://jsonplaceholder.typicode123456.com/posts"
// var xmlhttp = new XMLHttpRequest()
// xmlhttp.onload
//console.log(fetch(url))
fetch(url)
.then(data =>{
    //console.log(data)
    data.json() // 先對回來的data 解析成 json
})
.then(res=>console.log(res))
.catch(err=>console.log("error " + err))

