let index = {
    init:function(){
        // btn-save 버튼이 클릭되면, save함수를 호출
        document.querySelector("#delete-btn").addEventListener('click',()=>{
            this.delete();
        });
    },

    delete:function(){

        // ajax 요청
        fetch("/api/board/"+document.querySelector("#id").innerHTML,{
            method:'DELETE'
        })
            .then(response => response.json())
            .then(data=>{
                alert("글쓰기 삭제완료");
                console.log(data);
                location.href="/";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();