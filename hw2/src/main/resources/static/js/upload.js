function handleClick() {
    var url = 'http://localhost:8080/wordladder';

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
            alert(data);
        }

    });
    // $.get(`http://localhost:8080/wordladder?word1=${word1}&word2=${word2}`, function (data,status) {
    //     console.log(data);
    //
    // } );
}

