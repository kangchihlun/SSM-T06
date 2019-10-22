const fs=require('fs')
fs.readFile('./1.txt',(err,data)=>{
    if(err)
        console.log(err)
    else
        console.log(`success ${data.toString()}`)
})
fs.writeFile('2.txt','contentnoemo',err=>{
    if(err)
        console.log('Error occured')
    else
        console.log('successfully write')
})