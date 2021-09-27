
const url = new URL(window.location.href);

if (url.searchParams.get('error') !== null) {
    document.querySelector("#badCredentialsAlert").style.display = "block";
}
