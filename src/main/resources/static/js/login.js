
const url = new URL(window.location.href);

if (url.searchParams.get('error') !== null) {
    document.querySelector("#badCredentialsAlert").style.display = "block";
}
// document.querySelector("#signInButton").addEventListener("click", () => {
//
//     const obj = {
//         username:  document.querySelector("#username").value.trim(),
//         password: document.querySelector("#password").value.trim()
//     }
//
//     fetch("/perform_login",
//         {
//             method: "POST",
//             body: new URLSearchParams(obj),
//             redirect: "follow"
//         }).then(response => {
//         if (!response.ok) {
//             response.json().then(body => {
//                 alert(body.message);
//             })
//         } else if (response.redirected) {
//             window.location.href = response.url;
//         }
//
//     });
//
// });