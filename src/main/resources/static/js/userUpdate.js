let index = {
    init:function(){
        document.querySelector("#btn-update").addEventListener('click',()=>{
            this.update();
        });
    },

    update:function(){
        let data = {
            username: document.querySelector("#username").value,
            password: document.querySelector("#password").value
        }

        // console.log(data);

        // ajax 요청
        fetch('/user',{
            method:'PUT',headers:{'content-type':'application/json'},body:JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data=>{
                alert("회원수정 완료");
                console.log(data);
                location.href="/";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();