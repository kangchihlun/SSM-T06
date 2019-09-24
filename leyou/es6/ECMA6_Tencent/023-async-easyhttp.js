/**
 *  使用 async 封裝 fetch
 */

class EasyHttp{

    // 調用後回傳一個Promise，讓外部調用可以再用then取出保存
    async get(url){
        const response = await fetch(url)
        const resData = await response.json()
        return resData
    }

    async post(url,data){
        const response = await fetch(url,{
                method:'POST',
                headers:{
                    'Content-type':'application/json'
                },
                body:JSON.stringify(data)
            })
        const resData = await response.json()
        return resData
    }

    
    async put(url,data){
        const response = await fetch(url,{
            method:'PUT',
            headers:{
                'Content-type':'application/json'
            },
            body:JSON.stringify(data)
        })
        const resData = await response.json()
        return resData
    }

    async delete(url){
        const response = await fetch(url,{
                method:'DELETE',
                headers:{
                    'Content-type':'application/json'
                }
            })
        const resData = await "刪除成功"
        return resData
    }

}