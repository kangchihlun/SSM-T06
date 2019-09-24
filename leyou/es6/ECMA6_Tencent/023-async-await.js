// async & await

async function myFunc(){
    return "hello world"
}
const ve = myFunc() // 因為是async ，回來的是一個 promise
.then(data=>console.log(data))


async function myFunc2(){
    const promise = new Promise((resolve,reject)=>{
        setTimeout(()=>resolve('await Hello World'),2000)
    })

    //
    const error = true;
    if(!error){
        // await 等待 resolve 執行完畢之後再執行
        const ret = await promise; // 等待這個 promise
        return ret;
    }
    else{
        await Promise.reject(new Error("error:報錯"))
    }
}
const var2 = myFunc2()
.then(data=>console.log(data))
.catch(err=>console.log("err"))




const url = "http://jsonplaceholder.typicode.com/users/"
// 請求異步範例
async function getUsers(){
    const response = await fetch(url) // 請求一定要花時間，一定要異步，不然一定拿不到東西
    const data = await response.json() // 只有上面執行成功才會處理 response
    return data
}

getUsers()
.then(users=>console.log(users))
