const http = require('http');

const server = http.createServer((request, response) => {
    response.writeHead(200, {'Content-Type': 'text/plain'});
    response.end('Hello World!\n');
});

const PORT = process.env.PORT || 1337;

server.listen(PORT, () => {
    console.log(`Server running at http://localhost:${PORT}/`);
});
