let index = {
    init:function(){
        // btn-save 버튼이 클릭되면, save함수를 호출
        document.querySelector("#btn-login").addEventListener('click',()=>{
            this.save();
        });
    },

    save:function(){
        let data = {
            username: document.querySelector("#username").value,
            password: document.querySelector("#password").value,
        }

        // console.log(data);

        // ajax 요청
        fetch('/blog/api/user/login',{
            method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify(data)
        })
            .then(response =>{
                try{
                    const result = response.json();
                    return result;
                }catch(error){
                    alert("로그인에 실패했습니다.");
                    return false;
                }})
            .then(data=>{
                alert("로그인 완료");
                console.log(data);
                location.href="/blog";
            });
    }
};

index.init();