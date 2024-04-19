var submitButtonn;

var firstName = document.getElementById("firstName");
var checkFirstName = false;

firstName.addEventListener("input", function () {

        var lettere = /^[A-Za-z]*$/;

        if (firstName.value === "") {
            firstName.style.backgroundColor = 'white';
            firstName.focus();
            checkFirstName = false;

        } else if (firstName.value.match(lettere)) {
            firstName.style.backgroundColor = 'green';
            checkFirstName = true;

            console.log("OK1");

        } else {

            firstName.style.backgroundColor = 'red';
            firstName.focus();
            checkFirstName = false;

        }

        submitButton();
    },
    false
);

var lastName = document.getElementById("lastName");
var checkLastName = false;

lastName.addEventListener("input", function () {

        var lettere = /^[A-Za-z]*$/;

        if (lastName.value === "") {
            lastName.style.backgroundColor = 'white';
            lastName.focus();
            checkLastName = false;

        } else if (this.value.match(lettere)) {

            lastName.style.backgroundColor = 'green';
            checkLastName = true;
        } else {

            lastName.style.backgroundColor = 'red';
            lastName.focus();
            checkLastName = false;
        }

        submitButton();
    },
    false
);

var username = document.getElementById("username");
var checkUsername = false;

username.addEventListener("input", function () {

        var word = /^[a-zA-Z0-9_]*$/;


        if (this.value === "") {
            this.style.backgroundColor = 'white';
            username.focus();
            checkUsername = false;

        } else if (username.value.match(word)) {
            this.style.backgroundColor = 'green';
            checkUsername = true;
        } else {
            this.style.backgroundColor = 'red';
            username.focus();
            checkUsername = false;
        }

        submitButton();
    },
    false
);


var password = document.getElementById("password");
var checkPassword = false;

password.addEventListener("input", function () {


        var word = /^[a-zA-Z0-9_]*$/;
        var min = 8;
        var max = 15;

        if (this.value === "") {
            this.style.backgroundColor = 'white';
            password.focus();
            checkPassword = false;

        } else if (this.value.match(word) && this.value.length >= min && this.value.length <= max) {
            this.style.backgroundColor = 'green';
            checkPassword = true;
        } else {
            this.style.backgroundColor = 'red';
            password.focus();
            checkPassword = false;
        }

        submitButton();
    },
    false
);


var confirmPassword = document.getElementById("confirmPassword");
var checkConfirmPassword = false;

confirmPassword.addEventListener("input", function () {

        var word = /^[a-zA-Z0-9_]*$/;
        var min = 8;
        var max = 15;

        if (confirmPassword.value === "") {
            confirmPassword.style.backgroundColor = 'white';
            confirmPassword.focus();
            checkConfirmPassword = false;

        } else if (confirmPassword.value.match(word) && confirmPassword.value.length >= min && confirmPassword.value.length <= max && password.value === confirmPassword.value) {
            confirmPassword.style.backgroundColor = 'green';
            checkConfirmPassword = true;

        } else {
            confirmPassword.style.backgroundColor = 'red';
            confirmPassword.focus();
            checkConfirmPassword = false;

        }

        submitButton();
    },
    false
);

function submitButton() {

    var submitButtonn2;

    if (checkFirstName && checkLastName && checkUsername && checkPassword && checkConfirmPassword) {

        submitButtonn2 = document.formRegistration.querySelector("button");

        if (!submitButtonn2) {
            submitButtonn = document.createElement('button');
            submitButtonn.type = "submit";

            submitButtonn.append(document.createTextNode("Submit"));

            document.formRegistration.appendChild(submitButtonn);
        }


    } else {

        submitButtonn2 = document.formRegistration.querySelector("button");

        if (submitButtonn2)
            document.formRegistration.removeChild(submitButtonn);
    }

}