const firstNameInput = document.getElementById("firstName");
const lastNameInput = document.getElementById("lastName");
const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");
const passwordConfirmInput = document.getElementById("passwordConfirm");
//const userRoleSelect = document.getElementById("role");

firstNameInput.addEventListener("change", validateFirstNameAndLastName);
lastNameInput.addEventListener("change", validateFirstNameAndLastName);
usernameInput.addEventListener("change", validateUsername);
passwordInput.addEventListener("change", validatePassword);
passwordConfirmInput.addEventListener("change", validatePasswordConfirm);
//userRoleSelect.addEventListener("change", validateRole);

const registerButton = document.createElement("button");
registerButton.className = "btn btn-primary fs-5 col-12";
registerButton.type = "submit";
registerButton.textContent = "Register";

function validateFirstNameAndLastName(event) {
    let input = event.target;
    const error = document.querySelector(`#${input.id} + span.error-message`);

    if(/^[a-zA-Z]+$/.test(input.value)) {
        input.classList.remove("invalid-input");
        error.textContent = "";
        input.classList.add("valid-input");
    }
    else {
        input.classList.remove("valid-input");
        input.classList.add("invalid-input");

        if(input.id === "firstName") {
            error.textContent = "First name must contain only letters";
        }
        else {
            error.textContent = "Last name must contain only letters";
        }
    }

    checkInputFields();
}

function validateUsername(event) {
    let input = event.target;
    let value = input.value;
    const error = document.querySelector(`#${input.id} + span.error-message`);

    let request = new Request('/user/search?username=' + value);

    fetch(request)
        .then(response => {
            if(response.status === 200) {
                //username already in use
                input.classList.remove("valid-input");
                input.classList.add("invalid-input");
                error.textContent = "Username already in use"
            }
            else if(response.status === 204 && /^[a-zA-Z0-9_]+$/.test(value)) {
                input.classList.remove("invalid-input");
                error.textContent = "";
                input.classList.add("valid-input");
            }
            else {
                input.classList.remove("valid-input");
                input.classList.add("invalid-input");
                error.textContent = "Username must contain only letters, numbers or the character _"
            }

            checkInputFields();
        })
        .catch(error => {
            console.error('Error during fetch operation: ', error);
        })
}

function validatePassword(event) {
    let input = event.target;
    const error = document.querySelector(`#${input.id} + span.error-message`);

    if(/^[a-zA-Z0-9_]+$/.test(input.value)
        && input.value.length >= 8 && input.value.length <= 15) {
        input.classList.remove("invalid-input");
        error.textContent = "";
        input.classList.add("valid-input");

        passwordConfirmInput.dispatchEvent(new Event("change"));
    }
    else {
        input.classList.remove("valid-input");
        input.classList.add("invalid-input");
        error.textContent = "Password must contain only letters, numbers or the character _ and " +
            "be between 8 and 15 characters long"
    }

    checkInputFields();
}

function validatePasswordConfirm(event) {
    let input = event.target;
    const error = document.querySelector(`#${input.id} + span.error-message`);

    if(input.value === passwordInput.value) {
        input.classList.remove("invalid-input");
        error.textContent = "";
        input.classList.add("valid-input");
    }

    else {
        input.classList.remove("valid-input");
        input.classList.add("invalid-input");
        error.textContent = "Password must match the password inserted above";
    }

    checkInputFields();
}

/*function validateRole(event) {
    let select = event.target;
    let selectedOptionIndex = select.selectedIndex;
    let selectedOption = select.options[selectedOptionIndex];
    const error = document.querySelector(`#${select.id} + span.error-message`);

    if(selectedOption != null && selectedOption !== "") {
        select.classList.remove("invalid-input");
        error.textContent = "";
        select.classList.add("valid-input");
    }
    else {
        select.classList.remove("valid-input");
        select.classList.add("invalid-input");
        error.textContent = "You must select a role";
    }

    checkInputFields();
}*/

function allInputFieldsValid() {
    return firstNameInput.classList.contains("valid-input")
        && lastNameInput.classList.contains("valid-input")
        && usernameInput.classList.contains("valid-input")
        && passwordInput.classList.contains("valid-input")
        && passwordConfirmInput.classList.contains("valid-input");
}

function checkInputFields() {
    const registerButtonContainer = document.getElementById("registerButtonContainer");

    if(allInputFieldsValid() && !registerButtonContainer.contains(registerButton)) {
        registerButtonContainer.append(registerButton);
    }

    if(!allInputFieldsValid() && registerButtonContainer.contains(registerButton)) {
        registerButtonContainer.removeChild(registerButton);
    }
}


