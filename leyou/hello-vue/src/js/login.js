const loginform = {
    // 注意組件內的template只能有一個根標籤
    template:` 
        <div>
            <h1>登陸頁</h1>
            用&ensp;戶&ensp;名: <input type="email" value="user"><br>
            密&emsp;&emsp;碼: <input type="password"><br>
            登陸 <input type="button" value="登陸"><br>
        </div>
    `,
    data(){
        return{
            name:"劉德華"
        }
    }
}