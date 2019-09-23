/**
 *  場景1 獲取數組中指定類型的對象放到B數組中
 */
var products = [
    {name:"cucumber",type:"vegetable"},
    {name:"banana",type:"fruit"},
    {name:"celery",type:"vegetable"},
    {name:"orange",type:"fruit"}
]

// es5
var filteredProducts=[]
for (let index = 0; index < products.length; index++) {
    const element = products[index];
    if(element.type==="fruit"){
        filteredProducts.push(element);
    }
}
console.log(filteredProducts)


// es6 使用filter 來實現過濾
var collectedFruits = products.filter(prod=>{
    return prod.type==="vegetable"
})
console.log(collectedFruits)



/**
 *  場景2 過濾掉不滿足以下條件的對象
 *  蔬菜數量大於0，價格小於10
 */
var products = [
    {name:"cucumber",type:"vegetable",quantity:190,price:1},
    {name:"banana",type:"fruit",quantity:5,price:2},
    {name:"celery",type:"vegetable",quantity:10,price:3},
    {name:"orange",type:"fruit",quantity:15,price:4},
]
 var filteredFruits = products.filter(prod=>{
     return prod.type==="vegetable"
     && prod.quantity > 9
     && prod.price > 2
 })
 console.log(filteredFruits)

/**
 *  場景3 兩數組AB，根據A的id 值，過濾掉B數組不符合的數據
 */
 var post = {id:4,title:"javascript"}
 var comment = [
    {postId:4,content:"Angular4"},
    {postId:2,content:"Vue.js"},
    {postId:3,content:"Node.js"},
    {postId:4,content:"React.js"}
 ]

 const commmentForPost = (post,comments)=>{
     return comment.filter(comment=>{
         return comment.postId === post.id
     })
 }
console.log(commmentForPost(post,comment))
