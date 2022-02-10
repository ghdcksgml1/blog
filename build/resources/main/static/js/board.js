let index = {
    init:function(){
        // btn-save 버튼이 클릭되면, save함수를 호출
        document.querySelector("#save").addEventListener('click',()=>{
            this.save();
        });
    },

    save:function(){
        let data = {
            title: document.querySelector("#title").value,
            content: document.querySelector("#content").value
        }

        // ajax 요청
        fetch("/api/board",{
            method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data=>{
                alert("글쓰기 완료");
                console.log(data);
                location.href="/";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();