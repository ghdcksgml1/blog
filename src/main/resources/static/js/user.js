let index = {
    init:function(){
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

        // ajax
        fetch('/blog/api/user',{
            method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify(data)

        })
            .then(response => response.json())
            .then(data=>{
                alert("회원가입 완료");
                console.log(data);
                // location.href="/blog";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();