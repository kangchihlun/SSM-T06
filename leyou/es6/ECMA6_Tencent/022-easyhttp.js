class EasyHttp{

    // 調用後回傳一個Promise，讓外部調用可以再用then取出保存
    get(url){
        return new Promise((result,reject)=>{
            fetch(url)
            .then(res=>res.json())
            .then(data=>result(data))
            .catch(err=>reject(err))
        })
    }

    post(url,data){
        return new Promise((result,reject)=>{
            fetch(url,{
                method:'POST',
                headers:{
                    'Content-type':'application/json'
                },
                body:JSON.stringify(data)
            })
            .then(res=>res.json())
            .then(data=>result(data))
            .catch(err=>reject(err))
        })
    }

    
    put(url,data){
        return new Promise((result,reject)=>{
            fetch(url,{
                method:'POST',
                headers:{
                    'Content-type':'application/json'
                },
                body:JSON.stringify(data)
            })
            .then(res=>res.json())
            .then(data=>result(data))
            .catch(err=>reject(err))
        })
    }

    delete(url){
        return new Promise((result,reject)=>{
            fetch(url,{
                method:'DELETE',
                headers:{
                    'Content-type':'application/json'
                },
            })
            .then(res=>res.json())
            .then(data=>result('刪除成功'))
            .catch(err=>reject(err))
        })
    }

}