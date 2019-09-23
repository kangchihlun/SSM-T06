//console.log(document.getElementById("output"))
//document.getElementById("btn1").addEventListener('click',getText)
// document.getElementById("btn1").addEventListener('click',getJson)
// document.getElementById("btn1").addEventListener('click',getExteranl)

//本地純文本數據
function getText(){
    // 成功拿取會執行then方法
    // .then((res)=>console.log(res))
    fetch("text.txt")
    .then((res)=>res.text())
    .then(data=>{
        console.log(data)
        document.getElementById("output").innerHTML=data;
    })
    .catch(err=>console.log(err))
}


//本地json數據
function getJson(){
    // 成功拿取會執行then方法
    fetch("post.json")
    .then((res)=>res.json())
    .then(data=>{
        console.log(data)
        let output =""
        data.forEach(element => {
            output += `<li>${element.title} - ${element.body}</li>`
        });
        document.getElementById("output").innerHTML=output;
    })
    .catch(err=>console.log(err))
}

// 請求網絡 api
function getExteranl(){
    // 成功拿取會執行then方法
    fetch("https://api.github.com/users")
    .then((res)=>res.json())
    .then(data=>{
        console.log(data)
        let output =""
        data.forEach(element => {
            output += `<li>${element.id} - ${element.login}</li>`
            output += `<img src='${element.avatar_url}' height="42" width="42">`
        });
        document.getElementById("output").innerHTML=output;
    })
    .catch(err=>console.log(err))
}