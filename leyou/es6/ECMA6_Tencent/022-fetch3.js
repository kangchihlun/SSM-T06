const easyhttp = new EasyHttp()
const url = "http://jsonplaceholder.typicode.com/users"
let ret = easyhttp.get(url)
.then(res => console.log(res))
.catch(err=>console.log(err))

// 傳輸數據
// 數據
const data = {
    name:"hnery",
    username:"emla",
    email:"23154@qq.com"
}
// post
let ret2 = easyhttp.post(url,data)
.then(res => console.log(res))
.catch(err=>console.log(err))


// update user
url2 = "http://jsonplaceholder.typicode.com/users/2"
const data2 = {
    name:"mary",
    username:"emily",
    email:"emily@qq.com"
}

let ret3 = easyhttp.put(url2,data2)
.then(res => console.log(res))
.catch(err=>console.log(err))


// delete user
url3 = "http://jsonplaceholder.typicode.com/users/2"
let ret4 = easyhttp.delete(url2)
.then(data=>console.log(data))
.catch(err=>console.log(err))