var users = [
    {name:"Jill"},
    {name:"Alex",id:2},
    {name:"Bill"},
    {name:"Alex"},
]

// ES 5 的作法，找出 Alex
var curUser
for (let index = 0; index < users.length; index++) {
    const element = users[index];
    if(element.name ==="Alex"){
        curUser = element
        break
    }
        
}
console.log(curUser)



// ES 6 的作法，找出 Alex
curUser = users.find(usr=>{
    return usr.name ==="Alex"
})

console.log(curUser)


/*
    場景2 根據指定對象找到數組中符合條件的對象
*/
var post = [
    {id:1,title:"Node.js"},
    {id:2,title:"React.js"},
]

var comment = {postId:1,content:"Hello World"}

const postForComment = (post,comment)=>{
    return post.find(pt=>{
        return pt.id === comment.postId
    })
}
console.log(postForComment(post,comment))
