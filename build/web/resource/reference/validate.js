function validate() {
    var valid = true;
    var name = document.getElementById('txtName').value;
    var email = document.getElementById('txtEmail').value;
    var website = document.getElementById('txtWebsite').value;
    var file = document.getElementById("fImage");

    if (!/^[a-zA-Z\s']{1,50}$/.test(name)) {
        valid &= false;
        document.getElementById('nameError').innerHTML = 'Invalid Name';
    } else {
        valid &= true;
        document.getElementById('nameError').innerHTML = '';
    }

    if (!/^\w+@[a-zA-Z_]+?\.[a-zA-Z.]+?$/.test(email) || email.length > 50) {
        document.getElementById('emailError').innerHTML = 'Invalid Email';
        valid &= false;
    } else {
        valid &= true;
        document.getElementById('emailError').innerHTML = '';
    }

    var filename = file.value;
    var extension = filename.split('.').pop().toLowerCase();
    if (file.files[0] != null) {
        var size = file.files[0].size;
        if (extension != "jpg" && extension != "jpeg" && extension != "png") {
            document.getElementById('imageError').innerHTML = "Error extension of image. Must be jpg, jpeg or png.";
            valid &= false;
        } else if (size >= 2102840) {
            document.getElementById('imageError').innerHTML = "Image is too big for our server.";
            valid &= false;
        } else {
            document.getElementById('imageError').innerHTML = "";
            valid &= true;
        }
    }
    
    var websiteRegex = /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;

    if (website.length > 0) {
        if (!websiteRegex.test(website)) {
            document.getElementById('websiteError').innerHTML = 'Invalid Website';
            valid &= false;
        } else {
            valid &= true;
            document.getElementById('websiteError').innerHTML = '';
        }
    } else {
        document.getElementById('websiteError').innerHTML = '';
        valid &= true;
    }
    

    return valid === 1;
}