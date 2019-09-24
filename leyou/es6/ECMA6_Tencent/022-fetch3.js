const easyhttp = new EasyHttp()
const data = {
    id:2,
    name:"hnery",
    username:"emla",
    email:"23154@qq.com"
}

// const url = "http://jsonplaceholder.typicode.com/users"
// let ret = easyhttp.get(url)
// .then(res => console.log(res))
// .catch(err=>console.log(err))


// Post
// 數據
// let url2 = "http://jsonplaceholder.typicode.com/users/"
// let ret2 = easyhttp.post(url2,data)
// .then(res => console.log(res))
// .catch(err=>console.log(err))


// PUT
// url2 = "http://jsonplaceholder.typicode.com/users/3"
// let ret3 = easyhttp.put(url2,data)
// .then(res => console.log(res))
// .catch(err=>console.log(err))


// // delete user
let url2 = "http://jsonplaceholder.typicode.com/users/3"
let ret4 = easyhttp.delete(url2)
.then(data=>console.log(data))
.catch(err=>console.log(err))