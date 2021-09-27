
const chatBlockEl = document.querySelector("#chat_block");
const textInputEl = document.querySelector('#msg_input');

let stompClient = null;
let userName = null;

// could have saved it in headers on server side, but oh well.
fetch("/api/v1/username").then(response => {
    if (response.ok) response.text().then(name => userName = name);
})

function connect() {
    const socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/users/publish', function (messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
    });
}

function showMessageOutput(messageOutput) {
    const messageElement = document.createElement("div");
    const headerElement = document.createElement("div");
    const userNameElement = document.createElement("div");
    const messageTimeElement = document.createElement("div");
    const bodyElement = document.createElement("div");

    userNameElement.textContent = messageOutput.userName;
    messageTimeElement.textContent = messageOutput.messageTime;
    bodyElement.textContent = messageOutput.messageBody;

    headerElement.appendChild(userNameElement);
    headerElement.appendChild(messageTimeElement);

    messageElement.classList.add("container-message");
    headerElement.classList.add("message-info");
    if (userName !== messageOutput.userName) {
        messageElement.classList.add("right_msg");
        headerElement.classList.add("message-info_right");
    }

    messageElement.appendChild(headerElement);
    messageElement.appendChild(bodyElement);

    chatBlockEl.appendChild(messageElement);
}

function sendMessage() {
    if (textInputEl.value !== undefined && textInputEl.value !== null && textInputEl.value.trim() !== "") {
        stompClient.send("/api/publish", {}, JSON.stringify({
            "messageBody": textInputEl.value,
            "userName": userName
        }));
        textInputEl.value = "";
        textInputEl.textContent = "";
    }
}

document.querySelector("#send_msg_button").addEventListener("click", sendMessage);

