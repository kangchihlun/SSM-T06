const http = require('http')
const io = require('socket.io')
const fs = require('fs')
// http server
let server = http.createServer((request,response)=>{
    console.log(`someone request from : ${request.url},  the  method is: ${request.method}`)
    // response.write("abc01")
    fs.readFile(`air/${request.url}`,(err,data)=>{
        if(err)
            response.write('404')
        else
            response.write(data)
        
        response.end()  // 異步，要擺在這裡等文件讀完
    })
})
server.listen(8080)

// socket server
let wsServer = io.listen(server)

wsServer.on('connection',(sock)=>{
    sock.on('a',(num1,num2)=>{
        console.log("rararar")
        console.log(`received ${num1},${num2}`)  
    })
    setInterval(function(){
        sock.emit('b',Math.random())
    },1000)
})



