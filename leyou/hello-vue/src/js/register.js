const registerform = {
    template:` 
        <div>
            <h1>註冊頁</h1>
            用&ensp;戶&ensp;名: <input type="email" value="user"><br>
            密&emsp;&emsp;碼: <input type="password"><br>
            再次輸入密碼 <input type="password">  </br>
            <input type="button" value="註冊"><br>
        </div>
    `,
    data(){
        return{
            name:"劉德華"
        }
    }
}