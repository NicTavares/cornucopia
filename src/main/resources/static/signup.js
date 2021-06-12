const userAction = async () => {
    let name = document.getElementById("fname");
    let email = document.getElementById("Email");
    let birthday = document.getElementById("birthday");
    let username = document.getElementById("username");
    let password = document.getElementById("password");
    let city = document.getElementById("city");
    let postal = document.getElementById("postalcode");

    let user = {
        UUID: 0,
        birthday: "2018-01-01",
        email: "email2",
        username: "username2",
        name: "name",
        password: "password",
        city: "city",
        postalCode: "postal"
    }

    console.log(user);

    let user2 = JSON.stringify(user);

    const response = await fetch('http://localhost:8080/addUsr', {
        method: 'POST',
        body: user2, // string or object
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const myJson = await response.json(); //extract JSON from the http response

    console.log(response);
    // do something with myJson

}