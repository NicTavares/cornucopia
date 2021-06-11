var path = require('path');
var express = require("express"),
    app = express(),
    bodyParser = require('body-parser'),
    port = process.env["PORT"] || 7070;

app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.get("/", function(req, res) {
    // if you chose to send data as a query in the URL 
    console.log(req.query.q);
    // if you chose to send data as JSON 
    console.log(req.body.q);

    // finally, respond to the client 
    res.send("Okay");
});

app.listen(port)
console.log("listening to server on port:", port); 