function validate() {
    var valid = true;

    var name = document.getElementById('txtName').value;
    var email = document.getElementById('txtEmail').value;
    var message = document.getElementById('txtMessage').value;

    if (!/^[a-zA-Z\s']{1,50}$/.test(name)) {
        document.getElementById('nameError').innerHTML = 'Invalid Name';
        document.getElementById('nameError').style.display = 'block';
        valid &= false;
    } else {
        valid &= true;
        document.getElementById('nameError').innerHTML = '';
        document.getElementById('nameError').style.display = 'none';
    }

    if (!/^\w+@[a-zA-Z_]+?\.[a-zA-Z.]+?$/.test(email) || email.length > 50) {
        document.getElementById('emailError').innerHTML = 'Invalid Email';
        document.getElementById('emailError').style.display = 'block';
        valid &= false;
    } else {
        valid &= true;
        document.getElementById('emailError').innerHTML = '';
        document.getElementById('emailError').style.display = 'none';
    }   
    
    return valid === 1;
}