function handleClick() {
    var authurl = "http://localhost:9000/auth";

    var username = document.getElementById("user").value;
    var password = document.getElementById("password").value;

    $.ajax({
        url: authurl,
        data: {
            username: username,
            password: password,
        },
        type: "GET",
        success: function (data) {
            if (data == 200) {
                alert("认证成功");
                var url = 'http://localhost:9000/api/wordladder';

                var word1 = document.getElementById("word1").value;
                var word2 = document.getElementById("word2").value;

                console.log(word1);
                console.log(word2);

                $.ajax({
                    url: url,
                    data: {
                        word1: word1,
                        word2: word2,
                    },
                    type: "GET",
                    success: function (data) {
                        alert("wordladder: " + data);
                    }

                });

            }
            ;
            if (data == 401) {
                alert("权限不足")
            }
        }
    });


}

