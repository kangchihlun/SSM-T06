
const team = {
    members:["Henry","Elyse"],
    teamName:"es6",
    teamSummary:function(){
        return this.members.map(function(member){
            // this 已經發生指向變化了，不再指向 team
            return `${member}隸屬於${this.teamName}小組` 
        })
    }
}
console.log(team.teamSummary()) /// this.teamName = undefined

// 解法1：在fn外部對 this 做一個保存
const team2 = {
    members:["Henry","Elyse"],
    teamName:"es6",
    teamSummary:function(){
        let self = this
        return this.members.map(function(member){
            return `${member}隸屬於${self.teamName}小組` 
        })
    }
}
console.log(team2.teamSummary())


// 解法2：使用 .bind(this) 把 this 從外層綁入
const team3 = {
    members:["Henry","Elyse"],
    teamName:"es6",
    teamSummary:function(){
        return this.members.map(function(member){
            return `${member}隸屬於${this.teamName}小組` 
        }.bind(this))
    }
}
console.log(team3.teamSummary())


/// 改成用 ES 6 的箭頭函數，不用做甚麼事 this 就能指到外部
const team4 = {
    members:["Henry","Elyse"],
    teamName:"es6",
    teamSummary:function(){
        return this.members.map((member)=>{
            return `${member}隸屬於${this.teamName}小組` 
        })
    }
}
console.log(team4.teamSummary())

