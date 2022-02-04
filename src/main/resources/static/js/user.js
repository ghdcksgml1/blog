let index = {
    init:function(){
        // btn-save 버튼이 클릭되면, save함수를 호출
        document.querySelector("#btn-save").addEventListener('click',()=>{
            this.save();
        });
    },

    save:function(){
        let data = {
            username: document.querySelector("#username").value,
            password: document.querySelector("#password").value,
            email: document.querySelector("#email").value
        }

        // console.log(data);

        // ajax 요청
        fetch('/auth/joinProc',{
            method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data=>{
                alert("회원가입 완료");
                console.log(data);
                location.href="/";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();