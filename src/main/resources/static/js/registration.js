
const sAlertEl = document.querySelector("#successfulAlert");
const eAlertEl = document.querySelector("#existsAlert");

document.querySelector("#signUpButton").addEventListener("click", () => {

    const obj = {
        userName:  document.querySelector("#signUpUsername").value.trim(),
        password: document.querySelector("#signUpPassword").value.trim()
    }

    fetch("/api/v1/register",
        {
            method: "POST",
            body: JSON.stringify(obj),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => {
        if (response.ok) {
            sAlertEl.style.display = "block";
            eAlertEl.style.display = "none";
        } else {
            sAlertEl.style.display = "none";
            eAlertEl.style.display = "block";
        }
    });

});