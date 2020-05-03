<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Merchant Signup</title>
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <%
        String msg = request.getParameter("msg");
        if (msg != null) {
    %>
    <script>
            alert("<%=msg%>");
    </script>
    <%
        }
    %>
    <script>
        function readandpreview(fileobj, imageid)
        {
            var firstfile = fileobj.files[0];

            var reader = new FileReader();

            //alert("File name: " + firstfile.name);
            // alert("File size: " + firstfile.size);

            reader.onload = (function (f)
            {
                return function read12(e)
                {
                    document.getElementById(imageid).src = e.target.result;
                };
            })(firstfile);


            reader.readAsDataURL(firstfile);
        }
        function go()
        {   
            if (document.getElementById("password").value == document.getElementById("cp").value)
            {
                return true;
            } else
            {
                document.getElementById("cp_error").innerHTML = "**Password and Confirm Password does not match";
                document.getElementById("cp").value = "";
                return false;
            }
        }

    </script>

    <script>

        var xmlhttp = new XMLHttpRequest();
        function check_unique()
        {   
            var email=document.getElementById('email').value;
            xmlhttp.open("get", "../check_unique_merchant?email=" + email, true);
            xmlhttp.onreadystatechange = checked;
            xmlhttp.send();
        }

        function checked()
        {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                document.getElementById("checked").innerHTML = xmlhttp.responseText;
            }
        }

    </script>
    <body class="container">
        <div class="jumbotron">
            <h1 class="text-center">Merchant SignUp</h1>
        </div>
        <div class="frame">
            <form action="../merchant_signup" method="post" class="form-vertical" enctype="multipart/form-data" onsubmit="return go()">
                <div class="form-group">
                    <label for ="name">Enter your Name</label>
                    <input type="text" class="form-control" name="name" id="name"/>
                </div>
                <div class="form-group">
                    <label for ="email">Enter a valid email id <span style="color:red;">(this will be used as your Login Id)</span></label>
                    <label id="checked" style="color:red"></label>
                    <input type="email" class="form-control" name="email" id="email" required onblur ="check_unique()"/>
                </div>
                <div class="form-group">
                    <label for ="phone">Enter a 10 digit mobile number</label>
                    <input type="text" name="phone"class="form-control" id="phone" required/>
                </div>
                <div class="form-group">
                    <label for ="address">Enter ypur address</label>
                    <input type="text" name="address" class="form-control" id="address" required/>
                </div>
                <div class="form-group">
                    <label for ="city">Enter the city</label>
                    <input type="text" name="city" id="city"  class="form-control"required/>
                </div>
                <div class="form-group">
                    <label for ="state">Enter the state</label>
                    <input type="text" name="state" id="state" class="form-control" required/>
                </div>
                <div class="form-group">
                    <label for ="bank_acc">Enter the bank account number(this will be used during transactions)</label>
                    <input type="text" name="bank_acc"  class="form-control" id="bank_acc"/>
                </div>
                <div class="form-group">
                    <label for ="ifsc_code">Enter teh IFSC code of your bank</label>
                    <input type="text" name="ifsc_code" class="form-control" id="ifsc_code"/>
                </div>
                <div class="form-group">
                    <label for ="password">Create a password(it will be used during your login)</label>
                    <input type="password" name="password" class="form-control" id="password" required/>
                </div>
                <div class="form-group">
                    <label for ="password">Re-enter the password</label>
                    <label id="cp_error" style="color:red"></label>
                    <input type="password"  class="form-control" id="cp" required/>
                </div>
                <div class="form-group">
                    <label for ="aadhar_no">Upload the the picture of your Aadhaar Card</label>
                    <img src="" id="img" style="width: 100px;height: 100px;">
                    <input type="file" name="aadhar_no" id="aadhar_no" class="form-control" onchange="readandpreview(this, 'img')" required/>

                </div>
                <div class="form-group">
                    <label for ="tin_no">Upload the the picture of your TIN Number</label>
                    <img src="" id="img1" style="width: 100px;height: 100px;">
                    <input type="file" name="tin_no" id="tin_no" class="form-control" onchange="readandpreview(this, 'img1')" required/>
                </div>
                <div class="form-group">
                    <label for ="co_icon">Upload the the picture of your Company Icon(if exists)</label>
                    <img src="" id="img2" style="width: 100px;height: 100px;">
                    <input type="file" name="co_icon" id="co_icon" class="form-control" onchange="readandpreview(this, 'img2')"/>
                </div>
                <div class="form-group">
                    <input class="btn btn-lg btn-primary" type="submit" />
                </div>

            </form>
        </div>
    </body>
</html>
