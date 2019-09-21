function validate() {
    var valid = true;

    var name = document.getElementById('txtName').value;
    var email = document.getElementById('txtEmail').value;
    var message = document.getElementById('txtMessage').value;

    if (!name.match("^[a-zA-Z\s']{1,50}$")) {
        document.getElementById('errorName').innerHTML = 'Invalid Name';
        valid = false;
    } else {
        valid = true;
        document.getElementById('errorName').innerHTML = '';
    }

    console.log('in');

    return false;
}